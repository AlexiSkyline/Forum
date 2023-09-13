package com.skyline.forum.controller;

import com.skyline.forum.dto.user.AuthenticationResponseDto;
import com.skyline.forum.dto.user.LoginRequestDto;
import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.security.UserDetailSecurity;
import com.skyline.forum.security.jwt.JwtUtils;
import com.skyline.forum.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/authentication")
public class AuthenticationController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping( "/signin" )
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(this.authenticationResponse(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        if (this.userService.userNameExists(signupRequestDto.getUsername())) return ResponseEntity.badRequest().body("Error: Username is already taken!");
        if (this.userService.emailExists(signupRequestDto.getEmail())) return ResponseEntity.badRequest().body("Error: Email is already in use!");

        signupRequestDto.setPassword(this.passwordEncoder.encode(signupRequestDto.getPassword()));
        this.userService.saveUser(signupRequestDto);

        return new ResponseEntity<>(this.authenticationResponse(signupRequestDto.getUsername(), signupRequestDto.getPassword()), CREATED);
    }

    private AuthenticationResponseDto authenticationResponse(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailSecurity userDetails = (UserDetailSecurity) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return AuthenticationResponseDto.builder()
                .id(userDetails.getId())
                .username(username)
                .email(userDetails.getEmail())
                .roles(roles)
                .token(jwt)
                .build();
    }
}
