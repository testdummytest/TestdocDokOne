package PageObjects;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APIsPage extends BasePage{

    public APIsPage(WebDriver driver) {
        super(driver);
    }

    public String getTokenGeneratedByPassword() {
        baseURI = "https://auth-qa.dev.docdok.ch";
        RequestSpecification request = given();

        request.header("Content-Type", "application/x-www-form-urlencoded").
                formParam("grant_type", "password").
                formParam("client_id", "browser").
                formParam("username", "test@saluta.test").
                formParam("password", "12345678");

        Response response = request.post("/auth/realms/docdok/protocol/openid-connect/token");
//        response.prettyPrint();
        String jsonString = response.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        return tokenGenerated;
    }

    public String getTokenGeneratedByClientSecret() {
        baseURI = "https://auth-qa.dev.docdok.ch";
        RequestSpecification request = given();

        request.header("Content-Type", "application/x-www-form-urlencoded").
                formParam("grant_type", "client_credentials").
                formParam("client_id", "piswebserver").
                formParam("client_secret", "ed0c4566-9a07-4835-bfcd-b6de34b90d84");

        Response response = request.post("/auth/realms/docdok/protocol/openid-connect/token");
//        response.prettyPrint();
        String jsonString = response.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        return tokenGenerated;
    }

    public int getSurveysNumber() {
        baseURI = "https://qa.dev.docdok.ch";
        RequestSpecification request = given();
        request.header("Authorization","Bearer "+getTokenGeneratedByPassword());
        Response response = request.get("/rest/survey/api/patients/PAT-dfd92a77-69e4-48ca-a0e5-4d27a4ec992d/survey-definitions/");
//        response.prettyPrint();
        int surveysSent = response.path("find{it.name == 'Gesundheitsfragebogen final'}.nbSent");
        return surveysSent;
    }
}
