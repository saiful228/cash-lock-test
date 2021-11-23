package com.loyalty.avenger.cashlock.components.constant

enum ErrorCode {

    REQUEST_OBJECT("InvalidRequestObject"),
    INVALID_GRANT("invalid_grant"),
    TOO_MANY_ATTEMPTS("too_many_attempts"),
    ACCESS_DENIED("access_denied"),
    UNAUTHORIZED("unauthorized")

    private final String value

    private ErrorCode(final String key) {
        this.value = key
    }

    String getValue() {
        this.value
    }

}
