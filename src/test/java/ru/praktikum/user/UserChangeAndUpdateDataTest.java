package ru.praktikum.user;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.praktikum.BeforeAndAfterTest;
import ru.praktikum.models.user.User;
import java.util.HashMap;
import java.util.Map;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.constants.AllConstants.*;
import static ru.praktikum.user.UserGenerator.createCurrentRandomUser;
import static ru.praktikum.user.UserGenerator.faker;

@Slf4j
public class UserChangeAndUpdateDataTest extends BeforeAndAfterTest
{
    private final UserUpdateAndChangeData userUpdate = new UserUpdateAndChangeData();
    private final UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();



    @Test
    public void loginUserUpdateDataSuccess() {
        User user = createCurrentRandomUser();

        Response createUserResponse = userCreate.createUser(user);
        accessToken = createUserResponse.path(ACCESS_TOKEN);

        Map<String, String> updateData = new HashMap<>();
        updateData.put(EMAIL, generateEmail());
        updateData.put(PASSWORD, generatePassword());
        updateData.put(NAME, generateName());
        Response response = userUpdate.updateUser(updateData, accessToken);

        response.then().statusCode(SC_OK)
                .and().body(SUCCESS, equalTo(true));

}

    @Test
    public void updateDataWithoutLoginFail() {
        Map<String, String> updateData = new HashMap<>();
        updateData.put(EMAIL, generateEmail());
        updateData.put(PASSWORD, generatePassword());
        updateData.put(NAME, generateName());
        Response response = userUpdate.updateUser(updateData, "");

        response.then().statusCode(SC_UNAUTHORIZED)
                .and().body(SUCCESS, equalTo(false));
    }

    public static String generateEmail()
    {
        return faker.internet().safeEmailAddress();
    }

    public static String generatePassword()
    {
        return faker.internet().password(8, 10);
    }

    public static String generateName()
    {
        return faker.name().firstName();
    }
}