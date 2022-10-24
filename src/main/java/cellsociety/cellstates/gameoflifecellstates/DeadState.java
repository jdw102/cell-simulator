package cellsociety.cellstates.gameoflifecellstates;

import cellsociety.State;

public class DeadState implements State {

  public Enum getStateEnum() {
    return GameOfLifeCellState.DEAD;
  }
}
