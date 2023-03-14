package org.example.metods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Specification;
import static io.restassured.RestAssured.given;
import static org.example.model.Data.ApiDelete;
import static org.example.model.Specification.requestSpec;

public class DeleteAccount extends Specification {
    @Step("Удаление пользовательских данных из системы")
    public ValidatableResponse delete(String token){
    return given()
        .spec(requestSpec())
        .auth().oauth2(token.replace("Bearer ", ""))
        .when()
        .delete(ApiDelete)
        .then().log().all();
    }
}
