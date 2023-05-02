package amaralus.apps.sandbox.weather;

import amaralus.apps.sandbox.gamehelpers.ResourceCalculator;

import static amaralus.apps.sandbox.gamehelpers.DysonSphereProgramResources.PROCESSOR;
public class Main {

    private static final double R = 8.31446261815324;

    private double pressure;
    private double temperature;
    private double volume;
    private double molarMass;
    private double mass;


    public static void main(String[] args) {
        System.out.println("Dyson sphere power (GW): " + powerCalc(2.461, 30000, 0.08) / 1_000_000d);
        new Main().init();
    }

    public void init() {
        var rc = new ResourceCalculator();
        rc.calculate(PROCESSOR, 12);
    }

    // p*v=(m/M)*R*t      |  25*4=(30/3)*5*2
    // p=((m/M)*R*t)/v    |  25=((30/3)*5*2)/4
    // t=(p*v)/((m/M)*R)  |  2=(25*4)/((30/3)*5)

    // 25*4=50*2
    // 2=(25*4)/50

    private static double powerCalc(double luminosity, double radius, double structurePointsPerCell) {
        return luminosity * 4 * Math.PI * (Math.pow(radius * 0.0191, 2)) * (15 + (96 * structurePointsPerCell));
    }

    private void changeTemperature(double value) {
        var c = pressure / temperature;
        temperature += value;
        pressure = temperature * c;
    }

    private void changePressure(double value) {
        var c = pressure / temperature;
        pressure += value;
        temperature = pressure / c;
    }
}
