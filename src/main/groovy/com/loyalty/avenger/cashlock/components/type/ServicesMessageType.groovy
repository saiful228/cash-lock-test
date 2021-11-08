package com.loyalty.avenger.cashlock.components.type

import groovy.json.JsonOutput

import java.time.LocalDateTime

class ServicesMessageType {
    String code
    String param
    String message
    String localizedMessage
    String token
    String nextAction
    String action
    String cardNumber
    String errorType
    Integer status
    String channel


    //CheckStatus Related
    String email
    String emailStatus
    LocalDateTime createdTimeStamp
    Boolean nextActionCompleted

    //Auth0 Related
    String error


    @Override
    String toString() {
        Map parameters = [:]
        if(code!= null && code.length() > 0 ) {parameters["code"] = code}
        if(param!= null && param.length() > 0 ) {parameters["param"] = param}
        if(message!= null && message.length() > 0 ) {parameters["message"] = message}
        if(cardNumber!= null && cardNumber.length() > 0 ) {parameters["cardNumber"] = cardNumber}
        if(token!= null && token.length() > 0 ) {parameters["token"] = token}
        if(nextAction!= null && nextAction.length() > 0 ) {parameters["nextAction"] = nextAction}
        if(action!= null && action.length() > 0 ) {parameters["action"] = action}
        //CheckStatus Related
        if(email!= null && email.length() > 0 ) {parameters["email"] = email}
        if(emailStatus!= null && emailStatus.length() > 0 ) {parameters["emailStatus"] = emailStatus}
        if(createdTimeStamp!= null) {parameters["createdTimeStamp"] = createdTimeStamp}
        if(nextActionCompleted!= null) {parameters["nextActionCompleted"] = nextActionCompleted}

        JsonOutput.toJson(parameters)
    }

    String getSendVerificationError() {
        JsonOutput.toJson(
            [
                code: code,
                param: param,
                message: message
            ]
        )
    }

    String getVerifyError() {
        JsonOutput.toJson(
                [
                        message: message,
                        code: code
                ]
        )
    }

    String getSuccessfullyVerifiedMessage() {
        JsonOutput.toJson(
                [
                        cardNumber: cardNumber,
                        message: message,
                        action: action,
                        channel: channel
                ]
        )
    }

    String getAlreadyVerifiedMessage() {
        JsonOutput.toJson(
                [
                        nextAction: nextAction,
                        cardNumber: cardNumber,
                        message: message,
                        action: action,
                        channel: channel
                ]
        )
    }

    String getCheckStatusMessage() {
        JsonOutput.toJson(
                [
                        cardNumber: cardNumber,
                        email: email,
                        emailStatus: emailStatus,
                        createdTimeStamp: createdTimeStamp.toString(),
                        nxtAction: nextAction,
                        nxtActionCompleted: nextActionCompleted
                ]
        )
    }

    String getNotificationErrorMessage() {
        Map message = [
                code: code,
                message: message
        ]
        JsonOutput.toJson(
                [
                        error: message,
                        status: status
                ]
        ).toString()
    }

    String getAuthError() {
        JsonOutput.toJson(
                [
                        error: error,
                        error_description: message
                ]
        )
    }

    String getPersonalizationError() {
        JsonOutput.toJson(
                [
                        internalMessage: message,
                        localizedMessage: message,
                        errorType: errorType,
                        errorCode: code
                ]
        )
    }


    String getProfileUpdateError() {
        JsonOutput.toJson(
                [
                        internalMessage: message,
                        localizedMessage: localizedMessage,
                        errorType: errorType,
                        errorCode: code
                ]
        )
    }

    String getEnrollmentError() {
        getPersonalizationError()
    }
}
