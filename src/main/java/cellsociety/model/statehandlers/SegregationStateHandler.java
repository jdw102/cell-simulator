package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.segregationcellstates.SegregationCellState;
import cellsociety.model.Neighborhood;

public class SegregationStateHandler extends StateHandler {
  private static final String STATES_PACKAGE = "cellsociety.cellstates.segregationcellstates.";
  private static final String HANDLER_NAME = "SegregationStateHandler";
  public static final double THRESHOLD = 0.3;

  public SegregationStateHandler() {
    super(SegregationCellState.class, STATES_PACKAGE, HANDLER_NAME);
  }

  @Override
  public State figureOutNextState(Neighborhood neighborhood) {
    // return EMPTY if already EMPTY
    SegregationCellState currStateEnum = (SegregationCellState) neighborhood.getStateEnum();
    if (currStateEnum.equals(SegregationCellState.EMPTY)) {
      return getStateInstance(SegregationCellState.EMPTY);
    }

    // must be either AGENT_X or AGENT_Y
    int numCurrStateEnum = neighborhood.count(currStateEnum);
    int numOpposite = neighborhood.count(getOpposite(currStateEnum));
    int total = numCurrStateEnum + numOpposite;

    if ((double) numCurrStateEnum / total >= THRESHOLD) {
      return getStateInstance(currStateEnum);
    }
    else {
      return getStateInstance(SegregationCellState.EMPTY);
    }

  }

  private SegregationCellState getOpposite(SegregationCellState cellStateEnum) {
    if (cellStateEnum.equals(SegregationCellState.AGENT_X)) {
      return SegregationCellState.AGENT_Y;
    }
    else if (cellStateEnum.equals(SegregationCellState.AGENT_Y)) {
      return SegregationCellState.AGENT_X;
    }
    else {
      // protects against improper usage by programmer calling this function
      throw new RuntimeException("Must pass either AGENT_X or AGENT_Y into this private method");
    }
  }
}
