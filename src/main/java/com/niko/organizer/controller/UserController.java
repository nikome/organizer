package com.niko.organizer.controller;


import com.niko.organizer.model.payload.UserSummary;
import com.niko.organizer.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserSummary>> getCurrentUser(){
        return new ResponseEntity<>(BaseResponse.success(null), HttpStatus.OK);
    }
}
