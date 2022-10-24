package cellsociety.model;

import cellsociety.State;

public class Neighborhood {

  private final CellModel centerCell;
  private final CellModel[] myNeighboringCells;

  public Neighborhood(CellModel center, CellModel[] neighbors) {
    centerCell = center;
    myNeighboringCells = neighbors;
  }

  public int count(State targetState) {
    int retCounter = 0;
    for (CellModel cellModel : myNeighboringCells) {
      if (sameState(targetState, cellModel.getCurrentState())) {
        retCounter += 1;
      }
    }
    return retCounter;
  }

  public boolean contains(State targetState) {
    return count(targetState) >= 1;
  }

  private boolean sameState(State state1, State state2) {
    return (state1.getStateEnum()).equals(state2.getStateEnum());
  }

  public boolean isState(State stateType) {
    return (centerCell.getCurrentState().getStateEnum()).equals(stateType.getStateEnum());
  }

  public boolean isState(Enum stateType) {
    return (centerCell.getCurrentState().getStateEnum()).equals(stateType);
  }

  public void updateCellState() {
    centerCell.setCurrentState(centerCell.getNextState());
  }

  public void updateCellState(State state) {
    centerCell.setNextState(state);
    updateCellState();
  }

  public void updateCellNextState(State state) {
    centerCell.setNextState(state);
  }


}
