package com.niko.organizer.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String roles;
    private Long id;
}
