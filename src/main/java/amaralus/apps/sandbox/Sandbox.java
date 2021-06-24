package amaralus.apps.sandbox;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;


@Slf4j
public class Sandbox {

    static Random random = new Random();

    static int[] inputMockAB = {6, 5, 4, 3, 5, 2, 6};
    static int currentInputAB;

    static int[] numbers = {28000, 6, 5, 40, 1000, 7, 28, 55};
    static int currentNumber;

    List<String> str;
    Map<String, Integer> map;

    public static void main(String... args) throws NoSuchFieldException {
        var clazz = getFieldGenericType(Sandbox.class.getDeclaredField("map"), 0);
        System.out.println(clazz.getName());
    }


    private static Class<?> getFieldGenericType(Field field, int genericNumber) {
        var type = (ParameterizedType) field.getGenericType();
        return (Class<?>) type.getActualTypeArguments()[genericNumber];
    }

    private static void sameExerciseShort() {
        int x = readIntFromConsole2();

        int[] buffer = new int[readIntFromConsole2()];
        int size = 0;

        int result;
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = readIntFromConsole2();

            ++size;
            for (int j = 0; j < size; j++) {
                result = buffer[i] * buffer[j];
                if (result == x) {
                    System.out.println(result);
                    return;
                }
            }
        }
    }

    private static void sameExerciseFull() {
        System.out.println("Введи x");

        int x;
        do {
            x = readIntFromConsole2();
            if (x % 14 != 0)
                System.out.println("x = " + x + " не кратно 14 введи другое число!");
        } while (x % 14 != 0);


        System.out.println("Введи N");
        int[] buffer = new int[readIntFromConsole2()];
        int size = 0;

        int result;
        System.out.println("Вводи числа");
        for (int i = 0; i < buffer.length; i++) {
            do {
                buffer[i] = readIntFromConsole2();
                if (buffer[i] > 1000)
                    System.out.println("Введенное число больше чем 1000 попробуй еще раз");
            } while (buffer[i] > 1000);

            ++size;
            for (int j = 0; j < size; j++) {
                result = buffer[i] * buffer[j];
                if (result == x) {
                    System.out.println(result);
                    return;
                }
            }
        }
    }

    public static int readIntFromConsole2() {
        int input = numbers[currentNumber];
        ++currentNumber;
        return input;
    }

    private static void weirdExercise() {
        int number = readIntFromConsole();

        int red = 0;
        do {
            int remainder = number % 2;

            if (remainder == 1) {
                number -= remainder;
                red += remainder;
            } else
                number /= 2;

        } while (number >= 1);

        System.out.println(red);
    }

    private static void simpleA() {
        int n = readIntFromConsole();
        int[] array = new int[n];

        for (int i = 0; i < n; i++)
            array[i] = readIntFromConsole();

        int max = 0;
        int a = 0;
        int b = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && array[i] * array[j] > max) {
                    a = array[i];
                    b = array[j];
                    max = a * b;
                }

        if (a < b)
            System.out.print(a + " " + b + " ");
        else
            System.out.println(b + " " + a + " ");

        System.out.println(a * b);
    }

    private static void fullB() {
        int n;
        // читаем n с начала пока оно больше 1000
        // важно: в задании не указано что оно не должно быть больше 0,
        // а значит если будет меньше то программа сломается (можно сумничать и добавить проверку на больше 0)
        do {
            n = readIntFromConsole();
            // доп проверка: while(n < 0 && n > 10000);
        } while (n > 10000);

        int max;
        int prevMax;

        // первая проверка чтобы заполнить макс и пред макс
        int value;
        do {
            value = readIntFromConsole();
            // доп проверка: while(n < 30000 && n > 30000);
            // вероятнее всего по модулю занчит что отрицательные это тоже возможно
            // но возможно отрицательные нужно делать положительными (формулировка не уточняет что именно)
        } while (value > 30000);

        // написано что 2 числа нужно найти, но а если будет только одно?
        max = prevMax = value;

        // n-1 потому что первое число уже считали
        for (int i = 0; i < n - 1; i++) {
            do {
                value = readIntFromConsole();
            } while (value > 30000);

            // если больше максимального делаем максимальным иначе если больше пердыдущего делаем его предыдущим
            if (value > max)
                max = value;
            else if (value > prevMax)
                prevMax = value;
        }

        System.out.println(prevMax + " " + max + " " + max * prevMax);
    }

    private static void simpleB() {
        int n = readIntFromConsole();
        int value = readIntFromConsole();

        int max;
        int prevMax;
        max = prevMax = value;

        for (int i = 0; i < n - 1; i++) {
            value = readIntFromConsole();

            if (value > max)
                max = value;
            else if (value > prevMax)
                prevMax = value;
        }

        int result = max * prevMax;
        System.out.println(prevMax + " " + max + " " + result);
    }

    public static int readIntFromConsole() {
        int input = inputMockAB[currentInputAB];
        ++currentInputAB;
        return input;
    }

    public static int percentOfNumber(int number, int percent) {
        return (int) (number * ((double) percent / 100.0d));
    }

    public static boolean randChancePercent(int percent) {
        if (percent > 100) percent = 100;

        return random.nextInt(101) <= percent;
    }

    public interface Primary<T> {

        default Class<T> getGen() {
            return (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        }
    }

    public static class Secondary implements Primary<Integer> {

    }
}
