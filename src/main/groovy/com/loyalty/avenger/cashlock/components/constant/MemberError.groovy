package com.loyalty.avenger.cashlock.components.constant

enum MemberError {

    BUSINESS_RULE_ERROR_TYPE("BUSINESS_RULE"),
    BUSINESS_RULE_ERROR_CODE_DC_NV("ESB-MEM-DC-NV"),
    MEMBER_DUPLICATE_ERROR_MESSAGE("Member details are duplicate. Please modify")

    private final String value

    private MemberError(final String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }

}
