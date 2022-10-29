package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.model.statehandlers.StateHandler;

public interface GridModel {

  void updateState();

  void changeCellState(Coordinate coordinate);
}
