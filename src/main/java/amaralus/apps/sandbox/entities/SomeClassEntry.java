package amaralus.apps.sandbox.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SomeClassEntry {
    private long someLong;
    private String someString;
}
