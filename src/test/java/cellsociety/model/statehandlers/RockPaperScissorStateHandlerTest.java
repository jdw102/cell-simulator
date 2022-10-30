package cellsociety.model.statehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.State;
import cellsociety.cellstates.rockpaperscissorcellstates.PaperState;
import cellsociety.cellstates.rockpaperscissorcellstates.RockState;
import cellsociety.cellstates.rockpaperscissorcellstates.ScissorState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

class RockPaperScissorStateHandlerTest {

  @Test
  void figureOutNextStateStateChangeTest() {
    // Arrange
    CellModel center = new CellModel(new ScissorState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new RockState()),
        new CellModel(new RockState()),
        new CellModel(new RockState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    RockPaperScissorStateHandler rockPaperScissorStateHandler = new RockPaperScissorStateHandler();
    State expectedNextState = new RockState();

    // Act
    State actualNextState = rockPaperScissorStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateNoStateChangeTest() {
    // Arrange
    CellModel center = new CellModel(new ScissorState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new RockState()),
        new CellModel(new RockState()),
        new CellModel(new PaperState()),
        new CellModel(new PaperState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),
        new CellModel(new ScissorState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    RockPaperScissorStateHandler rockPaperScissorStateHandler = new RockPaperScissorStateHandler();
    State expectedNextState = new ScissorState();

    // Act
    State actualNextState = rockPaperScissorStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateEdgeCellChangeStateTest() {
    // Arrange
    CellModel center = new CellModel(new RockState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new PaperState()),
        new CellModel(new PaperState()),
        new CellModel(new PaperState())
    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    RockPaperScissorStateHandler rockPaperScissorStateHandler = new RockPaperScissorStateHandler();
    State expectedNextState = new PaperState();

    // Act
    State actualNextState = rockPaperScissorStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }
}