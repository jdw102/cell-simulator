package cellsociety.cellStates.gameoflifecellstates;

import cellsociety.State;
import cellsociety.cellStates.gameoflifecellstates.GameOfLifeCellState;

public class Dead implements State {

  public Enum getStateEnum() {
    return GameOfLifeCellState.DEAD;
  }
}
