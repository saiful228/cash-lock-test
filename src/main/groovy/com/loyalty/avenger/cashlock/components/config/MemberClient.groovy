package com.loyalty.avenger.cashlock.components.config

import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.Member
import com.loyalty.ws.cpm.esb.amrp._2._1.member.wsdl.MemberServiceAMRP
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemberClient {

    @Value ('${loyalty.celo-member-soap.protocol}')
    String protocol

    @Value ('${loyalty.celo-member-soap.host}')
    String host

    @Value ('${loyalty.celo-member-soap.path}')
    String path

    @Bean
    Member memberService() {
        MemberServiceAMRP memberServiceAMRP = new MemberServiceAMRP(new URL(protocol, host, path))
        memberServiceAMRP.getMember()
    }

}
