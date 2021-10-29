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

@ActiveProfiles("int")
@SpringBootTest(classes = TestApplication)
class BaseSpecification extends Specification{
    /*def "first try"(){
        expect: "True"
        true
    }*/

    @Shared
    Boolean startFlag = true

    @Shared
    String cardNumber

    @Autowired
    CeloRepository repository

    @Autowired
    Member memberService



}
