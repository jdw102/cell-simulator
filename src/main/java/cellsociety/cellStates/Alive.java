package cellsociety.cellStates;

import cellsociety.State;

public class Alive implements State {

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }

}
