package com.loyalty.avenger.cashlock.etc.encryptString

import com.loyalty.avenger.cashlock.components.util.Encryptor
import com.loyalty.avenger.cashlock.components.util.Logger
import spock.lang.Specification

class EncryptString extends Specification{

    def "Encrypt String" () {
        setup: "Encrypt String"

        String myString = "v6RfVeM1Nl10ce5BfMOJmgTsICWvO2VeQL6LYVQzdLkWvnvx0PNT3DFxuRTRVBN4"

        Logger.logMessage("Encrypted String: " + Encryptor.encrypt(myString))
        expect: "Done"
        true
    }
}
