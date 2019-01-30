import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import spock.lang.Specification

class Test extends Specification {

    def 'case1'() {
        expect:
        // open file
        GPathResult xml = new XmlSlurper().parse(getClass().getResource('1_Pac_valid.xml').toURI().toString())

        // get records
        def records = xml.FIToFICstmrCdtTrf
        assert records.size() == 4

        // take 1 record
        def baseRecord = records[0]

        // new xml
        xml.FIToFICstmrCdtTrf.replaceNode {}


        // create new file
        def writer = new FileWriter('out/' + 'Generated_' + String.valueOf(System.currentTimeMillis()) + '.xml')
        // pretty print
        XmlUtil.serialize(xml, writer)


        println 'xml.depthFirst().size() ' + xml.depthFirst().size()
        println 'xml.FIToFICstmrCdtTrf.size() ' + xml.FIToFICstmrCdtTrf.size()
    }

    def 'append'() {
//        // Edit File e.g. append an element called foo with attribute bar
//        xml.appendNode {
//            foo(bar: "bar value")
//        }
    }
}
