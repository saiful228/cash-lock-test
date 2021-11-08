package com.loyalty.avenger.cashlock.components.constant

enum NextAction {
    SET_PIN("set_pin")

    private final String value

    private NextAction(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}