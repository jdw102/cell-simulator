package cellsociety.model;

import cellsociety.State;

public interface StateHandler {

  State figureOutNextState(Neighborhood currNeighborhood);

  Class getMapping(int stateValue);
}
