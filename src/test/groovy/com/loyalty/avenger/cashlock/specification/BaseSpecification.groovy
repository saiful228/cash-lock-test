package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.TestApplication
import com.loyalty.avenger.cashlock.components.repository.CeloRepository
import com.loyalty.memberservice.MemberService
import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification



import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



@ActiveProfiles("int")
@SpringBootTest(classes = TestApplication)
class BaseSpecification extends Specification{



    @Shared
    Boolean startFlag = true

    @Shared
    String cardNumber

    @Autowired
    CeloRepository repository

    @Autowired
    Member memberService

//    def createAuth0EmailDBRecord() {
//        String createToken = getTokenClient.getCreateToken().accessToken
//        Logger.logMessage("Auth0 Create Token: $createToken")
//        createDataClient.generateNewTestDataSet(createToken, enrollMemberResponse.getResponseContext().cardNumber, enrollMemberRequest.getContactDetails().getEmail())
//        Logger.logMessage(createDataClient.currentRequest.toString())
//    }


}
