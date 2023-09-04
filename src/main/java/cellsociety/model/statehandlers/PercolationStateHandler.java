package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.percolationcellstates.PercolationCellState;
import cellsociety.model.Neighborhood;

/**
 * StateHandler that implements the transition function rules for the percolation simulation
 * @author Mazen Selim
 */
public class PercolationStateHandler extends StateHandler {

  private static final String SIM_TYPE = "Percolation";

  public PercolationStateHandler()  {
    super(PercolationCellState.class, SIM_TYPE);
  }

  /**
   * Logic for percolation simulation to determine next state depending on current state
   * @param currNeighborhood The current neighborhood being examined to determine next state of
   * @return The next state the cell at the center of the neighborhood should update to
   */
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
