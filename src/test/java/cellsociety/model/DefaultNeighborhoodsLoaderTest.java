package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Coordinate;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.controller.Spawner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cellsociety.controller.TestUtility;

class DefaultNeighborhoodsLoaderTest {

  Spawner mockSpawner;
  DefaultNeighborhoodsLoader testNeighborhoodsLoader;
  @BeforeEach
  void setup() {
    mockSpawner = new MockCellSpawner(10, 10);
    testNeighborhoodsLoader = new DefaultNeighborhoodsLoader(mockSpawner);
  }

  /**
   * Given the first cell index, we should be able to retrieve a specific cell instances
   */
  @Test
  void getsCorrectFirstCell() {
    Neighborhood flattenedNeighborhood = testNeighborhoodsLoader.getNeighborhood(0);
    Coordinate coord = new Coordinate(0,0);
    Neighborhood coordinatedNeighborhood = testNeighborhoodsLoader.getNeighborhood(coord);

    //Validates flattened index grabber
    assertSame(flattenedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));

    //Validates coordinate index grabber
    assertSame(coordinatedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));
  }

  /**
   * Given the center cell index, we should be able to retrieve a specific cell instances
   */
  @Test
  void getsCorrectCenterCell() {
    int x = 4;
    int y = 4;
    int flattenedIdx = 4*10 + 4;
    Neighborhood flattenedNeighborhood = testNeighborhoodsLoader.getNeighborhood(flattenedIdx);
    Coordinate coord = new Coordinate(x ,y);
    Neighborhood coordinatedNeighborhood = testNeighborhoodsLoader.getNeighborhood(coord);

    //Validates flattened index grabber
    assertSame(flattenedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));

    //Validates coordinate index grabber
    assertSame(coordinatedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));
  }

  /**
   * Given the last cell index, we should be able to retrieve a specific cell instances
   */
  @Test
  void getsCorrectLastCell() {
    int x = 9;
    int y = 9;
    int flattenedIdx = x*10 + y;
    Neighborhood flattenedNeighborhood = testNeighborhoodsLoader.getNeighborhood(flattenedIdx);
    Coordinate coord = new Coordinate(x ,y);
    Neighborhood coordinatedNeighborhood = testNeighborhoodsLoader.getNeighborhood(coord);

    //Validates flattened index grabber
    assertSame(flattenedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));

    //Validates coordinate index grabber
    assertSame(coordinatedNeighborhood.getCenterCell(), mockSpawner.getCell(coord));
  }


  /**
   * For a center cell, the neighborHoods loader should be able to get all 8 of its neighbors
   */
  @Test
  void getsCompleteNeighborhood() {
    int x = 4;
    int y = 4;
    int center = 4*10 + 4;
    Neighborhood centerNeighborhood = testNeighborhoodsLoader.getNeighborhood(center);

    assertEquals(8, centerNeighborhood.getNumNeighbors());

    Coordinate neighbor1 = new Coordinate(3,5);
    Coordinate neighbor2 = new Coordinate(4,5);
    Coordinate neighbor3 = new Coordinate(5,5);
    Coordinate neighbor4 = new Coordinate(5,4);
    Coordinate neighbor5 = new Coordinate(5,3);
    Coordinate neighbor6 = new Coordinate(4,3);
    Coordinate neighbor7 = new Coordinate(3,3);
    Coordinate neighbor8 = new Coordinate(3,4);


    Coordinate[] expectedNeighbors = {neighbor1,neighbor2,neighbor3,neighbor4,neighbor5,neighbor6,
        neighbor6,neighbor7,neighbor8};

    for(Coordinate coord: expectedNeighbors) {
      CellModel cell = mockSpawner.getCell(coord);
      assertTrue(inNeighborhood(cell, centerNeighborhood));
    }
  }

  /**
   * Given a neighborhood size equal to the max width/height of a grid, a cells neighborhood
   * should be every cell but itself
   */
  @Test
  void getsCompleteNeighborhoodFullSize() {
    int x = 4;
    int y = 4;
    int center = 4*10 + 4;
    testNeighborhoodsLoader.setDistance(10);
    Neighborhood centerNeighborhood = testNeighborhoodsLoader.getNeighborhood(center);
    assertEquals(99, centerNeighborhood.getNumNeighbors());

    for(int i = 0; i < 10; i ++) {
      for(int j = 0; j < 10; j++) {
        Coordinate cellCoord = new Coordinate(i, j);
        CellModel cell = mockSpawner.getCell(cellCoord);
        if(i != x || j != y) {
          assertTrue(inNeighborhood(cell, centerNeighborhood));
        } else {
          assertFalse(inNeighborhood(cell, centerNeighborhood));
        }
      }
    }
  }

  @Test
  void getsTorroidalNeighborhood() {

  }


  private boolean inNeighborhood(CellModel cell, Neighborhood neighborhood) {
    for(int i = 0; i < neighborhood.getNumNeighbors(); i++) {
      if(neighborhood.getNeighbor(i) == cell) {
        return true;
      }
    }
    return false;
  }

}