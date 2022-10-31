package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.rockpaperscissorcellstates.RockPaperScissorCellState;
import cellsociety.model.Neighborhood;
import java.util.Map;

/**
 * StateHandler that implements the transition function rules for the rock paper scissor simulation
 *
 * @author Mazen Selim
 * @author Daniel Feinblatt
 */
public class RockPaperScissorStateHandler extends StateHandler {

  private static final int THRESHOLD = 3;

  private static final String SIM_TYPE = "RockPaperScissor";

  private static final Map<RockPaperScissorCellState, RockPaperScissorCellState> enemiesMap = Map.of(
      RockPaperScissorCellState.SCISSOR, RockPaperScissorCellState.ROCK,
      RockPaperScissorCellState.ROCK, RockPaperScissorCellState.PAPER,
      RockPaperScissorCellState.PAPER, RockPaperScissorCellState.SCISSOR
  );

  public RockPaperScissorStateHandler()  {
    super(RockPaperScissorCellState.class, SIM_TYPE);
  }

  private State play(Enum currState, int numEnemiesSurrounding) {
    if (numEnemiesSurrounding >= THRESHOLD) {
      return getStateInstance(enemiesMap.get(currState));
    }
    return getStateInstance(currState);
  }

  /**
   * Logic for rock paper scissor simulation to determine next state depending on current state
   *
   * @param currNeighborhood The current neighborhood being examined to determine next state of
   * @return The next state the cell at the center of the neighborhood should update to
   */
  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();
    int numEnemiesSurrounding = currNeighborhood.count(enemiesMap.get(currState));
    return play(currState, numEnemiesSurrounding);
  }
}
