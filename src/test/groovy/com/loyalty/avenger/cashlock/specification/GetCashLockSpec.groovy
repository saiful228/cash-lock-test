package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.constant.CollectorStatus
import com.loyalty.avenger.cashlock.components.helper.CeloHelper
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.GetMemberProfileV2Response


class GetCashLockSpec extends BaseSpecification {


    def "TC-1  For Channel WEB- When collector locked cash lock button the cash redemption should locked"(){

        given: "Randon collector is selected from celo db"
        String cardNumber = getRandomActiveCollector()
        and: "Expected Celo values are retrived from the database"
        GetMemberProfileV2Response expectedCelodata = memberService.getMemberProfileV2(CeloHelper.getDefaultGetMemberProfileRequest(cardNumber))
        Logger.logMessage("Selected card number = $cardNumber")




    }

    String getRandomActiveCollector(){
        TestDataUtils.getRandomValueFromList(repository.getRandomCollectorsListByStatus(CollectorStatus.ACTIVE.getValue()))
    }

}









