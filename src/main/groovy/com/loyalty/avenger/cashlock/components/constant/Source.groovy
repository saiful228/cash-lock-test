package com.loyalty.avenger.cashlock.components.constant

enum Source {

    CSEN02LMGC("CSEN02LMGC")

    private final String value

    private Source(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}
