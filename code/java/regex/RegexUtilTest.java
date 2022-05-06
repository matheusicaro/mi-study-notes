package project.name.regex; //NOSONAR

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegexUtilTest {

    @Test
    void MATCH_ALL_BUT_NO_ONE_NUMBER_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "F5 F1 H5 sfs321 DS";
        String expected = "*5*1*5*321*";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_BUT_NO_ONE_NUMBER, "*"));
    }

    @Test
    void MATCH_NUMBER_BUT_NO_ONE_NUMBER_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "F5 F1 H5 sfs321 DS";
        String expected = "F* F* H* sfs*** DS";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_NUMBER, "*"));
    }

    @Test
    void MATCH_ALL_NUMBERS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "F5 F1 H5 sfs321 DS";
        String expected = "F* F* H* sfs* DS";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_NUMBERS, "*"));
    }

    @Test
    void MATCH_DOUBLE_NUMBERS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        List<String> tests = Arrays.asList("string 1.22 with double", "string 2131321.22 with double");

        tests.forEach(str -> {
            Assertions.assertEquals("string * with double", str.replaceAll(RegexUtil.MATCH_DOUBLE_NUMBERS, "*"));
        });
    }

    @Test
    void MATCH_ALL_DOUBLE_NUMBERS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "2131.22 string 1.22 with 5.33 double 1333.0";
        String expected = "* string * with * double *";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_DOUBLE_NUMBERS, "*"));
    }

    @Test
    void MATCH_LETTER_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "F5 F1 H5 sfs321 DS";
        String expected = "*5 *1 *5 ***321 **";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_LETTER, "*"));
    }

    @Test
    void MATCH_ALL_LETTERS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "F5 F1 H5 sfs321 DS";
        String expected = "*5 *1 *5 *321 *";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_LETTERS, "*"));
    }

    @Test
    void MATCH_ANY_CHAR_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "# $ & ? % 55 Ft SFS321";
        String expected = "# $ & ? % ** ** ******";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ANY_CHAR, "*"));
    }

    @Test
    void MATCH_ALL_ANY_CHAR_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "# $ & ? % 55 Ft SFS321";
        String expected = "# $ & ? % * * *";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_ANY_CHAR, "*"));
    }

    @Test
    void MATCH_SPECIAL_CHAR_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "$ome #ok @thor %& +!behind _*? 55 Ft SFS321";
        String expected = "*ome *ok *thor ** **behind *** 55 Ft SFS321";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_SPECIAL_CHAR, "*"));
    }

    @Test
    void MATCH_ALL_SPECIAL_CHAR_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "$ome #ok @thor %& +!behind _*? 55 Ft SFS321";
        String expected = "*ome *ok *thor * *behind * 55 Ft SFS321";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_SPECIAL_CHAR, "*"));
    }

    @Test
    void MATCH_QUOTATION_MARKS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "regular 'some' \"expression\" test";
        String expected = "regular *some* *expression* test";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_QUOTATION_MARKS, "*"));
    }

    @Test
    void MATCH_ALL_QUOTATION_MARKS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "regular ''some'' \"\"expression\" test";
        String expected = "regular *some* *expression* test";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_QUOTATION_MARKS, "*"));
    }

    @Test
    void MATCH_BREAK_LINES_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "regular \n'some'\n \"expression\"\n\n test";
        String expected = "regular *'some'* \"expression\"** test";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_BREAK_LINES, "*"));
    }

    @Test
    void MATCH_ALL_BREAK_LINES_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "regular \n'some'\n \"expression\"\n\n test";
        String expected = "regular *'some'* \"expression\"* test";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_BREAK_LINES, "*"));
    }

    @Test
    void MATCH_SPACES_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "regular  'some'       \"expression\"\n\n       test";
        String expected = "regular_'some'_\"expression\"_test";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_SPACES_CONSECUTIVELY, "_"));
    }

    @Test
    void MATCH_SLASH_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "https://www.url.com/my-account/55580/send/email";
        String expected = "https:[/][/]www.url.com[/]my-account[/]55580[/]send[/]email";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_SLASH, "[/]"));
    }

    @Test
    void MATCH_ALL_SLASH_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "https://www.url.com/my-account/55580/send/email";
        String expected = "https:[/]www.url.com[/]my-account[/]55580[/]send[/]email";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_ALL_SLASH, "[/]"));
    }

    @Test
    void MATCH_FIRST_URL_NUMERIC_PARAM_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "https://www.url.com/my-account/55580/send/email-id/00000150/changing-password";
        String expected = "https://www.url.com/my-account/*/send/email-id/*/changing-password";
        Assertions.assertEquals(expected, string.replaceAll(RegexUtil.MATCH_FIRST_URL_NUMERIC_PARAM, "/*/"));
    }

    @Test
    void MATCH_ALL_URL_NUMERIC_PARAMS_REGEX_should_return_expected_value_identify_by_regex_on_replacing_string() {
        String string = "https://www.url.com/my-account/55580/send/email-id/00000150/changing-password";
        String expected = "https://www.url.com/my-account/*/send/email-id/00000150/changing-password";
        Assertions.assertEquals(expected, string.replaceFirst(RegexUtil.MATCH_ALL_URL_NUMERIC_PARAMS, "/*/"));
    }

    @Test
    void MATCH_JSON_KEY_AND_VALUE_should_return_expected_values_identify_by_regex() {

        String expectedRegexValue = "(\"|')([[\\w]|[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]|[$#.;:/|@%&+!_*?\\(\\)\\[\\]\\{\\}\\-=]|[[\\n]|[\\t]|[\\s]]]+)(\"|'):(((\"|')([[\\w]|[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]|[$#.;:/|@%&+!_*?\\(\\)\\[\\]\\{\\}\\-=]|[[\\n]|[\\t]|[\\s]]]+)(\"|'))|(([\\d]+)(.)([\\d]+))|(([\\w]+))+)";

        List<HashMap<String, String>> tests = Arrays.asList( // @formatter:off
            new HashMap<String, String>(){{
                put("input", "string as a simple example with {'key':'value', 'key_2':'value', \"key\":\"value\"} to test on regex");
                put("expected", "string as a simple example with {, , } to test on regex");
            }},
            new HashMap<String, String>(){{
                put("input", "string with 'key':'value' values in the \"key\":\"value\" middle of");
                put("expected", "string with  values in the  middle of");
            }},
            new HashMap<String, String>(){{
                put("input", "string with double 'key':12.011 values in the \"key\":0.0444 \"key\":0.1 middle of");
                put("expected", "string with double  values in the   middle of");
            }}
        ); // @formatter:on

        tests.forEach(test -> {

            String[] valuesIdentifiedByRegex = test.get("input").split(RegexUtil.MATCH_JSON_KEY_AND_VALUE);
            StringBuilder returned = new StringBuilder();

            for (String v : valuesIdentifiedByRegex) {
                returned.append(v);
            }

//            Assertions.assertEquals(expectedRegexValue, RegexUtil.MATCH_JSON_KEY_AND_VALUE);
            Assertions.assertEquals(test.get("expected"), returned.toString());

        });
    }
}


