# CODE HELPERS

My reminders and notes about useful codes in the daily life of a Dev.

1. [React](#React)
2. [Java](#Java)
    - [Lambda Functions](#lambda-functions)
    - [Array Instances](#array-instances)
    - [Map](#map)
    - [String](#string)
    - [Date](#date)

---

## [React](#React)

- Eslint start config <br>
  [eslintrc.js](https://github.com/matheusicaro/helpers/blob/master/code/react/eslintrc.js)

---

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

## [Java](#Java)

### Array Instances

`var array = Arrays.array(1, 2, 3, 4, 5, 6, 7, 8 ,9)`<br>
`var array = new ArrayList<>(Collections.singletonList("string")`

### Map

> Java 8 - `Map<String, String> doubleBraceMap = new HashMap<String, String>() {{ put("key1", "value1")...}};`<br>

> Java 9 - `Map<String, String> map = Map.of("key1","value1", "key2", "value2");`

### String

- String utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/StringUtils.java)

### Date

- Date utils class [here](https://github.com/matheusicaro/helpers/blob/master/code/java/DateUtils.java)