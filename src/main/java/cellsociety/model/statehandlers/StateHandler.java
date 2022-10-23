package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellStates.gameoflifecellstates.Alive;
import cellsociety.model.Neighborhood;
import java.util.ResourceBundle;

public abstract class StateHandler {

  private String states;
  ResourceBundle properties;
  StateHandler() {
//    this.properties = properties;
//    this.states = states;

  }

  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  public abstract Class getMapping(int stateValue);

  public State getToggledState(Neighborhood currNeighborhood) {
//    Class temp = null;
//    try {
//      temp = Class.forName(states);
//    } catch (Exception e) {
//
//    }
//    temp.getEnumConstants();
    return new Alive();
  }


}

