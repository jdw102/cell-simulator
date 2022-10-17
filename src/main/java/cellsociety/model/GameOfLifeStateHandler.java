package cellsociety.model;

import cellsociety.State;

import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import java.util.List;
import java.util.Map;

public class GameOfLifeStateHandler implements StateHandler {

    Map<Integer, State> stateOfValue;

    public GameOfLifeStateHandler() {
        stateOfValue.put(0, new Dead());
        stateOfValue.put(1, new Alive());
    }

    @Override
    public State figureOutNextState(int cellRowIndex, int cellColIndex, List<List<CellModel>> cells) {
        return null;
    }

    @Override
    public State figureOutNextState(State currentState, State[] neighboringStates) {
        return null;
    }

    @Override
    public State getMapping(int stateValue) {
        return null;
    }

}
