package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class Agent1State implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT1;
  }
}
