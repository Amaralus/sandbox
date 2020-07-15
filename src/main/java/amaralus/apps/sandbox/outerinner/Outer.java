package amaralus.apps.sandbox.outerinner;

public class Outer {

    private String msg;

    public Outer(String msg) {
        this.msg = msg;
    }

    public Inner inner(int i) {
        return new Inner(i);
    }

    public void print(Inner inner) {
        System.out.println(msg + inner.i);
    }

    public class Inner {

        private int i;

        public Inner(int i) {
            this.i = i;
        }

        public void innerPrint() {
            print(this);
        }
    }
}
