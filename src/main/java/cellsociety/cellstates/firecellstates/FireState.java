package cellsociety.cellstates.firecellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents the on fire state in the spreading fire simulation
 */
public class FireState implements State {
  @Override
  public Enum getStateEnum() {
    return FireCellState.FIRE;
  }
}
