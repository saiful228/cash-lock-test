package com.loyalty.avenger.cashlock.components.constant

enum SourceCode {

    ABC("abc"),
    CDE("cde"),
    SOBEYS("SBYAOPTOUT")


    private final String value

    private SourceCode(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
