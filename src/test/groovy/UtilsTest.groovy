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

    def 'flow'() {
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
