package com.example.ReadingIsGood.service;

import com.example.ReadingIsGood.dto.UserDto;
import com.example.ReadingIsGood.payload.AuthenticationRequest;
import com.example.ReadingIsGood.payload.AuthenticationResponse;
import com.example.ReadingIsGood.payload.RegisterRequest;

public interface AuthenticationService {
    UserDto register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);


}
