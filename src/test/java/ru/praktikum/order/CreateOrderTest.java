package ru.praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.praktikum.Order.OrderCreate;
import ru.praktikum.ingredients.Ingredients;
import ru.praktikum.models.order.Order;
import ru.praktikum.models.order.OrderGenerator;
import ru.praktikum.models.user.User;
import ru.praktikum.BeforeAndAfterTest;
import ru.praktikum.user.UserCreateAndAuthorization;

import java.util.List;


import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

import static ru.praktikum.constants.AllConstants.*;
import static ru.praktikum.user.UserGenerator.createCurrentRandomUser;

@Slf4j

public class CreateOrderTest extends BeforeAndAfterTest {
    private final UserCreateAndAuthorization userCreate = new UserCreateAndAuthorization();

    @Test
    @DisplayName("Создание заказа с авторизацией")
    @Description("Создание заказа с авторизацией")
    public void createOrderWithLoginUserSuccess(){
        User user = createCurrentRandomUser();

        Response response = userCreate.createUser(user);
        accessToken = response.path(ACCESS_TOKEN);

        OrderGenerator createNewOrder = new OrderGenerator();

        Order order = createNewOrder.orderGenerator(getRandomIngredients());
        OrderCreate orderCreate = new OrderCreate();
        Response createOrderResponse = orderCreate.createNewOrderWithLogin(accessToken, order);
        createOrderResponse.then().assertThat().statusCode(SC_OK)
                .and().body(SUCCESS, equalTo(TRUE));
    }

    @Test
    @DisplayName("Создание заказа с неправильными ингридиентами ")
    @Description("Создание заказа с неправильными ингридиентами ")
    public void createOrderWithWrongIngredientsFail(){
        User user = createCurrentRandomUser();

        Response response = userCreate.createUser(user);
        accessToken = response.path(ACCESS_TOKEN);

        OrderGenerator createNewOrder = new OrderGenerator();

        Order order = createNewOrder.orderGenerator(WRONG_DATA_INGREDIENT);
        OrderCreate orderCreate = new OrderCreate();
        Response createOrderResponse = orderCreate.createNewOrderWithLogin(accessToken, order);
        createOrderResponse.then().assertThat().statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Создание заказа без ингридиентов")
    @Description("Создание заказа без ингридиентов")
    public void createOrderWithoutIngredientsFail(){
        User user = createCurrentRandomUser();

        Response response = userCreate.createUser(user);
        accessToken = response.path(ACCESS_TOKEN);

        OrderGenerator createNewOrder = new OrderGenerator();

        Order order = createNewOrder.orderGenerator(NULL_DATA_INGREDIENTS);
        OrderCreate orderCreate = new OrderCreate();
        Response createOrderResponse = orderCreate.createNewOrderWithLogin(accessToken, order);
        createOrderResponse.then().assertThat().statusCode(SC_BAD_REQUEST)
                .and().body(SUCCESS, equalTo(false));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Создание заказа без авторизации")
    public void createOrderWithoutLoginSuccess(){

        OrderGenerator createNewOrder = new OrderGenerator();

        Order order = createNewOrder.orderGenerator(getRandomIngredients());
        OrderCreate orderCreate = new OrderCreate();
        Response createOrderResponse = orderCreate.createNewOrderWithoutLogin(order);
        createOrderResponse.then().assertThat().statusCode(SC_OK)
                .and().body(SUCCESS, equalTo(true));
    }
    private String[] getRandomIngredients(){
        Ingredients ingredients = new Ingredients();
        Response getIngredientsResponse = ingredients.getIngredients();
        List<String> ingredientIds = getIngredientsResponse.jsonPath().getList("data._id");
        return ingredientIds.stream().limit(2).toArray(String[]::new);
    }
}
