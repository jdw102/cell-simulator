package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

/**
 * Represents the open state in the percolation simulation
 */
public class OpenState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.OPEN;
  }
}
