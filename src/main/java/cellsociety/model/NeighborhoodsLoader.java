package cellsociety.model;

import cellsociety.controller.CellSpawner;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating neighborhoods of cells
 */
public class NeighborhoodsLoader {

  private final int myDistance;
  private final CellSpawner myCellSpawner;
  private int myNumRows;
  private int myNumCols;
  private Neighborhood[] myNeighborhoods;

  /**
   * Generates a list of all neighborhoods that exist in the simulation, one neighborhood for each
   * cell
   *
   * @param cellSpawner Responsible for creating the cells that are placed into neighborhoods
   * @param distance    The number of cells in radius that constitutes being part of a cell's
   *                    neighborhood
   */
  public NeighborhoodsLoader(CellSpawner cellSpawner, int distance) {
    myCellSpawner = cellSpawner;
    myDistance = distance;

    setNumRows();
    setNumCols();

    loadNeighborhoods();
  }
  
  public Neighborhood getNeighborhood(int flattenedIdx) {
    return myNeighborhoods[flattenedIdx];
  }


  private boolean validNeighbor(int x1, int y1, int x2, int y2) {
    return (cellExists(x2, y2) && !tooFarHorizontally(x1, x2) && !tooFarVertically(y1, y2)
        && !sameCoord(x1, y1, x2, y2));
  }

  private boolean sameCoord(int x1, int y1, int x2, int y2) {
    return (x1 == x2 && y1 == y2);
  }

  private boolean tooFarHorizontally(int x1, int x2) {
    return (x1 - x2 > myDistance || x2 - x1 > myDistance);
  }

  private boolean tooFarVertically(int y1, int y2) {
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

  private <T> List<T> iterateRowsAndColsGenerateList(GridIterator<T> gridIterator) {
    List<T> cells = new ArrayList<>();
    for (int row = 0; row < myNumRows; row++) {
      for (int col = 0; col < myNumCols; col++) {
        T obj = gridIterator.create(row, col);
        if (obj != null) {
          cells.add(obj);
        }
      }
    }
    return cells;
  }


  private CellModel[] getNeighbors(int row, int col) {
    GridIterator<CellModel> gridIterator = (curr_row, curr_col) -> getIfNeighbor(row, col, curr_row, curr_col);
    List<CellModel> retCells = iterateRowsAndColsGenerateList(gridIterator);
    return retCells.toArray(new CellModel[0]);
  }

  private CellModel getIfNeighbor(int x1, int y1, int x2, int y2) {
    CellModel retCell = null;
    if (validNeighbor(x1, y1, x2, y2)) {
      retCell = myCellSpawner.getCell(x2, y2);
    }
    return retCell;
  }

  private void loadNeighborhoods() {
    GridIterator<Neighborhood> gridIterator = (row, col) -> createNeighborhood(row, col);
    List<Neighborhood> neighborhoods = iterateRowsAndColsGenerateList(gridIterator);
    myNeighborhoods = neighborhoods.toArray(new Neighborhood[0]);
  }

  private Neighborhood createNeighborhood(int row, int col) {
    CellModel currCell = myCellSpawner.getCell(row, col);
    CellModel[] neighbors = getNeighbors(row, col);

    Neighborhood currNeighborhood = new Neighborhood(currCell, neighbors);
    return currNeighborhood;
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
