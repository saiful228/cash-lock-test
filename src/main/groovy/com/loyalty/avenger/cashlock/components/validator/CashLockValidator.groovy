package com.loyalty.avenger.cashlock.components.validator

import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockRequest
import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockResponse
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockRequest
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockResponse
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.GetMemberProfileV2Response
import org.apache.http.HttpStatus

class CashLockValidator extends BaseValidator {

    static Boolean validateTheStatusOfCashLock(GetCashLockResponse actualResult, String expValue) {
        Boolean result = true
        if (!validateNumericValues(actualResult.statusCode, HttpStatus.SC_OK, "Status Code")){result=false}
        if (!validateStringValues(actualResult.cashRedemptionLocked.toString(), expValue, "cashRedemptionLocked")){result = false}
        result
    }

    static Boolean validateTheStatusOfCashLockAfterUpdating(UpdateCashLockResponse actualResult, String expValue) {
        Boolean result = true
        if (!validateNumericValues(actualResult.statusCode, HttpStatus.SC_OK, "Status Code")){result=false}
        if (!validateStringValues(actualResult.cashRedemptionLocked.toString(), expValue, "cashRedemptionLocked")){result = false}
        result
    }


    static Boolean validateErrorMessage(def responseBody, String errorMessage, String errorCode) {
        Boolean result = true
        Map errorResponse = new groovy.json.JsonSlurper().parseText(responseBody)
        if ( !validateStringValues(errorResponse.get("message"), errorMessage, "Error Message") ) {result = false}
        if ( !validateStringValues(errorResponse.get("error"), errorCode, "Error Code") ) {result = false}
        result
    }
}
