package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class AgentXState implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT_X;
  }
}
