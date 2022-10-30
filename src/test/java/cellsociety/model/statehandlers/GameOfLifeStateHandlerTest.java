package cellsociety.model.statehandlers;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.rockpaperscissorcellstates.RockState;
import cellsociety.cellstates.rockpaperscissorcellstates.ScissorState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

class GameOfLifeStateHandlerTest {

  @Test
  void figureOutNextStateAllAliveTest() {
    // Arrange
    CellModel center = new CellModel(new AliveState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new DeadState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateAllDeadTest() {
    // Arrange
    CellModel center = new CellModel(new DeadState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new DeadState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateOneAliveNeighborTest() {
    // Arrange
    CellModel center = new CellModel(new DeadState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new DeadState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateTwoAliveNeighborsTest() {
    // Arrange
    CellModel center = new CellModel(new DeadState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new DeadState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateThreeAliveNeighborsTest() {
    // Arrange
    CellModel center = new CellModel(new DeadState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new AliveState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateFourAliveNeighborsTest() {
    // Arrange
    CellModel center = new CellModel(new DeadState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),
        new CellModel(new DeadState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new AliveState()),
        new CellModel(new DeadState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    GameOfLifeStateHandler gameOfLifeStateHandler = new GameOfLifeStateHandler();
    State expectedNextState = new DeadState();

    // Act
    State actualNextState = gameOfLifeStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }
}