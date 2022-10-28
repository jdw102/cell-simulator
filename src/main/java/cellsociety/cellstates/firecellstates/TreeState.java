package cellsociety.cellstates.firecellstates;

import cellsociety.State;

public class TreeState implements State {
  @Override
  public Enum getStateEnum() {
    return FireCellState.TREE;
  }
}
