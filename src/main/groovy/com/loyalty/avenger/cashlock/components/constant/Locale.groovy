package com.loyalty.avenger.cashlock.components.constant

enum Locale {

    EN("en-CA"),
    FR("fr-CA")

    private final String locale

    private Locale(final String locale) {
        this.locale = locale
    }

    String getValue() {
        return this.locale
    }

}
