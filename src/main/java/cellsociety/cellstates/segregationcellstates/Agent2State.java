package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class Agent2State implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT2;
  }
}
