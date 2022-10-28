package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.rockpaperscissorcellstates.RockPaperScissorCellState;
import cellsociety.model.Neighborhood;

public class RockPaperScissorStateHandler extends StateHandler {
  private static final int THRESHOLD = 3;

  private static final String STATES_PACKAGE = "cellsociety.cellstates.rockpaperscissorcellstates.";
  private static final String HANDLER_NAME = "RockPaperScissorStateHandler";

  public RockPaperScissorStateHandler() throws RuntimeException {
    super(RockPaperScissorCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    Enum currState = currNeighborhood.getStateEnum();

    if (currState.equals(RockPaperScissorCellState.PAPER)) {
      if (currNeighborhood.count(RockPaperScissorCellState.SCISSOR) >= THRESHOLD) {
        return getStateInstance(RockPaperScissorCellState.SCISSOR);
      }
    }
    if (currState.equals(RockPaperScissorCellState.SCISSOR)) {
      if (currNeighborhood.count(RockPaperScissorCellState.ROCK) >= THRESHOLD) {
        return getStateInstance(RockPaperScissorCellState.ROCK);
      }
    }
    if (currState.equals(RockPaperScissorCellState.ROCK)) {
      if (currNeighborhood.count(RockPaperScissorCellState.PAPER) >= THRESHOLD) {
        return getStateInstance(RockPaperScissorCellState.PAPER);
      }
    }
    return getStateInstance(currState);
  }
}
