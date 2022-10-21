package cellsociety.model;

import cellsociety.controller.CellSpawner;
import java.util.ArrayList;

public class NeighborhoodsLoader {

  private final int myDistance;
  private int myNumRows;
  private int myNumCols;
  private Neighborhood[] myNeighborhoods;

  private final CellSpawner myCellSpawner;

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


  private boolean validNeighbor(int x1, int y1, int x2, int y2) {
    return (cellExists(x2, y2) && !tooFarHorizontally(x1, x2) && !tooFarVertically(y1, y2)
        && !sameCoord(x1, y1, x2, y2));
  }

  private boolean sameCoord(int x1, int y1, int x2, int y2) {
    return (x1 == x2 && y1 == y2);
  }

  private boolean tooFarHorizontally(int x1, int x2) {
//    if(warpHorizontal) return ((x1 - x2) % (myNumCols + 1) > myDistance ||(x2 - x1) % (myNumCols + 1) > myDistance);
    return (x1 - x2 > myDistance || x2 - x1 > myDistance);
  }

  private boolean tooFarVertically(int y1, int y2) {
    //    if(warpVertical) return ((y1 - y2) % (myNumRows + 1) > myDistance ||(y2 - y1) % (myNumRows + 1) > myDistance);
    return (y1 - y2 > myDistance || y2 - y1 > myDistance);
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

    for (int curr_row = 0; curr_row < myNumRows; curr_row++) {
      for (int curr_col = 0; curr_col < myNumCols; curr_col++) {

        if (validNeighbor(row, col, curr_row, curr_col)) {
          retCells.add(myCellSpawner.getCell(curr_row, curr_col));
        }

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
