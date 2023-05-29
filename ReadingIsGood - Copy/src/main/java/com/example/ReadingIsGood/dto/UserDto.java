package com.example.ReadingIsGood.dto;

import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.enums.Role;
import lombok.Data;

import static com.example.ReadingIsGood.enums.Role.USER;

@Data

public class UserDto {
    private String email;
    private String password;
    private String address;
    private Role role  ;

    public static UserDto convertToUser(User user) {
        UserDto dto = new UserDto();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        dto.address = user.getAddress();
        dto.role = user.getRole();
        return dto;

    }

}
