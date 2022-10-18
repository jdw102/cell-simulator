package cellsociety.model;

import cellsociety.State;

import java.util.List;

public interface StateHandler {

  State figureOutNextState(Neighborhood currNeighborhood);

  State getMapping(int stateValue);
}
