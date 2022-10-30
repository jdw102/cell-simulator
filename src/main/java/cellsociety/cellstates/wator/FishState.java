package cellsociety.cellstates.wator;

import cellsociety.State;

public class FishState extends Animal implements State {
  @Override
  public Enum getStateEnum() {
    return WatorCellState.FISH;
  }
}
