package cellsociety.controller;

import cellsociety.Coordinate;
import cellsociety.GameDisplayInfo;
import cellsociety.model.DefaultNeighborhoodsLoader;
import cellsociety.model.GridModel;
import cellsociety.model.UnrecognizedEdgeRuleException;
import cellsociety.model.statehandlers.InvalidParameterException;
import cellsociety.model.statehandlers.MissingParameterException;
import cellsociety.model.statehandlers.StateHandler;
import cellsociety.view.DisplayView;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;

/**
 * @author Daniel Feinblatt Controller class in the Model-View-Controller design
 */
public class Controller {

  private final DisplayView displayView;
  private final StateHandlerLoader stateHandlerLoader;
  private final GridModelLoader gridModelLoader;
  private GridModel gridModel;
  private DefaultNeighborhoodsLoader neighborhoodsLoader;

  /**
   * Instantiates a new Controller that handles communication between the model and view
   *
   * @param displayView The main View class that the controller communicates with to display
   *                    information to the user
   */
  public Controller(DisplayView displayView) {
    this.displayView = displayView;
    this.stateHandlerLoader = new StateHandlerLoader();
    this.gridModelLoader = new GridModelLoader();
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
   * @param simFile A file expected to be of type sim that details information about the simulation
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
      StateHandler stateHandler = stateHandlerLoader.getStateHandler(
          gameDisplayInfo.type(), simParser.getParams());
      InitialStateReader initialStateReader = new InitialStateReader(stateHandler, initStateCsv);
      CellSpawner cellSpawner = new CellSpawner(displayView, initialStateReader);
      neighborhoodsLoader = new DefaultNeighborhoodsLoader(
          cellSpawner);
      gridModel = gridModelLoader.getGridModel(gameDisplayInfo.type(), neighborhoodsLoader,
          stateHandler);
    } catch (IOException | CsvValidationException | WrongFileTypeException | IncorrectInputException |
    InvalidParameterException | MissingParameterException e) {
      displayView.showMessage(e);
    }
  }

  public void setGridPolicy(String policy) {
    try {
      neighborhoodsLoader.setEdgeRule(policy);
    } catch (UnrecognizedEdgeRuleException e) {
      displayView.showMessage(e);
    }
  }

  public void setRadius(int radius) {
    neighborhoodsLoader.setDistance(radius);
  }

  public void changeCellState(Coordinate coord) {
    gridModel.changeCellState(coord);
  }
}
