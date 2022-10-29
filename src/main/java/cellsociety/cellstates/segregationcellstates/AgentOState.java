package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

public class AgentOState implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT_0;
  }
}
