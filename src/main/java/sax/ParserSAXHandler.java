package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sax.model.Levels;
import sax.model.ModuleTraining;
import sax.model.Training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParserSAXHandler extends DefaultHandler {
    private static final String TAG_NAME_TRAINING = "name";
    private static final String TAG_MODULE_MODULES = "module";
    private static final String TAG_ID_MODULE = "id";
    private static final String TAG_TITLE_MODULE = "title";
    private static final String TAG_DESCRIPTION_MODULE = "description";
    private static final String TAG_PUBLISHDATE_MODULE = "publishDate";
    private static final String TAG_LEVELS_TRAINING = "levels";

    Training training = new Training();
    List<ModuleTraining> moduleTrainingList = new ArrayList<>();

    private String currentTagName;
    private int idModule;
    private String titleModule;
    private String descriptionModule;
    private LocalDate publishDateModule;

    public Training getTraining() {
        return training;
    }

    @Override
    public void endDocument() throws SAXException {
        training.setModuleTrainingList(moduleTrainingList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(TAG_MODULE_MODULES)) {
            ModuleTraining moduleTraining =
                    new ModuleTraining(idModule, titleModule, descriptionModule, publishDateModule);
            moduleTrainingList.add(moduleTraining);
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) {
            return;
        } else if (currentTagName.equals(TAG_NAME_TRAINING)) {
            training.setName(new String(ch, start, length));
        } else if (currentTagName.equals(TAG_LEVELS_TRAINING)) {
            training.setLevels(Levels.valueOf(new String(ch, start, length).toUpperCase(Locale.ROOT)));
        } else if (currentTagName.equals(TAG_ID_MODULE)) {
            idModule = Integer.parseInt(new String(ch, start, length));
        } else if (currentTagName.equals(TAG_TITLE_MODULE)) {
            titleModule = new String(ch, start, length);
        } else if (currentTagName.equals(TAG_DESCRIPTION_MODULE)) {
            descriptionModule = new String(ch, start, length);
        } else if (currentTagName.equals(TAG_PUBLISHDATE_MODULE)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            publishDateModule = LocalDate.parse(new String(ch, start, length), formatter);
        }
    }
}
