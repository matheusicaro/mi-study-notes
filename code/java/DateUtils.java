package service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateUtil {

  private DateUtil() {}

  /**
   * Build instance Datetime from some String by formats defined at DateFormat.
   *
   * @param date: Date like string - 10/02/2000, 2020/11/01
   * @param format: Date format customizing
   * */
  public static OffsetDateTime buildDateTimeFrom(String date, DateFormat format) throws ParseException {
    
      SimpleDateFormat formatter = new SimpleDateFormat(format.value);

      return formatter.parse(date).toInstant().atOffset(ZoneOffset.UTC);
  }

  public enum DateFormat {
    
      BRAZILIAN_SIMPLE_DATE("dd/MM/yyyy"),
      ENGLISH_AMERICAN_SIMPLE_DATE("MM/dd/yyyy");

      private final String value;

      DateFormat(String value) { this.value = value; }
  }
}