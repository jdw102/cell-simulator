package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
import cellsociety.cellStates.percolationcellstates.Blocked;
import cellsociety.cellStates.percolationcellstates.Open;
import cellsociety.cellStates.percolationcellstates.Percolated;
import cellsociety.cellStates.percolationcellstates.PercolationCellState;
import cellsociety.model.Neighborhood;
import cellsociety.model.StateHandler;
import java.util.HashMap;
import java.util.Map;

public class PercolationStateHandler implements StateHandler {

  Map<Integer, Class> stateOfValue;

  public PercolationStateHandler() {
    stateOfValue = new HashMap<>();
    stateOfValue.put(2, Blocked.class);
    stateOfValue.put(1, Percolated.class);
    stateOfValue.put(0, Open.class);
  }

  @Override
  public State figureOutNextState(Neighborhood currNeighborhood) {
//    if currNeighborhood.contains(PercolationCellState.PERCOLATED);

  }

  @Override
  public Class getMapping(int stateValue) {
    return stateOfValue.get(stateValue);
  }


}
