package com.loyalty.avenger.cashlock.components.type

import groovy.json.JsonOutput

class OptOutAccParametersType {

    String cardNumber
    String campaignCode
    String sourceCode
    String treatmentCode
    String lang

    @Override
    String toString() {
        JsonOutput.toJson(
                [
                        cardNumber: cardNumber,
                        campaignCode: campaignCode,
                        sourceCode: sourceCode,
                        treatmentCode: treatmentCode,
                        lang: lang
                ]
        )
    }
}
