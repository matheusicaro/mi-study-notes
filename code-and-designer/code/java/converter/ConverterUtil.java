package project.name.converter; //NOSONAR


import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.function.UnaryOperator;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConverterUtil {

    private static final String CLASS_NAME = "[" + ConverterUtil.class.toString() + "]";
    private static final String BREAK_LINES = "\\n";
    private static final String SPACES = "\\s{2,}";

    private ConverterUtil() {
        //    empty
    }

    public static Optional<String> toJsonStringNoBeautiful(Object object) {

        UnaryOperator<String> format = string -> string.replaceAll(BREAK_LINES.concat("|").concat(SPACES), "");

        return toJsonString(object).map(format);
    }

    public static Optional<String> toJsonString(Object object) {

        if (object == null) {
            return Optional.empty();
        }

        if (object instanceof String && StringUtil.isNullOrEmpty((String) object)) {
            return Optional.empty();
        }

        try {
            return Optional.ofNullable(new ObjectMapper().setVisibility(FIELD, ANY).writeValueAsString(object));
        } catch (Exception exe) {
            log.error(CLASS_NAME + " - toJsonString");
            exe.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> Optional<T> from(Object object, Class<T> clazz) {
        return converter(object, clazz, null);
    }

    public static <T> Optional<T> from(Object object, TypeReference<T> typeRef) {
        return converter(object, null, typeRef);
    }

    private static <T> Optional<T> converter(Object object, Class<T> clazz, TypeReference<T> typeRef) {

        if (object == null) {
            return Optional.empty();
        }

        boolean objectAlreadyConverted = clazz != null && StringUtil.equals(clazz.getName(), object.getClass().getName());

        if (objectAlreadyConverted) {
            return (Optional<T>) Optional.of(object);
        }

        ObjectMapper mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            T result = (clazz == null) ? mapper.convertValue(object, typeRef) : mapper.convertValue(object, clazz);

            return Optional.ofNullable(result);

        } catch (Exception exception) {
            try {
                Optional<String> asString = object instanceof String ? Optional.of((String) object) : toJsonString(object);

                if (!asString.isPresent()) {
                    return Optional.empty();
                }

                T result = (clazz == null) ? mapper.readValue(asString.get(), typeRef) : mapper.readValue(asString.get(), clazz);

                return Optional.ofNullable(result);

            } catch (Exception e) {

                String firstException = "FIRST_EXCEPTION: " + exception.getMessage();
                String secondException = ", SECOND_EXCEPTION: " + exception.getMessage();
                log.error(CLASS_NAME + ", converter - " + firstException + secondException);
                e.printStackTrace();

                return Optional.empty();
            }
        }
    }
}

