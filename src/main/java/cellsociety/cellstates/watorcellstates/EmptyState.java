package cellsociety.cellstates.watorcellstates;

import cellsociety.State;

public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return WatorCellState.EMPTY;
  }
}