# Summary

- [Summary](#summary)
- [STRUCTURAL patterns](#structural-patterns)
  - [Adapter](#adapter)
  - [Bridge](#bridge)
  - [Composite](#composite)
  - [Facade](#facade)
  - [Decorator](#decorator)
- [BEHAVIORAL patterns](#behavioral-patterns)
  - [Strategy](#strategy)
  - [Observer](#observer)
  - [Template Method](#template-method)
- [CREATIONAL patterns](#creational-patterns)
  - [Abstract-factory](#abstract-factory)
  - [Builder](#builder)
  - [Factory-method](#factory-method)
  - [Singleton](#singleton)

<Br>
<br>
<br>

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

# BEHAVIORAL patterns

## Strategy

## Observer

## Template Method

<Br>
<br>
<br>

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

