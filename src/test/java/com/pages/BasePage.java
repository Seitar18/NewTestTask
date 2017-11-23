package com.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.initializations.Wrappers;
import com.test.BaseTest;


public abstract class BasePage extends Wrappers {

    public static String currentDomain = BaseTest.getCurrentDomain();

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }



}
