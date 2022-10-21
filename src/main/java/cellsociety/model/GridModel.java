package cellsociety.model;

import cellsociety.State;
import cellsociety.model.statehandlers.StateHandler;

/**
 * Maintains a 2D List of CellModels
 */
public class GridModel {

  NeighborhoodsLoader myNeighborhoodsLoader;
  StateHandler myStateHandler;

  /**
   * Creates the GridModel
   *
   * @param neighborhoodsLoader the NeighborhoodsLoader that loads and contains the neighborhoods
   * @param stateHandler        the StateFigureOuter that determines the states of cells each time
   *                            GridModel determines the next state of the cells in the grid
   */
  public GridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
  }

  /**
   * Determine the next state of the grid.
   */
  public void updateState() {
    determineNextStates();
    setCurrentStatesToNextStates();
  }

  public void changeCellState(int x, int y) {
    Neighborhood n = myNeighborhoodsLoader.getNeighborhood(x, y);
    State newState = myStateHandler.toggleState(n);
    n.updateCellState(newState);
  }

  private void determineNextStates() {
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      State newState = myStateHandler.figureOutNextState(myNeighborhoodsLoader.getNeighborhood(i));
      myNeighborhoodsLoader.getNeighborhood(i).updateCellNextState(newState);
    }
  }

  private void setCurrentStatesToNextStates() {
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      myNeighborhoodsLoader.getNeighborhood(i).updateCellState();
    }
  }
}
