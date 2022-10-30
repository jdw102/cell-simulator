package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class AgentYState implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT_Y;
  }
}
