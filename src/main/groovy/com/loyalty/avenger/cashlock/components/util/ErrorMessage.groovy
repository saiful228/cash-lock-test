package com.loyalty.avenger.cashlock.components.util

class ErrorMessage {

    String internalMessage
    String localizedMessage
    String errorType
    String errorCode


    String getBadRequestError () {
        Map bodyMap = [
                internalMessage: internalMessage,
                localizedMessage: localizedMessage,
                errorType: errorType,
                errorCode: errorCode
        ]
        TestDataUtils.mapToJson(bodyMap)
    }

}
