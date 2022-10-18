package cellsociety.model;

import cellsociety.controller.CellSpawner;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Cell;

public class NeighborhoodsLoader {

  private int myDistance;
  private int myNumRows;
  private int myNumCols;
  private Neighborhood[] myNeighborhoods;

  private CellSpawner myCellSpawner;

  public NeighborhoodsLoader(CellSpawner cellSpawner, int distance) {
    myCellSpawner = cellSpawner;
    myDistance = distance;

    setNumRows();
    setNumCols();

    loadNeighborhoods();
  }

  private void initializeCells() {

  }

  public Neighborhood getNeighborhood(int row, int col) {
    int flattenedIdx = getFlattenedIdx(row, col);
    return myNeighborhoods[flattenedIdx];
  }

  public Neighborhood getNeighborhood(int flattenedIdx) {
    return myNeighborhoods[flattenedIdx];
  }

  private int getFlattenedIdx(int row, int col) {
    return row * myNumCols + col;
  }

  //Hard coded just for now w/ d = 1, will improve to calculate all neighbors for any given distance
  private int[][] calcAdjacencyList(int row, int col) {
    return new int[][]{{row, col + 1}, {row + 1, col}, {row + 1, col + 1}, {row - 1, col},
        {row, col - 1}, {row - 1, col - 1}, {row + 1, col - 1}, {row - 1, col + 1}};
  }

  private boolean cellExists(int row, int col) {
    try {
      myCellSpawner.getCell(row, col);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private CellModel[] getNeighbors(int row, int col) {
    ArrayList<CellModel> retCells = new ArrayList<>();

    int[][] adjacents = calcAdjacencyList(row, col);
    for (int[] coord : adjacents) {
      int curr_row = coord[0];
      int curr_col = coord[1];

      if (cellExists(curr_row, curr_col)) {
        retCells.add(myCellSpawner.getCell(curr_row, curr_col));
      }
    }

    return retCells.toArray(new CellModel[0]);
  }

  private void loadNeighborhoods() {
    ArrayList<Neighborhood> neighborhoodTracker = new ArrayList<>();

//    List<Neighborhood> retNeighborhoods = new ArrayList<>();
    for (int row = 0; row < myNumRows; row++) {
      for (int col = 0; col < myNumCols; col++) {

        CellModel currCell = myCellSpawner.getCell(row, col);
        CellModel[] neighbors = getNeighbors(row, col);

        Neighborhood currNeighborhood = new Neighborhood(currCell, neighbors);
        neighborhoodTracker.add(currNeighborhood);
      }
    }

    myNeighborhoods = neighborhoodTracker.toArray(new Neighborhood[0]);
  }


  private void setNumRows() {
    myNumRows = myCellSpawner.getNumRows();
  }

  private void setNumCols() {
    myNumCols = myCellSpawner.getNumCols();
  }

  public int getNumNeighborhoods() {
    return myNeighborhoods.length;
  }

}
