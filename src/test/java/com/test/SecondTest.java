package com.test;
import org.testng.Assert;
import org.testng.annotations.*;
import com.pages.*;
import com.utils.SchemaLoader;
import org.json.JSONObject;

import java.util.ArrayList;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class SecondTest extends BaseTest {
    private String userName = "serj_qa_test@mailinator.com";
    private String userPassword = "Great123";
    private String pdfFileUrl = "https://www.irs.gov/pub/irs-pdf/fw9.pdf";
    private String createdDocId;
    private String postDocSchema = SchemaLoader.loadSchema("json-schemas/post-doc.json");

    @Test
    public void postTheDocAndCheckExisting() {
        String pdfFileName;
        int baseDocsNumber;

        //JsonObject to post doc request
        JSONObject postParams = new JSONObject()
                .put("file", pdfFileUrl)
                .put("folderid", 0);

        MainPage mainPage = new MainPage(driver);
        mainPage.visit();
        LoginPage loginPage = mainPage.openLoginPage();
        FormsPage formsPage = loginPage.loginToPddffiler(userName, userPassword);
        ArrayList<String> uiDocs = formsPage.getFormsDocuments();
        baseDocsNumber = uiDocs.size();

        //validate the post doc response
        Response postDocResponse = extractResponseAndPrintLog(spec
                .body(postParams.toString())
                .contentType("application/json")
                .when()
                .post("v1/document")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(postDocSchema)));

        pdfFileName = postDocResponse.path("name");
        createdDocId = postDocResponse.path("id").toString();

        Response getDocsResponse = extractResponseAndPrintLog(spec
                .contentType("application/x-www-form-urlencoded")
                .when()
                .get("v1/document")
                .then()
                .statusCode(200));

        ArrayList<String> apiDocs = getDocsResponse.path("items.name");

        //Check that new doc appears in get docs response
        Assert.assertEquals(baseDocsNumber + 1, apiDocs.size());
        Assert.assertTrue(apiDocs.contains(pdfFileName));

        refreshPage();
        pdfFileName = pdfFileName.replace(".pdf", "");
        uiDocs = formsPage.getFormsDocuments();

        //Check that new doc appears in UI
        Assert.assertEquals(baseDocsNumber + 1, formsPage.getFormsDocuments().size());
        Assert.assertTrue(uiDocs.contains(pdfFileName));
    }

    @AfterClass(alwaysRun = true)
    public void deleteCreatedDoc(){
        if(createdDocId != null && !createdDocId.isEmpty()) {
            spec
                    .contentType("application/json")
                    .when()
                    .delete("v1/document/" + createdDocId);
        }
    }
}


