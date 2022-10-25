package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

/**
 * Represents the blocked state in the percolation simulation
 */
public class BlockedState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.BLOCKED;
  }
}
