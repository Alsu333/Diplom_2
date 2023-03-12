package org.example.metods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Data;
import static io.restassured.RestAssured.given;
import static org.example.model.Specification.requestSpec;

public class GetOrder {
    @Step("Получение заказа конкретного пользователя")
    public ValidatableResponse getOrder(String token){
        return given()
                .spec(requestSpec())
                .header("Authorization",token)
                .when()
                .get(Data.URL + Data.ApiOrders)
                .then().log().all();
    }
}
