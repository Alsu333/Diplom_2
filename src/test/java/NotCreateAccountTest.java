import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.metods.CreateAccount;
import org.example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class NotCreateAccountTest {
    private CreateAccount createAccount;
    private User user;
    private final String email;
    private final String password;
    private final String name;
    public NotCreateAccountTest(String email, String password,String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
    @Parameterized.Parameters
    public static Object[] userAccount() {
        return new Object[][]{
                {"test-data@yandex.ru", "password",null},
                {"test-data@yandex.ru",null, "Username"},
                { null,"password", "Username"},
                {null,null,null}// передали тестовые данные
        };
    }
    @Before
    public void setUp() {
        createAccount = new CreateAccount();
        user = new User("test-data@yandex.ru", "password", "Username");
    }
    @Test
    @DisplayName("создать пользователя, который уже зарегистрирован")
    public void theSameAccountTest() {
        ValidatableResponse response = createAccount.create(new User("test-data@yandex.ru", "password", "Username"));
        response.assertThat().body("success", is(false)).and().body("message", equalTo("User already exists"));
        int statusCode = response.extract().statusCode();
        assertEquals("Fail", 403, statusCode);
    }
    @Test
    @DisplayName("создать пользователя без поля данных")
    public void accountWithoutNameTest() {
        String Account = String.valueOf(userAccount());
        ValidatableResponse response = createAccount.create(new User(email,password,name));
        response.assertThat().body("success", is(false)).and().body("message", equalTo("Email, password and name are required fields"));
        int statusCode = response.extract().statusCode();
        assertEquals("Fail", 403, statusCode);
    }
}
