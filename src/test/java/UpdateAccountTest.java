import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.metods.Login;
import org.example.metods.СhangeAccount;
import org.example.model.Credentials;
import org.example.model.Data;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class UpdateAccountTest {
    private Login login;
    private String token;
    private СhangeAccount сhangeAccount;
    private Credentials credentials;

    @Before
    public void setUp() {
        credentials = new Credentials(Data.LOGIN, Data.PASSWORD);
        login = new Login();
        сhangeAccount = new СhangeAccount();
    }
    @Test
    @DisplayName("клиент может изменить данные с авторизацией")
    public void updateAccount() {
        ValidatableResponse response = login.login(credentials);
        token = response.extract().path("accessToken");
        ValidatableResponse response1 = сhangeAccount.change(token, new Credentials(Data.LOGIN, Data.PASSWORD));
        response1.assertThat().body("success", is(true));
    }
    @Test
    @DisplayName("клиент не может изменить данные без авторизацией")
    public void NotUpdateAccount() {

        ValidatableResponse response1 = сhangeAccount.change("", new Credentials(Data.LOGIN, Data.PASSWORD));
        int statusCode = response1.extract().statusCode();
        response1.assertThat().body("success", is(false)).and().body("message", equalTo("You should be authorised"));
        assertEquals("Fail", 401, statusCode);
    }
}
