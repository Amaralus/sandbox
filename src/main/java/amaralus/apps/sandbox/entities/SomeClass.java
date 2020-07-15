package amaralus.apps.sandbox.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SomeClass {
    private int someInt;
    private long someLong;
    private String someString;
    private List<SomeClassEntry> someClassEntries;
}
