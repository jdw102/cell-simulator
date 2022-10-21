package cellsociety.cellStates.percolationcellstates;

import cellsociety.State;

public class Blocked implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.BLOCKED;
  }
}
