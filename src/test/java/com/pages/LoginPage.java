package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By loginField = findById("form-login-email");
    private By passwordField = findById("form-login-password");
    private By loginButton= findById("form-login-submit");

    public LoginPage(WebDriver driver){super(driver);}

    public FormsPage loginToPddffiler(String login, String password){
        type(loginField, login);
        type(passwordField, password);
        click(loginButton);

        return new FormsPage(driver);
    }

}
