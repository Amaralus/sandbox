package amaralus.apps.sandbox;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AssertTool {

    private boolean skipMissingFields;

    public AssertTool() {
        this(false);
    }

    public AssertTool(boolean skipMissingFields) {
        this.skipMissingFields = skipMissingFields;
    }

    public boolean assertThat(Object expected, Object actual) {
        try {
            boolean result = true;
            Map<String, Object> actualFieldsValues = extractFieldsValues(actual);

            for (Map.Entry<String, Object> entry : extractFieldsValues(expected).entrySet()) {
                if (!actualFieldsValues.containsKey(entry.getKey())) {
                    if (skipMissingFields)
                        log.info("Отсутствующеее поле {} пропущено", entry.getKey());
                    else {
                        result = false;
                        log.info("Ожидаемое поле {} отсутсвует", entry.getKey());
                    }

                } else if (!Objects.equals(entry.getValue(), actualFieldsValues.get(entry.getKey()))) {
                    log.info("Для поля {} ожидалось значение {}, но было {}",
                            entry.getKey(), entry.getValue(), actualFieldsValues.get(entry.getKey()));
                    result = false;
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            log.error("assert error", e);
            return false;
        }
    }

    private Map<String, Object> extractFieldsValues(Object object) throws IllegalAccessException {
        Map<String, Object> fieldsValues = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fieldsValues.put(field.getName(), field.get(object));
        }
        return fieldsValues;
    }
}
