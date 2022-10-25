package cellsociety.model;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class GameOfLifeStateHandlerTest {

  /*
  options:
    load file into a neighborhood
    call figureoutnextstate on that neighborhood
    load correct output into neighborhood and make sure they match
   */

  @Test
  void testFigureOutNextState() throws IOException, CsvValidationException {
//    File neighborhoodFile = new File("test.txt");
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
      System.out.println(i);
    }

    //    File testFile = new File(DEFAULT_RESOURCE_FOLDER + "game_of_life/blinkers.csv");

//    System.out.println("yeeeee");
//    reader.readNext();
//    System.out.println(reader.readNext()[0]);
  }


}