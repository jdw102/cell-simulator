package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.watorworldcellstates.EmptyState;
import cellsociety.cellstates.watorworldcellstates.WatorWorldCellState;
import cellsociety.cellstates.rockpaperscissorcellstates.RockPaperScissorCellState;
import cellsociety.model.Neighborhood;

public class WatorWorldStateHandler extends StateHandler {
  private static final String STATES_PACKAGE = "cellsociety.cellstates.watorworldcellstates.";
  private static final String HANDLER_NAME = "WatorWorldStateHandler";

  public WatorWorldStateHandler() throws RuntimeException {
    super(WatorWorldCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  @Override
  public State figureOutNextState(Neighborhood currNeighborhood) {
    int availableSpots = currNeighborhood.countNextState(WatorWorldCellState.EMPTY);
    if (currNeighborhood.getStateEnum().equals(WatorWorldCellState.SHARK)) {
      availableSpots += currNeighborhood.countNextState(WatorWorldCellState.FISH);
    }

    if (availableSpots > 0) {
      return new EmptyState();
    }
    else {
      return currNeighborhood.getState();
    }
  }
}
