package cellsociety.cellstates.watorworldcellstates;

import cellsociety.State;

/**
 * @author Ryan Wolfram
 * Represents a state with no fish or shark in the wator simulation
 */
public class EmptyState implements State {
  @Override
  public Enum getStateEnum() {
    return WatorWorldCellState.EMPTY;
  }
}