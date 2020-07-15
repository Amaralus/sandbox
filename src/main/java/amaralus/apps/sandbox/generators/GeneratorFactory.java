package amaralus.apps.sandbox.generators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneratorFactory {

    private final Random random = new Random();
    private final Map<Class<?>, Generator<?>> randomGenerators;

    public GeneratorFactory() {
        randomGenerators = new HashMap<>();
        setUpRandomGenerators();
    }

    public <O> Generator<O> produce(Class<O> generatorClass) {
        return (Generator<O>) randomGenerators.get(generatorClass);
    }

    private void setUpRandomGenerators() {
        randomGenerators.put(Boolean.class, random::nextBoolean);
        randomGenerators.put(Byte.class, () -> (byte) random.nextInt());
        randomGenerators.put(Short.class, () -> (short) random.nextInt());
        randomGenerators.put(Integer.class, random::nextInt);
        randomGenerators.put(Long.class, random::nextLong);
        randomGenerators.put(Float.class, random::nextFloat);
        randomGenerators.put(Double.class, random::nextDouble);
        randomGenerators.put(BigInteger.class, () -> BigInteger.valueOf(random.nextLong()));
        randomGenerators.put(BigDecimal.class, () -> BigDecimal.valueOf(random.nextDouble()));
        randomGenerators.put(String.class, new RandomStringGenerator(random));
    }
}
