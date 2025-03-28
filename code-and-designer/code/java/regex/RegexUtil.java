package project.name.regex; //NOSONAR

public class RegexUtil {

    /**
     * RRegex to identify any characters except for digit character.
     *
     * <p> input: "@@ F5 F1 $#H5 sfs321 DS"
     * <p> match: [ @, @, F, F, $, #, H, s, f, s, D, S ]
     */
    public static final String MATCH_ALL_BUT_NO_ONE_NUMBER = "([\\D]+)";

    /**
     * Regex to identify the first number digit.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ 5, 1, 5, 3, 2, 1 ]
     */
    public static final String MATCH_NUMBER = "[\\d]";

    /**
     * Regex to identify all number digits.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ 5, 1, 5, 321 ]
     */
    public static final String MATCH_ALL_NUMBERS = addOneOrMoreToRegexCondition(MATCH_NUMBER);

    /**
     * Regex to identify all number digits.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ 5, 1, 5, 321 ]
     */
    public static final String MATCH_DOUBLE_NUMBERS = "([\\d]+)(.)([\\d]+)";

    /**
     * Regex to identify all number digits.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ 5, 1, 5, 321 ]
     */
    public static final String MATCH_ALL_DOUBLE_NUMBERS = addOneOrMoreToRegexCondition(MATCH_DOUBLE_NUMBERS);

    /**
     * Regex to identify any first letter upper or lower case.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ F, F, H, s, f, s, D, S ]
     */
    public static final String MATCH_LETTER = "[a-zA-Z]";

    /**
     * Regex to identify any letter upper or lower case.
     *
     * <p> input: "F5 F1 H5 sfs321 DS"
     * <p> match: [ F, F, H, sfs, DS ]
     */
    public static final String MATCH_ALL_LETTERS = addOneOrMoreToRegexCondition(MATCH_LETTER);

    /**
     * Regex to identify any first character like number or letter
     * <p> input: "# $ & ? % 55 Ft SFS321"
     * <p> match: [ 5, 5 F, t, S, F, S, 3, 2, 1 ]
     */
    public static final String MATCH_ANY_CHAR = "[\\w]";

    /**
     * Regex to identify any characters like numbers or letters.
     *
     * <p> input: "# $ & ? % 55 Ft SFS321"
     * <p> match: [ 55, Ft, SFS321 ]
     */
    public static final String MATCH_ALL_ANY_CHAR = addOneOrMoreToRegexCondition(MATCH_ANY_CHAR);


    /**
     * Regex to identify character with accented letters
     * <p> input: "aló português com açaí da vÔÔ"
     * <p> match: [ ó, ê, í, Ô, Ô ]
     */
    public static final String MATCH_ACCENTED_LETTERS = "[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]";

    /**
     * Regex to identify ANY character with accented letters
     * <p> input: "aló português com açaí da vÔvÔ"
     * <p> match: [ ó, ê, í, ÔÔ ]
     */
    public static final String MATCH_ALL_ACCENTED_LETTERS = addOneOrMoreToRegexCondition(MATCH_ACCENTED_LETTERS);


    /**
     * Regex to identify first special character.
     *
     * <p> input: "$$$ome ######ok [@thor] %& +!behind _*? ({55}). Ft, SFS321"
     * <p> match: [ $, $, $, #, #, #, #, #, #, [, ], ... ]
     */
    public static final String MATCH_SPECIAL_CHAR = "[$#.;:/|@%&+!_*?\\(\\)\\[\\]\\{\\}\\-=]";

    /**
     * Regex to identify all special characters.
     *
     * <p> input: "$$$ome ######ok [@thor] %& +!behind _*? ({55}). Ft, SFS321"
     * <p> match: [ $$$, ######, [, ], ... ]
     */
    public static final String MATCH_ALL_SPECIAL_CHAR = addOneOrMoreToRegexCondition(MATCH_SPECIAL_CHAR);

    /**
     * Regex to identify first quotation marks.
     *
     * <p> input: "regular ''some'' "expression" test"
     * <p> match: [ ', ', ', ', ", " ]
     */
    public static final String MATCH_QUOTATION_MARKS = "(\"|')";

    /**
     * Regex to all identify quotation marks.
     *
     * <p> input: "regular ''some'' "expression" test"
     * <p> match: [ '', '', ", " ]
     */
    public static final String MATCH_ALL_QUOTATION_MARKS = addOneOrMoreToRegexCondition(MATCH_QUOTATION_MARKS);

    /**
     * Regex to identify first break of line.
     *
     * <p> input: "regular \n'some'\n \"expression\"\n\n test"
     * <p> match: [ \n, \n, \n, \n ]
     */
    public static final String MATCH_BREAK_LINES = "\\n";

