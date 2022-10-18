package cellsociety.model;

import cellsociety.State;

import java.util.List;

public interface StateHandler {
    @Deprecated
    State figureOutNextState(int cellRowIndex, int cellColIndex, List<List<CellModel>> cells);

    @Deprecated
    State figureOutNextState(State currentState, State[] neighboringStates);

    State figureOutNextState(Neighborhood currNeighborhood);

    State getMapping(int stateValue);
}
