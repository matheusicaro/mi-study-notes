package project.name.date; //NOSONAR

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

    private static final String CONVERT_TO_DATETIME_ERROR = "Erro ao converter para datetime a string: ";

    /**
     * Function to build instance Datetime from any String defined by DateFormatEnum.
     *
     * @param date:   String Date like  - 10/02/2000, 2020/11/01
     * @param format: Date format customized
     */
    public static Optional<OffsetDateTime> convertToDateTime(String date, DateFormatEnum format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format.value);
        try {
            return Optional.of(formatter.parse(date).toInstant().atOffset(ZoneOffset.UTC));
        } catch (Exception exception) {
            log.error(CONVERT_TO_DATETIME_ERROR.concat(date));
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Build instance Datetime from timestamp.
     *
     * @param timestamp
     * @return OffsetDateTime
     */
    public static Optional<OffsetDateTime> buildDateTimeFrom(long timestamp) {
        try {
            Date date = new Date(timestamp);
            return Optional.of(LocalDateTime.of(date.toLocalDate(), LocalTime.ofNanoOfDay(date.getTime())).atOffset(ZoneOffset.UTC));
        } catch (Exception exception) {
            log.error(CONVERT_TO_DATETIME_ERROR.concat(String.valueOf(timestamp)));
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Build instance Datetime from Date.
     *
     * @param date
     * @return OffsetDateTime
     */
    public static OffsetDateTime buildDateTimeFrom(Date date) {
        validateInputs(date);
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    /**
     * Check if OffsetDateTime is before the system current date now
     *
     * @param date
     * @return OffsetDateTime
     */
    public static Boolean isDateBeforeCurrentDateNow(OffsetDateTime date) {
        validateInputs(date);
        return OffsetDateTime.now().compareTo(date) > 0;
    }

    /**
     * Check if OffsetDateTime is after the system current date now
     *
     * @param date
     * @return OffsetDateTime
     */
    public static Boolean isDateAfterCurrentDateNow(OffsetDateTime date) {
        validateInputs(date);
        return OffsetDateTime.now().compareTo(date) < 0;
    }

    public enum DateFormatEnum {
        BRAZILIAN_SIMPLE_DATE("dd/MM/yyyy"),
        BRAZILIAN_DATETIME_FORMATTER("dd/MM/yyyy HH:mm:ss"),
        ENGLISH_AMERICAN_SIMPLE_DATE("MM/dd/yyyy");

        private final String value;

        DateFormatEnum(String value) {
            this.value = value;
        }
    }

    private static void validateInputs(Object... inputs) {
        for (Object input : inputs) {
            Objects.requireNonNull(input, "Input cannot be null");
        }
    }

    private DateUtil() {
    }
}
