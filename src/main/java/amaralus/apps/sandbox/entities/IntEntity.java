package amaralus.apps.sandbox.entities;

import java.util.Objects;

public class IntEntity {
    private int i;

    public IntEntity(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntEntity intEntity = (IntEntity) o;
        return i == intEntity.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}
