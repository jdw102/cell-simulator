package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.firecellstates.FireCellState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;

public class FireStateHandler extends StateHandler {

  private static final String STATES_PACKAGE = "cellsociety.cellstates.firecellstates.";
  private static final String HANDLER_NAME = "FireStateHandler";

  private static final double PROBABILITY_FIRE = 0.1;
  private static final double PROBABILITY_TREE = 0.02;


  public FireStateHandler() throws RuntimeException {
    super(FireCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();

    if(currState.equals(FireCellState.FIRE)) {
      return getStateInstance(FireCellState.EMPTY);
    } else if(currState.equals(FireCellState.TREE) && currNeighborhood.contains(FireCellState.FIRE)) {
      return getStateInstance(FireCellState.FIRE);
    } else if (currState.equals(FireCellState.TREE) && Math.random() < PROBABILITY_FIRE) {
      return getStateInstance(FireCellState.FIRE);
    } else if (currState.equals(FireCellState.EMPTY) && Math.random() < PROBABILITY_TREE) {
      return getStateInstance(FireCellState.TREE);
    }

    return currNeighborhood.getState();
  }
}
