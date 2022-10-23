package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;
import java.util.ResourceBundle;

public abstract class StateHandler {

  private static final String STATE_SUFFIX = "State";

  private static String STATES_PACKAGE;
  Enum[] states;
  ResourceBundle properties;

  StateHandler(Enum[] states, String handlerName, String statesPackage) {
//    this.properties = properties;
    this.states = states;
    this.STATES_PACKAGE = statesPackage;

  }

  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  public abstract Class getMapping(int stateValue);

  public State getToggledState(Neighborhood currNeighborhood) {
    Enum state = currNeighborhood.getStateEnum();
    int stateIdx = getEnumIndex(state);
    return getStateInstance(states[(stateIdx + 1) % states.length]);
  }

  public State getGOLStateInstance(Enum state) {
    if (GameOfLifeCellState.ALIVE.equals(state)) {
      return new AliveState();
    } else if (GameOfLifeCellState.DEAD.equals(state)) {
      return new DeadState();
    }
    return null;
  }

  public State getStateInstance(Enum state) {
    String stateName = getEnumString(state);

    State retState = null;
    try {
      retState = (State) Class.forName(STATES_PACKAGE + stateName).getConstructor().newInstance();
    } catch (Exception e) {
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

  private int getEnumIndex(Enum state) {
    for (int i = 0; i < states.length; i++) {
      if (states[i].equals(state)) {
        return i;
      }
    }
    return -1;
  }

}

