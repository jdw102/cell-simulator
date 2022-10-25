package cellsociety.model;

import cellsociety.Coordinate;

public interface NeighborhoodsLoader {
  int getNumNeighborhoods();
  Neighborhood getNeighborhood(Coordinate coordinate);

  Neighborhood getNeighborhood(int flattenedIdx);
}
