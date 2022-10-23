package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.percolationcellstates.BlockedState;
import cellsociety.cellstates.percolationcellstates.OpenState;
import cellsociety.cellstates.percolationcellstates.PercolatedState;
import cellsociety.cellstates.percolationcellstates.PercolationCellState;
import cellsociety.model.Neighborhood;
import java.util.HashMap;
import java.util.Map;

public class PercolationStateHandler extends StateHandler {

  private Map<Integer, Class> stateOfValue;
  private static final String PROPERTIES_FILE = "PercolationStateHandler";
  private static final String STATES_PACKAGE = "cellsociety.cellstates.percolationcellstates";

  public PercolationStateHandler() {
    super(PercolationCellState.values(), PROPERTIES_FILE, STATES_PACKAGE);
    stateOfValue = new HashMap<>();
    stateOfValue.put(2, BlockedState.class);
    stateOfValue.put(1, PercolatedState.class);
    stateOfValue.put(0, OpenState.class);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
//    if currNeighborhood.contains(PercolationCellState.PERCOLATED);
    return new PercolatedState();

  }


  public Class getMapping(int stateValue) {
    return stateOfValue.get(stateValue);
  }


}
