package amaralus.apps.sandbox.generators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomGeneratorFactory {

    private final Random random = new Random();
    private final Map<Class<?>, Generator<?>> generators;

    public RandomGeneratorFactory() {
        generators = new HashMap<>();
        setUpRandomGenerators();
    }

    public <O> Generator<O> produce(Class<O> generatorClass) {
        return (Generator<O>) generators.get(generatorClass);
    }

    private void setUpRandomGenerators() {
        generators.put(Boolean.class, random::nextBoolean);
        generators.put(Byte.class, () -> (byte) random.nextInt());
        generators.put(Short.class, () -> (short) random.nextInt());
        generators.put(Integer.class, random::nextInt);
        generators.put(Long.class, random::nextLong);
        generators.put(Float.class, random::nextFloat);
        generators.put(Double.class, random::nextDouble);
        generators.put(BigInteger.class, () -> BigInteger.valueOf(random.nextLong()));
        generators.put(BigDecimal.class, () -> BigDecimal.valueOf(random.nextDouble()));
        generators.put(String.class, new RandomStringGenerator(random));
        generators.put(Date.class, () -> new Date(random.nextLong()));
        generators.put(java.sql.Date.class, () -> new java.sql.Date(random.nextLong()));
        generators.put(LocalDate.class, () -> new Date(random.nextLong()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        generators.put(LocalDateTime.class, () -> new Date(random.nextLong()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        generators.put(ZonedDateTime.class, () -> new Date(random.nextLong()).toInstant().atZone(ZoneId.systemDefault()));
    }
}
