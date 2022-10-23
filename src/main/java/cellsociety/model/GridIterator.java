package cellsociety.model;

import java.util.Collection;

@FunctionalInterface
public interface GridIterator<T> {
  T create(Coordinate coord);
}
