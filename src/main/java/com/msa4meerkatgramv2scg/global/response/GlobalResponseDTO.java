package com.msa4meerkatgramv2scg.global.response;

import com.msa4meerkatgramv2scg.global.response.constant.CustomResponseCode;

public record GlobalResponseDTO<T>(
    String code
    , String message
    , T data
) {
    public static <T> GlobalResponseDTO<T> from(CustomResponseCode customResponseCode, T data) {
        return new GlobalResponseDTO<T>(customResponseCode.getCode(), customResponseCode.name(), data);
    }

    public static GlobalResponseDTO<Void> from(CustomResponseCode customResponseCode) {
        return new GlobalResponseDTO<Void>(customResponseCode.getCode(), customResponseCode.name(), null);
    }

    public static <T> GlobalResponseDTO<T> success(T data) {
        return GlobalResponseDTO.<T>from(CustomResponseCode.SCG_SUCCESS, data);
    }

    public static GlobalResponseDTO<Void> success() {
        return GlobalResponseDTO.<Void>from(CustomResponseCode.SCG_SUCCESS);
    }
}
