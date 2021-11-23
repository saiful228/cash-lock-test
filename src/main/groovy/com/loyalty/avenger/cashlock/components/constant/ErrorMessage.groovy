package com.loyalty.avenger.cashlock.components.constant

enum ErrorMessage {

    UNATHORIZED("Unauthorized"),
    FORBIDDEN("User is not authorized to access this resource with an explicit deny"),
    EMAIL_INVALID("the email attribute in the request is invalid"),
    PROFILE_NOT_FOUND("Profile Not Found"),
    CARD_NUMBER_TOO_LONG("collector id must be a 11 digit number"),
    INVALID_LOGIN("Wrong email or password."),
    INVALID_CONVERTED_LOGIN("Invalid Email or Password."),
    ACCOUNT_IS_BLOCKED("Your account has been blocked after multiple consecutive login attempts. We've sent you an email with instructions on how to unblock it."),
    INVALID_CHANNEL("missing or channel should be between 2 to 20 characters"),
    UNSUCCESSFUL_AUTHENTICATION("UnsuccessfulAuthentication"),
    MERGED_MEMBER("MergedMember"),
    INACTIVE_MEMBER("InactiveMember"),
    LOCKED_MEMBER("LockedMember"),
    WRONG_EMAIL_PASSWORD("Wrong email or password."),
    CONVERTED_PIN_ERROR("Collector associated with this collector number has already been converted from using pin to email and password")

    private final String value

    private ErrorMessage(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
