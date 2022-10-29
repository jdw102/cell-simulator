package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.segregationcellstates.SegregationCellState;
import cellsociety.model.Neighborhood;

public class SegregationStateHandler extends StateHandler {
  private static final String STATES_PACKAGE = "cellsociety.cellstates.segregationcellstates.";
  private static final String HANDLER_NAME = "SegregationStateHandler";

  public SegregationStateHandler() {
    super(SegregationCellState.class, STATES_PACKAGE, HANDLER_NAME);
  }

  @Override
  public State figureOutNextState(Neighborhood neighborhood) {
    return null;
  }
}
