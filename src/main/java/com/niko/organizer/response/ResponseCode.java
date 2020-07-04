package com.niko.organizer.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESSFUL(200),
    MISSING_DATA(400),
    SERVER_ERROR(500);

    private final int value;
    ResponseCode(int value){
        this.value = value;
    }
}
