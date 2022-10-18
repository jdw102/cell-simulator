package cellsociety.cellStates;

import cellsociety.State;

public class Dead implements State {

  public Enum getStateEnum() {
    return GameOfLifeCellState.DEAD;
  }
}
