package com.loyalty.avenger.cashlock.components.constant

enum Language {

    ENGLISH("E"),
    FRENCH("F"),

    ENGLISH_FULL("ENGLISH"),
    FRENCH_FULL("FRENCH")

    private final String channel

    private Language(final String channel) {
        this.channel = channel
    }

    String getValue() {
        return this.channel
    }

}
