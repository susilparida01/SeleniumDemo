# SeleniumDemo
SeleniumDemo

Summary:
This project is a Selenium-based automation framework designed for web application testing, specifically targeting the OrangeHRM demo application. It follows industry best practices to ensure maintainability, scalability, and robust reporting.
Core Technologies
   * Language: Java 21
   * Build Tool: Maven
   * Testing Framework: TestNG
   * Browser Automation: Selenium WebDriver 4.40.0
   * Reporting: Extent Reports 5.1.2 (Spark Reporter)
   * Driver Management: WebDriverManager 6.3.3
Architectural Highlights
   * Page Object Model (POM): The project separates page-specific logic (locators and actions) from test scripts. Page classes like LoginPage and DashboardPage are located in src/main/java/com/simplilearn/SeleniumDemo/pages.
   * Centralized Configuration: Environment-specific settings, such as baseUrl, browser, and credentials, are managed  via a config.properties file.
   * Factory Design Pattern: A DriverFactory is used to encapsulate WebDriver initialization logic, supporting multiple browsers and headless execution.
   * Robust Reporting & Listeners:
TestListener: A custom TestNG listener that intercepts test events to generate Extent Reports automatically.
Automatic Artifact Capture: On test failure, the framework automatically captures screenshots and the full HTML page source, saving them to target/screenshots.
Thread Safety: Uses ThreadLocal via ReportPortal to ensure that reports are correctly mapped to tests, even during parallel execution.


  Project Structure
   * src/main/java: Contains the framework's engine, including Page Objects, Utilities (Log, Config, Reports), and Listeners.
   * src/test/java: Contains the test suites, organized into test cases (e.g., Login_AdminUser) and a BaseTest for common setup/teardown logic.
   * testng.xml: Orchestrates the test execution suite and enables reporting listeners.
   * target/: The output directory where Maven builds the project and where extent/ reports and screenshots/ are stored after execution.
This framework is ready for CI/CD integration, as it can be fully executed and reported via standard Maven commands like:  mvn test.

