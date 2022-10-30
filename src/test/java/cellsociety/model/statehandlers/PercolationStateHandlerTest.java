package cellsociety.model.statehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.State;
import cellsociety.cellstates.firecellstates.EmptyState;
import cellsociety.cellstates.percolationcellstates.BlockedState;
import cellsociety.cellstates.percolationcellstates.OpenState;
import cellsociety.cellstates.percolationcellstates.PercolatedState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

class PercolationStateHandlerTest {

  @Test
  void figureOutNextStateAllOpenStatesTest() {
    // Arrange
    CellModel center = new CellModel(new OpenState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    PercolationStateHandler percolationStateHandler = new PercolationStateHandler();
    State expectedNextState = new OpenState();

    // Act
    State actualNextState = percolationStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateShouldPercolateTest() {
    // Arrange
    CellModel center = new CellModel(new OpenState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),
        new CellModel(new PercolatedState()),
        new CellModel(new OpenState()),
        new CellModel(new OpenState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    PercolationStateHandler percolationStateHandler = new PercolationStateHandler();
    State expectedNextState = new PercolatedState();

    // Act
    State actualNextState = percolationStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateBlockedCellPercolatesAroundTest() {
    // Arrange
    CellModel center = new CellModel(new BlockedState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    PercolationStateHandler percolationStateHandler = new PercolationStateHandler();
    State expectedNextState = new BlockedState();

    // Act
    State actualNextState = percolationStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void getToggledStateIsEmptyTest() {
    // Arrange
    CellModel center = new CellModel(new EmptyState());
    // Neighbors states do not matter
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),
        new CellModel(new PercolatedState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    PercolationStateHandler percolationStateHandler = new PercolationStateHandler();
    State expectedNextState = new BlockedState();

    // Act
    State actualNextState = percolationStateHandler.getToggledState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }
}