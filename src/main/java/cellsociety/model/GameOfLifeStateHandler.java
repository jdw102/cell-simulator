package cellsociety.model;

import cellsociety.State;

import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
import java.util.List;
import java.util.Map;

public class GameOfLifeStateHandler implements StateHandler {

    Map<Integer, Enum> stateOfValue;

    public GameOfLifeStateHandler() {
        stateOfValue.put(0, GameOfLifeCellState.DEAD);
        stateOfValue.put(1, GameOfLifeCellState.ALIVE);
    }

    @Override
    public State figureOutNextState(Neighborhood currNeighborhood) {
        int liveNeighbors = currNeighborhood.count(new Alive());

        if((currNeighborhood.isState(new Alive()) && liveNeighbors == 2) || liveNeighbors == 3) {
            return new Alive();
        } else {
            return new Dead();
        }

    }

    @Override
    public Enum getMapping(int stateValue) {
        return stateOfValue.get(stateValue);
    }

}
