package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.rockpaperscissorcellstates.RockPaperScissorCellState;
import cellsociety.model.Neighborhood;

public class PredatorPreyStateHandler extends StateHandler {
  private static final String STATES_PACKAGE = "cellsociety.cellstates.predatorpreycellstates.";
  private static final String HANDLER_NAME = "PredatorPreyStateHandler";

  public PredatorPreyStateHandler() throws RuntimeException {
    super(RockPaperScissorCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  @Override
  public State figureOutNextState(Neighborhood currNeighborhood) {
    return null;
  }
}
