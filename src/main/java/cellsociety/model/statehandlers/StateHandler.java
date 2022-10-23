package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellStates.gameoflifecellstates.Alive;
import cellsociety.cellStates.gameoflifecellstates.Dead;
import cellsociety.cellStates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;
import java.util.ResourceBundle;

public abstract class StateHandler {

  Enum[] states;
  ResourceBundle properties;

  StateHandler(Enum[] states, String handlerName) {
//    this.properties = properties;
    this.states = states;

  }

  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  public abstract Class getMapping(int stateValue);

  public State getToggledState(Neighborhood currNeighborhood) {
    Enum state = currNeighborhood.getStateEnum();
    int stateIdx = getEnumIndex(state);
    return getGOLStateInstance(states[(stateIdx + 1) % states.length]);
  }

  public State getGOLStateInstance(Enum state) {
    if (GameOfLifeCellState.ALIVE.equals(state)) {
      return new Alive();
    } else if (GameOfLifeCellState.DEAD.equals(state)) {
      return new Dead();
    }
    return null;
  }

  private int getEnumIndex(Enum state) {
    for (int i = 0; i < states.length; i++) {
      if (states[i] == state) {
        return i;
      }
    }
    return -1;
  }

}

