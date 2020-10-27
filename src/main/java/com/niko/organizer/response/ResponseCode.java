package com.niko.organizer.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESSFUL(200),
    MISSING_DATA(400),
    USER_ACCOUNT_EXISTS(409),
    INVALID_CREDENTIAL(401),
    SERVER_ERROR(500);

    private final int value;
    ResponseCode(int value){
        this.value = value;
    }
}
