package project.name.date.test; //NOSONAR

import static org.junit.jupiter.api.Assertions.assertEquals;

import project.name.date.DateUtil.DateFormatEnum;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void should_converted_string_to_datetime_with_hour_minute_second() {
        // GIVE
        String dateTimeString = "30/06/2021 15:14:26";
        int expectedDay = 30;
        int expectedMonth = 6;
        int expectedYear = 2021;
        int expectedHour = 15;
        int expectedMinute = 14;
        int expectedSecond = 26;
        // WHEN
        Optional<OffsetDateTime> offsetDateTime = DateUtil.convertToDateTime(dateTimeString, DateFormatEnum.BRAZILIAN_DATETIME_FORMATTER);
        // THEN
        Assertions.assertTrue(offsetDateTime.isPresent());
        assertEquals(expectedDay, offsetDateTime.get().getDayOfMonth());
        assertEquals(expectedMonth, offsetDateTime.get().getMonthValue());
        assertEquals(expectedYear, offsetDateTime.get().getYear());
        assertEquals(expectedSecond, offsetDateTime.get().getSecond());
        assertEquals(expectedMinute, offsetDateTime.get().getMinute());
        assertEquals(expectedHour, offsetDateTime.get().getHour());
    }

    @Test
    void should_return_empty_datetime_when_string_is_different_expected_formatter() {
        // GIVE
        String dateTimeString = "30-06-2021 15:14:26";
        // WHEN
        Optional<OffsetDateTime> offsetDateTime = DateUtil.convertToDateTime(dateTimeString, DateFormatEnum.BRAZILIAN_DATETIME_FORMATTER);
        // THEN
        Assertions.assertFalse(offsetDateTime.isPresent());
    }

}
