package cellsociety.cellstates.gameoflifecellstates;

import cellsociety.State;

public class AliveState implements State {

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }

}
