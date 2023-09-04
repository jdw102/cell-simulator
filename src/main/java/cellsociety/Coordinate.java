package cellsociety;

/**
 * @param x
 * @param y
 * @author Daniel Feinblatt
 */
public record Coordinate(int x, int y) {

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coordinate otherCoord)) {
      return false;
    }
    return this.x == otherCoord.x() && this.y == otherCoord.y();
  }
}
