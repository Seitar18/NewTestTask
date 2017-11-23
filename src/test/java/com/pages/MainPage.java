package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class MainPage extends BasePage{

    private By loginButton = findByXPath("//a[text()='Log in']");

    public MainPage(WebDriver driver){super(driver);}

    public void visit(){ open(currentDomain); }

    public LoginPage openLoginPage(){
        click(loginButton);

        return new LoginPage(driver);
    }

}
