package com.loyalty.avenger.cashlock.specification

import com.loyalty.avenger.cashlock.components.TestApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("int")
@SpringBootTest(classes = TestApplication)
class BaseSpecification extends Specification{
    def "first try"(){
        expect: "True"
        true
    }
}
