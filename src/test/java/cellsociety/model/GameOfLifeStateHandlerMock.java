package cellsociety.model;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import cellsociety.model.statehandlers.StateHandler;

public class GameOfLifeStateHandlerMock extends GameOfLifeStateHandler {

  @Override
  public State figureOutNextState(Neighborhood currNeighborhood) {
    if (currNeighborhood.isState(GameOfLifeCellState.ALIVE)) {
      return new DeadState();
    }
    else {
      return new AliveState();
    }

  }

  @Override
  public State getToggledState(Neighborhood currNeighborhood) {
    if (currNeighborhood.isState(GameOfLifeCellState.ALIVE)) {
      return new DeadState();
    }
    else {
      return new AliveState();
    }
  }
}
