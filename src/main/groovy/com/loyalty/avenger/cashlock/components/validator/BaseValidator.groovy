package com.loyalty.avenger.cashlock.components.validator

import com.loyalty.avenger.cashlock.components.util.Logger
import org.junit.Assert


class BaseValidator {


    static boolean validateStringValues(String actual, String expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual, expected, fieldName)
        if(!actual.equalsIgnoreCase(expected)) {
            Logger.logEventValidationError(fieldName, expected, actual)
            result = false
        }
        result
    }

    static boolean validateNumericValues(def actual, def expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual.toString(), expected.toString(), fieldName)
        if( actual!=expected ) {
            Logger.logEventValidationError(fieldName, expected.toString(), actual.toString())
            result = false
        }
        result
    }

    static boolean validateKeyValueNotNull(def actual, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual.toString(), fieldName)
        if( Assert.assertNull(actual)) {
            Logger.logEventValidationError(fieldName, actual.toString())
            result = false
        }
        result
    }

    static boolean validateDateValues(def actual, def expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual.toString(), expected.toString(), fieldName)
        if( !actual.isEqual(expected) ) {
            Logger.logEventValidationError(fieldName, expected.toString(), actual.toString())
            result = false
        }
        result
    }
}
