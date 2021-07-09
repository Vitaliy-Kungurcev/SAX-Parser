package sax;

import org.xml.sax.SAXException;
import sax.model.Training;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ParserSAX {


    public Training parser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ParserSAXHandler handler = new ParserSAXHandler();
        SAXParser parser = factory.newSAXParser();

        URL url = this.getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getPath());
        parser.parse(file, handler);

        return handler.getTraining();
    }
}
