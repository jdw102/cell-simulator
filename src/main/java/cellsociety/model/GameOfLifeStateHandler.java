package cellsociety.model;

import cellsociety.State;

import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameOfLifeStateHandler implements StateHandler {

    Map<Integer, Class> stateOfValue;

    public GameOfLifeStateHandler() {
        stateOfValue = new HashMap<>();

        stateOfValue.put(0, Alive.class);
        stateOfValue.put(1, Dead.class);
    }

    @Override
    public State figureOutNextState(Neighborhood currNeighborhood) {
        int liveNeighbors = currNeighborhood.count(new Alive());


        if((currNeighborhood.isState(GameOfLifeCellState.ALIVE) && liveNeighbors == 2) || liveNeighbors == 3) {
            return new Alive();
        } else {
            return new Dead();
        }

    }

    @Override
    public Class getMapping(int stateValue) {
        return stateOfValue.get(stateValue);
    }

}
