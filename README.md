# Testing task. API + UI-tests for examplesite.com

## CONFIGURING ENVIRONMENT FOR STARTING TESTS

1. Use Windows 10 x64 and Chrome 60 x64 with defined JAVA_HOME and JDK 1.8(doesn't tested on other environments)
2. Clone project from github.
3. Install maven 3.5.2

## RUNNING TESTS

For running tests use command **mvn test** from project directory 

## PROJECT STRUCTURE

/test/java/com/:

1. **test** directory - package with test-classes with test-methods inside.
2. **pages** directory - package with page-objects files, all page-methods and locators inside.
3. **utils** directory - package with commonly used non-test methods.
4. **initializations** directory - package with only one file - Wrappers.java, which is used for wrapping Selenium methods.

/test:

**Resources** directory - resources with ChromeDriver for Windows and test-suite file TestNG.xml.

## TESTS STRUCTURE

All tests presented in TestNG.xml test-suite file
There are few required parameter in the test-suite file:

1. **uiDomain** - for UI-testing domain link(prod, dev, local, etc.), "https://examplesite.com/" by default.
2. **apiDomain** - for API-testing domain link(prod, dev, local, etc.), "https://api.examplesite.com/" by default.
3. **apiKey** - for app access

