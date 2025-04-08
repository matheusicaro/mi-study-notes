# Summary

- [Summary](#summary)
- [PROGRAMMING ORIENTATED BY OBJECTS](#programming-orientated-by-objects)
  - [SOLID](#solid)
          - [-](#-)
- [STRUCTURAL patterns](#structural-patterns)
  - [Adapter](#adapter)
  - [Bridge](#bridge)
  - [Composite](#composite)
  - [Facade](#facade)
  - [Decorator](#decorator)
          - [-](#--1)
- [BEHAVIORAL patterns](#behavioral-patterns)
  - [Strategy](#strategy)
  - [Observer](#observer)
  - [Template Method](#template-method)
          - [-](#--2)
- [CREATIONAL patterns](#creational-patterns)
  - [Abstract-factory](#abstract-factory)
  - [Builder](#builder)
  - [Factory-method](#factory-method)
  - [Singleton](#singleton)

<Br>
<br>
<br>

# PROGRAMMING ORIENTATED BY OBJECTS

## SOLID

A class should:

1. [S] **SINGLE RESPONSIBILITY**: Do or manage only one THING! Doesn't mean that we should have only one method, but means that everything should be strongly linked

<details><summary>Example: </summary>

```typescript
/******  BAD  *************************************************
 *
 * The UserService class is responsible for managing user data as well as handling email functionality.
 * These two tasks are unrelated and should be separated.
 * If the email service changes, it would affect the UserService class, violating SRP.
 *
 */
class UserService {
  private users: string[] = [];

  // Manages user data
  addUser();

  // Sends email (This is an unrelated responsibility)
  sendEmail();

  // More methods to handle both user and email concerns...
}

/******  GOD  *************************************************
 *
 * The UserService class is solely responsible for user management.
 * The EmailService class is responsible only for sending emails.
 *
 */
// Handles user data management
class UserService {
  private users: string[] = [];

  addUser();
  // ...Additional methods for managing users...
}

// Handles email functionality separately
class EmailService {
  sendEmail();
}

// Usage
```

</details>

2. [O] **OPEN / CLOSE**: A class should be open for extensions but closed for modifications.

<details><summary>Example: </summary>

```typescript
/******  BAD  *************************************************
 *
 * The class AreaCalculator is closed for modification.
 * When a new shape (e.g., a triangle) is added, you have to modify the calculateArea method.
 * This approach violates the Open/Closed Principle, which states that classes should be open for extension but closed for modification.
 *
 */
class AreaCalculator {
  calculateArea(shape: string): number {
    if (shape === "circle") {
      return; // Circle area calculation
    } else if (shape === "square") {
      return; // Square area calculation
    } else if (shape === "rectangle") {
      return; // Rectangle area calculation
    }
    throw new Error("Unknown shape");
  }
}

/******  GOD  *************************************************
 *
 * Open for Extension: We can add new shapes (like Triangle, Polygon, etc.) without modifying existing code.
 * Closed for Modification: The AreaCalculator class doesn’t need to be modified when new shapes are introduced.
 *
 */
//
// Base class Shape - it defines the contract for all shapes
abstract class Shape {
  // calculate the area
  abstract calculateArea();
}

// Circle class - extends Shape and provides implementation
class Circle extends Shape {
  private radius: number;

  calculateArea();
}

// Square class - extends Shape and provides implementation
class Square extends Shape {
  private side: number;

  calculateArea();
}

// AreaCalculator doesn't need to be modified if a new shape is added
class AreaCalculator {
  calculateArea();
}

const circle = new Circle(10);
const square = new Square(10);
const calculator = new AreaCalculator();
console.log(calculator.calculateArea(circle)); // 314.159...
console.log(calculator.calculateArea(square)); // 100
```

</details>

3. [S] **LISKOV SUBSTITUTION**: Is about ensuring that subclasses (or implementations) can be used interchangeably with their parent classes or interfaces without altering the expected behavior.

<details><summary>Example: </summary>

```typescript
/******  BAD  *************************************************
 *
 * The text explains that both Rectangle and Square now implement a common Shape interface
 * with a getArea() method, allowing them to be used interchangeably.
 * Rectangle has independent width and height, while Square uses a single side length.
 * Both classes adhere to the Liskov Substitution Principle by implementing getArea() for consistent behavior.
 *
 */
class Rectangle {
  protected width: number;
  protected height: number;

  setWidth()

  setHeight()

  getArea() { return this.width * this.height }
}

class Square extends Rectangle {
  constructor(sideLength: number) {
    super(sideLength, sideLength);
  }

  // Overriding the behavior to maintain square's constraint (width and height should be equal)
  setWidth(width: number) {
    this.width = width;
    this.height = width;  // In a square, width and height must always be the same
  }

  setHeight(height: number) {
    this.height = height;
    this.width = height;  // In a square, width and height must always be the same
  }
}

const calculateArea = (rectangle: Rectangle) => {
  rectangle.setWidth(5);
  rectangle.setHeight(10);
  return rectangle.getArea();
}

const rect = new Rectangle(5, 10);
const square = new Square(5);
console.log(calculateArea(rect));    // 50
console.log(calculateArea(square));  // 25
//
//
/******  GOD  *************************************************
 *
 * Instead of having Square extend Rectangle, both classes now implement a common Shape interface with a getArea() method.
 * This allows any Shape (like Rectangle or Square) to be used interchangeably without breaking functionality.
 * Rectangle allows independent width and height, while Square uses a single side length.
 * Both classes implement getArea(), ensuring consistent behavior and adhering to the Liskov Substitution Principle.
 *
 */

// Base class or interface for all shapes
interface Shape {
  getArea()
}

class Rectangle implements Shape {
  protected width: number;
  protected height: number;

  setWidth(width) 

  setHeight(height) 

  getArea() { return this.width * this.height }
}

class Square implements Shape {
  private sideLength: number;

  setSideLength(sideLength: number)

  getArea() { return this.sideLength * this.sideLength }
}

// A function to calculate area that accepts any Shape
function calculateArea(shape: Shape): number {
  return shape.getArea();
}

const rect = new Rectangle(5, 10);
const square = new Square(5);
console.log(calculateArea(rect));    // 50
console.log(calculateArea(square));  // 25

```

</details>

4. [I] **INTERFACE SEGREGATION**: The principle suggests that no client should be forced to depend on methods it does not use. In other words, you should create smaller, more specific interfaces rather than one large, general interface.

<details><summary>Example: </summary>

```typescript
/******  BAD  *************************************************
 *
 *
 */
interface RepositoryInterface {
  save();

  delete();

  findAll();
}

class Repository implements RepositoryInterface {
  save() { do something }
  delete() { do something }

  findAll() { throw } // You can't TRUST on this
}

/******  GOD  *************************************************
 *
 *
 */
//
interface RepositoryWriter {
  save();
  //...
}
interface RepositoryRemover {
  delete();
  //...
}
interface RepositoryReader {
  findAll();
  //...
}

class Repository implements RepositoryWriter, RepositoryRemover {
  save() { do something }

  delete() { do something }
}
```

5. [D] **DEPENDENCY INJECTION**: User injection of dependencies instead of instance a new class

<details><summary>Example: </summary>

```typescript
/******  BAD  ************************************************
 *
 */
class Repository {
  //...
}

class Service {
  something() {
    new Repository();
  }
}

/******  GOD  ************************************************
 *
 */
class Repository {
  //...
}

class Service {
  constructor(repository: Repository_INTERFACE) {}

  something() {
    this.repository;
  }
}
//
```

</details>

<br>
<br>
<br>

###### -

# STRUCTURAL patterns

## Adapter

Allows your code to communicate with other interfaces that are initially incompatible.

This pattern is great to use when you need to integrate third-party libraries that you can't change, or when you need to integrate legacy code that you can't change.

**Advantages**
Another advantage of this pattern is that your code is decoupled from external libraries. This means that you can replace the library that you are using or add new ones without having to change your code.

## Bridge

Allows you to separate a big class or set of related classes into two different domains: abstraction (also called interface) and implementation (also called platform).

The abstraction layer is the one that the client will use to interact with our application. This layer is not going to contain the business logic of our application. Instead, it will delegate the work to the implementation layer.

<details><summary>Scenario: </summary>

Imagine that you have a class that represents a vehicle. The vehicles can have different engines. You could start with a Mercedes and a BMW vehicles with V6 and V8 as possible engines. Initially you would have 4 combinations of vehicles (each one being a class in your application):

- Mercedes V6
- Mercedes V8
- BMW V6
- BMW V8

Imagine now that you want to add a new engine type: V12. You would have to create 2 new classes:

- Mercedes V6
- Mercedes V8
- Mercedes V12
- BMW V6
- BMW V8
- BMW V12

If now you add a new vehicle, you would have to create 3 new classes. This is not a big deal if you have only 2 or 3 vehicles, but what if you have 100 vehicles? You would have to create 100 new classes for each different engine.

As you can see, this problem grows exponentially. The Bridge pattern allows you to solve this in an easy way separating both domains and being able to develop them independently.

</details>
<Br>

**Applicability**

Use the Bridge pattern when:

you want to split a big class that has several variants of some functionality into two different layers.
you need to switch between different variants at runtime.

> [**I M P L E M E N T A T I O N > HERE**](./structure-patterns/adapter/README.md)

<br>

## Composite

Allows you to create tree structures of objects and treat them as a single object.
The tree can contain individual or groups of objects which can be treated in the exact same way.
This pattern is useful to represent a hierarchy of objects.

**Applicability**

- your model can be represented as a tree structure
- you want to simplify the interaction of the client code with your model

**Advantages**

- Work with complex object structures in a simpler way, making it easier to manipulate and interact with the tree of objects.
- Open/Closed Principle. You can introduce new types of objects to the application without modifying the existing code.

> [**I M P L E M E N T A T I O N > HERE**](./structure-patterns/composite/README.md)

<br>

## Facade

It is used to create a simplified interface to a complex system, like a library or framework in a simpler way.

Applicability:

- you don't want your application to be tightly coupled to a 3rd party library or framework
- you want to simplify the interaction of your application with a complex system

> [**I M P L E M E N T A T I O N > HERE**](./structure-patterns/facade/README.md)

<br>

## Decorator

**The Decorator pattern is useful** when you want to add behavior to individual objects, rather than to an entire class of objects.
It is also useful when you want to add behavior without affecting the existing hierarchy, or when you want to add behavior that can be changed dynamically at runtime.

**Applicability**:

- Want to add new behavior
  - to an existing object, but you don't want or can't to modify its class.
  - to a set of objects, but you don't want to create a new subclass for each object.
  - that can be changed dynamically at runtime. The Decorator pattern allows you to add or remove the behavior by adding or removing decorator objects.

> [**I M P L E M E N T A T I O N > HERE**](./structure-patterns/decorator/README.md)

<Br>
<Br>
<Br>

###### -

# BEHAVIORAL patterns

## Strategy

## Observer

## Template Method

<Br>
<br>
<br>

###### -

# CREATIONAL patterns

https://github.com/josemiguel-alvarez/design-patterns-typescript/tree/main/creational-patterns

## Abstract-factory

https://www.jmalvarez.dev/posts/abstract-factory-typescript

## Builder

https://www.jmalvarez.dev/posts/builder-pattern-typescript

## Factory-method

https://www.jmalvarez.dev/posts/factory-method-typescript

## Singleton

https://www.jmalvarez.dev/posts/singleton-typescript

