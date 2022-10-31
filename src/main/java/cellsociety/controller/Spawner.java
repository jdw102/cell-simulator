package cellsociety.controller;

import cellsociety.Coordinate;
import cellsociety.model.CellModel;
import cellsociety.view.GridView;

public interface Spawner {

  CellModel getCell(Coordinate coord);

  int getNumRows();

  int getNumCols();
}
