package amaralus.apps.sandbox.generators;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EntityGenerator {

    private final GeneratorFactory generatorFactory;

    public EntityGenerator() {
        this.generatorFactory = new GeneratorFactory();
    }

    public void generate(Object object) throws IllegalAccessException {
        Objects.requireNonNull(object);

        for (Map.Entry<Field, Class<?>> entry : extractFieldsTypes(object).entrySet()) {
            Generator<?> generator = generatorFactory.produce(entry.getValue());

            if (generator != null) {
                entry.getKey().set(object, generator.generate());
            }
        }
    }

    private Map<Field, Class<?>> extractFieldsTypes(Object object) throws IllegalAccessException {
        Map<Field, Class<?>> fieldsTypes = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            fieldsTypes.put(field, fieldType.isPrimitive() ? field.get(object).getClass() : fieldType);
        }
        return fieldsTypes;
    }
}
