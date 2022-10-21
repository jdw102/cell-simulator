package cellsociety.controller;

import cellsociety.model.GridModel;
import cellsociety.model.NeighborhoodsLoader;
import cellsociety.model.statehandlers.StateHandler;
import cellsociety.view.DisplayView;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;

public class Controller {
  public static final int DEFAULT_NEIGHBOR_DISTANCE = 1;
  private final DisplayView displayView;
  private GridModel gridModel;
  private StateHandlerLoader stateHandlerLoader;

  //  private StateHandler stateHandler;
  public Controller(DisplayView displayView) {
    this.displayView = displayView;
    this.stateHandlerLoader = new StateHandlerLoader();
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
   *
   * @param simFile
   */
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

  public void changeSimulation(String simulationName) {

  }
}
