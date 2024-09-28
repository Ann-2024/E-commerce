package com.example.Ecommerce.Auth;


import com.example.Ecommerce.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(value="*")
public class AuthController {

    private final AuthService authService;
    private  UsersRepository usersRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) throws EmailAlreadyExistsException {
        AuthenticationResponse authResponse = authService.register(registerRequest);
        return ResponseEntity.ok(new AuthenticationResponse("Success"));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<AuthenticationResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse(e.getMessage()));
    }

}
