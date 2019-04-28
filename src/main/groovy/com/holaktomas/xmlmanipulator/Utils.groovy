package com.holaktomas.xmlmanipulator

import groovy.xml.XmlUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Utils {

    final static NEW_FILE_PREFIX = 'Manipulated_'
    final static NEW_FILE_EXT = 'xml'
    final static NEW_FILE_FOLDER_NAME = 'out'
    final static USER_DIR = 'user.dir'

    final static Logger logger = LoggerFactory.getLogger(this.class)

    static Node getXmlFromFile(String pathUnderUserDir) {
        File file = new File(System.getProperty(USER_DIR) + '/' + pathUnderUserDir)
        logger.info("Going to use an input file located in: ${file.toString()}")
        return new XmlParser().parse(file)
    }

    static void writeXmlToFile(Node xml) {
        FileWriter writer = new FileWriter(new File(System.getProperty(USER_DIR) + '/' + NEW_FILE_FOLDER_NAME + '/' + NEW_FILE_PREFIX
                + String.valueOf(System.currentTimeMillis()) + '.' + NEW_FILE_EXT))
        XmlUtil.serialize(xml, writer)
        logger.info("New file written into: ${writer.lock.path}")
    }
}