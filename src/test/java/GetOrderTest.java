import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.metods.CreateOrder;
import org.example.metods.GetOrder;
import org.example.metods.Login;
import org.example.model.Credentials;
import org.example.model.Data;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class GetOrderTest {
    private Login login;
    private String token;
    private CreateOrder createOrder;
    private Credentials credentials;
    private GetOrder getOrder;

    @Before
    public void setUp() {
        credentials = new Credentials(Data.LOGIN, Data.PASSWORD);
        login = new Login();
        createOrder = new CreateOrder();
        getOrder = new GetOrder();
    }
    @Test
    @DisplayName("Получение заказа авторизованного пользователя")
    public void CreateOrderSuccessTest(){
        ValidatableResponse response = login.login(credentials);
        token = response.extract().path("accessToken");
        ValidatableResponse response1 = getOrder.getOrder(token);
        int statusCode = response1.extract().statusCode();
        response1.assertThat().body("success", is(true));
        assertEquals("Order don't create",200,statusCode);
    }
    @Test
    @DisplayName("Получение заказа неавторизованного пользователя")
    public void CreateOrderNotSuccessTest(){
        ValidatableResponse response = getOrder.getOrder("");
        int statusCode = response.extract().statusCode();
        response.assertThat().body("success", is(false)).and().body("message",equalTo("You should be authorised"));
        assertEquals("Order don't create",401,statusCode);
    }
}
