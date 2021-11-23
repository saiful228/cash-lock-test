package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.auth.AuthRequest
import com.loyalty.avenger.cashlock.components.auth.AuthResponse
import com.loyalty.avenger.cashlock.components.constant.Channels
import com.loyalty.avenger.cashlock.components.constant.Constants
import com.loyalty.avenger.cashlock.components.auth.GetTokenClient
import com.loyalty.avenger.cashlock.components.constant.MembershipStatus
import com.loyalty.avenger.cashlock.components.repository.CeloRepository
import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockClient
import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockResponse
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockClient
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockResponse
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.avenger.cashlock.components.validator.CashLockValidator
import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.Member
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared


class GetCashLockSpec extends BaseSpecification {
    @Autowired
    Member memberService
    @Autowired
    CeloRepository repository
    @Autowired
    GetTokenClient getTokenClient
    @Autowired
    UpdateCashLockClient updateCashLockClient
    @Autowired
    GetCashLockClient getCashLockClient
    @Shared
    String accessToken


    def "TC-1. When Active collector lock their redemption from  mobile channel=  (cash redemption = true )then the collector  is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.MOBILE.getValue()).accessToken
        when: "Update the customer preference to lock the cash redemption ( cash redemption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Cash redemption is locked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is locked "
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "true")
        def cashLockStatus = "true"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "Collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-2. When Active  collector unlock their redemption from  mobile (cash redemption = false) then the collector is  able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.MOBILE.getValue()).accessToken
        when: "Update the customer preference to unlock cash redemption ( cash redemption: unlocked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Cash redemption is unlocked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is unlocked"
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "false")
        def cashLockStatus = "false"
        then: "Collector get the response that cash redemption is unlocked and collector is able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-3. When Active  collector lock their redemption from  WEB (cash redemption = true) then  collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        when: "Update the customer preference to lock the cash redemption ( cash redemption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Cash redemption is locked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is locked "
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "true")
        def cashLockStatus = "true"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "Collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-4. When Active  collector unlock their redemption from  WEB (cash redemption = false) then collector is  able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        when: "Update the customer preference to unlock cash redemption ( cash redemption: unlocked)"
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Cash redemption is unlocked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is unlocked"
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "false")
        def cashLockStatus = "false"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-5. When Active  collector lock their redemption from Celo Green (GRSC) (cash redemption = true) then collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.CELO_GREEN.getValue()).accessToken
        when: "Update the customer preference to lock cash redemption ( cash redemption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Cash redemption is locked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is locked"
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "true")
        def cashLockStatus = "true"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "Collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-6. When Active collector unlock their redemption from  mobile (cash redemption = false) then collector is able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.CELO_GREEN.getValue()).accessToken
        when: "Update the customer preference to unlock cash redemption ( cash redemption: unlocked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Cash redemption is unlocked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is unlocked"
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "false")
        def cashLockStatus = "false"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }
    def "TC-7. When dormant collector lock their redemption from  WEB (cash redemption = true) then  collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.DORMANT.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        when: "Update the customer preference to lock the cash redemption ( cash redemption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Cash redemption is locked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is locked "
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "true")
        def cashLockStatus = "true"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "Collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }

    def "TC-8. When dormant collector unlock their redemption from  WEB (cash redemption = false) then collector is  able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)
        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        when: "Update the customer preference to unlock cash redemption ( cash redemption: unlocked)"
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Cash redemption is unlocked and response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        then: "Validate the update response that cash redemption is unlocked"
        CashLockValidator.validateTheStatusOfCashLockAfterUpdating(response, "false")
        def cashLockStatus = "false"
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from their account"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and: "collector actual cash redemption status  matches expected cash redemption status "
        CashLockValidator.validateTheStatusOfCashLock(getCashLockResponse, cashLockStatus)


    }



}