package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.model.Neighborhood;

public interface StateHandler {

  State figureOutNextState(Neighborhood currNeighborhood);

  Class getMapping(int stateValue);
}
