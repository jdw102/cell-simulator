package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.segregationcellstates.SegregationCellState;
import cellsociety.model.Neighborhood;

public class SegregationStateHandler extends StateHandler {
  private static final String STATES_PACKAGE = "cellsociety.cellstates.segregationcellstates.";
  private static final String HANDLER_NAME = "SegregationStateHandler";
  public static final double THRESHOLD = 0.3;   // TODO: Read this in from SIM params!

  public SegregationStateHandler() throws RuntimeException {
    super(SegregationCellState.class, HANDLER_NAME, STATES_PACKAGE);
  }

  @Override
  public State figureOutNextState(Neighborhood neighborhood) {
    // return EMPTY if already EMPTY
    Enum currStateEnum = neighborhood.getStateEnum();
    if (currStateEnum.equals(SegregationCellState.EMPTY)) {
      return getStateInstance(SegregationCellState.EMPTY);
    }

    int numCurrStateEnum = neighborhood.count(currStateEnum);
    int total = countNonEmptyAgents(neighborhood);

    if (total == 0 || (double) numCurrStateEnum / total >= THRESHOLD) {
      return getStateInstance(currStateEnum);
    }
    else {
      return getStateInstance(SegregationCellState.EMPTY);
    }
  }

  private int countNonEmptyAgents(Neighborhood neighborhood) {
    int count = 0;
    for (SegregationCellState cellStateEnum : SegregationCellState.values()) {
      if (!cellStateEnum.equals(SegregationCellState.EMPTY)) {
        count += neighborhood.count(cellStateEnum);
      }
    }
    return count;
  }
}
