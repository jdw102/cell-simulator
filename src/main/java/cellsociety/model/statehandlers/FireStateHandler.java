package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.firecellstates.FireCellState;
import cellsociety.model.Neighborhood;
import java.util.Map;
import java.util.function.Function;

public class FireStateHandler extends StateHandler {

  private static final String STATES_PACKAGE = "cellsociety.cellstates.firecellstates.";
  private static final String HANDLER_NAME = "FireStateHandler";

  private static final double PROBABILITY_FIRE = 0.1;
  private static final double PROBABILITY_TREE = 0.02;

  private Map<FireCellState, Function<Neighborhood, State>> nextStateMap;

  public FireStateHandler() throws RuntimeException {
    super(FireCellState.class, HANDLER_NAME, STATES_PACKAGE);
    nextStateMap = Map.of(
        FireCellState.FIRE, neighborhood -> getStateInstance(FireCellState.EMPTY),
        // Doesn't need to use neighborhood to figure out next state
        FireCellState.EMPTY, neighborhood -> chooseStateAfterEmpty(),
        // Doesn't need to use neighborhood to figure out next state
        FireCellState.TREE, neighborhood -> chooseStateAfterTree(neighborhood)
    );
  }

  private State chooseStateAfterEmpty() {
    if (Math.random() < PROBABILITY_TREE) {
      return getStateInstance(FireCellState.TREE);
    }
    return getStateInstance(FireCellState.EMPTY);
  }

  private State chooseStateAfterTree(Neighborhood neighborhood) {
    if (neighborhood.contains(FireCellState.FIRE) || Math.random() < PROBABILITY_FIRE) {
      return getStateInstance(FireCellState.FIRE);
    }
    return getStateInstance(FireCellState.TREE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();
    return nextStateMap.get(currState).apply(currNeighborhood);
  }
}
