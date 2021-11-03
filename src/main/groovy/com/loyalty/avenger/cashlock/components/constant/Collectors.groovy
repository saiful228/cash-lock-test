package com.loyalty.avenger.cashlock.components.constant

enum Collectors {
    ACTIVE(["84000665700", "80147543239", "84098311078", "80147546388", "80147547305"]),
    EMAIL_DUPLICATE(["84098535044", "80147527808"]),
    EMAIL_INVALID(["80147544693"]),
    EMAIL_STATUS_MULTIPE_ENTRIES(["80000037596"])


    private final List<String> issuerList

    private Collectors(final List<String> id) {
        issuerList = id
    }

    List<String> getId() {
        return this.issuerList
    }
}