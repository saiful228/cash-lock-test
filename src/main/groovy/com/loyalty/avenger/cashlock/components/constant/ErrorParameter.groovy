package com.loyalty.avenger.cashlock.components.constant

enum ErrorParameter {

    EMAIL("email"),
    CHANNEL("channel"),
    ID("id")

    private final String value

    private ErrorParameter(final String key) {
        this.value = key
    }

    String getValue() {
        this.value
    }

}
