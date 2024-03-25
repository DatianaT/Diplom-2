package ru.praktikum.constants;

public class AllConstants
{
    public static final String[] CORRECT_DATA_HASH_WITH_TWO_INGREDIENTS = {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f"};
    public static final String[] WRONG_DATA_INGREDIENT = {"61c0c5a71d1f82001bdaaa6d", "123"};
    public static final String[] NULL_DATA_INGREDIENTS = {null};
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_USER_API = "/api/auth/register";
    public static final String LOGIN_USER_API = "/api/auth/login";
    public static final String DELETE_USER_API = "/api/auth/user";
    public static final String TAKE_USER_DATA_API = "/api/auth/user";
    public static final String UPDATE_USER_DATA_API = "/api/auth/user";
    public static final String ORDER_CREATE_API = "/api/orders";
    public static final String ORDER_GET_API = "/api/orders";
    public static final String INGREDIENTS_GET_API = "/api/ingredients";
    public final static String ACCESS_TOKEN = "accessToken";
    public final static String AUTHORIZATION = "Authorization";
    public final static String SUCCESS = "success";
    public final static String MESSAGE = "message";
    public final static String TEXT_USER_SUCCESSFULLY_REMOVED = "User successfully removed";
    public final static boolean TRUE = true;
    public final static boolean FALSE = false;
    public final static String TEXT_EMAIL_ALREADY_EXISTS = "User with such email already exists";
    public final static String TEXT_EMAIL_OR_PASSWORD_INCORRECT = "email or password are incorrect";
    public static final String OLD = "old";
    public static final String NULL = "null";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String WRONG_PASSWORD = "qwerty";
    public static final String WRONG_EMAIL = "qwertyu@qwe.qwe";

}
