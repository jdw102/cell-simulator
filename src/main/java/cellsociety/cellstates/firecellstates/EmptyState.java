package cellsociety.cellstates.firecellstates;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;

public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return FireCellState.EMPTY;
  }
}
