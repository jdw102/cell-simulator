package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
import cellsociety.controller.WrongFileTypeException;
import java.awt.Dimension;
import java.io.IOException;
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
  /*
  public static final int DEFAULT_NEIGHBOR_DISTANCE = 1;
  private final DisplayView displayView;
  private GridModel gridModel;
  private StateHandlerLoader stateHandlerLoader;

  public void setupSimulation(File simFile) {
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
  }
  */

  /*
  Make sure that updateNextCellState is called for every neighborhood in the GridModel.
  Make sure that the current cell state is updated for every CellModel in the GridModel
   */

  @BeforeEach
  void setup() throws IOException, WrongFileTypeException {
//    DisplayView view = new DisplayView(DEFAULT_LANGUAGE, new Stage());
//    Controller controller = new Controller(view);
//    view.setController(controller);

  }

  @Test
  void updateState() {
    GameOfLifeNeighborhoodsLoaderMock loaderMock = new GameOfLifeNeighborhoodsLoaderMock(GameOfLifeCellState.ALIVE);
    GridModel gridModel = new GridModel(loaderMock, new GameOfLifeStateHandlerMock());
    gridModel.updateState();

    // code below should change to use iterator instead of direct access
    for (Neighborhood n : loaderMock.getNeighborhoods()) {
      assertTrue(n.isState(GameOfLifeCellState.DEAD));
    }



  }
}