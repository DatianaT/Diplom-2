package ru.praktikum.models.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserCredsWithoutEmail
{
    private String password;

    public static UserCredsWithoutEmail fromUserWithoutEmail(User user)
    {
        return new UserCredsWithoutEmail(user.getPassword());
    }
}
