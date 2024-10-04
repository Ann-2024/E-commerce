package com.example.Ecommerce.Auth;

import com.example.Ecommerce.config.JwtService;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) throws EmailAlreadyExistsException {
        if (usersRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + registerRequest.getEmail());
        }

        var users = Users.builder()
                .email(registerRequest.getEmail())
                .avatar(registerRequest.getAvatar())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getFirstName())
//                .username(registerRequest.getUsername())
                .birthOfDate(registerRequest.getBirthOfDate())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        var savedUser = usersRepository.save(users);
        String jwtToken = jwtService.generateToken(users);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        System.out.println("jwt token generation" );

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("jwt token generation" + request.getEmail() );

        var users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Address with id " + request.getEmail() + " does not exist"));

        System.out.println(users);
        System.out.println(users);
        String jwtToken = jwtService.generateToken(users);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }

}
