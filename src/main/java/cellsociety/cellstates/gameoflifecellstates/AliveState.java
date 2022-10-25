package cellsociety.cellstates.gameoflifecellstates;

import cellsociety.State;

/**
 * Represents the alive state in the game of life simulation
 */
public class AliveState implements State {

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }
}
