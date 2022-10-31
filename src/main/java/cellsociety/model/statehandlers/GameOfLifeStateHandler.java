package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.controller.IncorrectInputException;
import cellsociety.model.Neighborhood;

/**
 * StateHandler that implements the transition function rules for the game of life simulation
 */
public class GameOfLifeStateHandler extends StateHandler {

  private static final String SIM_TYPE = "GameOfLife";

  public GameOfLifeStateHandler() {
    super(GameOfLifeCellState.class, SIM_TYPE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    int liveNeighbors = currNeighborhood.count(GameOfLifeCellState.ALIVE);
    if ((currNeighborhood.isState(GameOfLifeCellState.ALIVE) && liveNeighbors == 2)
        || liveNeighbors == 3) {
      return getStateInstance(GameOfLifeCellState.ALIVE);
    } else {
      return getStateInstance(GameOfLifeCellState.DEAD);
    }
  }
}
