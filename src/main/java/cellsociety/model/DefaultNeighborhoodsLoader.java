package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.controller.CellSpawner;
import cellsociety.controller.Spawner;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating neighborhoods of cells
 */
public class DefaultNeighborhoodsLoader implements NeighborhoodsLoader {

  private static final int DEFAULT_DISTANCE = 1;
  private  int distance;
  private final Spawner cellSpawner;
  private int numRows;
  private int numCols;
  private Neighborhood[] neighborhoods;

  /**
   * Generates a list of all neighborhoods that exist in the simulation, one neighborhood for each
   * cell
   *
   * @param cellSpawner Responsible for creating the cells that are placed into neighborhoods
   * @param distance    The number of cells in radius that constitutes being part of a cell's
   *                    neighborhood
   */
  public DefaultNeighborhoodsLoader(CellSpawner cellSpawner, int distance) {
    this.cellSpawner = cellSpawner;
    this.distance = DEFAULT_DISTANCE;

    setNumRows();
    setNumCols();

    loadNeighborhoods();
  }

  public DefaultNeighborhoodsLoader(Spawner cellSpawner) {
    this.cellSpawner = cellSpawner;
  }

  @Override
  public Neighborhood getNeighborhood(int flattenedIdx) {
    return neighborhoods[flattenedIdx];
  }

  @Override
  public Neighborhood getNeighborhood(Coordinate coordinate) {
    return neighborhoods[getFlattenedIdx(coordinate)];
  }

  private boolean validNeighbor(Coordinate centerCoordinate, Coordinate candidateNeighbor) {
    return cellExists(candidateNeighbor) && inNeighborhood(centerCoordinate, candidateNeighbor)
        && !centerCoordinate.equals(candidateNeighbor);
  }

  private boolean tooFarHorizontally(int x1, int x2) {
    return Math.abs(x1 - x2) > distance;
  }

  private boolean tooFarVertically(int y1, int y2) {
    return Math.abs(y1 - y2) > distance;
  }

  private boolean inNeighborhood(Coordinate centerCoord, Coordinate candidateNeighbor) {
    return !(tooFarVertically(centerCoord.y(), candidateNeighbor.y()) || tooFarHorizontally(
        centerCoord.x(), candidateNeighbor.x()));
  }


  private boolean cellExists(Coordinate coordinate) {
    try {
      cellSpawner.getCell(coordinate);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private <T> List<T> iterateRowsAndColsGenerateList(GridIterator<T> gridIterator) {
    List<T> cells = new ArrayList<>();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Coordinate coord = new Coordinate(row, col);
        T obj = gridIterator.create(coord);
        if (obj != null) {
          cells.add(obj);
        }
      }
    }
    return cells;
  }


  private CellModel[] getNeighbors(Coordinate centerCoordinate) {
    GridIterator<CellModel> gridIterator = (coordinate) -> getIfNeighbor(centerCoordinate,
        coordinate);
    List<CellModel> retCells = iterateRowsAndColsGenerateList(gridIterator);
    return retCells.toArray(new CellModel[0]);
  }

  private CellModel getIfNeighbor(Coordinate coord1, Coordinate coord2) {
    CellModel retCell = null;
    if (validNeighbor(coord1, coord2)) {
      retCell = cellSpawner.getCell(coord2);
    }
    return retCell;
  }

  private void loadNeighborhoods() {
    GridIterator<Neighborhood> gridIterator = (coordinate) -> createNeighborhood(coordinate);
    List<Neighborhood> neighborhoods = iterateRowsAndColsGenerateList(gridIterator);
    this.neighborhoods = neighborhoods.toArray(new Neighborhood[0]);
  }

  private Neighborhood createNeighborhood(Coordinate centerCoordinate) {
    CellModel currCell = cellSpawner.getCell(centerCoordinate);
    CellModel[] neighbors = getNeighbors(centerCoordinate);
    Neighborhood currNeighborhood = new Neighborhood(currCell, neighbors);
    return currNeighborhood;
  }


  private int getFlattenedIdx(Coordinate coordinate) {
    return coordinate.y() * numCols + coordinate.x();
  }

  private void setNumRows() {
    numRows = cellSpawner.getNumRows();
  }

  private void setNumCols() {
    numCols = cellSpawner.getNumCols();
  }

  @Override
  public int getNumNeighborhoods() {
    return neighborhoods.length;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }
}
