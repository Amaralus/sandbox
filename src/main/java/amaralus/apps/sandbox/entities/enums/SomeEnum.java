package amaralus.apps.sandbox.entities.enums;

public enum SomeEnum {
    ONE,
    TWO,
    THREE;

    public static SomeEnum ofByteOrdinal(byte byteOrdinal) {
        return values()[byteOrdinal];
    }

    public byte byteOrdinal() {
        return (byte) ordinal();
    }
}
