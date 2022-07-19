# CODE HELPERS

My reminders and notes about useful codes in the daily life of a Dev.

- [Mongo](#mongo)
    - [Queries](#queries)
- [React](#React)
- [JavaScript](#javascript)
    - [Mocked Functions](#mocked-functions)
    - [JEST - Tips](#jest---tips)
- [Java](#Java)
    - [Array Instances](#array-instances)
    - [Converter](#converter)
    - [Certificate CA SSL Importing JAVA Cacerts](#certificate-ca-ssl-importing-java-cacerts)
    - [Date](#date)
    - [Encryption](#encryption)
    - [Kafka Implementation](#kafka-implementation)
    - [Lambda Functions](#lambda-functions)
    - [Map](#map)
    - [**MAVEN CLI Commands**](#maven-cli-commands)
    - [Mocked Static Method](#mocked-static-method)
    - [Regex](#regex)
    - [String](#string)

---

## Mongo

### Queries

- find object by ID and return some attributes:

```js
const id = "d27c58065d058065f2d7d2df";
const POSITIVE_BINARY_VALUE = 1;
const attributesToBeReturned = {_id: POSITIVE_BINARY_VALUE, status: POSITIVE_BINARY_VALUE, createdAt: POSITIVE_BINARY_VALUE }

db.getCollection("my_collection").find({_id: ObjectId(id)}, attributesToBeReturned)
```

---

## [React](#React)

- Eslint start config
    - [eslintrc.js](https://github.com/matheusicaro/private-helpers/master/code/react/eslintrc.js)
- [Stack React Apps](#stack-react-apps)
    - [Inter](#inter)

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

### JEST - Tips


##### Run just one file using only JEST no script

1. Use WSL
2. install jest globaly
3. run:
```powershell
jest --runTestsByPath "path/my-file.test.ts"
```

##### Test specific file:

```shell
# FIRST: Needs to change de SLASH bar when it is copied by clicking on the right mouse bottom on "COPY RELATIVE PATH" from VS CODE

jest --runTestsByPath "my_path/../my_file_here_.test.ts"
# OR
npm test --runTestsByPath "my_path/../my_file_here_.test.ts"
```

##### Assert for Date time now, new Date()

```js
const input = new Date();

...

expect(input).toBe(
    expect.any(Date)
);

```

##### Assert TO TROW EXEPTIONS

```js
    test('should throw an error', async () => {
        
    await expect(funct.method(input)).rejects.toThrow(
        'It should be the same as this message here from the thrown exception'
    );
});
```

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

- String utils class [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/string)

[_# sumario_](#sumario)

| METODO                                                 | INPUT                                                 | OUTPUT                                       | OBSERVAÇÃO                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
|--------------------------------------------------------|-------------------------------------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| StringUtil.getOnlyNumbers                              | "string 1123 with 3123 numbers "                      | "11233123"                                   |                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| StringUtil.removeBeautifulFormatting                   | "string with \n break lines \p and \n\n\n spaces."    | "string with break lines and spaces."        | Method to return string without formatting for line breaks and unnecessary spaces, such as JSON beautiful                                                                                                                                                                                                                                                                                                                                                   |
| StringUtil.formatValueToBrazilianRealCurrencyNoCents   | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,00" <br/>"R$ 1,00" | Method to return value formatted to Brazilian Real Currency considering no CENTS between values. (unit-tests)[https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/string/StringUtilTest.java#L25]                                                                                                                                                                                           |
| StringUtil.formatValueToBrazilianRealCurrencyWithCents | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,55" <br/>"R$ 1,56" | Method to return value formatted to Brazilian Real Currency considering CENTS between values throuth the last 2 decimal places when the input is a integer number, for the inputs which have decimal cases will be convert to only 2 decimal places. <br> More examples can be found here: (unit-tests)[https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/string/StringUtilTest.java#L52] |
| StringUtil.extractJsonKeyAndValuesFromPrimitiveTypes   | Object as String: "{\"key_1\":\"value\",\"key_2\":2}" | [ "\"key_1\":\"value\"" , "\"key_2\":2" ]    | Method to return only key and values from primitive values of OBJECT                                                                                                                                                                                                                                                                                                                                                                                         |


### Regex

- String utils class [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/regex)

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

- Files [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/date)

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

- Files [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/encryption)


| METHOD                          | INPUT                        | OUTPUT                                                                                            |
|---------------------------------|------------------------------| ------------------------------------------------------------------------------------------------- |
| Encryption.INSTANCE.encrypt()   | "valueHere"                  | "enc_W1RoGd7Fqv0AlbKc4orUUA=="                                                                    |
| Encryption.INSTANCE.decrypt()   | "enc_W1RoGd7Fqv0AlbKc4orUUA" | "valueHere"                                                                                       |
| Encryption.INSTANCE.decrypt()   | "valueHere"                  | @throws EncryptionException("Encryption error (Operation: 'value is unknown or is not encrypted") |
| Encryption.INSTANCE.isEncrypt() | "valueHere"                  | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "W1RoGd7Fqv0AlbKc4orUUA"     | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "enc_W1RoGd7Fqv0AlbKc4orUUA" | true                                                                                              |


### Kafka Implementation

- Files [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/kafka)



### Converter

- Files [here](https://raw.githubusercontent.com/matheusicaro/private-helpers/master/code/java/converter)


| METHOD                                 | INPUT                                                  | OUTPUT                              | OBSERVATION |
|----------------------------------------| ------------------------------------------------------ | ----------------------------------- | ---------- |
| ConverterUtil.toJsonStringNoBeautiful  | new Map("key", "value \t with \n \n\ \n format chars") | "{'key':'value with format chars'}" |            |
| ConverterUtil.toJsonStringNoBeautiful  | new Map("key", "value")                                | "{'key':'value'}"                   |            |
| ConverterUtil.from**                   | ( "{'key':'value'}", Map.class )                       | new Map("key", "value")             |            |


### Certificate CA SSL Importing JAVA Cacerts

When is necessary import Certificate SSL, should use the script below:

```bash

#!/bin/bash

VERSION=1.0.0
USAGE="./import-certificates.sh"

#java versions jdk-11.0.7.jdk   jdk1.7.0_80.jdk  jdk1.8.0_171.jdk

# ====> TO JAVA 8<
# /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/lib/security

# ====> TO JAVA 11+
# /Library/Java/JavaVirtualMachines/jdk-11.0.7.jdk/Contents/Home/lib/security

# HERE IS BEING CLONED THE CERTIFICATES AVAILABLE AT GIT REPOSITORY
git clone --single-branch --branch master https://gitlab.sharedservices.local/XXXXXXX/certificates.git

java_path="/Library/Java/JavaVirtualMachines"
certificates_path="$(pwd)/certificates/ca"

certificates=($certificates_path/*)
java_versions=($java_path/*)

for j in "${java_versions[@]}"; do
    for c in "${certificates[@]}"; do
        java_verion=$(basename $j)
        certificate_alias=$(basename "${c%.*}")
        certificate=$(basename $c)
        echo "Installing certificate $certificate for java $java_verion"
        if [[ $java_verion =~ jdk([0-9]+).([0-9]+).([0-9_]+) ]]; then
            sudo keytool -import -file $c -alias $certificate_alias -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/lib/security/cacerts -storepass changeit -trustcacerts -noprompt
        elif [[ $java_verion =~ jdk-([0-9]+).([0-9]+).([0-9_]+) ]]; then
            sudo keytool -import -file $c -alias $certificate_alias -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/lib/security/cacerts -storepass changeit -trustcacerts -noprompt
        else
            echo "No supported java version found"
        fi
    done
done

rm -rf certificates
```


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

# REACT

## Stack React Apps

### Inter

![i-app-stack-1](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-1.png?raw=true)

![i-app-stack-2](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-2.png?raw=true)

- A funcao lambda pode atuar em 4 pontos diferentes
    1 - no VIEWER-REQUEST quando a solicitação esta chegando antes de ser bater no cloud-front
    2 - no ORIGIN-REQUEST quando já passou do cloud-front e antes de buscar no bucker S3
    3 - no ORIGIN-RESPONSE após ser retornado os dado do bucket S3
    4 - no VIEWER-RESPONSE após passar pelo cloud-front e antes de ser retornado para o user

 ![i-app-stack-3](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-3.png?raw=true)

- Processo de deploy por feature para cada branch, onde que o nome da branch gera o hash do qual fica como nome da pasta no S3 e tambem no prefixo de endereço do host:

 ![i-app-stack-4](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-4.png?raw=true)


- A manipulaçao para buscar diferentes versoes de site em pastas no bucker é feito por uma LAMBDA:

 ![i-app-stack-5](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-5.png?raw=true)
