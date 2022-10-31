package cellsociety.model.statehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.State;
import cellsociety.cellstates.watorworldcellstates.EmptyState;
import cellsociety.cellstates.watorworldcellstates.FishState;
import cellsociety.cellstates.watorworldcellstates.SharkState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

public class WatorWorldStateHandlerTest {

  @Test
  void figureOutNextState_OpenSpacesTest() {
    figureOutNextState_OpenSpacesTest_helper(new FishState());
    figureOutNextState_OpenSpacesTest_helper(new SharkState());
    figureOutNextState_OpenSpacesTest_helper(new EmptyState());
  }

  void figureOutNextState_OpenSpacesTest_helper(State centerState) {
    // Arrange
    CellModel center = new CellModel(centerState);
    CellModel[] neighbors = new CellModel[] {
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
        new CellModel(new EmptyState()),
        new CellModel(new SharkState()),
        new CellModel(new EmptyState()),
    };

    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    WatorWorldStateHandler watorWorldStateHandler = new WatorWorldStateHandler();
    State expectedNextState = new EmptyState();

    // Act
    State actualNextState = watorWorldStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());

  }

  @Test
  void figureOutNextState_SharksWithOnlyFish() {
    // Arrange
    CellModel center = new CellModel(new SharkState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
    };

    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    WatorWorldStateHandler watorWorldStateHandler = new WatorWorldStateHandler();
    State expectedNextState = new EmptyState();

    // Act
    State actualNextState = watorWorldStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());

  }

  @Test
  void figureOutNextState_NoAvailableSpacesTest() {
    figureOutNextState_NoAvailableSpacesTest_helper(new FishState());
    figureOutNextState_NoAvailableSpacesTest_helper(new EmptyState());
  }

  void figureOutNextState_NoAvailableSpacesTest_helper(State centerState) {
    // Arrange
    CellModel center = new CellModel(centerState);
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
        new CellModel(new FishState()),
        new CellModel(new SharkState()),
        new CellModel(new FishState()),
    };

    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    WatorWorldStateHandler watorWorldStateHandler = new WatorWorldStateHandler();
    State expectedNextState = centerState;

    // Act
    State actualNextState = watorWorldStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

}
