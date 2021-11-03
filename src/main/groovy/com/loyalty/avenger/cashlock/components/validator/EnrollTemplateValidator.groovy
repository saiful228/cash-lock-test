package com.loyalty.avenger.cashlock.components.validator

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
@EnableAutoConfiguration
class EnrollTemplateValidator extends BaseValidator {

    final static String EXEMPTED_LINE_PATTERN = "verify?token="
    final static String EXEMPTED_LINE_PATTERN_EMAIL_UPDATE = "<!--Begin CONTAINER-->"


    boolean validatePageLine(String actualPageLine, String expectedPageLine) {
        boolean result = true
        if( !actualPageLine.contains(EXEMPTED_LINE_PATTERN) && !actualPageLine.contains(EXEMPTED_LINE_PATTERN_EMAIL_UPDATE)) {
            if(!validateStringValues(actualPageLine.trim(), expectedPageLine.trim(), "HTML template line")) {
                result=false
            }
        }
        result
    }
}
