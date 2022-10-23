package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.model.Neighborhood;

public abstract class StateHandler {

  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  public abstract Class getMapping(int stateValue);

  public abstract State getToggledState(Neighborhood currNeighborhood);


}

