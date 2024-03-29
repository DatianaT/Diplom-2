package ru.praktikum.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikum.BeforeAndAfterTest;
import ru.praktikum.models.user.User;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.constants.AllConstants.*;
import static ru.praktikum.models.user.UserCreds.fromCorrectUser;
import static ru.praktikum.user.UserGenerator.*;

public class UserCreateTest extends BeforeAndAfterTest
{
    private User user;

    @Test
    @DisplayName("Корректное создание пользователя со случайными данными")
    @Description("Корректное создание пользователя со случайными данными")
    public void createCurrentUserTest()
    {
        user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);
        response.then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body(SUCCESS, equalTo(TRUE));

        accessToken = response.path(ACCESS_TOKEN);
        System.out.println(accessToken);

        Response loginResponse = userCreate.loginUser(fromCorrectUser(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body(SUCCESS, equalTo(TRUE));
    }

    @Test
    @DisplayName("Создание двух одинаковых пользователей со случайными данными")
    @Description("Создание двух одинаковых пользователей со случайными данными")
    public void createTwoUsersWithSameData()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization firstUserCreate = new UserCreateAndAuthorization();

        Response firstUserResponse = firstUserCreate.createUser(user);
        firstUserResponse.then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body(SUCCESS, equalTo(TRUE));
        accessToken = firstUserResponse.path(ACCESS_TOKEN);

        UserCreateAndAuthorization secondUserCreate = new UserCreateAndAuthorization();
        Response secondUserResponse = secondUserCreate.createUser(user);
        secondUserResponse.then()
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_ALREADY_EXISTS));
    }

    @Test
    @DisplayName("Создание пользователя без пароля со случайными данными")
    @Description("Создание пользователя без пароля со случайными данными")
    public void createUserWithoutPasswordTest()
    {
        user = createRandomUserWithoutPassword();
        UserCreateAndAuthorization userCreateWithoutPassword = new UserCreateAndAuthorization();

        Response response = userCreateWithoutPassword.createUser(user);
        response.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }

    @Test
    @DisplayName("Создание пользователя без почты со случайными данными")
    @Description("Создание пользователя без почты со случайными данными")
    public void createUserWithOutEmailTest()
    {
        user = createRandomUserWithoutEmail();
        UserCreateAndAuthorization userCreateWithoutEmail = new UserCreateAndAuthorization();

        Response response = userCreateWithoutEmail.createUser(user);
        response.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }

    @Test
    @DisplayName("Создание пользователя без имени со случайными данными")
    @Description("Создание пользователя без имени со случайными данными")
    public void createUserWithoutNameTest()
    {
        user = createRandomUserWithoutName();
        UserCreateAndAuthorization userCreateWithoutName = new UserCreateAndAuthorization();

        Response response = userCreateWithoutName.createUser(user);
        response.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }
}
