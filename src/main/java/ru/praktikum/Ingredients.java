package ru.praktikum.ingredients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static ru.praktikum.constants.AllConstants.*;

public class Ingredients {
    @Step("Получение ингридиентов")
    public Response getIngredients()
    {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(INGREDIENTS_GET_API);
    }
}
