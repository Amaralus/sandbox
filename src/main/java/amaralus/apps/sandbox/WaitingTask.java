package amaralus.apps.sandbox;

import java.util.concurrent.*;
import java.util.function.Predicate;

public class WaitingTask<M> {

    private Callable<M> task;
    private Predicate<M> taskCondition;
    private long taskDelay;
    private TimeUnit taskTimeUnit;

    Future<M> taskFuture;

    private long timeoutDelay;
    private TimeUnit timeoutTimeUnit;

    private WaitingTask() {}

    public M executeAndGetResult() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        try {
            return scheduler.submit(() -> {
                M result = null;

                do {
                    setTaskFuture(scheduler.schedule(task, taskDelay, taskTimeUnit));
                    result = getTaskFuture().get(timeoutDelay, timeoutTimeUnit);
                } while (!taskCondition.test(result));

                return result;
            }).get(timeoutDelay, timeoutTimeUnit);

        } catch (TimeoutException e) {
            getTaskFuture().cancel(true);
            System.out.println("timeout");
            return null;
        } catch (Exception e) {
            System.out.println("some exception");
            return null;
        } finally {
            scheduler.shutdown();
        }
    }

    private Future<M> getTaskFuture() {
        return taskFuture;
    }

    private void setTaskFuture(Future<M> taskFuture) {
        this.taskFuture = taskFuture;
    }

    public static class WaitingTaskBuilder<M> {

        private WaitingTask<M> waitingTask = new WaitingTask<>();

        public WaitingTask<M> build() {
            return waitingTask;
        }

        public WaitingTaskBuilder<M> timeout(long delay, TimeUnit timeUnit) {
            waitingTask.timeoutDelay = delay;
            waitingTask.timeoutTimeUnit = timeUnit;
            return this;
        }

        public WaitingTaskBuilder<M> period(long delay, TimeUnit timeUnit) {
            waitingTask.taskDelay = delay;
            waitingTask.taskTimeUnit = timeUnit;
            return this;
        }

        public WaitingTaskBuilder<M> doTask(Callable<M> callable) {
            waitingTask.task = callable;
            return this;
        }

        public WaitingTaskBuilder<M> returnCondition(Predicate<M> predicate) {
            waitingTask.taskCondition = predicate;
            return this;
        }
    }
}
