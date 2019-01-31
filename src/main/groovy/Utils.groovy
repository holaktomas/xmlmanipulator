import groovy.xml.XmlUtil

class Utils {
    static Node getXmlFromFile(String path) {
        return new XmlParser().parse(new File(System.getProperty("user.dir") + '/' + path))
    }

    static void writeXmlToFile(Node xml) {
        def writer = new FileWriter(new File(System.getProperty("user.dir") + '/' + 'build/' + 'Manipulated_'
                + String.valueOf(System.currentTimeMillis()) + '.xml'))
        XmlUtil.serialize(xml, writer)
    }
}