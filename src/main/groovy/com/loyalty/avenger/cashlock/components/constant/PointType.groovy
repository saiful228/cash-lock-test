package com.loyalty.avenger.cashlock.components.constant


enum PointType {
    CASH("CASH"),
    DREAM("DREAM")

    private final String value

    private PointType(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}