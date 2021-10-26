package com.loyalty.avenger.cashlock.components.constant


enum CollectorStatus {
    ACTIVE("31"),
    DORMANT("32"),
    EXPIRED("33"),
    SUSPENDED("249"),
    INACTIVE("250"),
    CANCELLED("251"),
    DELETED("615"),
    VICTIM("616"),
    MERGE_PENDING("799")

    private final String status

    private CollectorStatus(final String id) {
        status = id
    }

    String getValue() {
        return this.status
    }
}

