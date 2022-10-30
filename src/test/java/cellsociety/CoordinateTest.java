package cellsociety;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateTest {

  @Test
  void differentCoordinatesNotEqualsTest() {
    // Arrange
    Coordinate coordinate1 = new Coordinate(5, 4);
    Coordinate coordinate2 = new Coordinate(4,5);

    // Assert
    assertNotEquals(coordinate1, coordinate2);
  }

  @Test
  void coordinatesAreEqualTest() {
    // Arrange
    Coordinate coordinate1 = new Coordinate(5, 4);
    Coordinate coordinate2 = new Coordinate(5,4);

    // Assert
    assertEquals(coordinate1, coordinate2);
  }

  @Test
  void coordinateNotEqualToNonCoordinateTest() {
    // Arrange
    Coordinate coordinate = new Coordinate(5,4);
    Object object = new Object();

    // Assert
    assertNotEquals(coordinate, object);
  }

}