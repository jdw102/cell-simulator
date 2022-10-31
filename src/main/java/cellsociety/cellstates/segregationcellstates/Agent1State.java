package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

/**
 * @author Ryan Wolfram
 * Represents Agent1 in the segregation simulation
 */
public class Agent1State implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT1;
  }
}
