package cellsociety.model;

import cellsociety.State;

import java.util.List;

/**
 * Maintains a 2D List of CellModels
 */
public class GridModel {
  List<List<CellModel>> myCells;
//    Collection<Neighborhood> myNeighborhoods;
  // GridModel gridModel = new GridModel(neighborhoodsSpawner, stateHandler);

  StateHandler myStateHandler;

  /**
   * Creates the GridModel
   *
   * @param cellStateGrid    2D List of initial states the cells will contain
   * @param stateHandler the StateFigureOuter that determines the states of cells each time
   *                         GridModel determines the next state of the cells in the grid
   */
  public GridModel(List<List<State>> cellStateGrid, StateHandler stateHandler) {
    myStateHandler = stateHandler;
  }

//    public GridModel(NeighborhoodSpawner neighborhoodSpawner, StateHandler stateHandler) {
//        neighborhoods = neighborhoodSpawner.spawnNeighborhoods();
//        myStateHandler = stateHandler;
//    }

  /**
   * Determine the next state of the grid.
   */
  public void updateState() {
    determineNextStates();
    setCurrentStatesToNextStates();
  }

  private void determineNextStates() {
    for (int i = 0; i < myCells.size(); i++) {
      for (int k = 0; k < myCells.get(i).size(); k++) {
        CellModel currentCell = myCells.get(i).get(k);
        currentCell.setNextState(myStateHandler.figureOutNextState(i, k, myCells));
      }
    }
  }

  private void setCurrentStatesToNextStates() {
    for (List<CellModel> myCell : myCells) {
      for (CellModel currentCell : myCell) {
        currentCell.setCurrentState(currentCell.getNextState());
      }
    }
  }

}
