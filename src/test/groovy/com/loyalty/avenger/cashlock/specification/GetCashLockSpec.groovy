package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.auth.GetTokenClient
import com.loyalty.avenger.cashlock.components.helper.CeloHelper
import com.loyalty.avenger.cashlock.components.constant.CollectorStatus
import com.loyalty.avenger.cashlock.components.constant.MembershipStatus
import com.loyalty.avenger.cashlock.components.repository.CeloRepository
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.GetMemberProfileV2Response
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
    @Shared
    String accessToken

    //@Shared
    //String auth0Token

    def setup() {
        if( startFlag == true) {
            accessToken = getTokenClient.getAccessToken().accessToken
            //auth0Token = getTokenClient.getAuth0Token().accessToken
            Logger.logMessage(accessToken)
            startFlag = false
        }
    }






    def "TC-1. Valid random short info request for active collector should return 200 Ok status with current collector information." (){
        given: "Valid active collector number is provided"
        String cardNumber = TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatusRelaxed(MembershipStatus.ACTIVE.getValue(), 100))
        and: "Expected current collector information is retrieved"
        GetMemberProfileV2Response expectedCollectorInfo = memberService.getMemberProfileV2(CeloHelper.getDefaultGetMemberProfileRequest(cardNumber))

        and: "Expected Auth0 information for this collector is retrieved"
        GetInfoResponse expectedAuth0Info =  getInfoClient.getInfoForCollector(cardNumber, auth0Token)


//        when: "Basic info request is sent to collector api using this card number."
//        ShortInfoResponse response = shortInfoClient.sendShortInfoRequest(cardNumber, accessToken)
//        then: "Response contains 200 OK status"
//        ShortInfoApiValidator.validateNumericValues(response.status, HttpStatus.SC_OK, "Response status")
//        and: "Actual short info response values match expected values for this collector"
//        ShortInfoApiValidator.validateShortInfo(response, expectedCollectorInfo)
//        and: "Actual auth0 info matches expected auth0 info"
//        ShortInfoApiValidator.validateAuth0Info(response, expectedAuth0Info)
    }

}









