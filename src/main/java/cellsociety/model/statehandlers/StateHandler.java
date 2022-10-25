package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class StateHandler {

  private static final String STATE_SUFFIX = "State";

  private String statesPackage;
  private static final String PROPERTIES_PACKAGE = "cellsociety.statehandlers.";
  private String handlerName;
  private Map<Integer, Enum> stateOf;
  private SimulationStates states;

  StateHandler(Class<?> states, String handler, String statesPackage) throws RuntimeException {
    this.handlerName = handler;
    this.states = new SimulationStates(states);
    this.statesPackage = statesPackage;
    try {
      loadStates();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void loadStates() throws Exception {
    stateOf = new HashMap<>();
    ResourceBundle myResources = ResourceBundle.getBundle(PROPERTIES_PACKAGE + handlerName);
    for (String key : myResources.keySet()) {
      Enum currEnum = states.getEnum(key);
      int val;

      try {
        val = Integer.parseInt(myResources.getString(key));
      } catch (Exception e) {
        throw new Exception(e);
      }

      stateOf.put(val, currEnum);
    }
  }

  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  public Enum getMapping(int stateValue) {
    return stateOf.get(stateValue);
  }

  public State getToggledState(Neighborhood currNeighborhood) {
    Enum state = currNeighborhood.getStateEnum();
    Enum nextState = states.getNextEnum(state);
    return getStateInstance(nextState);
  }

  public State getStateInstance(Enum state) {
    String stateName = getEnumString(state);
    State retState = null;
    try {
      retState = (State) Class.forName(statesPackage + stateName).getConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    return retState;
  }

  private String getEnumString(Enum state) {
    String myState = state.toString();
    String simpleName = (myState.toLowerCase()).split(STATE_SUFFIX.toLowerCase())[0];
    StringBuilder outputName = new StringBuilder();

    char[] simpleNameAsArray = simpleName.toCharArray();
    for (int i = 0; i < simpleNameAsArray.length; i++) {
      if (i == 0) {
        outputName.append(Character.toUpperCase(simpleNameAsArray[i]));
      } else {
        outputName.append(simpleNameAsArray[i]);
      }
    }
    return outputName + STATE_SUFFIX;
  }

}

