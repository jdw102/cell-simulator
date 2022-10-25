package cellsociety.model;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class GameOfLifeStateHandlerTest {

  @Test
  void testFigureOutNextState() throws IOException {
    int i = 1;
    try {
      for (i = 1; i < 10; i++) {
        CSVReader testReader = new CSVReader(new FileReader(
            getClass().getResource(
                    String.format("/cellsociety/data/game_of_life/stateHandlerTestData/test%s.csv", i))
                .getPath()
        ));
        CSVReader desiredResultReader = new CSVReader(new FileReader(
            getClass().getResource(
                    String.format("/cellsociety/data/game_of_life/stateHandlerTestData/test%s.csv", i))
                .getPath()
        ));
      }
    } catch (NullPointerException e) {
      // do nothing
    }
  }
}