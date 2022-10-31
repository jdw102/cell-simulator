package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents the percolated state in the percolation simulation
 */
public class PercolatedState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.PERCOLATED;
  }
}
