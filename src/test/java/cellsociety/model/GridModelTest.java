package cellsociety.model;

import cellsociety.controller.Controller;
import cellsociety.controller.SimParser;
import cellsociety.controller.WrongFileTypeException;
import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridModelTest {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";

  /*
  Things to test:
  GridModel,
  updateState,
  changeCellState,


  SimParser simParser;
    try {
      simParser = new SimParser(simFile);
      // Give the view the info about the game
      GameDisplayInfo gameDisplayInfo = simParser.getGameDisplayInfo();
      displayView.setInfoText(gameDisplayInfo);

      File initStateCsv = simParser.getInitStateCsv();
      // Instantiate a CellSpawner
      StateHandler stateHandler = stateHandlerLoader.getStateHandler(gameDisplayInfo.type());
      InitialStateReader initialStateReader = new InitialStateReader(stateHandler, initStateCsv);
      CellSpawner cellSpawner = new CellSpawner(displayView.getGridView(), initialStateReader);
      NeighborhoodsLoader neighborhoodsLoader = new NeighborhoodsLoader(cellSpawner,
          DEFAULT_NEIGHBOR_DISTANCE); // for now use default, but later allow user to choose this
      gridModel = new GridModel(neighborhoodsLoader, stateHandler);
    } catch (IOException | CsvValidationException | WrongFileTypeException e) {
      displayView.showMessage(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
   */

  @BeforeEach
  void setup() throws IOException, WrongFileTypeException {
//    DisplayView view = new DisplayView(DEFAULT_LANGUAGE, new Stage());
//    Controller controller = new Controller(view);
//    view.setController(controller);


  }

  @Test
  void updateState() {

  }
}