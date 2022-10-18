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
    public State figureOutNextState(Neighborhood currNeighborhood) {
        int liveNeighbors = currNeighborhood.count(new Alive());

        if((currNeighborhood.isState(new Alive()) && liveNeighbors == 2) || liveNeighbors == 3) {
            return new Alive();
        } else {
            return new Dead();
        }

    }

    @Override
    public State getMapping(int stateValue) {
        return stateOfValue.get(stateValue);
    }

}
