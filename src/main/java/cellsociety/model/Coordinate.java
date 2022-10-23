package cellsociety.model;

public record Coordinate(int x, int y) {

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coordinate)) {
      return false;
    }
    Coordinate otherCoord = (Coordinate) obj;
    return this.x == otherCoord.x() && this.y == otherCoord.y();
  }
}
