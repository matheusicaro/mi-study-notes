package service.utils;

import org.apache.commons.lang3.StringUtils;

  /**
   * Class util to wrap common functions to work with Strings..
   *
   * */
public class StringUtil extends StringUtils {

    private StringUtil() {}

    public static String getOnlyNumbers(String string) {
        return isNullOrEmpty(string) ? "" : string.replaceAll("[^0-9]", "");
    }

    public static boolean isNullOrEmpty(String text) {
        return isBlank(text);
    }

    public static boolean isNotNullOrEmpty(String text) {
        return isNotBlank(text);
    }
}

