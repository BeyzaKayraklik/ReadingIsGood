package com.example.ReadingIsGood.service;

import com.example.ReadingIsGood.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserDetailReadingService {

//    User saveUser(String userName, String email, String password, List<Role> roleList);

    Optional<User> findByEmail(String username) throws UsernameNotFoundException;

}