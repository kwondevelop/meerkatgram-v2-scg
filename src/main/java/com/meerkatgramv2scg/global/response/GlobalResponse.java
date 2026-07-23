package com.meerkatgramv2scg.global.response;

import com.meerkatgramv2scg.global.response.constant.CustomResponseCode;

public record GlobalResponse<T>(
    String code
    , String message
    , T data
) {
    public static <T> GlobalResponse<T> from(CustomResponseCode customResponseCode, T data) {
        return new GlobalResponse<T>(customResponseCode.getCode(), customResponseCode.name(), data);
    }

    public static GlobalResponse<Void> from(CustomResponseCode customResponseCode) {
        return new GlobalResponse<Void>(customResponseCode.getCode(), customResponseCode.name(), null);
    }

    public static <T> GlobalResponse<T> success(T data) {
        return GlobalResponse.<T>from(CustomResponseCode.SUCCESS, data);
    }

    public static GlobalResponse<Void> success() {
        return GlobalResponse.<Void>from(CustomResponseCode.SUCCESS);
    }
}
