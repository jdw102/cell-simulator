package cellsociety.model;

import cellsociety.State;

import java.util.List;

public interface StateFigureOuter {

  @Deprecated
  State figureOutNextState(int cellRowIndex, int cellColIndex, List<List<CellModel>> cells);

  State figureOutNextState(State currentState, State[] neighboringStates);
}
