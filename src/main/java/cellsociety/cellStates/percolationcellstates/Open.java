package cellsociety.cellStates.percolationcellstates;

import cellsociety.State;

public class Open implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.OPEN;
  }
}
