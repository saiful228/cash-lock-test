package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.constant.Channels
import com.loyalty.avenger.cashlock.components.helper.CeloHelper
import com.loyalty.avenger.cashlock.components.repository.CeloRepository
import com.loyalty.avenger.cashlock.components.type.EmailVerifyTableType
import com.loyalty.avenger.cashlock.components.util.Logger
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.ws.cpm.esb.amrp._2._1.member.UpdateContactDetailsType
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.UpdateMemberProfileRequest
import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.Member
import groovy.CreatePinRequest
import groovy.CreatePinResponse
import groovy.ETSContextType
import groovy.LanguageCodeType
import groovy.Security
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification


@ActiveProfiles("int")
@SpringBootTest(classes = com.loyalty.avenger.cashlock.components.TestApplication)
class BaseSpecification extends Specification {


    @Autowired
    CeloRepository celoRepository

    @Autowired
    Security securityClient

    @Autowired
    Member memberService

    @Shared
    EmailVerifyTableType record

    @Shared
    Boolean startFlag = true

    @Shared
    String cardNumber

    @Shared
    WellKnownFolderName emailFolder = WellKnownFolderName.JunkEmail

    def cleanEmailDuplicates(List<String> collectors) {
        try {
            collectors.each {
                UpdateMemberProfileRequest request = CeloHelper.getDefaultUpdateMemberRequest(it)
                request.getRequestContext().channel = Channels.WEB.getValue()
                request.contactDetails = new UpdateContactDetailsType()
                request.getContactDetails().setEmail(TestDataUtils.getRandomValidEmail())
                memberService.updateMemberProfile(request)
            }
        }
        catch (Exception e) {
            Logger.logError("Error while cleaning up email::"+e.getMessage())
        }
    }

    Boolean createSecretPin(String cardNumber, String secretPin) {
        Boolean result = true
        CreatePinRequest request = new CreatePinRequest()
        request.collectorNumber = new BigInteger(cardNumber)
        request.pin = secretPin
        request.confirmedPin = secretPin
        request.reminderQuestionAnswer = "Secret Answer"
        request.reminderQuestionId = 1
        request.agentId = "1"
        ETSContextType contextType = new ETSContextType()
        contextType.channel = "WEB"
        contextType.source = "ANY"
        contextType.languageCode = LanguageCodeType.EN_CA
        request.context = contextType
        try {
            CreatePinResponse response = securityClient.createPin(request)
            if( !response.ETSServiceVersion.equalsIgnoreCase("security-1.5.2-20120501")) {
                result = false
            }
        }
        catch(Exception e) {
            result = false
        }
        Logger.logMessage("Card Number: $cardNumber, New Security Pin: ${secretPin}")
        Thread.sleep(40000)
        result
    }
}
