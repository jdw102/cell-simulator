package cellsociety.model;

import cellsociety.State;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Cell;

public class Neighborhood {


  private CellModel centerCell;
  private CellModel[] myNeighboringCells;

  public Neighborhood(CellModel center, CellModel[] neighbors) {
    centerCell = center;
    myNeighboringCells = neighbors;
  }

  public int count(State targetState) {
    int retCounter = 0;
//    for(State currState: myNeighboringStates) {
//      if(sameState(targetState, currState)) {
//        retCounter += 1;
//      }
//    }
    return retCounter;
  }

  public boolean contains(State targetState) {
    return count(targetState) >= 1;
  }

  private boolean sameState(State state1, State state2) {
    return (state1.getClass()).equals(state2.getClass());
  }

  public boolean isState(State stateType) {
    return (centerCell.getClass()).equals(stateType.getClass());
  }

  public void updateCellState() {
    centerCell.setMyCurrentState(centerCell.getMyNextState());
  }

  public void updateCellNextState(State state) {
    centerCell.setMyNextState(state);
  }


}
