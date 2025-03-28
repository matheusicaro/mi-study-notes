package project.name.string; //NOSONAR

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class StringUtil {

    private StringUtil() {
    }

    public static String getOnlyNumbers(String string) {
        return isNullOrEmpty(string) ? "" : string.replaceAll(RegexUtil.MATCH_ALL_BUT_NO_ONE_NUMBER, "");
    }

    public static boolean isNullOrEmpty(String text) {
        return StringUtils.isBlank(text);
    }

    public static boolean isNotNullOrEmpty(String text) {
        return StringUtils.isNotBlank(text);
    }

    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    public static String substring(String str, int start, int end) {
        return StringUtils.substring(str, start, end);
    }

    /**
     * Method to return string without formatting for line breaks and unnecessary spaces, such as JSON beautiful
     *
     * @param string: "string with \n break lines \p and \n\n\n spaces."
     * @return string: "string with break lines and spaces."
     */
    public static String removeBeautifulFormatting(String string) {

        if (StringUtil.isNullOrEmpty(string)) {
            return "";
        }

        return string.replaceAll(RegexUtil.MATCH_BREAK_LINES.concat("|").concat(RegexUtil.MATCH_SPACES_CONSECUTIVELY), "");
    }

    /**
     * Method to return value formatted to Brazilian Real Currency considering no CENTS between values
     * <p>
     * CENTS are not considered
     * <p>
     * Example: [ 1005 => R$ 1.005,00 ], [ 100.5 => R$ 1.005,00 ]
     *
     * @param value: "19999"
     * @return "R$ 19.999,00"
     */
    public static Optional<String> formatValueToBrazilianRealCurrencyNoCents(String value) {
        String DOT = "."; // NOSONAR
        String COMMA = ","; // NOSONAR
        String MARKER = "#"; // NOSONAR

        try {

            if (StringUtil.isNullOrEmpty(value)) {
                return Optional.empty();
            }

            UnaryOperator<String> buildAnyCharRegex = stringChars -> "([" + stringChars + "]+)";

            int indexOfCentSeparator = indexOfCentSeparator(value);
            String valueWithoutCents = indexOfCentSeparator > 0 ? value.substring(0, indexOfCentSeparator) : value;

            String valueInEnglishFormat = String.format("R$ %,.2f", Double.valueOf(removeAnyCommaOrDor(valueWithoutCents)));

            String valueFormatted = valueInEnglishFormat // @formatter:off
                .replaceAll(buildAnyCharRegex.apply(COMMA), MARKER)
                .replace(DOT, COMMA)
                .replaceAll(buildAnyCharRegex.apply(MARKER), DOT); // @formatter:on

            return Optional.of(valueFormatted);

        } catch (Exception exception) {
            log.error("Error in formatting values to Real currency, value: ".concat(value));
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<String> formatValueToBrazilianRealCurrencyNoCents(Integer value) {
        return value == null ? Optional.empty() : formatValueToBrazilianRealCurrencyNoCents(String.valueOf(value));
    }

    public static Optional<String> formatValueToBrazilianRealCurrencyNoCents(Double value) {
        return value == null ? Optional.empty() : formatValueToBrazilianRealCurrencyNoCents(String.valueOf(value));
    }

    /**
     * Method to return value formatted to Brazilian Real Currency considering CENTS as the last right side places
     * <p>
     * CENTS are considered
     *
     * @param value: "198.99"
     * @return "R$ 199,00"
     */
    public static Optional<String> formatValueToBrazilianRealCurrencyWithCents(String value) {
        try {

            if (StringUtil.isNullOrEmpty(value)) {
                return Optional.empty();
            }

            if (indexOfCentSeparator(value) == -1) {
                return formatValueToBrazilianRealCurrencyNoCents(removeAnyCommaOrDor(value));
            }

            String valueTobeFormat = formatCentsToTwoDecimalPlaces(value);
            int indexOfCentsDot = indexOfCentSeparator(valueTobeFormat);

            String integerValue = valueTobeFormat.substring(0, indexOfCentsDot);
            String currentCents = valueTobeFormat.substring(indexOfCentsDot, valueTobeFormat.length());

            UnaryOperator<String> replaceCentsWithCurrentCents = s -> s.substring(0, s.indexOf(",") + 1)
                .concat(currentCents.substring(1, currentCents.length()));

            return formatValueToBrazilianRealCurrencyNoCents(removeAnyCommaOrDor(integerValue)).map(replaceCentsWithCurrentCents);
        } catch (Exception exception) {
            log.error("Input: " + value);
            exception.printStackTrace();
            throw exception;
        }
    }

    public static Optional<String> formatValueToBrazilianRealCurrencyWithCents(Integer value) {
        return value == null ? Optional.empty() : formatValueToBrazilianRealCurrencyWithCents(String.valueOf(value));
    }

    public static Optional<String> formatValueToBrazilianRealCurrencyWithCents(Double value) {
        return value == null ? Optional.empty() : formatValueToBrazilianRealCurrencyWithCents(String.valueOf(value));
    }

    public static String replaceFirst(String value, String newValue, String stringToReplace) {

        if (isNullOrEmpty(stringToReplace)) {
            return stringToReplace;
        }

        try {
            return stringToReplace.replaceFirst(value, newValue);
        } catch (PatternSyntaxException e) {
            return stringToReplace.replace(value, newValue);
        }
    }

    /**
     * The method is responsible to return the set of keys and values as string from primitive values.
     * <p> Primitive values: boolean, byte, char, short, int, long, float, double, null
     *
     * <p> OBS: Other types like Map, Array, OwnClass was not extract as key and values.
     *
     * @param string : "example of string "key_1":"value" some 'key_2':'value' keys ad values"
     * @return List<String>: [ "key_1":"value", 'key_2':'value' ]
     */
    public static List<String> extractJsonKeyAndValuesFromPrimitiveTypes(String string) {
        try {
            String cleanString = StringUtil.removeBeautifulFormatting(string);

            String[] stringValuesNotJsonKeyAndValue = cleanString.split(RegexUtil.MATCH_JSON_KEY_AND_VALUE);

            BiPredicate<String, String> isJsonComma = (s, fullString) -> {
                int sIndex = fullString.indexOf(s);

                String nextChar = String.valueOf(fullString.charAt(sIndex + 1));
                boolean nextCharIsJsonKey = "\"".equals(nextChar) || "'".equals(nextChar); //NOSONAR

                return nextCharIsJsonKey;
            };

            String marker = ">###<";

            for (String stringValue : stringValuesNotJsonKeyAndValue) {
                if (!" ".equals(stringValue)) {
                    boolean isNotAStringComma = !",".equals(stringValue) || isJsonComma.test(stringValue, cleanString);
                    if (isNotAStringComma) {
                        cleanString = StringUtil.replaceFirst(stringValue, marker, cleanString);
                    }
                }
            }

            String regexMarkerOrComma = "(" + marker + "|,)";

            return Arrays.stream(cleanString.split(regexMarkerOrComma))
                .filter(e -> StringUtil.isNotNullOrEmpty(e) && e.matches(RegexUtil.MATCH_JSON_KEY_AND_VALUE)).collect(Collectors.toList());

        } catch (Exception exception) {
            log.error("Input: " + string);
            exception.printStackTrace();
            throw exception;
        }
    }

    /**
     * Function to format cents to two decimal places
     * <p>
     * Example:
     * <p>
     * - Input value: "0.1" -> Return: 0,10
     * <p>
     * - Input value: "1" -> Return: 0,10
     * <p>
     * - Input value: "10" -> Return: 0,10
     * <p>
     * - Input value: "100" -> Return: 100
     * <p>
     * - Input value: "100" -> Return: 100
     * <p>
     * - Input value: "1000"" -> Return: 10,00
     * <p>
     * - Input value: "10000"" -> Return: 100,00
     */
    private static String formatCentsToTwoDecimalPlaces(String value) {
        try {

            int indexOfCentsSeparator = indexOfCentSeparator(value);

            if (indexOfCentsSeparator == -1) {
                return formatIntegerValueWithTwoDecimalPlaces(value);
            }

            String cents = value.substring(indexOfCentsSeparator + 1, value.length());

            if (cents.length() == 0) { // @formatter:off
                return value + "00";
            } else if (cents.length() == 1) {
                return value + "0";
            } else if (cents.length() > 2) {
                String roundedCents = new BigDecimal("." + cents)
                    .setScale(3, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP).toString(); // @formatter:on

                boolean roundedCentsAddedToOne = "1.00".equals(roundedCents);

                if (roundedCentsAddedToOne) {
                    return (Integer.parseInt(removeAnyCommaOrDor(value.substring(0, indexOfCentsSeparator))) + 1) + ",00";
                }

                return value.replace(cents, roundedCents.split("([.]+)")[1]);
            } else {
                return value;
            }
        } catch (Exception exception) {
            log.error(getInputValue(value));
            exception.printStackTrace();
            throw exception;
        }
    }

    private static String getInputValue(String value) {
        return "Input: " + value;
    }

    /**
     * Function to add comma for the cents whe the value is only INTEGER
     *
     * <p> Example:
     * <p>- Input value: "0" -> Return: 0,00
     * <p>- Input value: "00" -> Return: 0,00
     * <p>- Input value: "1" -> Return: 0,10
     * <p>- Input value: "10" -> Return: 0,10
     * <p>- Input value: "100" -> Return: 1,00
     * <p>- Input value: "0100" -> Return: 1,00
     * <p>- Input value: "1000"" -> Return: 10,00
     * <p>- Input value: "10000"" -> Return: 100,00
     */
    private static String formatIntegerValueWithTwoDecimalPlaces(String value) {
        try {
            String valueWithoutLeadingZeros = value.length() > 10 ? value : Integer.valueOf(value).toString();

            if (valueWithoutLeadingZeros.length() > 2) {
                String valueWithoutCents = value.substring(0, value.length() - 2);
                String cents = value.replaceAll(valueWithoutCents, "");
                return valueWithoutCents + "," + cents;
            }

            return valueWithoutLeadingZeros.length() > 1 ? "0," + valueWithoutLeadingZeros : "0,0" + valueWithoutLeadingZeros;
        } catch (Exception exception) {
            log.error(getInputValue(value));
            exception.printStackTrace();
            throw exception;
        }
    }

    /**
     * Function to return the index of cent separator
     * <p> Example:
     * <b>
     * <p>- Input value: "1.5"" -> Return: 1
     * <p>- Input value: "1,5" -> Return: 1
     * <p>- Input value: "1,50" -> Return: 1
     * <p>- Input value: "1.000,505" -> Return: 5
     * <p>- Input value: "1000.0" -> Return: 4
     * <p>- Input value: "1" -> Return: -1
     */
    private static int indexOfCentSeparator(String value) {
        try {
            for (int i = value.length() - 1; i >= 0; i--) {
                String charAsString = Character.toString(value.charAt(i));

                if (".".equals(charAsString) || ",".equals(charAsString)) {
                    return i;
                }
            }

            return -1;
        } catch (Exception exception) {
            log.error(getInputValue(value));
            exception.printStackTrace();
            throw exception;
        }
    }

    private static String removeAnyCommaOrDor(String str) {
        return str.replaceAll("([,.])+", "");
    }
}