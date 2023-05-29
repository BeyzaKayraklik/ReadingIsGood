package com.example.ReadingIsGood.service.serviceImpl;

import com.example.ReadingIsGood.config.JwtService;
import com.example.ReadingIsGood.dto.UserDto;
import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.payload.AuthenticationRequest;
import com.example.ReadingIsGood.payload.AuthenticationResponse;
import com.example.ReadingIsGood.payload.RegisterRequest;
import com.example.ReadingIsGood.repository.UserRepository;
import com.example.ReadingIsGood.service.AuthenticationService;
import com.example.ReadingIsGood.util.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public UserDto register(RegisterRequest request) {
        User user = new User();
          user.setEmail(request.getEmail());
          user.setAddress(request.getAddress());
          user.setRole(request.getRole());
          user.setPassword(passwordEncoder.encode(request.getPassword()));
          repository.save(user);
        LogUtil.info("Register successfully with email:", request.getEmail());

        return UserDto.convertToUser(user);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String jwtToken = jwtService.generateToken(userDetails);
            LogUtil.info("Authentication successful with email:", request.getEmail());

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            LogUtil.error("Authentication failed with email:", request.getEmail());
            throw new RuntimeException("Authentication failed");
        }
    }
}
