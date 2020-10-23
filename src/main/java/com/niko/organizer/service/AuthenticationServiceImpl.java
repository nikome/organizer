package com.niko.organizer.service;

import com.niko.organizer.exception.AppException;
import com.niko.organizer.model.Role;
import com.niko.organizer.model.RoleName;
import com.niko.organizer.model.User;
import com.niko.organizer.payload.SignUpRequest;
import com.niko.organizer.repository.RoleRepository;
import com.niko.organizer.repository.UserRepository;
import com.niko.organizer.response.RegisterResponse;
import com.niko.organizer.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @Override
    public RegisterResponse registerUser(final SignUpRequest signUpRequest) {

        System.out.println("hello: " + signUpRequest);
        final User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        final Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set"));

        user.setRoles(Collections.singleton(userRole));

        final User result = userRepository.save(user);

        return RegisterResponse.builder().email(result.getEmail()).name(result.getName()).build();
    }

}
