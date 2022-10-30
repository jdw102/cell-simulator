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
    SegregationCellState currStateEnum = (SegregationCellState) neighborhood.getStateEnum();
    if (currStateEnum.equals(SegregationCellState.EMPTY)) {
      return getStateInstance(SegregationCellState.EMPTY);
    }

    // must be either AGENT1 or AGENT2
    int numCurrStateEnum = neighborhood.count(currStateEnum);
    int numOpposite = neighborhood.count(getOpposite(currStateEnum));
    int total = numCurrStateEnum + numOpposite;

    if (total == 0 || (double) numCurrStateEnum / total >= THRESHOLD) {
      return getStateInstance(currStateEnum);
    }
    else {
      return getStateInstance(SegregationCellState.EMPTY);
    }

  }

  private SegregationCellState getOpposite(SegregationCellState cellStateEnum) {
    if (cellStateEnum.equals(SegregationCellState.AGENT1)) {
      return SegregationCellState.AGENT2;
    }
    else if (cellStateEnum.equals(SegregationCellState.AGENT2)) {
      return SegregationCellState.AGENT1;
    }
    else {
      // protects against improper usage by programmer calling this function
      throw new RuntimeException("Must pass either AGENT1 or AGENT2 into this private method");
    }
  }
}