    /**
     * Regex to identify all break of lines.
     *
     * <p> input: "regular \n'some'\n \"expression\"\n\n test"
     * <p> match: [ \n, \n, \n\n ]
     */
    public static final String MATCH_ALL_BREAK_LINES = addOneOrMoreToRegexCondition(MATCH_BREAK_LINES);

    /**
     * Regex to identify spaces.
     *
     * <p> input: "regular  'some'       \"expression\"\n\n       test"
     * <p> match: [ "regular 'some' \"expression\" test" ]
     */
    public static final String MATCH_SPACES_CONSECUTIVELY = "\\s{2,}";

    /**
     * Regex to identify formatting like spaces, tabs and line breaks.
     *
     * <p> input: "regular \n'some' \t\t \"expression\"\s test"
     * <p> match: [ \n, \t, \t, \s ]
     */
    public static final String MATCH_FORMATTING_LINES = "[[\\n]|[\\t]|[\\s]]";

    /**
     * Regex to identify ALL formatting like spaces, tabs and line breaks.
     *
     * <p> input: "regular \n'some' \t\t \"expression\"\s test"
     * <p> match: [ \n, \t\t, \s ]
     */
    public static final String MATCH_ALL_FORMATTING_LINES = addOneOrMoreToRegexCondition(MATCH_FORMATTING_LINES);

    /**
     * Regex to identify first slash.
     *
     * <p> input: "https://www.url.com/my-account/55580/send/email"
     * <p> match: [ /, /, /, /, /, / ]
     */
    public static final String MATCH_SLASH = "\\/";

    /**
     * Regex to identify all slash.
     *
     * <p> input: "https://www.url.com/my-account/55580/send/email"
     * <p> match: [ //, /, /, /, / ]
     */
    public static final String MATCH_ALL_SLASH = addOneOrMoreToRegexCondition(MATCH_SLASH);

    /**
     * Regex to identify first URL numeric params.
     *
     * <p> input: "https://www.url.com/my-account/55580/00010/send/email-id/00000150/changing-password"
     * <p> match: [ /55580/, /00010/, /00000150/ ]
     */
    public static final String MATCH_FIRST_URL_NUMERIC_PARAM = "((/)" + RegexUtil.MATCH_ALL_NUMBERS + "(/))";

    /**
     * Regex to identify all URL numeric params.
     *
     * <p> input: "https://www.url.com/my-account/55580/00010/send/email-id/00000150/changing-password"
     * <p> match: [ /55580/00010/, /00000150/]
     */
    public static final String MATCH_ALL_URL_NUMERIC_PARAMS = addOneOrMoreToRegexCondition(MATCH_FIRST_URL_NUMERIC_PARAM);

    /**
     * Regex to identify key and values, for example:
     * <p> input: "this is a simple string with {'key':'value', 'key_2':'value', "key":"value"} to test on regex"
     * <p> match: [ 'key':'value', 'key_2':'value', "key":"value" ]     *
     */
    public static final String MATCH_JSON_KEY_AND_VALUE = buildRegexJsonKeyAndValue();

    /**
     * Regex to identify key and values, for example:
     * <p> input: "this is a simple string with {'key':'value', 'key_2':'value', "key":"value"} to test on regex]
     * <p> identify: 'key':'value', 'key_2':'value', "key":"value"
     */
    private static String buildRegexJsonKeyAndValue() {

        String value = ("([") // @formatter:off
            .concat(MATCH_ANY_CHAR)
            .concat("|")
            .concat(MATCH_ACCENTED_LETTERS)
            .concat("|")
            .concat(MATCH_SPECIAL_CHAR)
            .concat("|")
            .concat(MATCH_FORMATTING_LINES)
            .concat("]+)"); // @formatter:on

        String keyOrValueInQuotationMarks = MATCH_QUOTATION_MARKS.concat(value).concat(MATCH_QUOTATION_MARKS);

        String valuesInQuotationMarksOrPrimitiveValues = "("   // @formatter:off
                + "(" + keyOrValueInQuotationMarks + ")"
                + "|"
                + "(" + MATCH_DOUBLE_NUMBERS + ")"
                + "|"
                + "(" + MATCH_ALL_ANY_CHAR + ")"
            + "+)"; // @formatter:on

        return keyOrValueInQuotationMarks.concat(":").concat(valuesInQuotationMarksOrPrimitiveValues);
    }

    private static String addOneOrMoreToRegexCondition(String regex) {
        return "(".concat(regex).concat("+)");
    }

    private RegexUtil() {
    }
}
