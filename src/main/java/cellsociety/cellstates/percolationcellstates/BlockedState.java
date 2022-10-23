package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

public class BlockedState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.BLOCKED;
  }
}
