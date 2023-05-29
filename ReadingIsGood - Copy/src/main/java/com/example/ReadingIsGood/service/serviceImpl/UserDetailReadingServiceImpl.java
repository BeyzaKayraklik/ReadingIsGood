package com.example.ReadingIsGood.service.serviceImpl;

import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.repository.UserRepository;
import com.example.ReadingIsGood.service.UserDetailReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailReadingServiceImpl implements UserDetailReadingService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> findByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }
}
