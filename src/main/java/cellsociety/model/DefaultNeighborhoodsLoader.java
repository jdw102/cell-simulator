package cellsociety.model;

import cellsociety.Coordinate;
import cellsociety.controller.CellSpawner;
import cellsociety.controller.Spawner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Responsible for creating neighborhoods of cells
 */
public class DefaultNeighborhoodsLoader implements NeighborhoodsLoader {

  private static final String DEFAULT_EDGE_RULE = "Complete";
  private static final int DEFAULT_DISTANCE = 1;
  private  int distance;
  private final Spawner cellSpawner;
  private int numRows;
  private int numCols;
  private Neighborhood[] neighborhoods;

  private Map<String, BiFunction<Coordinate, Coordinate, Boolean>> edgeRules;

  String edgeRule;

  /**
   * Generates a list of all neighborhoods that exist in the simulation, one neighborhood for each
   * cell
   *
   * @param cellSpawner Responsible for creating the cells that are placed into neighborhoods
   */
  public DefaultNeighborhoodsLoader(Spawner cellSpawner) {
    this.cellSpawner = cellSpawner;
    this.distance = DEFAULT_DISTANCE;
    this.edgeRule = DEFAULT_EDGE_RULE;

    setNumRows();
    setNumCols();

    this.edgeRules = new HashMap<>();
    loadEdgeRules();

    loadNeighborhoods();
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

  private boolean validCardinalDistance(Coordinate centerCoordinate, Coordinate candidateNeighbor) {
    return closeAboveOrBelow(centerCoordinate, candidateNeighbor) || closeRightOrLeft(centerCoordinate, candidateNeighbor);
  }
  private boolean closeAboveOrBelow(Coordinate centerCoordinate, Coordinate candidateNeighbor) {
    return centerCoordinate.x() == candidateNeighbor.x() && validDistance(centerCoordinate, candidateNeighbor);
  }

  private boolean closeRightOrLeft(Coordinate centerCoordinate, Coordinate candidateNeighbor) {
    return centerCoordinate.y() == candidateNeighbor.y() && validDistance(centerCoordinate,
        candidateNeighbor);
  }

  private boolean validDistance(Coordinate centerCoordinate, Coordinate candidateCoordinate) {
    boolean xPlane = validDistanceOnPlane(centerCoordinate.x(), candidateCoordinate.x());
    boolean yPlane = validDistanceOnPlane(centerCoordinate.y(), candidateCoordinate.y());
    return xPlane && yPlane;
  }

  /**
   * Given two x or two y values, determine if the difference between the two values is less than
   * the predefined distance value.
   * @param val1 first x or y val
   * @param val2 second x or y val
   * @return
   */
  private boolean validDistanceOnPlane(int val1, int val2) {
    return Math.abs(val1 - val2) <= distance;
  }

  private boolean validToroidalDistance(Coordinate centerCoordinate,
      Coordinate candidateCoordinate) {
    boolean xPlane = validDistanceOnToroidalPlane(centerCoordinate.x(), candidateCoordinate.x(),
        numCols);
    boolean yPlane = validDistanceOnToroidalPlane(centerCoordinate.y(), candidateCoordinate.y(),
        numRows);
    return xPlane && yPlane;
  }

  private boolean validDistanceOnToroidalPlane(int val1, int val2, int planeLength) {
    int min = Math.min(val1, val2);
    int max = Math.max(val1, val2);
    return  validDistanceOnPlane(min,max) || validDistanceOnPlane(min + planeLength,max);
  }

  private boolean inNeighborhood(Coordinate centerCoord, Coordinate candidateNeighbor) {
    return edgeRules.get(edgeRule).apply(centerCoord, candidateNeighbor);
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
    loadNeighborhoods();
  }

  public void setEdgeRule(String rule) throws UnrecognizedEdgeRuleException {

    String actualRule = getValidRule(rule);

    this.edgeRule = rule;

    loadNeighborhoods();
  }

  private String getValidRule(String rule) throws UnrecognizedEdgeRuleException {
    String validRule = null;
    for(String key: edgeRules.keySet()) {
      if(key.equalsIgnoreCase(rule)) {
        validRule = key;
      }
    }
    if (validRule == null) {
      throw new UnrecognizedEdgeRuleException(rule);
    } else {
      return validRule;
    }
  }

  private void loadEdgeRules() {
    edgeRules.put("Complete", (coord1, coord2) -> validDistance(coord1,coord2));
    edgeRules.put("Cardinal", (coord1, coord2) -> validCardinalDistance(coord1,coord2));
    edgeRules.put("Toroidal", (coord1, coord2)-> validToroidalDistance(coord1, coord2));
  }


}
