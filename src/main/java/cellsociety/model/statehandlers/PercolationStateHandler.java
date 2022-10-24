package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.percolationcellstates.BlockedState;
import cellsociety.cellstates.percolationcellstates.OpenState;
import cellsociety.cellstates.percolationcellstates.PercolatedState;
import cellsociety.cellstates.percolationcellstates.PercolationCellState;
import cellsociety.model.Neighborhood;
import java.util.HashMap;
import java.util.Map;

public class PercolationStateHandler extends StateHandler {

  private static final String STATES_PACKAGE = "cellsociety.cellstates.percolationcellstates.";
  private static final String HANDLER_NAME = "PercolationStateHandler";

  public PercolationStateHandler() {
    super(PercolationCellState.values(), HANDLER_NAME, STATES_PACKAGE);
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
