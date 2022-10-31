package cellsociety.cellstates.gameoflifecellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * @author Daniel Feinblatt
 * Represents the dead state in the game of life simulation
 */
public class DeadState implements State {

  public Enum getStateEnum() {
    return GameOfLifeCellState.DEAD;
  }
}
