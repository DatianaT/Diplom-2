package ru.praktikum.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikum.BeforeAndAfterTest;
import ru.praktikum.models.user.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.constants.AllConstants.*;
import static ru.praktikum.models.user.UserCreds.*;
import static ru.praktikum.models.user.UserCredsWithoutEmail.fromUserWithoutEmail;
import static ru.praktikum.models.user.UserCredsWithoutPassword.fromUserWithoutPassword;
import static ru.praktikum.user.UserGenerator.createCurrentRandomUser;

public class UserLoginTest extends BeforeAndAfterTest
{

    @Test
    @DisplayName("Корректная авторизация под существующим пользователем")
    @Description("Корректная авторизация под существующим пользователем")
    public void currentLoginTest()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);

        accessToken = response.path(ACCESS_TOKEN);

        Response loginResponse = userCreate.loginUser(fromCorrectUser(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body(SUCCESS, equalTo(TRUE));
    }

    @Test
    @DisplayName("Авторизация под пользователем без почты")
    @Description("Авторизация под пользователем без почты")
    public void loginWithoutEmailTest()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);

        accessToken = response.path(ACCESS_TOKEN);

        Response loginResponse = userCreate.loginUserWithoutEmail(fromUserWithoutEmail(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }

    @Test
    @DisplayName("Авторизация под пользователем без пароля")
    @Description("Авторизация под пользователем без пароля")
    public void loginWithoutPasswordTest()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);

        accessToken = response.path(ACCESS_TOKEN);

        Response loginResponse = userCreate.loginUserWithoutPassword(fromUserWithoutPassword(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }

    @Test
    @DisplayName("Авторизация пользователя под некорректным паролем")
    @Description("Авторизация пользователя под некорректным паролем")
    public void loginUserWithWrongPasswordTest()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);

        accessToken = response.path(ACCESS_TOKEN);

        Response loginResponse = userCreate.loginUser(fromUserWithWrongPassword(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }

    @Test
    @DisplayName("Авторизация пользователя под не корректной почтой")
    @Description("Авторизация пользователя под не корректной почтой")
    public void loginUserWithWrongEmailTest()
    {
        User user = createCurrentRandomUser();
        UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

        Response response = userCreate.createUser(user);

        accessToken = response.path(ACCESS_TOKEN);

        Response loginResponse = userCreate.loginUser(fromUserWithWrongEmail(user));
        loginResponse.then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body(SUCCESS, equalTo(FALSE))
                .body(MESSAGE, equalTo(TEXT_EMAIL_OR_PASSWORD_INCORRECT));
    }
}
