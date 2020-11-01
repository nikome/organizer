package com.niko.organizer.controller;


import com.niko.organizer.model.payload.UserSummary;
import com.niko.organizer.repository.UserRepository;
import com.niko.organizer.response.BaseResponse;
import com.niko.organizer.security.CurrentUser;
import com.niko.organizer.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @GetMapping("/testapi")
    public ResponseEntity<BaseResponse<UserSummary>> testApi(){

        return new ResponseEntity<>(BaseResponse.success(null), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserSummary>> getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getEmail(), currentUser.getName());
        return new ResponseEntity<>(BaseResponse.success(userSummary), HttpStatus.OK);
    }
}
