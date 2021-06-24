package amaralus.apps.sandbox;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AssertTool {

    private boolean skipMissingFields = false;
    private StringBuilder failedAssertBuilder;

    /**
     * Проверяет поля объектов на equals, в случает если они не совпадают
     * записывает ошибку в журнал, который можно извлечь позже. Так же
     * имеет возможность проверять начличие полей в проверяемом объекте
     * с записью в журнал если поле не будет найдено.
     *
     * @param expected Ожидаемый эталонный объект.
     * @param actual   Фактический объект котоырй нужно проверить.
     * @return Возвращает true когда все полля совпадают и есть в проверяемом объекте (если включено).
     */
    public boolean assertEquals(Object expected, Object actual) {
        Objects.requireNonNull(expected, "expected object must be not null!");
        Objects.requireNonNull(actual, "actual object must be not null!");

        try {
            failedAssertBuilder = new StringBuilder();
            var result = true;
            var actualFieldsValues = extractFieldsValues(actual);

            for (var entry : extractFieldsValues(expected).entrySet()) {
                if (!actualFieldsValues.containsKey(entry.getKey())) {
                    if (!skipMissingFields) {
                        result = false;
                        addFailedAssert(String.format("Ожидаемое поле %s отсутсвует", entry.getKey()));
                    }
                } else if (!Objects.equals(entry.getValue(), actualFieldsValues.get(entry.getKey()))) {
                    result = false;
                    addFailedAssert(String.format("Для поля %s ожидалось значение %s, но было %S",
                            entry.getKey(), entry.getValue(), actualFieldsValues.get(entry.getKey())));
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            log.error("Ошибка доступа во время сравнения объектов", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Извлекает из объекта поля и их значения в виде мапы.
     *
     * @param object Объект из которого нужно вытащить значения полей.
     * @return Возвразает мапу с полями и их значениями.
     * @throws IllegalAccessException В случае отсутствия доступа к полю.
     */
    private Map<String, Object> extractFieldsValues(Object object) throws IllegalAccessException {
        Map<String, Object> fieldsValues = new HashMap<>();

        for (var field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fieldsValues.put(field.getName(), field.get(object));
        }
        return fieldsValues;
    }

    private void addFailedAssert(String message) {
        log.info(message);
        failedAssertBuilder.append("\n").append(message);
    }

    public String getFailedAsserts() {
        return failedAssertBuilder.toString();
    }

    public AssertTool setSkipMissingFields(boolean skipMissingFields) {
        this.skipMissingFields = skipMissingFields;
        return this;
    }
}
