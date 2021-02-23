package amaralus.apps.sandbox.gamehelpers;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ResourceCalculator {

    private final List<ResourceInfo> demandedResources = new ArrayList<>();
    private final List<ResourceInfo> demandedResourcesWithoutProcess = new ArrayList<>();
    private final List<FactoryInfo> factoryInfoList = new ArrayList<>();

    public void calculate(Resource resource, int factoryNodeCount) {
        var productivity = calculateOutputProductivity(resource.getProductionProcesses().get(0), resource) * factoryNodeCount;
        addDemandedResource(resource, productivity);

        while (!demandedResources.isEmpty()) {
            processDemandedResource(demandedResources.get(0));
        }

        printResult();
    }

    private void processDemandedResource(ResourceInfo demandedResource) {
        var process = getDefaultProductionProcess(demandedResource.resource);
        if (process == null) {
            addDemandedResourceWithoutProcess(demandedResource);
            demandedResources.remove(demandedResource);
            return;
        }

        var factoryInfo = new FactoryInfo();
        factoryInfo.productionProcess = process;

        var productivity = calculateOutputProductivity(process, demandedResource.resource);
        factoryInfo.nodes = demandedResource.productivity / productivity;

        var nodeSurplus = factoryInfo.nodes % 1d;

        if (nodeSurplus != 0) {
            factoryInfo.nodes = Math.ceil(factoryInfo.nodes);
            factoryInfo.surplus = productivity - (productivity * nodeSurplus);
        }
        factoryInfo.totalProductivity = productivity * factoryInfo.nodes;

        factoryInfoList.add(factoryInfo);
        demandedResources.remove(demandedResource);

        for (var inputResource : process.getInput().keySet()) {
            addDemandedResource(inputResource, calculateInputProductivity(process, inputResource) * factoryInfo.nodes);
        }
    }

    private double calculateOutputProductivity(ProductionProcess process, Resource resource) {
        return 60d / process.getProductionTime() * process.getOutput().get(resource);
    }

    private double calculateInputProductivity(ProductionProcess process, Resource resource) {
        return 60d / process.getProductionTime() * process.getInput().get(resource);
    }

    private ProductionProcess getDefaultProductionProcess(Resource resource) {
        var processes = resource.getProductionProcesses();
        return processes.isEmpty() ? null : processes.get(0);
    }

    private void addDemandedResource(Resource resource, double productivity) {
        demandedResources.add(new ResourceInfo(resource, productivity));
    }

    private void addDemandedResourceWithoutProcess(ResourceInfo demandedResource) {
        demandedResourcesWithoutProcess.add(demandedResource);
    }

    private void printResult() {
        printFactoryInfo();
        printDemanded();
        printDemandWithoutProcess();
    }

    private void printFactoryInfo() {
        System.out.println("Result factories");
        printLine(38, 7, 14, 9);
        System.out.printf("| %-37s| %-6s| %-13s| %-8s|%n", "Production processes", "Nodes", "Productivity", "Surplus");

        for (var factoryInfo : factoryInfoList) {
            printLine(38, 7, 14, 9);
            factoryInfo.print();
        }
        printLine(38, 7, 14, 9);
    }

    private void printDemanded() {
        if (demandedResources.isEmpty())
            return;

        System.out.println("\nDemanded resources");
        printLine(21, 14);
        System.out.printf("| %-21s| %-13s|%n", "Resource", "Productivity");

        for (var demanded : demandedResources) {
            printLine(21, 14);
            demanded.print();
        }
        printLine(21, 14);
    }

    private void printDemandWithoutProcess() {
        if (demandedResourcesWithoutProcess.isEmpty())
            return;
        System.out.println("\nDemanded resources without process");

        printLine(21, 14);
        System.out.printf("| %-20s| %-13s|%n", "Resource", "Productivity");

        for (var demand : demandedResourcesWithoutProcess) {
            printLine(21, 14);
            demand.print();
        }
        printLine(21, 14);
    }

    private void printLine(int... repeats) {
        var builder = new StringBuilder("|");

        for (int count : repeats)
            builder.append("-".repeat(count)).append("|");

        System.out.println(builder);
    }

    @EqualsAndHashCode
    private static class FactoryInfo {
        ProductionProcess productionProcess;
        double nodes;
        double totalProductivity;
        double surplus;

        void print() {
            System.out.printf("| %-37s| %-6.0f| %-13.1f| %-8.1f|%n", productionProcess.getName(), nodes, totalProductivity, surplus);
        }
    }

    @EqualsAndHashCode
    private static class ResourceInfo {
        Resource resource;
        double productivity;
        double surplus;

        ResourceInfo(Resource resource, double productivity) {
            this.resource = resource;
            this.productivity = productivity;
        }

        void print() {
            System.out.printf("| %-20s| %-13.1f|%n", resource.getName(), productivity);
        }
    }
}
