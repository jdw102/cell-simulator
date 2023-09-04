package cellsociety.cellstates.firecellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents a tree in the spreading fire simulation
 */
public class TreeState implements State {
  @Override
  public Enum getStateEnum() {
    return FireCellState.TREE;
  }
}
