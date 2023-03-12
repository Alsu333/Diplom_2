package org.example.metods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Credentials;
import org.example.model.Data;
import org.example.model.Specification;
import static io.restassured.RestAssured.given;

public class СhangeAccount extends Specification {
    @Step("Клиент может поменять данные")
    public ValidatableResponse change(String token, Credentials credentials){
        return given()
                .spec(requestSpec())
                .header("Authorization",token)
                .body(credentials)
                .when()
                .patch(Data.URL+Data.ApiPatch)
                .then().log().all();
    }
}
