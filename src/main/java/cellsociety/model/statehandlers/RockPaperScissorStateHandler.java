package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.rockpaperscissorcellstates.RockPaperScissorCellState;
import cellsociety.model.Neighborhood;
import java.util.Map;

public class RockPaperScissorStateHandler extends StateHandler {
  private static final int THRESHOLD = 3;

  private static final String STATES_PACKAGE = "cellsociety.cellstates.rockpaperscissorcellstates.";
  private static final String HANDLER_NAME = "RockPaperScissorStateHandler";

  private static final Map<RockPaperScissorCellState, RockPaperScissorCellState> enemiesMap = Map.of(
      RockPaperScissorCellState.SCISSOR, RockPaperScissorCellState.ROCK,
      RockPaperScissorCellState.ROCK, RockPaperScissorCellState.PAPER,
      RockPaperScissorCellState.PAPER, RockPaperScissorCellState.SCISSOR
  );

  public RockPaperScissorStateHandler() throws RuntimeException {
    super(RockPaperScissorCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  private State play(Enum currState, int numEnemiesSurrounding) {
      if (numEnemiesSurrounding >= THRESHOLD) {
        return getStateInstance(enemiesMap.get(currState));
      }
    return getStateInstance(currState);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();
    int numEnemiesSurrounding = currNeighborhood.count(enemiesMap.get(currState));
    return play(currState, numEnemiesSurrounding);
  }
}
