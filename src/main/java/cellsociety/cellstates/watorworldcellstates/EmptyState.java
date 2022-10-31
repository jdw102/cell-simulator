package cellsociety.cellstates.watorworldcellstates;

import cellsociety.State;

public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return WatorWorldCellState.EMPTY;
  }
}