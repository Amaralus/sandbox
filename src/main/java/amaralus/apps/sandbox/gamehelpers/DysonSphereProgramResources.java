package amaralus.apps.sandbox.gamehelpers;

public final class DysonSphereProgramResources {

    // resources
    public static final Resource IRON_ORE = new Resource("Iron ore");
    public static final Resource COPPER_ORE = new Resource("Copper ore");
    public static final Resource TITANIUM_ORE = new Resource("Titanium ore");
    public static final Resource SILICON_ORE = new Resource("Silicon ore");
    public static final Resource STONE = new Resource("Stone");
    public static final Resource WATER = new Resource("Water");
    public static final Resource HYDROGEN = new Resource("Hydrogen");

    public static final Resource IRON_INGOT = new Resource("Iron ingot");
    public static final Resource COPPER_INGOT = new Resource("Copper ingot");
    public static final Resource TITANIUM_INGOT = new Resource("Titanium ingot");
    public static final Resource HIGH_PURITY_SILICON = new Resource("High-purity silicon");
    public static final Resource GLASS = new Resource("Glass");
    public static final Resource MAGNET = new Resource("Magnet");

    public static final Resource MAGNETIC_COIL = new Resource("Magnetic coil");
    public static final Resource MICROCRYSTALLINE_COMPONENT = new Resource("Microrystalline component");
    public static final Resource CIRCUIT_BOARD = new Resource("Circuit board");
    public static final Resource PROCESSOR = new Resource("Processor");
    public static final Resource GRAPHENE = new Resource("Graphene");
    public static final Resource OPTICAL_GRATING_CRYSTAL = new Resource("Optical grating crystal");
    public static final Resource CASIMIR_CRYSTAL = new Resource("Casimir crystal");
    public static final Resource TITANIUM_GLASS = new Resource("Titanium glass");
    public static final Resource PLANE_FILTER = new Resource("Plane filter");
    public static final Resource QUANTUM_CHIP = new Resource("Quantum chip");

    // processes
    public static final ProductionProcess IRON_INGOT_PRODUCTION =
            new ProductionProcess("Iron ingot production", 1)
                    .input(IRON_ORE, 1)
                    .output(IRON_INGOT, 1);

    public static final ProductionProcess COPPER_INGOT_PRODUCTION =
            new ProductionProcess("Copper ingot production", 1)
                    .input(COPPER_ORE, 1)
                    .output(COPPER_INGOT, 1);

    public static final ProductionProcess TITANIUM_INGOT_PRODUCTION =
            new ProductionProcess("Titanium ingot production", 2)
                    .input(TITANIUM_ORE, 2)
                    .output(TITANIUM_INGOT, 1);

    public static final ProductionProcess HIGH_PURITY_SILICON_PRODUCTION =
            new ProductionProcess("High-purity silicon production", 2)
                    .input(SILICON_ORE, 2)
                    .output(HIGH_PURITY_SILICON, 1);

    public static final ProductionProcess GLASS_PRODUCTION =
            new ProductionProcess("Glass production", 2)
                    .output(STONE, 2)
                    .output(GLASS, 1);

    public static final ProductionProcess MAGNET_PRODUCTION =
            new ProductionProcess("Magnet production", 1.5)
                    .input(IRON_ORE, 1)
                    .output(MAGNET, 1);

    public static final ProductionProcess MAGNETIC_COIL_PRODUCTION =
            new ProductionProcess("Magnetic coil production", 1)
                    .input(MAGNET, 2)
                    .input(COPPER_INGOT, 1)
                    .output(MAGNETIC_COIL, 2);

    public static final ProductionProcess CIRCUIT_BOARD_PRODUCTION =
            new ProductionProcess("Circuit board production", 1)
                    .input(IRON_INGOT, 2)
                    .input(COPPER_INGOT, 1)
                    .output(CIRCUIT_BOARD, 2);

    public static final ProductionProcess MICROCRYSTALLINE_COMPONENT_PRODUCTION =
            new ProductionProcess("Microrystalline component production", 2)
                    .input(HIGH_PURITY_SILICON, 2)
                    .input(COPPER_INGOT, 1)
                    .output(MICROCRYSTALLINE_COMPONENT, 1);

    public static final ProductionProcess PROCESSOR_PRODUCTION =
            new ProductionProcess("Processor production", 3)
                    .input(CIRCUIT_BOARD, 2)
                    .input(MICROCRYSTALLINE_COMPONENT, 2)
                    .output(PROCESSOR, 1);

    public static final ProductionProcess TITANIUM_GLASS_PRODUCTION =
            new ProductionProcess("Titanium glass production", 5)
                    .input(GLASS, 2)
                    .input(TITANIUM_INGOT, 2)
                    .input(WATER, 2)
                    .output(TITANIUM_GLASS, 2);

    public static final ProductionProcess CASIMIR_CRYSTAL_PRODUCTION =
            new ProductionProcess("Casimir crystal production" ,4)
                    .input(OPTICAL_GRATING_CRYSTAL, 6)
                    //todo
                    .input(GRAPHENE, 2)
                    // todo
                    .input(HYDROGEN, 12)
                    .output(CASIMIR_CRYSTAL, 1);

    public static final ProductionProcess PLANE_FILTER_PRODUCTION =
            new ProductionProcess("Plane filter production", 12)
                    .input(CASIMIR_CRYSTAL, 1)
                    .input(TITANIUM_GLASS, 2)
                    .output(PLANE_FILTER, 1);

    public static final ProductionProcess QUANTUM_CHIP_PRODUCTION =
            new ProductionProcess("Quantum chip production", 6)
                    .input(PROCESSOR, 2)
                    .input(PLANE_FILTER, 2)
                    .output(QUANTUM_CHIP, 1);

    private DysonSphereProgramResources() {
        throw new AssertionError();
    }
}
