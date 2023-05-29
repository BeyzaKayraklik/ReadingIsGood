package ServiceImplTests.Authentication;

import com.example.ReadingIsGood.dto.UserDto;
import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.enums.Role;
import com.example.ReadingIsGood.payload.RegisterRequest;
import com.example.ReadingIsGood.repository.UserRepository;
import com.example.ReadingIsGood.service.AuthenticationService;
import com.example.ReadingIsGood.service.serviceImpl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AuthenticationService.class})
public class RegistrationTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl registrationClass;

    @Test
    public void testRegister_Success() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setAddress("123 ABC Street");
        request.setRole(Role.USER);
        request.setPassword("password");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode(request.getPassword()))
                .thenReturn("encodedPassword");
        when(repository.save(any(User.class)))
                .thenReturn(user);

        UserDto result = registrationClass.register(request);

        assertNotNull(result);
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getAddress(), result.getAddress());
        assertEquals(request.getRole(), result.getRole());

        // Verify that the password was encoded
        verify(passwordEncoder).encode(request.getPassword());

        // Verify that the user was saved to the repository
        verify(repository).save(any(User.class));

    }
}
