package cellsociety.model;

import cellsociety.Coordinate;

@FunctionalInterface
public interface GridIterator<T> {

  T create(Coordinate coord);
}
