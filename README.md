# SeleniumDemo

SeleniumDemo is a Java automation project for testing the OrangeHRM demo application with Selenium WebDriver, TestNG, Page Object Model classes, WebDriverManager, and ExtentReports.

The current suite validates login behavior, dashboard quick-launch navigation, and an Admin job-title workflow that uploads a job specification file.

## Tech Stack

- Java 21
- Maven
- Selenium Java 4.37.0
- TestNG 7.11.0
- WebDriverManager 6.3.2
- ExtentReports 5.1.1
- Commons IO
- AutoIT for native Windows file-upload dialog handling

## Project Structure

```text
.
|-- pom.xml
|-- testng.xml
|-- src
|   |-- main
|   |   |-- java/com/simplilearn/seleniumdemo
|   |   |   |-- factory/DriverFactory.java
|   |   |   |-- listeners/TestListener.java
|   |   |   |-- pages/
|   |   |   `-- utils/
|   |   `-- resources/config/config.properties
|   `-- test
|       |-- java/com/simplilearn/seleniumdemo/testcases/
|       `-- resources
|           |-- autoit/UploadFile.exe
|           `-- data/sample.pdf
|-- target/extent
|-- target/screenshots
`-- test-output
```

## Framework Overview

- `BaseTest` loads configuration, starts the browser before each test method, opens the base URL, and quits the driver after each method.
- `DriverFactory` creates Chrome, Edge, or Firefox browser sessions using WebDriverManager and stores the driver in a `ThreadLocal`.
- `pages` contains Page Object Model classes for Login, Dashboard, Leave, and Admin Job Titles pages.
- `BasePage` provides reusable wait, click, type, text, and URL helper methods.
- `TestListener` creates ExtentReports entries, logs test results, and captures screenshots plus page source on failures.
- `ReportManager`, `ReportPortal`, and `Log` support report creation and step logging.
- `AutoItUtil` runs the bundled AutoIT executable for file upload when Selenium cannot use the hidden file input directly.

## Test Scenarios

The intended TestNG suite is defined in `testng.xml`:

- `Login_AdminUser`
  - Valid admin login should navigate to Dashboard.
  - Invalid login should show `Invalid credentials`.
- `Dashboard_QuickLaunch`
  - Validates that the Assign Leave quick-launch button opens the Leave page.
- `Admin_UploadJobSpec`
  - Logs in as Admin.
  - Navigates to Admin > Job > Job Titles.
  - Adds a unique job title and uploads `src/test/resources/data/sample.pdf`.

## Configuration

Default values are stored in:

```text
src/main/resources/config/config.properties
```

Current defaults:

```properties
baseUrl=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
browser=chrome
headless=false
```

Supported browsers:

- `chrome`
- `edge`
- `firefox`

`ConfigReader` allows these values to be overridden with Maven system properties:

```powershell
mvn test -DsuiteXmlFile=testng.xml -Dbrowser=edge -Dheadless=true
```

The suite file also passes `browser` and `headless` parameters:

```xml
<parameter name="browser" value="chrome"/>
<parameter name="headless" value="false"/>
```

## Prerequisites

Install the following before running the tests:

- JDK 21
- Maven
- Chrome, Edge, or Firefox
- Windows, if running the AutoIT upload path

WebDriver binaries are managed automatically by WebDriverManager.

## Running Tests

Run the intended TestNG suite:

```powershell
mvn test -DsuiteXmlFile=testng.xml
```

Run in headless Chrome:

```powershell
mvn test -DsuiteXmlFile=testng.xml -Dbrowser=chrome -Dheadless=true
```

Run in Edge:

```powershell
mvn test -DsuiteXmlFile=testng.xml -Dbrowser=edge
```

Run in Firefox:

```powershell
mvn test -DsuiteXmlFile=testng.xml -Dbrowser=firefox
```

You can also run `testng.xml` directly from an IDE such as Eclipse or IntelliJ.

## Reports and Artifacts

After execution, generated reports and artifacts are written to:

```text
target/extent/ExtentReport_<timestamp>.html
target/screenshots/
test-output/
```

Failure handling includes:

- Screenshot capture
- HTML page-source capture
- ExtentReports failure logging

## File Upload Notes

The Admin job-title test uploads:

```text
src/test/resources/data/sample.pdf
```

The framework first tries Selenium `sendKeys()` on the file input. If that is not possible, it uses:

```text
src/test/resources/autoit/UploadFile.exe
```

AutoIT works only on Windows. On non-Windows machines, file-upload fallback through AutoIT will throw an unsupported-operation error.

## Adding New Tests

1. Create or update a page class under `src/main/java/com/simplilearn/seleniumdemo/pages`.
2. Add reusable waits/actions in `BasePage` only when they are useful across pages.
3. Create a test class under `src/test/java/com/simplilearn/seleniumdemo/testcases`.
4. Extend `BaseTest` to reuse browser setup, config loading, and teardown.
5. Add the new test class to `testng.xml` when it should run in the main suite.

## Notes

- The project currently targets the public OrangeHRM demo site, so test stability depends on that site being available and keeping the same UI structure.
- Generated folders such as `target` and `test-output` contain build and report artifacts.
