package amaralus.apps.sandbox.gamehelpers;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class ProductionProcess {

    private final String name;
    private final double productionTime;
    private final Map<Resource, Integer> input = new HashMap<>();
    private final Map<Resource, Integer> output = new HashMap<>();

    public ProductionProcess(String name, double productionTime) {
        this.name = name;
        this.productionTime = productionTime;
    }

    public ProductionProcess input(Resource resource, int count) {
        input.put(resource, count);
        return this;
    }

    public ProductionProcess output(Resource resource, int count) {
        output.put(resource, count);
        resource.addProductionProcess(this);
        return this;
    }
}
