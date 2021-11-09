package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.constant.Channels
import com.loyalty.avenger.cashlock.components.constant.Constants
import com.loyalty.avenger.cashlock.components.auth.GetTokenClient
import com.loyalty.avenger.cashlock.components.helper.CeloHelper
import com.loyalty.avenger.cashlock.components.constant.CollectorStatus
import com.loyalty.avenger.cashlock.components.constant.MembershipStatus
import com.loyalty.avenger.cashlock.components.repository.CeloRepository
import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockClient
import com.loyalty.avenger.cashlock.components.restclient.getcashlock.GetCashLockResponse
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockClient
import com.loyalty.avenger.cashlock.components.restclient.updatecashlock.UpdateCashLockResponse
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.GetMemberProfileV2Response
import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.Member
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.Test
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
    //@Shared
    //String auth0Token

//    def setup() {
//
//        String token = getTokenClient.getAccessToken("89029797679", "1100").accessToken
//        //auth0Token = getTokenClient.getAccessToken().accessToken
//        accessToken = "Bearer "+token
//        Logger.logMessage(accessToken)
//
//    }


    def "TC-1. When collector lock their redemption from  mobile (cash redemption = true) then collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.MOBILE.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }

    def  "TC-2. When collector unlock their redemption from  mobile (cash redemption = false) then collector is  able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.MOBILE.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }

    def "TC-3. When collector lock their redemption from  WEB (cash redemption = true) then collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }
    def "TC-4. When collector unlock their redemption from  WEB (cash redemption = false) then collector is  able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.WEB.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }
    def "TC-5. When collector lock their redemption from Celo Green (GRSC) (cash redemption = true) then collector is not able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.CELO_GREEN.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("true", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }
    def "TC-6. When collector unlock their redemption from  mobile (cash redemption = false) then collector is able to redeem points from their account ."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))

        and: "Expected current collector information is retrieved and create pin for that collector "
        createSecretPin(cardNumber, Constants.DEFAULT_SECRET_PIN)

        and: "Expected Auth0 information for this collector is retrieved from the expected channel "
        String accessToken = getTokenClient.getAccessToken(cardNumber, Constants.DEFAULT_SECRET_PIN, Channels.CELO_GREEN.getValue()).accessToken
        /*If accesstoken = null make or repeat line 63, retry get-access token  */
        //println(accessToken)
        //String token = "Bearer "+accessToken

        when: "update the customer preference to lock ( cash redeption: locked) "
        UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest("false", accessToken)
        and: "Response returns Http status 200 Ok"
        response.getStatusCode() == HttpStatus.SC_OK
        //println response.responseBody
        //println(response.statusCode)
        then: "Collector get the response that cash redemption is locked and collector is not able to redeem airmiles point from air"
        GetCashLockResponse getCashLockResponse = getCashLockClient.getCashLock(accessToken)
        and:
        and: "Response returns Http status 200 Ok"
        getCashLockResponse.getStatusCode() == HttpStatus.SC_OK
        and: "actual cash redemption request matches expected cash redemption "

    }


            //println(getCashLockResponse.responseBody)
        //println(getCashLockResponse.statusCode)


//        String getRandomActiveCollector(String securityPin = Constants.DEFAULT_SECRET_PIN) {
//        String cardNumber
//        for (int i =0; i <=20; i++) {
//            cardNumber = TestDataUtils.(
//                    repository.getRandomActiveIssuer())
//            if(createSecretPin(cardNumber, securityPin)) {
//                this.cardNumber = cardNumber
//                break
//            }
//        }
//        Logger.logMessage("Victim Card Number: $cardNumber")
//        cardNumber
//   }

    }
