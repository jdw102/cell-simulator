package cellsociety.model;

import cellsociety.State;
import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
import cellsociety.model.statehandlers.StateHandler;

public class GameOfLifeStateHandlerMock implements StateHandler {

  @Override
  public State figureOutNextState(Neighborhood currNeighborhood) {
    return new Dead();
  }

  @Override
  public State getToggledState(Neighborhood currNeighborhood) {
    if (currNeighborhood.isState(GameOfLifeCellState.ALIVE)) {
      return new Dead();
    }
    else {
      return new Alive();
    }
  }

  @Override
  public Class getMapping(int stateValue) {
    return null;
  }
}
