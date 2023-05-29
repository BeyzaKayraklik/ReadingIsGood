package ServiceImplTests.Authentication;

import com.example.ReadingIsGood.config.JwtService;
import com.example.ReadingIsGood.payload.AuthenticationRequest;
import com.example.ReadingIsGood.payload.AuthenticationResponse;
import com.example.ReadingIsGood.service.AuthenticationService;
import com.example.ReadingIsGood.service.serviceImpl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AuthenticationService.class})
public class AuthenticationTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationClass;

    @Test
    public void testAuthenticate_Success() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userDetailsService.loadUserByUsername(request.getEmail()))
                .thenReturn(userDetails);
        when(jwtService.generateToken(userDetails))
                .thenReturn("jwt-token");

        AuthenticationResponse response = authenticationClass.authenticate(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.getAccessToken());

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Verify that the user details service was called with the correct argument
        verify(userDetailsService).loadUserByUsername(request.getEmail());

        // Verify that the JWT service was called to generate the token
        verify(jwtService).generateToken(userDetails);

    }

    @Test
    public void testAuthenticate_Failure() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        assertThrows(RuntimeException.class, () -> authenticationClass.authenticate(request));

        // Verify that the authentication manager was called with the correct arguments
        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    }
}
