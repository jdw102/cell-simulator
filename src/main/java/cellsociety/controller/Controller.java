package cellsociety.controller;

import cellsociety.view.DisplayView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

  public static final int DEFAULT_NEIGHBOR_DISTANCE = 1;
  private DisplayView displayView;

  //  private StateHandler stateHandler;
  public Controller(DisplayView displayView) {
    this.displayView = displayView;
    // initialize default stateHandler
  }

  /**
   * Method to be called by the view to communicate when the state of the grid should be updated
   */
  public void updateState() {
    // calls model.updateState()
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
      // displayView.setInfo(simParser.getGameDisplayInfo());

      File initStateCsv = simParser.getInitStateCsv();
      // Instantiate a CellSpawner
      // CellSpawner cellSpawner = new CellSpawner(GridView, initStateCsv);
      // NeighborhoodsSpawner neighborhoodsSpawner = new NeighborhoodsSpawner(cellSpawner, DEFAULT_NEIGHBOR_DISTANCE); // for now use default, but later allow user to choose this
      // GridModel gridModel = new GridModel(neighborhoodsSpawner, stateHandler);
    } catch (FileNotFoundException fnfe) {
      // tell user the file is not found via popup by calling View
    } catch (IOException ioe) {
      // tell user could not load file via popup by calling View
    }

  }

  public void changeSimulation(String simulationName) {
    // use reflection!
    // stateHandler =
  }
}
