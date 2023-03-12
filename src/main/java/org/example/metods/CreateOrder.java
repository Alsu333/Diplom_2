package org.example.metods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.Data;
import org.example.model.Orders;
import org.example.model.Specification;
import static io.restassured.RestAssured.given;

public class CreateOrder extends Specification {
    @Step("клиент может создать заказ с авторизацией")
    public ValidatableResponse createOrder(String token, Orders orders){
        return given()
                .spec(requestSpec())
                .header("Authorization",token)
                .body(orders)
                .when()
                .post(Data.URL + Data.ApiOrders)
                .then().log().all();
    }
    @Step("клиент может создать заказ без авторизацией")
    public ValidatableResponse createOrderWithoutToken( Orders orders){
        return given()
                .spec(requestSpec())
                .body(orders)
                .when()
                .post(Data.URL + Data.ApiOrders)
                .then().log().all();
    }
}
