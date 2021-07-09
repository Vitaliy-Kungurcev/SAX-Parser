package sax;

import org.junit.Test;
import org.xml.sax.SAXException;
import sax.model.Levels;
import sax.model.ModuleTraining;
import sax.model.Training;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ParserSAXTest {

    @Test
    public void parser() throws ParserConfigurationException, IOException, SAXException {
        ParserSAX parser = new ParserSAX();
        Training training = parser.parser("trainingSAX.xml");
        assertEquals(training,trainingCreat());
    }



    private static Training trainingCreat() {
        List<ModuleTraining> moduleTrainingList = new ArrayList<>();
        ModuleTraining moduleTraining1 = new ModuleTraining(1, "Collections", "description of the collections module", LocalDate.of(2021, 06, 15));
        ModuleTraining moduleTraining2 = new ModuleTraining(2, "Streams", "description of the streams module", LocalDate.of(2021, 06, 20));
        ModuleTraining moduleTraining3 = new ModuleTraining(3, "Tests", "description of the tests module", LocalDate.of(2021, 06, 25));

        moduleTrainingList.add(moduleTraining1);
        moduleTrainingList.add(moduleTraining2);
        moduleTrainingList.add(moduleTraining3);

        Training training = new Training("epam", moduleTrainingList, Levels.HARD);

        return training;
    }
}