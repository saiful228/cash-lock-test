package com.loyalty.avenger.cashlock.components.constant

enum ValidAddress {
    ADDRESS1([
            line1: "100 Queen St",
            line2: null,
            line3: null,
            city: "Toronto",
            province: "ON",
            postalCode: "M5H2N1"

    ]),
    ADDRESS2([
            line1: "12 Brant St",
            line2: null,
            line3: null,
            city: "Toronto",
            province: "ON",
            postalCode: "M5V2M1"

    ]),
    ADDRESS3([
            line1: "30 Mercer St",
            line2: null,
            line3: null,
            city: "Toronto",
            province: "ON",
            postalCode: "M5V1H3"

    ]),
    ADDRESS4([
            line1: "260 King St W",
            line2: null,
            line3: null,
            city: "Toronto",
            province: "ON",
            postalCode: "M5V1H9"
    ])

    private final Map value

    private ValidAddress(final Map id) {
        value = id
    }

    Map getValue() {
        return this.value
    }
}