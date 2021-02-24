package amaralus.apps.sandbox.gamehelpers;

public final class DysonSphereProgramResources {

    // resources
    public static final Resource IRON_ORE = new Resource("Iron ore");
    public static final Resource COPPER_ORE = new Resource("Copper ore");
    public static final Resource TITANIUM_ORE = new Resource("Titanium ore");
    public static final Resource SILICON_ORE = new Resource("Silicon ore");
    public static final Resource STONE = new Resource("Stone");
    public static final Resource COAL = new Resource("Coal");
    public static final Resource WATER = new Resource("Water");
    public static final Resource HYDROGEN = new Resource("Hydrogen");
    public static final Resource FIRE_ICE = new Resource("Fire ice");

    public static final Resource IRON_INGOT = new Resource("Iron ingot");
    public static final Resource COPPER_INGOT = new Resource("Copper ingot");
    public static final Resource TITANIUM_INGOT = new Resource("Titanium ingot");
    public static final Resource HIGH_PURITY_SILICON = new Resource("High-purity silicon");
    public static final Resource GLASS = new Resource("Glass");
    public static final Resource ENERGETIC_GRAPHITE = new Resource("Energetic graphite");
    public static final Resource MAGNET = new Resource("Magnet");

    public static final Resource GEAR = new Resource("Gear");
    public static final Resource STEEL = new Resource("Steel");
    public static final Resource MAGNETIC_COIL = new Resource("Magnetic coil");
    public static final Resource ELECTRIC_MOTOR = new Resource("Electric motor");
    public static final Resource ELECTROMAGNETIC_TURBINE = new Resource("Electromagnetic turbine");
    public static final Resource SUPER_MAGNETIC_RING = new Resource("Super-magnetic ring");
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

    public static final ProductionProcess ENERGETIC_GRAPHITE_PRODUCTION =
            new ProductionProcess("Energetic graphite production", 2)
                    .output(COAL, 2)
                    .output(ENERGETIC_GRAPHITE, 1);

    public static final ProductionProcess MAGNET_PRODUCTION =
            new ProductionProcess("Magnet production", 1.5)
                    .input(IRON_ORE, 1)
                    .output(MAGNET, 1);

    public static final ProductionProcess GEAR_PRODUCTION =
            new ProductionProcess("Gear production", 1)
                    .input(IRON_INGOT, 1)
                    .output(GEAR, 1);

    public static final ProductionProcess STEEL_PRODUCTION =
            new ProductionProcess("Steel productuin", 3)
                    .input(IRON_INGOT, 3)
                    .output(STEEL, 1);

    public static final ProductionProcess MAGNETIC_COIL_PRODUCTION =
            new ProductionProcess("Magnetic coil production", 1)
                    .input(MAGNET, 2)
                    .input(COPPER_INGOT, 1)
                    .output(MAGNETIC_COIL, 2);

    public static final ProductionProcess ELECTRIC_MOTOR_PRODUCTION =
            new ProductionProcess("Electric motor production", 2)
                    .input(IRON_INGOT, 2)
                    .input(GEAR, 1)
                    .input(MAGNETIC_COIL, 1)
                    .output(ELECTRIC_MOTOR, 1);

    public static final ProductionProcess ELECTROMAGNETIC_TURBINE_PRODUCTION =
            new ProductionProcess("Electromagnetic turbine production", 2)
                    .input(ELECTRIC_MOTOR, 2)
                    .input(MAGNETIC_COIL, 2)
                    .output(ELECTROMAGNETIC_TURBINE, 1);

    public static final ProductionProcess SUPER_MAGNETIC_RING_PRODUCTION =
            new ProductionProcess("Super-magnetic ring production", 3)
                    .input(ELECTROMAGNETIC_TURBINE, 2)
                    .input(MAGNET, 3)
                    .input(ENERGETIC_GRAPHITE, 1)
                    .output(SUPER_MAGNETIC_RING, 1);

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

    public static final ProductionProcess GRAPHENE_PRODUCTION =
            new ProductionProcess("Graphene production", 2)
                    .input(FIRE_ICE, 2)
                    // todo учет нескольких выходов
                    //.output(HYDROGEN, 1)
                    .output(GRAPHENE, 2);

    public static final ProductionProcess CASIMIR_CRYSTAL_PRODUCTION =
            new ProductionProcess("Casimir crystal production" ,4)
                    .input(OPTICAL_GRATING_CRYSTAL, 6)
                    .input(GRAPHENE, 2)
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
