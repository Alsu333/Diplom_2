package org.example.metods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Data;
import org.example.model.Specification;
import org.example.model.User;
import static io.restassured.RestAssured.given;
import static org.example.model.Data.ApiCreate;

public class CreateAccount extends Specification {
    @Step("Get response for correct data")
    public ValidatableResponse create(User user){
        return given()
                .spec(requestSpec())
                .body(user)
                .when()
                .post( Data.URL + ApiCreate)
                .then().log().all();
    }
}
