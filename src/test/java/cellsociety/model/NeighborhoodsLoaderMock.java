package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import java.util.ArrayList;
import java.util.List;

public class NeighborhoodsLoaderMock implements NeighborhoodsLoader {

  Neighborhood[] myNeighborhoods;
  private State myInitStates;
  public static final int NUM_ROWS = 20;
  public static final int NUM_COLS = 30;

  public NeighborhoodsLoaderMock(State initStateOfAllCellModels) {
    myInitStates = initStateOfAllCellModels;
    loadNeighborhoods();
  }

  public Neighborhood[] getNeighborhoods() {
    return myNeighborhoods;
  }

  protected State getInitState() {
    return myInitStates;
  };

  private void loadNeighborhoods() {
    CellModel[][] grid = genCellModelGrid(NUM_ROWS, NUM_COLS);
    myNeighborhoods = convertToNeighborhoods(grid);
  }

  // get a 2D array of Alive Cells (from GameOfLifeCellState)
  private CellModel[][] genCellModelGrid(int numRows, int numCols) {
    CellModel[][] ret = new CellModel[numRows][numCols];
    for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numCols; colIndex++) {
        ret[rowIndex][colIndex] = new CellModel(getInitState());
      }
    }

    return ret;
  }

  // convert 2D array of CellModels to array of neighborhoods
  private Neighborhood[] convertToNeighborhoods(CellModel[][] cellModels) {
    List<Neighborhood> neighborhoods = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < cellModels.length; rowIndex++) {
      for (int colIndex = 0; colIndex < cellModels[rowIndex].length; colIndex++) {
        neighborhoods.add(new Neighborhood(cellModels[rowIndex][colIndex],
            getNeighbors(cellModels, rowIndex, colIndex)));
      }
    }

    return neighborhoods.toArray(new Neighborhood[0]);
  }

  // get 3x3 grid group of cellModels representing the neighbors
  private CellModel[] getNeighbors(CellModel[][] cellModels, int rowIndex, int colIndex) {
    List<CellModel> neighbors = new ArrayList<>();
    Coordinate[] coordinates = new Coordinate[]{
        new Coordinate(-1, 1),
        new Coordinate(-1, -1),
        new Coordinate(1, 1),
        new Coordinate(1, -1),
        new Coordinate(0, 1),
        new Coordinate(0, -1),
        new Coordinate(1, 0),
        new Coordinate(-1, 0),
    };

    for (Coordinate coord : coordinates) {
      try {
        neighbors.add(cellModels[rowIndex + coord.x()][colIndex + coord.y()]);
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      }
    }

    return neighbors.toArray(new CellModel[0]);
  }

  @Override
  public int getNumNeighborhoods() {
    return myNeighborhoods.length;
  }

  @Override
  public Neighborhood getNeighborhood(int flattenedIdx) {
    return myNeighborhoods[flattenedIdx];
  }

  @Override
  public Neighborhood getNeighborhood(Coordinate coordinate) {
    return myNeighborhoods[getFlattenedIdx(coordinate)];
  }

  public int getFlattenedIdx(Coordinate coordinate) {
    return coordinate.y() * NUM_COLS + coordinate.x();
  }
}
