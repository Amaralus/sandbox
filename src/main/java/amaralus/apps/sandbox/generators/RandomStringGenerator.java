package amaralus.apps.sandbox.generators;

import java.util.Random;

public class RandomStringGenerator implements Generator<String> {

    private final Random random;
    private int stringLength = 10;

    public RandomStringGenerator(Random random) {
        this(random, 10);
    }

    public RandomStringGenerator(Random random, int stringLength) {
        this.random = random;
        this.stringLength = stringLength;
    }

    @Override
    public String generate() {
        // символ 0
        int leftLimit = 48;
        // символ z
        int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }
}
