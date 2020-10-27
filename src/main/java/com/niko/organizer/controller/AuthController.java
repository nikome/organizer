package com.niko.organizer.controller;

import com.niko.organizer.exception.AppException;
import com.niko.organizer.model.Role;
import com.niko.organizer.model.RoleName;
import com.niko.organizer.model.User;
import com.niko.organizer.payload.LoginRequest;
import com.niko.organizer.payload.SignUpRequest;
import com.niko.organizer.repository.RoleRepository;
import com.niko.organizer.repository.UserRepository;
import com.niko.organizer.response.BaseResponse;
import com.niko.organizer.response.LoginResponse;
import com.niko.organizer.response.RegisterResponse;
import com.niko.organizer.response.ResponseCode;
import com.niko.organizer.service.AuthenticationService;
import com.niko.organizer.service.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<RegisterResponse>> signUp(@Valid @RequestBody final SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(BaseResponse.error(ResponseCode.USER_ACCOUNT_EXISTS), HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole =roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        final Optional<RegisterResponse> newUserResponse = Optional.ofNullable(authenticationService.registerUser(signUpRequest));
        if(newUserResponse.isPresent()){
            return new ResponseEntity<>(BaseResponse.success(newUserResponse.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(BaseResponse.error(ResponseCode.SERVER_ERROR), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody final LoginRequest loginRequest) {
        final Optional<LoginResponse> login = Optional.ofNullable(authenticationService.getLoginCredentials(loginRequest));
        if(login.isPresent()) {
            return new ResponseEntity<>(BaseResponse.success(login.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(BaseResponse.error(ResponseCode.INVALID_CREDENTIAL), HttpStatus.UNAUTHORIZED);
    }
}
