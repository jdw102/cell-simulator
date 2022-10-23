package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellStates.percolationcellstates.Blocked;
import cellsociety.cellStates.percolationcellstates.Open;
import cellsociety.cellStates.percolationcellstates.Percolated;
import cellsociety.cellStates.percolationcellstates.PercolationCellState;
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
    stateOfValue.put(2, Blocked.class);
    stateOfValue.put(1, Percolated.class);
    stateOfValue.put(0, Open.class);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
//    if currNeighborhood.contains(PercolationCellState.PERCOLATED);
    return new Percolated();

  }


  public Class getMapping(int stateValue) {
    return stateOfValue.get(stateValue);
  }


}
