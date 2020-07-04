package com.niko.organizer.model.payload;

import lombok.Data;

@Data
public class UserSummary { //Needs BUILDER, find out on Frog project
    private Long id;
    private String email;
    private String name;

    public UserSummary(Long id, String email, String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
