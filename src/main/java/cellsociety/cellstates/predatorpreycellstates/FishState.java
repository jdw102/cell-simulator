package cellsociety.cellstates.predatorpreycellstates;

import cellsociety.State;

public class FishState extends Animal implements State {
  @Override
  public Enum getStateEnum() {
    return PredatorPreyCellState.FISH;
  }
}
