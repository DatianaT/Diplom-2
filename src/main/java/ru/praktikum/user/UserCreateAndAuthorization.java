package ru.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.models.user.User;
import ru.praktikum.models.user.UserCreds;
import ru.praktikum.models.user.UserCredsWithoutEmail;
import ru.praktikum.models.user.UserCredsWithoutPassword;

import static io.restassured.RestAssured.given;
import static ru.praktikum.constants.AllConstants.*;

public class UserCreateAndAuthorization
{
    @Step("Создание клиента {user}")
    public Response createUser(User user)
    {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER_USER_API);
    }

    @Step("Авторизация курьером с корректными данными {userCreds}")
    public Response loginUser(UserCreds userCreds)
    {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .post(LOGIN_USER_API);
    }

    @Step("Авторизация курьером без логина {userCreds}")
    public Response loginUserWithoutEmail(UserCredsWithoutEmail userCreds)
    {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .post(LOGIN_USER_API);
    }

    @Step("Авторизация курьером без пароля {userCreds}")
    public Response loginUserWithoutPassword(UserCredsWithoutPassword userCreds)
    {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .post(LOGIN_USER_API);
    }
}
