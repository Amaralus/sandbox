package amaralus.apps.sandbox;


import amaralus.apps.sandbox.gamehelpers.ResourceCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static amaralus.apps.sandbox.gamehelpers.DysonSphereProgramResources.*;

@Slf4j
public class Sandbox {

    static Random random = new Random();

    public static void main(String... args) {
        var calc = new ResourceCalculator();

        calc.calculate(SUPER_MAGNETIC_RING, 1);
    }

    public static int percentOfNumber(int number, int percent) {
        return (int) (number * ((double) percent / 100.0d));
    }

    public static boolean randChancePercent(int percent) {
        if (percent > 100) percent = 100;

        return random.nextInt(101) <= percent;
    }
}
