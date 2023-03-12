import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.metods.CreateAccount;
import org.example.metods.DeleteAccount;
import org.example.model.User;
import org.example.model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CreateAccountTest {
    private CreateAccount createAccount;
    private User user;
    private String token;
    private DeleteAccount deleteAccount;

    @Before
    public void setUp(){
        createAccount = new CreateAccount();
        user = UserGenerator.getDefault();
        deleteAccount = new DeleteAccount();
    }
    @Test
    @DisplayName("Клиент может успешно зарегестрироваться")
    public void accountCanBeCreateTest(){
    ValidatableResponse response = createAccount.create(user);
    token = response.extract().path("accessToken");
    int statusCode = response.extract().statusCode();
    assertEquals(200, statusCode);
    }
    @After
    public void delete(){
    deleteAccount.delete(token);
    }
}
