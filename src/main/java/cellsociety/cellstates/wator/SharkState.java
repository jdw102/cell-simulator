package cellsociety.cellstates.wator;

import cellsociety.State;

public class SharkState implements State {
  @Override
  public Enum getStateEnum() {
    return WatorCellState.SHARK;
  }
}
