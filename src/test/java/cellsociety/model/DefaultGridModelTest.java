package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Coordinate;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import org.junit.jupiter.api.Test;

class DefaultGridModelTest {

  @Test
  void testUpdateState() {
    NeighborhoodsLoader loaderMock = new GameOfLifeNeighborhoodsLoaderMock(GameOfLifeCellState.ALIVE);
    DefaultGridModel defaultGridModel = new DefaultGridModel(loaderMock, new GameOfLifeStateHandlerMock());
    defaultGridModel.updateState();

    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      assertTrue(loaderMock.getNeighborhood(i).isState(GameOfLifeCellState.DEAD));
    }

  }

  @Test
  void testChangeCellState() {
    NeighborhoodsLoader loaderMock = new GameOfLifeNeighborhoodsLoaderMock(GameOfLifeCellState.ALIVE);
    DefaultGridModel defaultGridModel = new DefaultGridModel(loaderMock, new GameOfLifeStateHandlerMock());
    defaultGridModel.changeCellState(new Coordinate(5, 5));
    assertTrue(loaderMock.getNeighborhood(new Coordinate(5, 5)).isState(GameOfLifeCellState.DEAD));
  }
}