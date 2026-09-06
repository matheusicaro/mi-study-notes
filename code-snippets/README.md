# CODE HELPERS

My reminders and notes about useful codes in the daily life of a Dev.

- [Mongo](#mongo)
  - [Queries](#queries)
- [SQL / Databases](#sql--databases)
  - [SQL vs NoSQL](#sql-vs-nosql)
  - [Elasticsearch](#elasticsearch)
  - [SQL basics](#sql-basics)
  - [PostgreSQL vs MySQL](#postgresql-vs-mysql)
- [React](#React)
- [JavaScript](#javascript)
  - [_Update dependencies with Vulnerability_](#update-dependencies-with-vulnerability)
  - [Add script GLOBALLY](#add-script-globally)
  - [Mocked Functions](#mocked-functions)
  - [JEST - Tips](#jest---tips)
- [Java](#Java)
  - [Array Instances](#array-instances)
  - [Converter](#converter)
  - [Certificate CA SSL Importing JAVA Cacerts](#certificate-ca-ssl-importing-java-cacerts)
  - [Date](#date)
  - [Encryption](#encryption)
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
const attributesToBeReturned = {
  _id: POSITIVE_BINARY_VALUE,
  status: POSITIVE_BINARY_VALUE,
  createdAt: POSITIVE_BINARY_VALUE,
};

db.getCollection("my_collection").find({ _id: ObjectId(id) }, attributesToBeReturned);
```

- find object by ATTRIBUTE not NULL/UNDEFINED ID:

```js
// single condition
db.getCollection("my_collection").find({ my_attribute_name: {$exists: true} })

// multiple conditions
db.getCollection("my_collection").find($and: [ {first_attribute: {$exists: true}}, {second_attribute: 'some value'} ] )

```

---

## SQL / Databases

### SQL vs NoSQL

| | Use when |
| --- | --- |
| **SQL** (PostgreSQL, MySQL) | Structured data, clear relationships, consistency, complex queries — banking, inventory |
| **NoSQL** (MongoDB, Redis, Cassandra) | Flexible/changing schema, relationships not strictly consistent — social media, big data, real-time |

### Elasticsearch

Search engine built for large volumes of data with near real-time full-text search and analytics.

- **Real-time search** — data searchable almost immediately after indexing
- **Schema-free** — auto-detects structure, but you can define mappings for control/performance
- **RESTful API** — GET/POST/PUT/DELETE over HTTP for queries, inserts, config
- **Analytics** — aggregations (like SQL `GROUP BY`) for real-time analysis
- **Elastic Stack (ELK)**: Elasticsearch + **Logstash** (ingest/transform pipeline) + **Kibana** (dashboards) + **Beats** (lightweight log shippers)

### SQL basics

| Term | Definition | Example |
| --- | --- | --- |
| **Primary key** | Unique identifier per record | `client_id` in Users |
| **Foreign key** | Links to another table's primary key | `user_id` in Cards → Users |
| **Unique key** | Enforces a unique value per column | `email` in Users |

**Indexes**: faster `SELECT`, slower `INSERT`/`UPDATE`.

**Normalization** — organize data to reduce redundancy/dependency:

```
# Before (unnormalized — repeats customer/product data per row)
OrderID | CustomerName | CustomerPhone | ProductName | ProductPrice | Quantity | OrderDate
1       | John Doe     | 555-1234      | Laptop      | 1200.00      | 1        | 2025-03-20
1       | John Doe     | 555-1234      | Mouse       | 25.00        | 2        | 2025-03-20

# After (normalized — split into two tables)
Orders:        OrderID | CustomerName | CustomerPhone | OrderDate
OrderDetails:  OrderID | ProductName  | ProductPrice  | Quantity
```

**Denormalization**: merge tables back to improve read performance (fewer joins) — worth it when joins are large/complex and reads vastly outnumber writes.

**JOIN vs subquery**: JOIN combines rows from 2+ tables on a related column; a subquery nests a query inside another. JOIN is generally more efficient than an equivalent subquery.

| Join | Returns |
| --- | --- |
| **INNER** | Only matching rows in both tables |
| **LEFT** | All left rows + matches from right (NULL if none) |
| **RIGHT** | All right rows + matches from left (NULL if none) |

**Aggregate functions**: `COUNT()`, `SUM()`, `AVG()`, `MAX()`, `MIN()`.
`GROUP BY` groups rows into summary rows (e.g. avg salary per department). `HAVING` filters *after* `GROUP BY` (e.g. `HAVING AVG(salary) > 50000`).

**2nd highest salary** — two idiomatic ways:

```sql
SELECT MAX(salary) AS SecondHighestSalary
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);

-- or
SELECT salary FROM employees ORDER BY salary DESC LIMIT 1 OFFSET 1;
```

### PostgreSQL vs MySQL

| | PostgreSQL | MySQL |
| --- | --- | --- |
| Strengths | ACID-compliant, advanced types (JSONB, HSTORE), full-text search, extensible | Fast, simple, widely used in web apps |
| Trade-off | More feature-rich for complex queries | Easier to set up/manage, fewer advanced features |

---

## [React](#React)

- Eslint start config
  - [eslintrc.js](https://github.com/matheusicaro/private-helpers/blob/master/guides/react/eslintrc.js)

---

## [JavaScript](#JavaScript)

### Update dependencies with Vulnerability

1. run `npm ls +lib` to show what package its has been used:
```js
➜  $: npm ls serve-static  
my-service@1.0.0 /Users/me/DEVELOPMENT/repositories/my-org/my-service
└─┬ @apollo/server@4.9.4
  └─┬ express@4.18.2
    └── serve-static@1.15.0
```
2. run `npm upgrade socks` to fix it automatically, otherwise you need to fix manul :(
```
➜  $: npm upgrade socks 

├─┬ @my-org/framework@6.1.10
│ ├─┬ @my-org/graphql-tools@17.2.2
│ │ └─┬ @apollo/gateway@2.9.2
│ │   └─┬ make-fetch-happen@11.1.1
│ │     └─┬ socks-proxy-agent@7.0.0
│ │       └── socks@2.7.1 deduped <===============
│ ├─┬ @my-org/job@3.2.0
│ │ └─┬ mongodb@5.9.2
│ │   └── socks@2.7.1 deduped <===============
│ └─┬ @my-org/mongo@13.2.2
│   └─┬ mongoose@7.8.1
│     └─┬ mongodb@5.9.2
│       └── socks@2.7.1 deduped <===============
├─┬ @my-org/migration@5.0.0-rc.2
│ └─┬ mongodb@5.7.0
│   └── socks@2.7.1
└─┬ mongodb-memory-server-core@8.15.1
  └─┬ mongodb@4.17.2
    └── socks@2.7.1 deduped <===============
```

### Add Script GLOBALLY

When receive the error: `'yarn' is not recognized as an internal or external command, operable program or batch file.`
<br>
Just add the script globally in the environment:

1. Install the package globally ==> `npm uninstall -g <package-name>`
2. Add the npm global modules in the environment path.

```shell
# common npm global modules path

C:\Users\Administrator\AppData\Roaming\npm
```

### JEST - Tips

#### Run just one file using only JEST no script

1. Use WSL
2. install jest globaly
3. run:

```powershell
jest --runTestsByPath "path/my-file.test.ts"
```

#### Test specific file:

```shell
# FIRST: Needs to change de SLASH bar when it is copied by clicking on the right mouse bottom on "COPY RELATIVE PATH" from VS CODE

jest --runTestsByPath "my_path/../my_file_here_.test.ts"
# OR
npm test --runTestsByPath "my_path/../my_file_here_.test.ts"
```

#### Assert for Date time now, new Date()

```js
const input = new Date();

...

expect(input).toBe(
    expect.any(Date)
);

```

#### Assert TO TROW EXEPTIONS

```js
test("should throw an error", async () => {
  await expect(funct.method(input)).rejects.toThrow(
    "It should be the same as this message here from the thrown exception"
  );
});
```

#### Mocked Functions

##### Mocked by JEST

OP_1)

```js
import * as NameModule from './module';

jest.mock('./module');

[...]

jest.spyOn(NameModule, 'fuction-name').mockReturnValueOnce(null);
```

OP_2)

```js
import Service from "../../services";

jest.mock("some-npm-module"); // mock expected services dir file
import ExternalService from "some-npm-module"; // import expected services dir file

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

##### Mocked by VITEST

```js
import { vi } from 'vitest';

import * as NameModule from './module';

vi.mock('./module');

[...]

vi.spyOn(NameModule, 'fuction-name').mockReturnValueOnce(null);

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

- String utils class [here](/code-snippets/java/string/)

[_# sumario_](#sumario)

| METODO                                                 | INPUT                                                 | OUTPUT                                       | OBSERVAÇÃO                                                                                                                                                                                                                                                                                                                                         |
| ------------------------------------------------------ | ----------------------------------------------------- | -------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| StringUtil.getOnlyNumbers                              | "string 1123 with 3123 numbers "                      | "11233123"                                   |                                                                                                                                                                                                                                                                                                                                                    |
| StringUtil.removeBeautifulFormatting                   | "string with \n break lines \p and \n\n\n spaces."    | "string with break lines and spaces."        | Method to return string without formatting for line breaks and unnecessary spaces, such as JSON beautiful                                                                                                                                                                                                                                          |
| StringUtil.formatValueToBrazilianRealCurrencyNoCents   | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,00" <br/>"R$ 1,00" | Method to return value formatted to Brazilian Real Currency considering no CENTS between values. (unit-tests)[/code-snippets/java/string/StringUtilTest.java#L25]                                                                                                                                                                                           |
| StringUtil.formatValueToBrazilianRealCurrencyWithCents | "19999" <br/>"1.55" <br/>"1.55555"                    | "R$ 19.999,00" <br/>"R$ 1,55" <br/>"R$ 1,56" | Method to return value formatted to Brazilian Real Currency considering CENTS between values throuth the last 2 decimal places when the input is a integer number, for the inputs which have decimal cases will be convert to only 2 decimal places. <br> More examples can be found here: (unit-tests)[/code-snippets/java/string/StringUtilTest.java#L52] |
| StringUtil.extractJsonKeyAndValuesFromPrimitiveTypes   | Object as String: "{\"key_1\":\"value\",\"key_2\":2}" | [ "\"key_1\":\"value\"" , "\"key_2\":2" ]    | Method to return only key and values from primitive values of OBJECT                                                                                                                                                                                                                                                                               |

### Regex

- String utils class [here](/code-snippets/java/regex)

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

- Files [here](/code-snippets/java/date)

| METHOD                                  | INPUT                            | OUTPUT         | OBSERVATION |
| --------------------------------------- | -------------------------------- | -------------- | ----------- |
| DateUtil.**isDateBeforeCurrentDateNow** | "22/04/1500"                     | true           |             |
| DateUtil.**isDateBeforeCurrentDateNow** | new OffsetDateTime("22/04/1500") | true           |             |
| DateUtil.**isDateAfterCurrentDateNow**  | new OffsetDateTime("22/04/1500") | false          |             |
| DateUtil.**buildDateTimeFrom**          | ( new Date(), DateFormat )       | OffsetDateTime |             |
| DateUtil.**buildDateTimeFrom**          | ( timestamp )                    | OffsetDateTime |             |
| DateUtil.**buildDateTimeFrom**          | ( new Date() )                   | OffsetDateTime |             |
| DateUtil.**convertToDateTime**          | "10/02/2000"                     | OffsetDateTime |             |

### Encryption

- Files [here](/code-snippets/java/encryption)

| METHOD                          | INPUT                        | OUTPUT                                                                                            |
| ------------------------------- | ---------------------------- | ------------------------------------------------------------------------------------------------- |
| Encryption.INSTANCE.encrypt()   | "valueHere"                  | "enc_W1RoGd7Fqv0AlbKc4orUUA=="                                                                    |
| Encryption.INSTANCE.decrypt()   | "enc_W1RoGd7Fqv0AlbKc4orUUA" | "valueHere"                                                                                       |
| Encryption.INSTANCE.decrypt()   | "valueHere"                  | @throws EncryptionException("Encryption error (Operation: 'value is unknown or is not encrypted") |
| Encryption.INSTANCE.isEncrypt() | "valueHere"                  | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "W1RoGd7Fqv0AlbKc4orUUA"     | false                                                                                             |
| Encryption.INSTANCE.isEncrypt() | "enc_W1RoGd7Fqv0AlbKc4orUUA" | true                                                                                              |

### Converter

- Files [here](/code-snippets/java/converter)

| METHOD                                | INPUT                                                  | OUTPUT                              | OBSERVATION |
| ------------------------------------- | ------------------------------------------------------ | ----------------------------------- | ----------- |
| ConverterUtil.toJsonStringNoBeautiful | new Map("key", "value \t with \n \n\ \n format chars") | "{'key':'value with format chars'}" |             |
| ConverterUtil.toJsonStringNoBeautiful | new Map("key", "value")                                | "{'key':'value'}"                   |             |
| ConverterUtil.from\*\*                | ( "{'key':'value'}", Map.class )                       | new Map("key", "value")             |             |

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

