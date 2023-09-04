package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Coordinate;
import cellsociety.State;
import cellsociety.cellstates.watorworldcellstates.EmptyState;
import cellsociety.cellstates.watorworldcellstates.FishState;
import cellsociety.cellstates.watorworldcellstates.SharkState;
import cellsociety.cellstates.watorworldcellstates.WatorWorldCellState;
import cellsociety.model.statehandlers.WatorWorldStateHandler;
import org.junit.jupiter.api.Test;

public class WatorGridModelTest {
  @Test
  void testUpdateState_AllEmptyStates() {
    NeighborhoodsLoader loaderMock = new NeighborhoodsLoaderMock(new EmptyState());
    WatorWorldGridModel defaultGridModel = new WatorWorldGridModel(loaderMock, new WatorWorldStateHandler());
    defaultGridModel.updateState();

    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      assertTrue(loaderMock.getNeighborhood(i).isState(WatorWorldCellState.EMPTY));
    }
  }

  @Test
  void testUpdateState_AllFishOneShark() {
    NeighborhoodsLoaderMock loaderMock = new NeighborhoodsLoaderMock(new EmptyState());
    WatorWorldGridModel watorWorldGridModel = new WatorWorldGridModel(loaderMock, new WatorWorldStateHandler());

    // Load shark and fish next to each other
    int flattenedIndex = loaderMock.getFlattenedIdx(new Coordinate(5, 5));
    loaderMock.getNeighborhood(new Coordinate(5, 5)).updateCellState(new FishState());
    loaderMock.getNeighborhood(new Coordinate(5, 6)).updateCellState(new SharkState());

    watorWorldGridModel.updateState();

    int sharkCount = 0;
    int fishCount = 0;
    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      if (loaderMock.getNeighborhood(i).isState(WatorWorldCellState.SHARK)) {
        sharkCount += 1;
      }
      if (loaderMock.getNeighborhood(i).isState(WatorWorldCellState.SHARK)) {
        fishCount += 1;
      }
    }

    assertEquals(1, sharkCount);
    assertTrue(fishCount == 1 || fishCount == 0);
  }
}
