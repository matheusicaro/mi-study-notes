# Facade

The facade pattern is a structural pattern which allows you to communicate your application with any complex software (like a library or framework) in a simpler way. It is used to create a simplified interface to a complex system.

Applicability:

- you don't want your application to be tightly coupled to a 3rd party library or framework
- you want to simplify the interaction of your application with a complex system

IMPLEMENTATION:

```typescript
/**
 * Let's imagine that the 3rd-party code is something like the following:
 */
class CloudProviderService {
  public isLoggedIn(): boolean {
    return Math.random() > 0.5 ? true : false;
  }

  public logIn(): void {
    console.log("Logging in...");
  }

  public convertFile(file: string): string {
    console.log("Converting file...", file);
    return file + ".converted";
  }

  public uploadFile(file: string): void {
    console.log("Uploading file...", file);
  }

  public getFileLink(file: string): string {
    return `https://example.com/${file}`;
  }
}

/**
 * As we don't want to couple our application with this 3rd-party code
 * we are going to create a facade that will hide the complexity of it.
 * This facade will be the only class that our application will interact with.
 */
class CloudProviderFacade {
  private service: CloudProviderService;

  constructor() {
    this.service = new CloudProviderService();
  }

  /**
   * Now we have to implement the functions that our application will use to interact with
   * the 3rd-party code. For the example I'm going to only implement one function:
   */
  public uploadFile(file: string): string {
    if (!this.service.isLoggedIn()) {
      this.service.logIn();
    }

    const convertedFile = this.service.convertFile(file);
    this.service.uploadFile(convertedFile);

    return this.service.getFileLink(convertedFile);
  }
}

/**
 * And that's it! Like this we have implemented the Facade pattern.
 * It's very simple and very useful at the same time.
 * A simple example of how the client would use this code:
 */
const facade = new CloudProviderFacade();
const fileLink = facade.uploadFile("file.txt");
console.log("File link:", fileLink);
/*  
  Output:

  Logging in...
  Converting file... file.txt
  Uploading file... file.txt.converted
  File link: https://example.com/file.txt.converted
  
*/
```

