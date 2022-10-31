package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.firecellstates.FireCellState;
import cellsociety.model.Neighborhood;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FireStateHandler extends StateHandler {

  private static final String SIM_TYPE = "Fire";
  private static final double probFire = 1;
  private double probTree;

  private Map<FireCellState, Function<Neighborhood, State>> nextStateMap;


  public FireStateHandler() {
    super(FireCellState.class, SIM_TYPE);
    nextStateMap = new HashMap<>();
    populateNextStateMap();
  }

  private void populateNextStateMap() {
    nextStateMap.put(FireCellState.FIRE, neighborhood -> getStateInstance(FireCellState.EMPTY));
    nextStateMap.put(FireCellState.EMPTY, neighborhood -> chooseStateAfterEmpty());
    nextStateMap.put(FireCellState.TREE, neighborhood -> chooseStateAfterTree(neighborhood));
  }

  private State chooseStateAfterEmpty() {
    if (Math.random() < probTree) {
      return getStateInstance(FireCellState.TREE);
    }
    return getStateInstance(FireCellState.EMPTY);
  }

  private State chooseStateAfterTree(Neighborhood neighborhood) {
    if (neighborhood.contains(FireCellState.FIRE) || Math.random() < probFire) {
      return getStateInstance(FireCellState.FIRE);
    }
    return getStateInstance(FireCellState.TREE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    updateParameters();
    Enum currState = currNeighborhood.getStateEnum();
    return nextStateMap.get(currState).apply(currNeighborhood);
  }

  private void updateParameters() {
    double parameterRatio = getParameter();
    probTree = parameterRatio;
  }
  @Override
  public void setParameter(double parameter) {
    overwriteParameter(parameter);
  }
}
