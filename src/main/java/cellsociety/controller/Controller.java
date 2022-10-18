package cellsociety.controller;

import cellsociety.model.GridModel;
import cellsociety.model.NeighborhoodsLoader;
import cellsociety.model.StateHandler;
import cellsociety.view.DisplayView;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;

public class Controller {

  public static final int DEFAULT_NEIGHBOR_DISTANCE = 1;
  private DisplayView displayView;
  private GridModel gridModel;

  //  private StateHandler stateHandler;
  public Controller(DisplayView displayView) {
    this.displayView = displayView;
    // initialize default stateHandler
  }

  /**
   * Method to be called by the view to communicate when the state of the grid should be updated
   */
  public void updateState() {
    gridModel.updateState();
  }

  /**
   * Called by view on sim file upload
   * @param simFile
   */
  public void setupSimulation(File simFile) {
    SimParser simParser;
    try {
      simParser = new SimParser(simFile);
      // Give the view the info about the game
      displayView.setInfoText(simParser.getGameDisplayInfo());

      File initStateCsv = simParser.getInitStateCsv();
      // Instantiate a CellSpawner
      StateHandlerLoader stateHandlerLoader = new StateHandlerLoader("gameOfLife");
      StateHandler stateHandler = stateHandlerLoader.getStateHandler();
      InitialStateReader initialStateReader = new InitialStateReader(stateHandler, initStateCsv);
      CellSpawner cellSpawner = new CellSpawner(displayView.getGridView(), initialStateReader);
      NeighborhoodsLoader neighborhoodsLoader = new NeighborhoodsLoader(cellSpawner, DEFAULT_NEIGHBOR_DISTANCE); // for now use default, but later allow user to choose this
      gridModel = new GridModel(neighborhoodsLoader, stateHandler);
    } catch (IOException | CsvValidationException | WrongFileTypeException e) {
      displayView.showMessage(e);
    }
  }

  public void changeSimulation(String simulationName) {
    // use reflection!
    // stateHandler =
  }
}
