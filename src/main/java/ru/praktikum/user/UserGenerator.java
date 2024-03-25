package ru.praktikum.user;

import com.github.javafaker.Faker;
import ru.praktikum.models.user.User;

public class UserGenerator
{
    static Faker faker = new Faker();
    public static User createCurrentRandomUser()
    {
        return User.builder()
                .email(faker.internet().safeEmailAddress())
                .password(faker.internet().password(8,10))
                .name(faker.name().firstName())
                .build();
    }

    public static User createRandomUserWithoutPassword()
    {
        return User.builder()
                .email(faker.internet().safeEmailAddress())
                .name(faker.name().firstName())
                .build();
    }

    public static User createRandomUserWithoutEmail()
    {
        return User.builder()
                .password(faker.internet().password(8,10))
                .name(faker.name().firstName())
                .build();
    }

    public static User createRandomUserWithoutName()
    {
        return User.builder()
                .email(faker.internet().safeEmailAddress())
                .password(faker.internet().password(8,10))
                .build();
    }
}
