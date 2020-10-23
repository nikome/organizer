package com.niko.organizer.service;

import com.niko.organizer.payload.SignUpRequest;
import com.niko.organizer.response.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse registerUser(SignUpRequest signUpRequest);
}
