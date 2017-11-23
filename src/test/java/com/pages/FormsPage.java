package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FormsPage extends BasePage{

    private By formsLocator = findByCss(".mf-doc-item__name");
    private By loadCircleLocator = findByCss(".g-loader__circle");

    public FormsPage(WebDriver driver){super(driver);}

    public ArrayList<String> getFormsDocuments(){
        waitUntilElementNotVisible(loadCircleLocator);
        ArrayList<String> docsList = new ArrayList();
        List<WebElement> docsLocator = getWebDriver().findElements(formsLocator);

        for (WebElement w : docsLocator){
            docsList.add(w.getText());
        }


        return docsList;
    }

}
