package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Coordinate;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import org.junit.jupiter.api.Test;

class GridModelTest {

  @Test
  void testUpdateState() {
    NeighborhoodsLoader loaderMock = new GameOfLifeNeighborhoodsLoaderMock(GameOfLifeCellState.ALIVE);
    GridModel gridModel = new GridModel(loaderMock, new GameOfLifeStateHandlerMock());
    gridModel.updateState();

    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      assertTrue(loaderMock.getNeighborhood(i).isState(GameOfLifeCellState.DEAD));
    }

  }

  @Test
  void testChangeCellState() {
    NeighborhoodsLoader loaderMock = new GameOfLifeNeighborhoodsLoaderMock(GameOfLifeCellState.ALIVE);
    GridModel gridModel = new GridModel(loaderMock, new GameOfLifeStateHandlerMock());
    gridModel.changeCellState(new Coordinate(5, 5));
    assertTrue(loaderMock.getNeighborhood(new Coordinate(5, 5)).isState(GameOfLifeCellState.DEAD));
  }
}