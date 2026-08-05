package com.meerkatgramv2scg.global.response.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomResponseCode {
    SUCCESS(HttpStatus.OK, "00")
    ,SCG_NOT_REGISTERED_ERROR(HttpStatus.UNAUTHORIZED, "E01")
    , SCG_UNAUTHENTICATED_ERROR(HttpStatus.UNAUTHORIZED, "E02")
    , SCG_UNAUTHORIZED_ERROR(HttpStatus.FORBIDDEN, "E03")
    , SCG_INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "E04")
    , SCG_NOT_FOUND_DATA_ERROR(HttpStatus.NOT_FOUND, "E10")
    , SCG_DUPLICATED_DATA_ERROR(HttpStatus.CONFLICT, "E11")
    , SCG_INVALID_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "E21")
    , SCG_FILE_MANAGED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E40")
    , SCG_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "E50")
    , SCG_DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E80")
    , SCG_SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E99")
    ;

    private final HttpStatus httpStatus;
    private final String code;

    CustomResponseCode(HttpStatus httpStatus, String code) {
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
