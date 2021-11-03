package com.loyalty.avenger.cashlock.components.type

import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import groovy.json.JsonOutput

import java.time.LocalDateTime

class EmailVerifyTableType {

    String cardNumber
    Integer versionId
    String action
    String channel
    Boolean  claimedFlag
    LocalDateTime created
    LocalDateTime expired
    String secureValue
    String tokenId
    String tokenStatus
    String nextAction
    Boolean nextActionCompleted
    LocalDateTime updated
    Integer originalVersion
    String resendRequested


    @Override
    String toString() {
        JsonOutput.toJson(
                [
                        cardNumber: cardNumber,
                        versionId: versionId,
                        action: action,
                        channel: channel,
                        claimedFlag: claimedFlag,
                        created: created.toString(),
                        expired: expired.toString(),
                        updated: updated.toString(),
                        secureValue: secureValue,
                        tokenId: tokenId,
                        tokenStatus: tokenStatus,
                        nextAction: nextAction,
                        nextActionCompleted: nextActionCompleted,
                        originalVersion: originalVersion,
                        resendRequested: resendRequested
                ]
        )
    }

    def setValuesFromMap(Map values) {
        try { this.cardNumber = values.get("card_number").getProperties().get("s") } catch (Exception e) {}
        try { this.versionId = values.get("version_id").getProperties().get("n").toString().toInteger() } catch (Exception e) {}
        try { this.action = values.get("action").getProperties().get("s") } catch (Exception e) {}
        try { this.channel = values.get("channel").getProperties().get("s") } catch (Exception e) {}
        try { this.claimedFlag = values.get("claimed_flag").getProperties().get("BOOL") } catch (Exception e) {}
        try { this.created = TestDataUtils.timeStampToLocalDateTime(values.get("created_timestamp").getProperties().get("s")) } catch (Exception e) {}
        try { this.expired = TestDataUtils.timeStampToLocalDateTime(values.get("expired_time").getProperties().get("s")) } catch (Exception e) {}
        try { this.secureValue = values.get("secure_value").getProperties().get("s") } catch (Exception e) {}
        try { this.tokenId = values.get("token_id").getProperties().get("s") } catch (Exception e) {}
        try { this.tokenStatus = values.get("token_status").getProperties().get("s") } catch (Exception e) {}
        try { this.nextAction = values.get("nxt_action").getProperties().get("s") } catch (Exception e) {}
        try { this.nextActionCompleted = values.get("nxt_action_completed").getProperties().get("BOOL") } catch (Exception e) {}
        try { this.updated = TestDataUtils.timeStampToLocalDateTime(values.get("updated_timestamp").getProperties().get("s")) } catch (Exception e) {}
        try { this.originalVersion = values.get("version_id").getProperties().get("n").toString().toInteger() } catch (Exception e) {}
        try { this.resendRequested = values.get("resend_requested_by").getProperties().get("s") } catch (Exception e) {}
    }

}
