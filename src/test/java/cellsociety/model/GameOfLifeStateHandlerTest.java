package cellsociety.model;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.controller.CellSpawner;
import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.Test;

public class GameOfLifeStateHandlerTest {

  @Test
  void testFigureOutNextState() throws IOException, CsvValidationException {
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

        List<CellModel> cellModels = loadCellModelArray(testReader);
        CellModel center = cellModels.get(4);
        cellModels.remove(4);

        Neighborhood startingNeighborhood = new Neighborhood(center,
            cellModels.toArray(new CellModel[0]));
        GameOfLifeStateHandler stateHandler = new GameOfLifeStateHandler();
        State resultingState = stateHandler.figureOutNextState(startingNeighborhood);

        desiredResultReader.readNext();
        desiredResultReader.readNext();
        String desiredResultString = desiredResultReader.readNext()[0];
        State desiredResult;
        if (desiredResultString.equals("1")) {
          desiredResult = new AliveState();
        } else if (desiredResultString.equals("0")) {
          desiredResult = new DeadState();
        } else {
          throw new RuntimeException("Invalid CSV file given to this test");
        }

        assertSame(desiredResult.getStateEnum(), resultingState.getStateEnum());
      }

    } catch (NullPointerException e) {
      // do nothing
    }
  }

  private Neighborhood getNeighborhood(CSVReader reader)
      throws IOException, CsvValidationException {
    List<CellModel> cellModels = loadCellModelArray(reader);
    CellModel center = cellModels.get(4);
    cellModels.remove(4);

    return new Neighborhood(center, cellModels.toArray(new CellModel[0]));
  }

  private List<CellModel> loadCellModelArray(CSVReader csvReader)
      throws IOException, CsvValidationException {
    List<CellModel> cellModels = new ArrayList<>();
    csvReader.readNext();
    for (String[] line : csvReader) {
      for (String item : line) {
        if (item.equals("1")) {
          cellModels.add(new CellModel(new AliveState()));
        } else if (item.equals("0")) {
          cellModels.add(new CellModel(new DeadState()));
        }
      }
    }

    return cellModels;
  }

}