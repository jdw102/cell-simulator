package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.State;
import cellsociety.model.statehandlers.StateHandler;

/**
 * Maintains a 2D List of CellModels
 */
public class DefaultGridModel implements GridModel {

  private NeighborhoodsLoader myNeighborhoodsLoader;
  private StateHandler myStateHandler;

  /**
   * Creates the GridModel
   *
   * @param neighborhoodsLoader the NeighborhoodsLoader that loads and contains the neighborhoods
   * @param stateHandler        the StateFigureOuter that determines the states of cells each time
   *                            GridModel determines the next state of the cells in the grid
   */
  public DefaultGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
  }

  /**
   * Determine the next state of the grid.
   */
  @Override
  public void updateState() {
    determineNextStates();
    setCurrentStatesToNextStates();
  }

  /**
   * For the cell whose x and y coordinates match params x and y, toggle its state to the next state
   * that it should be based on a list of possible states. For example, if a cell's state is B, then
   * calling this method on that cell should result in the state becoming C, assuming the list of
   * possible states is [A, B, C].
   *
   * @param coordinate holds the coordinate of the cell to change
   */
  @Override
  public void changeCellState(Coordinate coordinate) {
    Neighborhood n = myNeighborhoodsLoader.getNeighborhood(coordinate);
    n.updateCellState(myStateHandler.getToggledState(n));
  }

  protected void determineNextStates() {
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      State newState = myStateHandler.figureOutNextState(myNeighborhoodsLoader.getNeighborhood(i));
      myNeighborhoodsLoader.getNeighborhood(i).updateCellNextState(newState);
    }
  }

  protected void setCurrentStatesToNextStates() {
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      myNeighborhoodsLoader.getNeighborhood(i).updateCellState();
    }
  }
}
