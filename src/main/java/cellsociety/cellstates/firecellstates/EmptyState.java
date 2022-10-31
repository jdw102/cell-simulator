package cellsociety.cellstates.firecellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents an empty state in the spreading fire simulation
 */
public class EmptyState implements State {

  @Override
  public Enum getStateEnum() {
    return FireCellState.EMPTY;
  }
}
