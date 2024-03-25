package ru.praktikum.Order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.praktikum.constants.AllConstants.AUTHORIZATION;
import static ru.praktikum.constants.AllConstants.ORDER_GET_API;

public class OrderGetFromUser
{
    @Step("Получение заказа пользователя с авторизацией")
    public Response getOrderFromUserWithAuth(String accessToken)
    {
        return given()
                .header(AUTHORIZATION, accessToken)
                .header("Content-type", "application/json")
                .when()
                .get(ORDER_GET_API);
    }
    @Step("Получение заказа пользователя без авторизации")
    public Response getOrderFromUserWithoutAuth()
    {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDER_GET_API);
    }
}
