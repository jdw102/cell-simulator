package cellsociety.cellstates.firecellstates;

import cellsociety.State;

public class FireState implements State {
  @Override
  public Enum getStateEnum() {
    return FireCellState.FIRE;
  }
}
