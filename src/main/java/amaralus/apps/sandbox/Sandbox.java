package amaralus.apps.sandbox;

import amaralus.apps.sandbox.entities.SomeClass;
import amaralus.apps.sandbox.generators.EntityGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Sandbox {

    static Random random = new Random();

    public static void main(String... args) throws IllegalAccessException, NoSuchFieldException {
        EntityGenerator entityGenerator = new EntityGenerator();
        SomeClass someClass = SomeClass.builder().build();

        entityGenerator.generate(someClass);

        log.info("someclass = {}", someClass);
    }

    public static int percentOfNumber(int number, int percent) {
        return (int) (number * ((double) percent / 100.0d));
    }

    public static boolean randChancePercent(int percent) {
        if (percent > 100) percent = 100;

        return random.nextInt(101) <= percent;
    }
}
