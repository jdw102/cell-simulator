package cellsociety.model;

import cellsociety.Coordinate;

public interface GridModel {

  void updateState();

  void changeCellState(Coordinate coordinate);
}
