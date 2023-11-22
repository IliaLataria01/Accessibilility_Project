

## Requirements

- Chrome must be installed; follow the directions at https://www.google.com/chrome/ to install it. On Unix, ensure that Chrome is on your path.
- Chrome Driver must be installed; follow the directions at: https://sites.google.com/a/chromium.org/chromedriver/getting-started to install it.
- The Java SE Development Kit must be installed; follow the directions at http://www.oracle.com/technetwork/java/javase/downloads/index.html to install it.
- Maven must be installed; follow the directions at http://maven.apache.org/ to install it. Ensure that it is on your path.


## To modify the example

To run the example tests on your own web page, change the URL passed to `driver.get` in `ExampleTest.setUp()`.

## To use the AXE helper library in your own tests

Include this library as a test-scoped dependency in your POM. Ensure the `version` matches the one in `[pom.xml](./pom.xml)`:

```xml
<dependency>
  <groupId>com.deque</groupId>
  <artifactId>axe-selenium</artifactId>
  <version>3.0</version>
  <scope>test</scope>
</dependency>
```


The `AXE` helper defines three public methods and a nested `Builder` class for your unit tests.

- `inject` will inject the required script into the page under test and any iframes. This only needs to be run against a given page once, and `Builder` will take care of it for you if you use that.
- `report` will pretty-print a list of violations.
- `writeResults` will write the JSON violations list out to a file with the specified name in the current working directory.

The `Builder` class allows tests to chain configuration and analyze pages. The constructor takes in a `WebDriver` that has already navigated to the page under test and a `java.net.URL` pointing to the aXe script; from there, you can set `options()`, `include()` and `exclude()` selectors, `skipFrames()`, and finally, `analyze()` the page.

- `options` wires a JSON string to aXe, allowing rules to be toggled on or off. See the `testAccessibilityWithOptions` unit test for a sample single-rule execution, and the [axe-core API documentation](https://github.com/dequelabs/axe-core/blob/master/doc/API.md#b-options-parameter) for full documentation on the options object. The runOnly option with tags may be of particular interest, allowing aXe to execute all rules with the specified tag(s).
- `include` adds to the list of included selectors. If you do not call `include` at all, aXe will run against the entire document.
- `exclude` adds to the list of excluded selectors. Exclusions allow you to focus scope exactly where you need it, ignoring child elements you don't want to test.
- `skipFrames` prevents aXe to be recursively injected into all iframes.
- `analyze` executes aXe with any configuration you have previously defined. If you want to test a single `WebElement`, you may pass it into `analyze` instead of using `include` and `exclude`.



