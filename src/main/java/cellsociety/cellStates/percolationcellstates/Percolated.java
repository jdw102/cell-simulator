package cellsociety.cellStates.percolationcellstates;

import cellsociety.State;

public class Percolated implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.PERCOLATED;
  }
}
