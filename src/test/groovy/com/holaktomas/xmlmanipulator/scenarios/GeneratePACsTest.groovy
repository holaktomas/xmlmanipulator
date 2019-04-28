package com.holaktomas.xmlmanipulator.scenarios

import com.holaktomas.xmlmanipulator.Utils
import spock.lang.Specification

class GeneratePACsTest extends Specification {

    def 'Replicate records, MsgId should not be the same'() {
        expect:
        Node xml = Utils.getXmlFromFile('src/test/resources/1_Pac_valid.xml')
        Node newXml = new XmlParser().parseText('''
<Document/>
''')
        // get records
        def originalRecords = xml.FIToFICstmrCdtTrf
        assert originalRecords.size() == 4
        def baseRecord = originalRecords[0]

        // append new nodes
        (1..10).each {
            Node newRecord = baseRecord.clone()
            // dynamic values
            newRecord.GrpHdr.MsgId[0].value()[0] = it
            // add
            newXml.append(newRecord)
        }

        Utils.writeXmlToFile(newXml)
    }
}