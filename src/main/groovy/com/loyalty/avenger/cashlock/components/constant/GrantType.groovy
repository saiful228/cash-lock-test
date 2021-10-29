package com.loyalty.avenger.cashlock.components.constant

enum GrantType {

    CLIENT_CREDENTIALS("password")


    private final String value

    private GrantType(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}
