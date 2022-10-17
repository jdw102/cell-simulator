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
    loadNeighborhoods();
  }

  private void initializeCells() {

  }

  public CellSpawner getNeighborhood(int row, int col) {
    return null;
  }

  //returns coordinates of adjacent cells
  private int[][] calcAdjacencyList(int row, int col) {
    List<Integer> adjacents;
    return null;
  }

  private int[][] getVerticalAdjacents(int row, int col) {
    List<int[]> verticalAdjacents = new ArrayList<>();
    for(int i = 1; i <= myDistance; i++) {
      int[] coord_above = new int[]{row, col + i};
      int[] coord_below = new int[]{row, col - i};
      verticalAdjacents.add(coord_above);
      verticalAdjacents.add(coord_below);
    }

    return (int[][]) verticalAdjacents.toArray();
  }

  private int[][] getHorizontalAdjacents(int row, int col) {
    List<int[]> verticalAdjacents = new ArrayList<>();

    for(int i = 1; i <= myDistance; i++) {
      int[] coord_right = new int[]{row + 1, col};
      int[] coord_left = new int[]{row - 1, col};
      verticalAdjacents.add(coord_left);
      verticalAdjacents.add(coord_right);
    }
    return (int[][]) verticalAdjacents.toArray();

  }

  private int[][] getDiagonalAdjacents(int row, int col) {
    List<int[]> verticalAdjacents = new ArrayList<>();

    for(int i = 1; i <= myDistance; i++) {
      int[] coord_top_right = new int[]{row + 1, col + 1};
      int[] coord_top_left = new int[] {row - 1, col + 1};
      int[] coord_bottom_left = new int[]{row - 1, col - 1};
      int[] coord_bottom_right = new int[]{row - 1, col - 1};

      verticalAdjacents.add(coord_top_left);
      verticalAdjacents.add(coord_top_right);
      verticalAdjacents.add(coord_bottom_left);
      verticalAdjacents.add(coord_bottom_right);
    }
    return (int[][]) verticalAdjacents.toArray();
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

    int[][] adjacencies = calcAdjacencyList(row, col);
    for(int[] coord: adjacencies) {
      int curr_row = coord[0];
      int curr_col = coord[1];

      if (cellExists(curr_row, curr_col)) {
        retCells.add(myCellSpawner.getCell(row, col));
      }
    }

    return (CellModel[]) retCells.toArray();
  }

  private void loadNeighborhoods() {
    ArrayList<Neighborhood> neighborhoodTracker = new ArrayList<>();

    int numRows = myCellSpawner.getNumRows();
    int numCols = myCellSpawner.getNumCols();

    List<Neighborhood> retNeighborhoods = new ArrayList<>();

    for(int row = 0; row < numRows; row++) {
      for(int col = 0; col < numCols; col++) {

        CellModel currCell = myCellSpawner.getCell(row, col);
        CellModel[] neighbors = getNeighbors(row, col);

        Neighborhood currNeighborhood = new Neighborhood(currCell, neighbors);
        neighborhoodTracker.add(currNeighborhood);
      }
    }

    myNeighborhoods = (Neighborhood[]) retNeighborhoods.toArray();
  }

  public int getNumCols(List<List<CellModel>> cells) {
    try{
      return cells.get(0).size();
    } catch (Exception e) {
      return 0;
    }
  }

//  public Neighborhood getNeighborhood(int row, int col) {
//    return
//  }

}
