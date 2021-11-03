package com.loyalty.avenger.cashlock.components.validator

import com.loyalty.avenger.cashlock.components.util.Logger
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
@EnableAutoConfiguration
class AuthValidator extends BaseValidator {

    boolean validateAccessToken(String token) {
        isFieldNotEmpty(token, "Access Token")
    }

    boolean validateScope(String scope) {
        isFieldNotEmpty(scope, "Scope")
    }


    boolean isFieldNotEmpty(String value, String fieldName) {
        boolean result = true
        if (value == null) {
            result = false
            Logger.logEventValidationError(fieldName, "Not null", "null")
        }
        else {
            Integer valueLength = value.length()
            Logger.logValidationValues("Field Value is not empty length = ${valueLength.toString()}", "Field Value is not empty", fieldName)
            if( valueLength == 0)  {
                Logger.logEventValidationError(fieldName, "Field Value is not empty length = ${valueLength.toString()}", "Field Value is not empty")
                result = false
            }
        }
        result
    }

}
