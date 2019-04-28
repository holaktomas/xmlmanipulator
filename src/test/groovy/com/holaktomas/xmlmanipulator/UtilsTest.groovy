package com.holaktomas.xmlmanipulator

import spock.lang.Specification

class UtilsTest extends Specification {

    def 'getXmlFromFile'() {
        when:
        Utils.getXmlFromFile('src/test/resources/1_Pac_valid.xml')
        then:
        noExceptionThrown()
    }

    def 'localize records'() {
        given:
        def xml = Utils.getXmlFromFile('src/test/resources/1_Pac_valid.xml')
        when:
        def records = xml.FIToFICstmrCdtTrf
        then:
        records.size() == 4
    }
}