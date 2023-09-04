package cellsociety.cellstates.watorworldcellstates;

import cellsociety.State;

public class FishState extends Animal implements State {
  @Override
  public Enum getStateEnum() {
    return WatorWorldCellState.FISH;
  }
}
