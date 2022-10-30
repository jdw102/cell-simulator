package cellsociety.model.statehandlers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.State;
import cellsociety.cellstates.firecellstates.EmptyState;
import cellsociety.cellstates.firecellstates.FireCellState;
import cellsociety.cellstates.firecellstates.FireState;
import cellsociety.cellstates.firecellstates.TreeState;
import cellsociety.model.CellModel;
import cellsociety.model.Neighborhood;
import org.junit.jupiter.api.Test;

class FireStateHandlerTest {

  @Test
  void figureOutNextStateFirePutOutTest() {
    // Arrange
    CellModel center = new CellModel(new FireState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),
        new CellModel(new TreeState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    FireStateHandler fireStateHandler = new FireStateHandler();
    State expectedNextState = new EmptyState();

    // Act
    State actualNextState = fireStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  @Test
  void figureOutNextStateTreeCatchesOnFireTest() {
    // Arrange
    CellModel center = new CellModel(new TreeState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new FireState()),
        new CellModel(new EmptyState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    FireStateHandler fireStateHandler = new FireStateHandler();
    State expectedNextState = new FireState();

    // Act
    State actualNextState = fireStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedNextState.getStateEnum(), actualNextState.getStateEnum());
  }

  /**
   * Tests that a tree either catches on fire or remains as a tree
   */
  @Test
  void figureOutNextStateIsolatedTreeTest() {
    // Arrange
    CellModel center = new CellModel(new TreeState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    FireStateHandler fireStateHandler = new FireStateHandler();

    // Act
    State actualNextState = fireStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertThat(actualNextState.getStateEnum(),
        anyOf(is(FireCellState.TREE), is(FireCellState.FIRE)));
  }

  /**
   * Tests that a tree either catches on fire or remains as a tree
   */
  @Test
  void figureOutNextStateEmptyTest() {
    // Arrange
    CellModel center = new CellModel(new EmptyState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    FireStateHandler fireStateHandler = new FireStateHandler();

    // Act
    State actualNextState = fireStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertThat(actualNextState.getStateEnum(),
        anyOf(is(FireCellState.TREE), is(FireCellState.EMPTY)));
  }

  @Test
  void testGetMapping() {
    // Arrange
    CellModel center = new CellModel(new EmptyState());
    CellModel[] neighbors = new CellModel[]{
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),
        new CellModel(new EmptyState()),

    };
    Neighborhood neighborhood = new Neighborhood(center, neighbors);
    FireStateHandler fireStateHandler = new FireStateHandler();
    FireCellState expectedMapping0 = FireCellState.EMPTY;
    FireCellState expectedMapping1 = FireCellState.TREE;
    FireCellState expectedMapping2 = FireCellState.FIRE;

    // Act
    FireCellState actualMapping0 = (FireCellState) fireStateHandler.getMapping(0);
    FireCellState actualMapping1 = (FireCellState) fireStateHandler.getMapping(1);
    FireCellState actualMapping2 = (FireCellState) fireStateHandler.getMapping(2);

    // Act
    State actualNextState = fireStateHandler.figureOutNextState(neighborhood);

    // Assert
    assertEquals(expectedMapping0, actualMapping0);
    assertEquals(expectedMapping1, actualMapping1);
    assertEquals(expectedMapping2, actualMapping2);
  }
}