package cellsociety.model;

import cellsociety.State;

import java.util.List;

public interface StateHandler {
    @Deprecated
    State figureOutNextState(int cellRowIndex, int cellColIndex, List<List<CellModel>> cells);

    State figureOutNextState(State currentState, State[] neighboringStates);

    State getMapping(int stateValue);
}
