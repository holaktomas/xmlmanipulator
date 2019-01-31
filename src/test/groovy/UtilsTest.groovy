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
<Document>
    <Record></Record>
</Document>
''')
        // get records
        def records = xml.FIToFICstmrCdtTrf
        assert records.size() == 4

        [1, 2, 3].each {
            def newRecord = records[0]
            println 'newRecord.GrpHdr.MsgId ' + newRecord.GrpHdr.MsgId
//            newRecord.GrpHdr.MsgId = it
            println newRecord.GrpHdr.MsgId
            // add
            newXml.appendNode(newRecord)
            println 'done'
        }

        // create new file
        Utils.writeXmlToFile(newXml)
    }
}
