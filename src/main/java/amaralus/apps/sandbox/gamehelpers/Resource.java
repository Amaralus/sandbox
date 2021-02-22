package amaralus.apps.sandbox.gamehelpers;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
public class Resource {

    private final String name;
    @EqualsAndHashCode.Exclude
    private final List<ProductionProcess> productionProcesses = new ArrayList<>();

    public Resource(String name) {
        this.name = name;
    }

    public void addProductionProcess(ProductionProcess productionProcess) {
        productionProcesses.add(productionProcess);
    }
}
