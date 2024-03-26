# JET-Challenge-Project

This project is a Java Selenium TestNG automation framework designed for testing career search functionalities on the Just Eat Takeaway careers website.

## Introduction

The JET Challenge Project is a test automation suite developed to ensure the functionality and reliability of the career search features on the Just Eat Takeaway (JET) careers website. The project utilizes Java, Selenium WebDriver, and TestNG to automate test scenarios covering various aspects of job searching, filtering, and result verification and follows the Page Object Model (POM) design pattern to enhance code readability, reusability, and maintainability.
### Key Components:

- **Selenium WebDriver:** Enables automated interactions with web browsers to simulate user actions.
- **TestNG:** A testing framework for organizing and executing test cases, providing features such as parallel test execution, data-driven testing, and reporting.
- **Page Object Model (POM):** A design pattern used to represent web pages as objects, encapsulating their behavior and interactions in separate classes.

### Goals:

- Automate the testing process to ensure the reliability and stability of Just Eat Takeaway company's career page.
- Provide a scalable and maintainable testing framework that can accommodate future changes and additions.
- Improve the efficiency of the testing process by enabling parallel execution and comprehensive reporting.
- Foster collaboration among team members by standardizing test case organization and execution.

## Features

- Search for job titles and verify search results.
- Refine search by country and verify location-specific results.
- Select job categories and validate corresponding search results.
- Dynamically handle pagination to verify results across multiple pages.
- Perform assertions to ensure accuracy and reliability of test outcomes.

## Installation

To use this project locally and run the automated tests, follow these steps:

### 1. Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/gokayari/JET-Challenge-Project.git
```

### 2. Set Up Dependencies

Make sure you have the following dependencies installed on your system:

Java Development Kit (JDK): Install JDK 8 or higher from the official Oracle website or your package manager.
Maven: Install Maven to manage project dependencies and build automation. You can download Maven from the official Apache Maven website or install it via a package manager.

### 3. Configure WebDriver
Download the appropriate WebDriver executable for your preferred browser (e.g., ChromeDriver for Google Chrome) and ensure it is added to your system PATH.

### 4. Build the Project
Navigate to the project directory and build the project using Maven:
```bash
cd JET-Challenge-Project
mvn clean install
```
### 5. Run Tests
You can now execute the automated tests using the TestNG test runner. Run the following command to execute all tests:
```bash
mvn test
```
Alternatively, you can run specific test classes or methods by specifying them as parameters to the test command. For example:
```bash
mvn test -Dtest=TestClass1,TestClass2#testMethod1
```
### 6. View Test Reports
After running the tests, view the generated test reports located in the target/surefire-reports directory. These reports provide detailed information about test execution, including pass/fail status, execution time, and error messages.

## Usage

To execute the test suite, follow these steps:

1. Open the project in your preferred Java IDE.
2. Locate the SearchTest class in the src/test/java directory.
3. Right-click on the class and select "Run As" > "TestNG Test" to execute all test cases.
4. Alternatively, run individual test methods by right-clicking on the method and selecting "Run".

## License

This project is licensed under the MIT License.
