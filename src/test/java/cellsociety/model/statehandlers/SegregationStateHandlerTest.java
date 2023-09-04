package cellsociety.model.statehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.State;
import cellsociety.cellstates.segregationcellstates.Agent1State;
import cellsociety.cellstates.segregationcellstates.Agent2State;
import cellsociety.cellstates.segregationcellstates.EmptyState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

public class SegregationStateHandlerTest {

  @Test
  void figureOutNextState_ShouldMove() {
    // Arrange
    CellModel center = new CellModel(new Agent2State());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent2State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
    };

    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    StateHandler segregationStateHandler = new SegregationStateHandler();
    State expectedNextState = new EmptyState();

    // Act
    State actualNextState = segregationStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());

  }

  @Test
  void figureOutNextState_ShouldStay() {
    // Arrange
    CellModel center = new CellModel(new Agent1State());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent2State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
        new CellModel(new Agent1State()),
    };

    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    StateHandler segregationStateHandler = new SegregationStateHandler();
    State expectedNextState = new Agent1State();

    // Act
    State actualNextState = segregationStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());

  }

}
