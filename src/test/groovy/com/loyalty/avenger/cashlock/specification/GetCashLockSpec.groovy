package com.loyalty.avenger.cashlock.specification


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


    def "TC-1. Valid random cash lock request for active collector should return 200 Ok status with current collector information."() {

        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        //println cardNumber
        and: "Expected current collector information is retrieved"
        //String accessToken = getTokenClient.getAccessToken("80000009950", "1111").accessToken
        //println accessToken
        //String token = "Bearer "+accessToken
        println cardNumber
        //UpdateCashLockResponse response = updateCashLockClient.sendUpdateCashLockRequest(true, token)
        //println response.toString()



//    String getRandomActiveCollector(String securityPin = Constants.DEFAULT_SECRET_PIN) {
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
   }

    }
