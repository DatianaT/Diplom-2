package ru.praktikum.models.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserCredsWithoutPassword
{
    private String email;

    public static UserCredsWithoutPassword fromUserWithoutPassword(User user)
    {
        return new UserCredsWithoutPassword(user.getEmail());
    }
}
