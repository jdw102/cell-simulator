package cellsociety.cellStates.gameoflifecellstates;

import cellsociety.State;
import cellsociety.cellStates.gameoflifecellstates.GameOfLifeCellState;

public class Alive implements State {

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }

}
