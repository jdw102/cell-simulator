package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.EMPTY;
  }
}
