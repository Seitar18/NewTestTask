package com.test;
import org.testng.Assert;
import org.testng.annotations.*;
import com.pages.*;
import com.utils.SchemaLoader;
import java.util.ArrayList;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class FirstTest extends BaseTest {
    private String userName = "serj_qa_test@mailinator.com";
    private String userPassword = "Great123";
    private String getDocsSchema = SchemaLoader.loadSchema("json-schemas/get-docs.json");

    @Test
    public void checkDocsExisitingsAndView() {
        //validate the get docs response
        Response getDocsResponse = extractResponseAndPrintLog(spec
                .contentType("application/x-www-form-urlencoded")
                .when()
                .get("v1/document")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(getDocsSchema)));

        ArrayList<String> apiDocs = getDocsResponse.path("items.name");

        MainPage mainPage = new MainPage(driver);
        mainPage.visit();
        LoginPage loginPage = mainPage.openLoginPage();
        FormsPage formsPage = loginPage.loginToPddffiler(userName, userPassword);
        ArrayList<String> uiDocs = formsPage.getFormsDocuments();

        //Check that number of API docs and UI-viewed docs are the same
        Assert.assertEquals(apiDocs.size(), uiDocs.size());

        for(int i=0;i<apiDocs.size();i++){
            apiDocs.set(i, apiDocs.get(i).replace(".pdf", ""));
        }
        //Assert that all of API docs presented in UI
        for(String s: uiDocs){
            Assert.assertTrue(apiDocs.contains(s));
        }
    }
}

