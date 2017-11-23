package com.initializations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public abstract class Wrappers {

    public abstract WebDriver getWebDriver();

    protected By findById(String id){
        return By.id(id);
    }

    protected By findByCss(String css){
        return By.cssSelector(css);
    }

    protected By findByXPath(String xpath){
        return By.xpath(xpath);
    }

    protected void waitUntilElementNotVisible(By locator){
        new WebDriverWait(getWebDriver(), 15).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void type(By field, String value){
        getWebDriver().findElement(field).sendKeys(value);
    }

    protected void click(By element){
        getWebDriver().findElement(element).click();
        pause(100);
    }


    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    protected static Response extractResponseAndPrintLog(ValidatableResponse valRes) {
        return valRes.log().all().extract().response();
    }

    protected void open(String url){
        getWebDriver().get(url);
    }


    protected void refreshPage(){
        getWebDriver().navigate().refresh();
    }

}
