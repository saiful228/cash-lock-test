package com.loyalty.avenger.cashlock.etc.encryptString

import com.loyalty.avenger.cashlock.components.util.Encryptor
import com.loyalty.avenger.cashlock.components.util.Logger
import spock.lang.Specification

class DecryptString extends Specification{

    def "Decrypt String" () {
        setup: "Decrypt String"

        String myString = "DcrTDRHAhjJmEZA2q0TlSM2/mJwnbxEv5WJ2TLXQ1mkZiEfW7nRC3xyATH/oBU6ja1DtDKeS4uFOyALxShIWtG37D5IyzpC4bFOadf5mBm/0ETffu1wc2/nFWImG9uKKLDKrnUsNvZ5gpj4dKwgYUwyqGXPVBf6aBdRpth6CA+IVLN7itxQyspHRTp+saw5QPzLwfcPAz0zD2bCBEJTrGHNdOq+UqnlSeNn5Xgol8t8M1wop/cQDTk1cnEMA0qsjMONW0hG1BV9zMeUv2SCiTrK7OEtSyOFHOjiu5pc0wQGZvGsKEESPqAesxAifdxyO1V0upRAj5nNf78pxbi8lVg=="
        Logger.logMessage("Decrypted String: " + Encryptor.decrypt(myString))
        expect: "Done"
        true
    }
}
