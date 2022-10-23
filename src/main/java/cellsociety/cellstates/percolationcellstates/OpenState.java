package cellsociety.cellstates.percolationcellstates;

import cellsociety.State;

public class OpenState implements State {

  @Override
  public Enum getStateEnum() {
    return PercolationCellState.OPEN;
  }
}
