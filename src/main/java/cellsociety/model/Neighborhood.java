package cellsociety.model;

import cellsociety.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Neighborhood {

  private final CellModel centerCell;
  private final CellModel[] myNeighboringCells;

  public Neighborhood(CellModel center, CellModel[] neighbors) {
    centerCell = center;
    myNeighboringCells = neighbors;
  }

  public int count(Enum targetState) {
    int retCounter = 0;
    for (CellModel cellModel : myNeighboringCells) {
      if (sameState(targetState, cellModel.getCurrentStateEnum())) {
        retCounter += 1;
      }
    }
    return retCounter;
  }

  public int countNextState(Enum targetState) {
    int retCounter = 0;
    for (CellModel cellModel : myNeighboringCells) {
      if (sameState(targetState, cellModel.getNextStateEnum())) {
        retCounter += 1;
      }
    }
    return retCounter;
  }

//  private int count(Enum targetState, <CellModel> predicate) {
//    int retCounter = 0;
//    for (CellModel cellModel : myNeighboringCells) {
//      if (sameState(targetState, predicate.test(cellModel))) {
//        retCounter += 1;
//      }
//    }
//    return retCounter;
//  }

  public boolean contains(Enum targetState) {
    return count(targetState) >= 1;
  }

  public boolean setNextStateOfRandomNeighborWithNextState(Enum targetNextStateEnum, State state) {
    List<CellModel> cellModels = new ArrayList<>();
    for (CellModel cellModel : myNeighboringCells) {
      if (cellModel.getNextStateEnum().equals(targetNextStateEnum)) {
        cellModels.add(cellModel);
      }
    }

    if (cellModels.size() == 0) {
      return false;
    }
    else {
      Random rand = new Random();
      CellModel target = cellModels.get(rand.nextInt(0, cellModels.size()));
      target.setNextState(state);
      return true;
    }
  }

  private boolean sameState(Enum state1, Enum state2) {
    return state1.equals(state2);
  }

  private boolean sameState(State state1, State state2) {
    return (state1.getStateEnum()).equals(state2.getStateEnum());
  }

  public boolean isState(Enum stateType) {
    return (centerCell.getCurrentStateEnum().equals(stateType));
  }

  public boolean isNextState(Enum stateType) {
    return (centerCell.getNextStateEnum().equals(stateType));
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

  public Enum getStateEnum() {
    return centerCell.getCurrentStateEnum();
  }

  public Enum getNextStateEnum() {
    return centerCell.getNextStateEnum();
  }

  public State getState() {
    return centerCell.getCurrentState();
  }

  public State getNextState() {
    return centerCell.getNextState();
  }
}
