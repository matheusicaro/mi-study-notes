# CODE HELPERS

My reminders and notes about useful codes in the daily life of a Dev.

- [React](#React)
- [Java](#Java)
   - [Lambda Functions](#lambda-functions)
   - [Array Instances](#array-instances)
   - [Map](#map)
   - [String](#string)
   - [Date](#date)
   - [Mocked Static Method](#mocked-static-method)
   - [**MAVEN CLI Commands**](#maven-cli-commands)
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

> Java 8 - `Map<String, String> doubleBraceMap = new HashMap<String, String>() {{ put("key1", "value1")...}};`<br>

> Java 9 - `Map<String, String> map = Map.of("key1","value1", "key2", "value2");`

### String

- String utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/StringUtils.java)

### Date

- Date utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/DateUtils.java)

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
