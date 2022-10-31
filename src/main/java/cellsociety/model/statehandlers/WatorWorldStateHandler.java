package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.watorworldcellstates.EmptyState;
import cellsociety.cellstates.watorworldcellstates.WatorWorldCellState;
import cellsociety.model.Neighborhood;

public class WatorWorldStateHandler extends StateHandler {
  private static final String SIM_TYPE = "WatorWorld";

  public WatorWorldStateHandler() throws RuntimeException {
    super(WatorWorldCellState.class, SIM_TYPE);
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
