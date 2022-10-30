package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.controller.Spawner;

public class MockCellSpawner implements Spawner {

  private CellModel[][] grid;
  private int numRows;
  private int numCols;

  public MockCellSpawner(int numRows, int numCols) {
    this.numRows = numRows;
    this.numCols = numCols;
    grid = new CellModel[numRows][numCols];
  }

  @Override
  public CellModel getCell(Coordinate coord) {
    return grid[coord.x()][coord.y()];
  }

  @Override
  public int getNumRows() {
    return numRows;
  }

  @Override
  public int getNumCols() {
    return numCols;
  }
}
