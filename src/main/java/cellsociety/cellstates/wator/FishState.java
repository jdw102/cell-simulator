package cellsociety.cellstates.wator;

import cellsociety.State;

public class FishState implements State {
  @Override
  public Enum getStateEnum() {
    return WatorCellState.FISH;
  }
}
