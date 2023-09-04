package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.controller.IncorrectInputException;
import cellsociety.model.Neighborhood;

/**
 * StateHandler that implements the transition function rules for the game of life simulation
 *
 * @author Mazen Selim
 * @author Daniel Feinblatt
 */
public class GameOfLifeStateHandler extends StateHandler {

  private static final String SIM_TYPE = "GameOfLife";

  /**
   * Uses reflection to instantiate itself in the StateHandler super-class
   *
   * @throws RuntimeException
   */
  public GameOfLifeStateHandler() {
    super(GameOfLifeCellState.class, SIM_TYPE);
  }

  /**
   * Logic for game of life simulation to determine next state depending on current state
   *
   * @param currNeighborhood The current neighborhood being examined to determine next state of
   * @return The next state the cell at the center of the neighborhood should update to
   */
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
