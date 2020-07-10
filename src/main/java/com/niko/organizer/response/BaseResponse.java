package com.niko.organizer.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse <T>{
    private ResponseCode code;
    private Object data;

    public static <T> BaseResponse<T> error(final ResponseCode code){
        return BaseResponse.<T>builder().code(code).data(null).build();
    }
    
    public static <T> BaseResponse<T> success(final T data){
        return BaseResponse.<T>builder().code(ResponseCode.SUCCESSFUL).data(data).build();
    }

}
