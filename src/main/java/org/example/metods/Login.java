package org.example.metods;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Credentials;
import org.example.model.Data;
import org.example.model.Specification;
import static io.restassured.RestAssured.given;

public class Login extends Specification {
    @Step("Авторизация в системе")
    public ValidatableResponse login(Credentials credentials){
        return given()
                .spec(requestSpec())
                .body(credentials)
                .when()
                .post(Data.URL + Data.ApiLogin)
                .then().log().all();
    }
}
