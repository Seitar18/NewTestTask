package com.test;


import com.initializations.Wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.*;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public abstract class BaseTest extends Wrappers {

    private static String currentDomain = "";
    private static String currentAPI = "";

    protected WebDriver driver;

    protected static RequestSpecification spec;

    //Setting chromedriver for Windows. Remember that
    // names of executable drivers files in different OSes are also different
    protected  WebDriver setChromeDriver(){
        String pathToChromeDriver =
                Paths.get("./src/test/resources/ChromeDriver/chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver chromeDriver = new ChromeDriver(options);
        return chromeDriver;
    }

    static public String getCurrentDomain(){
        return currentDomain;
    }

    static public String getCurrentAPI(){
        return currentAPI;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Parameters({"uiDomain", "apiDomain"})
    @BeforeTest
    public void setUpDomainAndApi(String uiDomain, String apiDomain) {
        this.currentDomain = uiDomain;
        this.currentAPI = apiDomain;
    }

    @BeforeClass
    public void setUp(){driver = setChromeDriver(); }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    //setting a request specification for each API-request
    @Parameters({"apiKey"})
    @BeforeClass
    public static void initSpec(String apiKey) {
        spec = given()
                .baseUri(getCurrentAPI())
                .header("Authorization", "Bearer " + apiKey)
                .given()
                .log().all();
    }
}
