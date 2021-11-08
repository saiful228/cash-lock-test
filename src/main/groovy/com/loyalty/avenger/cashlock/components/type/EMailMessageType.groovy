package com.loyalty.avenger.cashlock.components.type

import groovy.json.JsonOutput
import microsoft.exchange.webservices.data.core.service.item.EmailMessage

import java.time.LocalDateTime

class EMailMessageType {

    String emailItemId
    String subject
    String fromAddress
    String senderName
    LocalDateTime senddDate
    LocalDateTime receivedDate
    String emailBody

    EmailMessage emailMessage


    @Override
    String toString() {
        JsonOutput.toJson(
                [
                        emailItemId: emailItemId,
                        subject: subject,
                        fromAddress: fromAddress,
                        senderName: senderName,
                        senddDate: senddDate.toString(),
                        receivedDate: receivedDate.toString(),
                        emailBody: emailBody.toString()
                ]
        )
    }
}
