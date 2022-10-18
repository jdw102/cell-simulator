package cellsociety.cellStates;

import cellsociety.State;

public class Alive implements State {

  public Alive() {
  }

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }

}
