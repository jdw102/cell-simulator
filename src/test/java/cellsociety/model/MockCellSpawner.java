package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.controller.Spawner;

public class MockCellSpawner implements Spawner {

  private CellModel[][] grid;
  private int numRows;
  private int numCols;

  public MockCellSpawner(int numRows, int numCols) {
    this.numRows = numRows;
    this.numCols = numCols;
    grid = new CellModel[numRows][numCols];
    setUpGrid();
  }

  /**
   * Initializes a grid of all dead cells, useful to be able to count up
   * the number of neighbors
   */
  private void setUpGrid() {
    for(int i = 0; i < numRows; i++) {
      for(int j = 0; j < numRows; j++) {
        grid[i][j] = new CellModel(new DeadState());
      }
    }

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
