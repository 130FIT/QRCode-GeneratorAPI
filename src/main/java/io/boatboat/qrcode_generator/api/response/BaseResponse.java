package io.boatboat.qrcode_generator.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse {
    private String message;
    private int status;
}
