package com.xs.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("200","success"),
    FAIL("500","failed"),
    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");

    private static final List<ResponseStatus> HTTP_STATUS_ALL = List.of(
            SUCCESS, FAIL, HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
    );
    private final String responseCode;
    private final String description;
}
