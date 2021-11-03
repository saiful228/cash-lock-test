package com.loyalty.avenger.cashlock.components.constant

enum Channels {

    WEB("WEB"),
    MOBILE("MOBILE"),
    VERIFYAPI("VERIFYAPI"),
    CELO_GREEN("GRSC")


    private final String value

    private Channels(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
