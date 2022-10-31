package cellsociety.cellstates.segregationcellstates;

import cellsociety.State;

/**
 * @author Ryan Wolfram
 * Represents a state without an agent in the segregation simulation
 */
public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return SegregationCellState.EMPTY;
  }
}
