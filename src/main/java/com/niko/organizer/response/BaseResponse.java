package com.niko.organizer.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse {
    private ResponseCode code;
    private Object data;

    public static <T> BaseResponse error(final ResponseCode code){
        return null;
    }

}
