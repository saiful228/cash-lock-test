package com.loyalty.avenger.cashlock.etc.spec2excel

import com.loyalty.avenger.cashlock.components.util.SpecToExcel

import spock.lang.Specification

class ExtractTestCases extends Specification {

    def setup() {
        String rootFolder = "src//test//groovy//com//loyalty//cashlock//components//Specification//"
        String destinationRootFolder = "src//test//groovy//com//loyalty//cashlock//etc///excelScenarios//"



        SpecToExcel.update(rootFolder,destinationRootFolder)
    }

    def "Done" () {
        expect: "Done"
            true
    }
}