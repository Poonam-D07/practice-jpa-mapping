package com.poonam.jpamapping.auth;

import com.poonam.jpamapping.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequestDto requestDto){

          // Step 1: Username pehle se to nahi hai?
        if(userRepository.existsByUsername(requestDto.getUsername())){
            throw new DuplicateResourceException("Username already exists");
        }

        // Step 2: User banao, password secret code me badalke
        UserEntity user = UserEntity.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.DOCTOR)
                .build();

        // Step 3: Save karo
        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        // Step 1: Username se user dhundo
        UserEntity user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        // Step 2: Password match karo
        boolean passwordMatches = passwordEncoder.matches(requestDto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Step 3: Pass (token) banao
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        // Step 4: Slip (response) banao
        return LoginResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
