package cellsociety.cellstates.predatorpreycellstates;

import cellsociety.State;

public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return PredatorPreyCellState.EMPTY;
  }
}