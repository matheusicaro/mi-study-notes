# Design Patterns — Quick Review

## SOLID

| Principle | One-line rule | Smell it fixes |
| --- | --- | --- |
| **S** — Single Responsibility | A class does one thing; one reason to change | God classes, `UserService` that also sends emails |
| **O** — Open/Closed | Open for extension, closed for modification | `if/else` chain that grows per new type — use polymorphism instead |
| **L** — Liskov Substitution | A subclass must be usable anywhere its parent is, no surprises | `Square extends Rectangle` breaking callers that set width/height independently |
| **I** — Interface Segregation | Small, specific interfaces beat one fat interface | A `findAll()` that throws because the class never needed it |
| **D** — Dependency Injection | Depend on abstractions, inject them — don't `new` your dependencies | `Service` doing `new Repository()` internally, untestable |

**Say out loud:** "class should have one reason to change, extend behavior without touching existing code, subclasses must be drop-in replacements, keep interfaces thin, and inject dependencies instead of constructing them."

## Chain of Responsibility (behavioral)

Pass a request along a chain of handlers; each either handles it or forwards it to the next.

- **Handler** — processes or forwards
- **Request** — the thing being processed
- **Chain** — linked handlers, client doesn't know which one will handle it

```ts
interface Handler { setNext(h: Handler): Handler; handle(req: string): string; }

class HandlerA implements Handler {
  private next?: Handler;
  setNext(h: Handler) { this.next = h; return h; }
  handle(req: string) {
    if (req === "A") return "Handler A";
    return this.next ? this.next.handle(req) : "not handled";
  }
}
```

Use when: client shouldn't know which handler processes the request, and handlers need to be added/removed dynamically.

## Structural patterns

| Pattern | Problem it solves | Mechanism | Recognize it by |
| --- | --- | --- | --- |
| **Adapter** | Incompatible interface (3rd-party lib, legacy code) you can't change | Wrap it in a class that translates calls into the shape your app expects | "convert format A to format B without touching either side" |
| **Bridge** | N×M explosion of subclasses (e.g. `MercedesV6`, `MercedesV8`, `BMWV6`...) | Split into abstraction (what client uses) + implementation (the variants), abstraction holds a reference to implementation | Two independent dimensions that each vary |
| **Composite** | Need to treat a single object and a group of objects the same way | Common interface (e.g. `render()`); a `Block` composite holds children and delegates | Tree structures, "treat a group like one object" |
| **Facade** | Complex subsystem/3rd-party API is painful to use directly | One class exposes a simplified method that orchestrates the subsystem calls internally | "hide the complexity of X behind one simple call" |
| **Decorator** | Need to add behavior to individual objects at runtime, without subclassing | Wrap the object in a decorator implementing the same interface, delegate + add behavior before/after | Stackable behavior — encryption + compression + logging, any combo |

### Minimal shapes

```ts
// Adapter: translate CSV lib's shape into what the app expects
class LibraryCSVAdapter implements LibraryAdapter {
  constructor(private lib: LibraryCSV) {}
  getBooks() { return parseCsv(this.lib.getBooks()); }
}

// Bridge: abstraction (UI) holds implementation (Backend), vary independently
abstract class UI { constructor(protected backend: Backend) {} abstract render(): void; }
class AndroidUI extends UI { render() { console.log(this.backend.getData()); } }

// Composite: Block delegates render() to all children
class Block implements Component {
  private children: Component[] = [];
  add(c: Component) { this.children.push(c); }
  render() { this.children.forEach(c => c.render()); }
}

// Facade: one method hides multi-step orchestration
class CloudProviderFacade {
  uploadFile(file: string) {
    if (!this.service.isLoggedIn()) this.service.logIn();
    return this.service.getFileLink(this.service.convertFile(file));
  }
}

// Decorator: wraps + delegates + adds behavior
class EncryptionDecorator extends DataSourceDecorator {
  writeData(data: string) { super.writeData(btoa(data)); }
  readData() { return atob(super.readData()); }
}
```

> Full worked implementations (with bad/good before-after, multi-step build-up): see `structure-patterns/` folder if you want to re-derive one from scratch before a live-coding round.

## Behavioral & Creational patterns — not reviewed yet

- Behavioral: Strategy, Observer, Template Method
- Creational: Abstract Factory, Builder, Factory Method, Singleton

External refs when you get to these: https://github.com/josemiguel-alvarez/design-patterns-typescript
