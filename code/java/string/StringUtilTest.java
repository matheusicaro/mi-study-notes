package project.name.string; //NOSONAR

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void method_getOnlyNumbers_should_Return_only_numbers_from_string() {
        Assertions.assertEquals("1258", StringUtil.getOnlyNumbers("abc1258.-=*"));
        Assertions.assertEquals("78963258635412000", StringUtil.getOnlyNumbers("78963258635412000"));
        Assertions.assertEquals("", StringUtil.getOnlyNumbers("asdfasrfwe/*-+.-=[]^^."));
        Assertions.assertEquals("", StringUtil.getOnlyNumbers(""));
        Assertions.assertEquals("", StringUtil.getOnlyNumbers("    "));
        Assertions.assertEquals("", StringUtil.getOnlyNumbers(null));
    }

    @Test
    void method_formatValueToBrazilianRealCurrencyNoCents_should_format_inputs_to_expected_values() {
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyNoCents((Integer) null).isPresent());
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyNoCents((Double) null).isPresent());
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyNoCents((String) null).isPresent());

        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents(1).orElse(null));
        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents(1.5).orElse(null));
        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("1,5").orElse(null));

        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents(1.55555).orElse(null));
        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("1,55555").orElse(null));

        Assertions.assertEquals("R$ 10,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents(10.75).orElse(null));
        Assertions.assertEquals("R$ 10,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("10.75").orElse(null));
        Assertions.assertEquals("R$ 1.075,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents(1075).orElse(null));

        Assertions.assertEquals("R$ 100.500,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("100500.99").orElse(null));
        Assertions.assertEquals("R$ 100.500,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("100500.9999").orElse(null));
        Assertions.assertEquals("R$ 100.500,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("100.500.9999").orElse(null));
        Assertions.assertEquals("R$ 100.500,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("100,500.9999").orElse(null));
        Assertions.assertEquals("R$ 10.050.099,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("10050099").orElse(null));

        Assertions.assertEquals("R$ 100.200.300,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("100,200,300,40").orElse(null));
        Assertions.assertEquals("R$ 10.020.030.040,00", StringUtil.formatValueToBrazilianRealCurrencyNoCents("10020030040").orElse(null));
    }

    @Test
    void method_formatValueToBrazilianRealCurrencyWithCents_should_format_inputs_to_expected_values() {
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyWithCents((Integer) null).isPresent());
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyWithCents((Double) null).isPresent());
        Assertions.assertFalse(StringUtil.formatValueToBrazilianRealCurrencyWithCents((String) null).isPresent());

        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents(1.0).orElse(null));
        Assertions.assertEquals("R$ 1,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("1,0").orElse(null));
        Assertions.assertEquals("R$ 100,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents(100).orElse(null));

        Assertions.assertEquals("R$ 1,55", StringUtil.formatValueToBrazilianRealCurrencyWithCents(1.55).orElse(null));
        Assertions.assertEquals("R$ 1,55", StringUtil.formatValueToBrazilianRealCurrencyWithCents("1.544666666667").orElse(null));
        Assertions.assertEquals("R$ 1,56", StringUtil.formatValueToBrazilianRealCurrencyWithCents(1.55555).orElse(null));
        Assertions.assertEquals("R$ 1,56", StringUtil.formatValueToBrazilianRealCurrencyWithCents("1,55555").orElse(null));

        Assertions.assertEquals("R$ 10,75", StringUtil.formatValueToBrazilianRealCurrencyWithCents(10.75).orElse(null));
        Assertions.assertEquals("R$ 10,75", StringUtil.formatValueToBrazilianRealCurrencyWithCents("10.75").orElse(null));
        Assertions.assertEquals("R$ 1.075,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents(1075).orElse(null));

        Assertions.assertEquals("R$ 100.500,99", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100500.99").orElse(null));
        Assertions.assertEquals("R$ 10.050.099,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("10050099").orElse(null));
        Assertions.assertEquals("R$ 100.500.999,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100500999").orElse(null));
        Assertions.assertEquals("R$ 100.501,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100500.9999").orElse(null));
        Assertions.assertEquals("R$ 100.501,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100.500.9999").orElse(null));
        Assertions.assertEquals("R$ 100.501,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100,500.9999").orElse(null));

        Assertions.assertEquals("R$ 100.200.300,40", StringUtil.formatValueToBrazilianRealCurrencyWithCents("100,200,300,40").orElse(null));
        Assertions.assertEquals("R$ 10.020.030.040,00", StringUtil.formatValueToBrazilianRealCurrencyWithCents("10020030040").orElse(null));
    }

    @Test
    void method_removeBeautifulFormatting_should_remove_beautiful_json_from_text() {

        List<HashMap<String, String>> tests = Arrays.asList(// @formatter:off
            new HashMap<String, String>(){{
                put("input", null);
                put("expected", "");
            }},
            new HashMap<String, String>(){{
                put("input", " ");
                put("expected", "");
            }},
            new HashMap<String, String>(){{
                put("input", "{\n" + "  \"input\": {\n" + "    \"text\": \"qual é o melhor dia de pagamento\"\n" + "  },\n }");
                put("expected", "{\"input\": {\"text\": \"qual é o melhor dia de pagamento\"}, }");
            }}
        ); // @formatter:on

        tests.forEach(test -> {

            String returned = StringUtil.removeBeautifulFormatting(test.get("input"));

            Assertions.assertEquals(test.get("expected"), returned);
        });
    }

    @Test
    void method_extractJsonKeyAndValuesFromPrimitiveTypes_should_return_a_list_of_json_key_and_values_as_string()
        throws FileNotFoundException {
        String jsonAsString = "{\"name\":\"John\", \"age\":30, \"car\":null}";
        List<String> expectValues = Arrays.asList("\"name\":\"John\"", "\"age\":30", "\"car\":null");

        List<String> returned = StringUtil.extractJsonKeyAndValuesFromPrimitiveTypes(jsonAsString);

        Assertions.assertEquals(expectValues.size(), returned.size());
        Assertions.assertTrue(expectValues.containsAll(returned));
    }

    @Test
    void method_extractJsonKeyAndValuesFromPrimitiveTypes_should_return_only_a_primitive_values_from_json() throws FileNotFoundException {

        Map<Object, Object> complexJson = new HashMap<Object, Object>() {{
            put("NOT_PRIMITIVE_TYPE", new HashMap<Object, Object>() {{
                put("primitiveType_1", null);
                put("primitiveType_2", "value");
            }});
            put("primitiveType_3", 0.001);
        }};

        List<String> expectValues = Arrays.asList("\"primitiveType_1\":null", "\"primitiveType_2\":\"value\"", "\"primitiveType_3\":0.001");

        String complexJsonAsString = ConverterUtil.toJsonString(complexJson).orElse(null);

        List<String> returned = StringUtil.extractJsonKeyAndValuesFromPrimitiveTypes(complexJsonAsString);

        Assertions.assertEquals(expectValues.size(), returned.size());
        Assertions.assertTrue(expectValues.containsAll(returned));
    }
}
