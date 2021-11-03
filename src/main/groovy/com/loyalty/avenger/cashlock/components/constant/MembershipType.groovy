package com.loyalty.avenger.cashlock.components.constant

enum MembershipType {

    INDIVIDUAL("I"),
    BUSINESS("B"),
    CHARITY("C")

    private final String value

    private MembershipType(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}
