package cellsociety.cellstates.gameoflifecellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * @author Daniel Feinblatt
 * Represents the alive state in the game of life simulation
 */
public class AliveState implements State {

  @Override
  public Enum getStateEnum() {
    return GameOfLifeCellState.ALIVE;
  }
}
