package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

/**
 * @author Ryan Wolfram
 * Represents Agent2 in the segregation simulation
 */
public class Agent2State implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.AGENT2;
  }
}
