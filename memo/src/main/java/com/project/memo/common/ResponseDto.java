package com.project.memo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ResponseDto<D> {

    private final String resultCode;
    private final String message;
    private final D data;

    public static <D> ResponseDto<D> ofSuccess() {
        return new ResponseDto<>("SUCCESS", null, null);
    }

    public static <D> ResponseDto<D> ofSuccess(String message, D data) {
        return new ResponseDto<>("SUCCESS", message, data);
    }

    public static <D> ResponseDto<D> ofFail(String message) {
        return new ResponseDto<>("FAIL", message, null);
    }

}
