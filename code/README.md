# CODE HELPERS

My reminders and notes about useful codes in the daily life of a Dev.

- [React](#React)
- [Java](#Java)
   - [Array Instances](#array-instances)
   - [Converter](#converter)
   - [Date](#date)
   - [Encryption](#encryption)
   - [Lambda Functions](#lambda-functions)
   - [Map](#map)
   - [**MAVEN CLI Commands**](#maven-cli-commands)
   - [Mocked Static Method](#mocked-static-method)
   - [Regex](#regex)
   - [String](#string)
- [JavaScript](#JavaScript)
   - [Mocked Functions](#mocked-functions)

---

## [React](#React)

- Eslint start config <br>
  [eslintrc.js](https://github.com/matheusicaro/helpers/blob/master/code/react/eslintrc.js)

---

## [Java](#Java)

### Lambda Functions

```
Supplier        ()      ->  x
Consumer        (x)     ->  ()
Callable        ()      ->  throws ex
Runnable        ()      ->  ()
Function        (x)     ->  y
BiFunction      (x,y)   ->  z
Predicate       (x)     ->  boolean
UnaryOperator   (x1)    ->  x2
BinaryOperator  (x1,x2) ->  x3
```

### Array Instances

```java
var array = Arrays.array(1, 2, 3, 4, 5, 6, 7, 8 ,9)

var array = new ArrayList<>(Collections.singletonList("string")
```

### Map

```java
// Java 8
Map<String, String> doubleBraceMap = new HashMap<String, String>() {{ 
	put("key1", "value1")
	...
}};

// Java 9
Map<String, String> map = Map.of("key1","value1", "key2", "value2");
```

### String

- String utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/string)

[_# sumario_](#sumario)

| METODO                                                 | INPUT                                                 | OUTPUT                                       | OBSERVAÇÃO                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
|--------------------------------------------------------|-------------------------------------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| StringUtil.getOnlyNumbers                              | "string 1123 with 3123 numbers "                      | "11233123"                                   |                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| StringUtil.removeBeautifulFormatting                   | "string with \n break lines \p and \n\n\n spaces."    | "string with break lines and spaces."        | Method to return string without formatting for line breaks and unnecessary spaces, such as JSON beautiful                                                                                                                                                                                                                                                                                                                                                   |
| StringUtil.formatValueToBrazilianRealCurrencyNoCents   | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,00" <br/>"R$ 1,00" | Method to return value formatted to Brazilian Real Currency considering no CENTS between values. (unit-tests)[https://github.com/matheusicaro/helpers/blob/master/code/java/string/StringUtilTest.java#L25]                                                                                                                                                                                           |
| StringUtil.formatValueToBrazilianRealCurrencyWithCents | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,55" <br/>"R$ 1,56" | Method to return value formatted to Brazilian Real Currency considering CENTS between values throuth the last 2 decimal places when the input is a integer number, for the inputs which have decimal cases will be convert to only 2 decimal places. <br> More examples can be found here: (unit-tests)[https://github.com/matheusicaro/helpers/blob/master/code/java/string/StringUtilTest.java#L52] |
| StringUtil.extractJsonKeyAndValuesFromPrimitiveTypes   | Object as String: "{\"key_1\":\"value\",\"key_2\":2}" | [ "\"key_1\":\"value\"" , "\"key_2\":2" ]    | Method to return only key and values from primitive values of OBJECT                                                                                                                                                                                                                                                                                                                                                                                         |


### Regex

- String utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/regex)

| REGEX                         | STRING                                                                                          | MATCH                                                | OBSERVAÇÃO                                                    |
| ----------------------------- | ----------------------------------------------------------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------- |
| MATCH_ALL_BUT_NO_ONE_NUMBER   | "@@ F5 F1 $#H5 sfs321 DS"                                                                       | [ "@@ F F $#H sfs DS" ]                              | RRegex to identify any characters except for digit character. |
| MATCH_NUMBER                  | "F5 F1 H5 sfs321 DS"                                                                            | [ "5" ]                                              | Regex to identify the first number digit                      |
| MATCH_ALL_NUMBERS             | "F5 F1 H5 sfs321 DS"                                                                            | [ "5 1 5 321" ]                                      | Regex to identify all number digits.                          |
| MATCH_LETTER                  | "F5 F1 H5 sfs321 DS"                                                                            | [ "F F1 H5 sfs321 DS" ]                              | Regex to identify any first letter upper or lower case.       |
| MATCH_ALL_LETTERS             | "F5 F1 H5 sfs321 DS"                                                                            | [ "F F H sfs DS" ]                                   | Regex to identify any letter upper or lower case.             |
| MATCH_ANY_CHAR                | "# $ & ? % 55 Ft SFS321"                                                                        | [ 5 ]                                                | Regex to identify any first character like number or letter   |
| MATCH_ALL_ANY_CHAR            | "# $ & ? % 55 Ft SFS321"                                                                        | [ 55, Ft, SFS321 ]                                   | Regex to identify any characters like numbers or letters.     |
| MATCH_SPECIAL_CHAR            | "$ome #ok @thor %& +!behind \_\*? 55 Ft SFS321"                                                 | [ $ ]                                                | Regex to identify first special character.                    |
| MATCH_ALL_SPECIAL_CHAR        | "$ome #ok @thor %& +!behind \_\*? 55 Ft SFS321"                                                 | [ $, #, @, %, &, +, !, _, *, ? ]                     | Regex to identify all special characters.                     |
| MATCH_QUOTATION_MARKS         | "regular 'some' "expression" test"                                                              | [ "regular some' "expression" test" ]                | Regex to identify first quotation marks.                      |
| MATCH_ALL_QUOTATION_MARKS     | "regular 'some' "expression" test"                                                              | [ "regular some expression test" ]                   | Regex to all identify quotation marks.                        |
| MATCH_BREAK_LINES             | "regular \n'some'\n \"expression\"\n\n test"                                                    | [ \n ]                                               | Regex to identify first break of line.                        |
| MATCH_ALL_BREAK_LINES         | "regular \n'some'\n \"expression\"\n\n test"                                                    | [ \n, \n, \n, \n, ]                                  | Regex to identify all break of lines.                         |
| MATCH_SPACES_CONSECUTIVELY    | "regular 'some' \"expression\"\n\n test"                                                        | [ "regular 'some' \"expression\" test" ]             | Regex to identify spaces.                                     |
| MATCH_SLASH                   | "https://www.url.com/my-account/55580/send/email"                                               | [ "https:/www.url.com/my-account/55580/send/email" ] | Regex to identify first slash.                                |
| MATCH_ALL_SLASH               | "https://www.url.com/my-account/55580/send/email"                                               | [ "https:www.url.commy-account55580sendemail" ]      | Regex to identify all slash.                                  |
| MATCH_FIRST_URL_NUMERIC_PARAM | "https://www.url.com/my-account/55580/send/email-id/00000150/changing-password"                 | [ /55580/ ]                                          | Regex to identify first URL numeric params.                   |
| MATCH_ALL_URL_NUMERIC_PARAMS  | "https://www.url.com/my-account/55580/send/email-id/00000150/changing-password"                 | [ /55580/, /00000150/]                               | Regex to identify all URL numeric params.                     |
| MATCH_JSON_KEY_AND_VALUE      | "this is a simple string with {'key':'value', 'key_2':'value', "key":"value"} to test on regex" | [ 'key':'value', 'key_2':'value', "key":"value" ]    | Regex to identify key and values                              |


### Date

- Files [here](https://github.com/matheusicaro/helpers/blob/master/code/java/date)

| METHOD                                  | INPUT                            | OUTPUT         | OBSERVATION |
| --------------------------------------- | -------------------------------- | -------------- | ---------- |
| DateUtil.**isDateBeforeCurrentDateNow** | "22/04/1500"                     | true           |            |
| DateUtil.**isDateBeforeCurrentDateNow** | new OffsetDateTime("22/04/1500") | true           |            |
| DateUtil.**isDateAfterCurrentDateNow**  | new OffsetDateTime("22/04/1500") | false          |            |
| DateUtil.**buildDateTimeFrom**          | ( new Date(), DateFormat )       | OffsetDateTime |            |
| DateUtil.**buildDateTimeFrom**          | ( timestamp )                    | OffsetDateTime |            |
| DateUtil.**buildDateTimeFrom**          | ( new Date() )                   | OffsetDateTime |            |
| DateUtil.**convertToDateTime**          | "10/02/2000"                     | OffsetDateTime |            |


### Encryption

- Files [here](https://github.com/matheusicaro/helpers/blob/master/code/java/encryption)


| METHOD                          | INPUT                        | OUTPUT                                                                                            |
|---------------------------------|------------------------------| ------------------------------------------------------------------------------------------------- |
| Encryption.INSTANCE.encrypt()   | "valueHere"                  | "enc_W1RoGd7Fqv0AlbKc4orUUA=="                                                                    |
| Encryption.INSTANCE.decrypt()   | "enc_W1RoGd7Fqv0AlbKc4orUUA" | "valueHere"                                                                                       |
| Encryption.INSTANCE.decrypt()   | "valueHere"                  | @throws EncryptionException("Encryption error (Operation: 'value is unknown or is not encrypted") |
| Encryption.INSTANCE.isEncrypt() | "valueHere"                  | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "W1RoGd7Fqv0AlbKc4orUUA"     | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "enc_W1RoGd7Fqv0AlbKc4orUUA" | true                                                                                              |


### Converter

- Files [here](https://github.com/matheusicaro/helpers/blob/master/code/java/converter)


| METHOD                                 | INPUT                                                  | OUTPUT                              | OBSERVATION |
|----------------------------------------| ------------------------------------------------------ | ----------------------------------- | ---------- |
| ConverterUtil.toJsonStringNoBeautiful  | new Map("key", "value \t with \n \n\ \n format chars") | "{'key':'value with format chars'}" |            |
| ConverterUtil.toJsonStringNoBeautiful  | new Map("key", "value")                                | "{'key':'value'}"                   |            |
| ConverterUtil.from**                   | ( "{'key':'value'}", Map.class )                       | new Map("key", "value")             |            |


### Mocked Static Method

- Install [Mockito Core dependency](https://mvnrepository.com/artifact/org.mockito/mockito-core) from 3.8 version.

```xml
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>3.12.4</version>
    </dependency>
```

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StaticClassTest {

    static MockedStatic<StaticClasse> classMockedStatic;

    @BeforeEach
    void setupTest() {
        classMockedStatic = Mockito.mockStatic(StaticClasse.class);
    }

    @AfterEach
    void after() {
        classMockedStatic.close();
    }

    @Test
    void test_description() {

        classMockedStatic.when(() -> StaticClasse.method(Mockito.any())).thenReturn("empty");		// mock method with return
        classMockedStatic.when(StaticClasse::method).thenThrow(new Exception());			        // mock void method

        ArgumentCaptor<String> captorInput = ArgumentCaptor.forClass(String.class);
 
        classMockedStatic.verify(() -> StaticClasse.method(captorInput.capture());

        Assertions.assertEquals("expected", captorInput.getValue());
    }
}
```

### MAVEN CLI Commands

```powershell
# clean and install with trace log
mvn clean install > log-file.log
```

---

## [JavaScript](#JavaScript)

### Mocked Functions

```js
import Service from '../../services';

jest.mock('some-npm-module');	// mock expected services dir file
import ExternalService from 'some-npm-module';	// import expected services dir file

describe("...", () => {
	test("...", async () => {

		// when the method from the service is called, return what I want to.
		// object, function, exception, etc...
		ExternalService.method = jest.fn().mockImplementation(() => {
			return myObject;
		});

		const returned = Service.run();

		expect(Service.method).toHaveBeenCalledTimes(1);
		expect(Service.method).toHaveBeenCalledWith("params_1", "params_2");
		expect(returned).toBe(expected);
	});
});

```
