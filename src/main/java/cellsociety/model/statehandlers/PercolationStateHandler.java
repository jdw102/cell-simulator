package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.percolationcellstates.PercolationCellState;
import cellsociety.model.Neighborhood;

/**
 * StateHandler that implements the transition function rules for the percolation simulation
 */
public class PercolationStateHandler extends StateHandler {

  private static final String SIM_TYPE = "Percolation";

  public PercolationStateHandler()  {
    super(PercolationCellState.class, SIM_TYPE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();
    if (currState.equals(PercolationCellState.OPEN) && currNeighborhood.contains(
        PercolationCellState.PERCOLATED)) {
      return getStateInstance(PercolationCellState.PERCOLATED);
    } else {
      return currNeighborhood.getState();
    }
  }
}
