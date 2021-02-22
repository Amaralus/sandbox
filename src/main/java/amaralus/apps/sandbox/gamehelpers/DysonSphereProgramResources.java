package amaralus.apps.sandbox.gamehelpers;

public final class DysonSphereProgramResources {

    // resources
    public static final Resource IRON_ORE = new Resource("Iron ore");
    public static final Resource COPPER_ORE = new Resource("Copper ore");

    public static final Resource IRON_INGOT = new Resource("Iron ingot");
    public static final Resource COPPER_INGOT = new Resource("Copper ingot");

    public static final Resource MAGNET = new Resource("Magnet");
    public static final Resource MAGNETIC_COIL = new Resource("Magnetic coil");

    // processes
    public static final ProductionProcess IRON_INGOT_PRODUCTION = new ProductionProcess("Iron ingot production", 1)
            .input(IRON_ORE, 1)
            .output(IRON_INGOT, 1);

    public static final ProductionProcess COPPER_INGOT_PRODUCTION = new ProductionProcess("Copper ingot production", 1)
            .input(COPPER_ORE, 1)
            .output(COPPER_INGOT, 1);

    public static final ProductionProcess MAGNET_PRODUCTION = new ProductionProcess("Magnet production", 1.5)
            .input(IRON_ORE, 1)
            .output(MAGNET, 1);

    public static final ProductionProcess MAGNETIC_COIL_PRODUCTION = new ProductionProcess("Magnetic coil production", 1)
            .input(MAGNET, 2)
            .input(COPPER_INGOT, 1)
            .output(MAGNETIC_COIL, 2);

    private DysonSphereProgramResources() {
        throw new AssertionError();
    }
}
