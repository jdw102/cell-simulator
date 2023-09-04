package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Coordinate;
import cellsociety.cellstates.segregationcellstates.Agent1State;
import cellsociety.cellstates.segregationcellstates.Agent2State;
import cellsociety.cellstates.segregationcellstates.EmptyState;
import cellsociety.cellstates.segregationcellstates.SegregationCellState;
import cellsociety.model.statehandlers.SegregationStateHandler;
import org.junit.jupiter.api.Test;

public class SegregationGridModelTest {
  @Test
  void testUpdateState_AllEmptyStates() {
    NeighborhoodsLoader loaderMock = new NeighborhoodsLoaderMock(new EmptyState());
    SegregationGridModel defaultGridModel = new SegregationGridModel(loaderMock, new SegregationStateHandler());
    defaultGridModel.updateState();

    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      assertTrue(loaderMock.getNeighborhood(i).isState(SegregationCellState.EMPTY));
    }
  }

  @Test
  void testUpdateState_OneAgent1_ManyAgent2() {
    NeighborhoodsLoaderMock loaderMock = new NeighborhoodsLoaderMock(new EmptyState());
    SegregationGridModel segregationGridModel = new SegregationGridModel(loaderMock, new SegregationStateHandler());

    // Load shark and fish next to each other
    int flattenedIndex = loaderMock.getFlattenedIdx(new Coordinate(5, 5));
    loaderMock.getNeighborhood(new Coordinate(5, 5)).updateCellState(new Agent1State());
    loaderMock.getNeighborhood(new Coordinate(5, 6)).updateCellState(new Agent2State());
    loaderMock.getNeighborhood(new Coordinate(6, 5)).updateCellState(new Agent2State());
    loaderMock.getNeighborhood(new Coordinate(5, 4)).updateCellState(new Agent2State());
    loaderMock.getNeighborhood(new Coordinate(4, 5)).updateCellState(new Agent2State());

    segregationGridModel.updateState();

    int agent1Count = 0;
    int agent2Count = 0;
    for (int i = 0; i < loaderMock.getNumNeighborhoods(); i++) {
      if (loaderMock.getNeighborhood(i).isState(SegregationCellState.AGENT1)) {
        agent1Count += 1;
        assertFalse(flattenedIndex == i);
      }
      if (loaderMock.getNeighborhood(i).isState(SegregationCellState.AGENT2)) {
        agent2Count += 1;
      }
    }

    assertEquals(1, agent1Count);
    assertEquals(4, agent2Count);
  }
}
