package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

public class PercolatedState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.PERCOLATED;
  }
}
