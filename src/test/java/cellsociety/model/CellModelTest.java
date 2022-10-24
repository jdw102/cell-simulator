package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Observable;
import cellsociety.Observer;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.Test;

public class CellModelTest {

  public class TestObserver implements Observer {
    private int updatedCount = 0;
    @Override
    public void update() {
      updatedCount += 1;
    }

    public int getUpdatedCount() {
      return updatedCount;
    }
  }

  @Test
  public void testNotifyObservers_onOneObserver() {
    CellModel cellModel = new CellModel(new AliveState());
    TestObserver observer = new TestObserver();
    cellModel.addObserver(observer);
    cellModel.notifyObservers();

    assertEquals(1, observer.getUpdatedCount());
  }

  @Test
  public void testNotifyObservers_onManyObservers() {
    CellModel cellModel = new CellModel(new AliveState());
    TestObserver[] observers = new TestObserver[20];

    for (int i = 0; i < observers.length; i++) {
      observers[i] = new TestObserver();
      cellModel.addObserver(observers[i]);
    }

    cellModel.notifyObservers();
    for (TestObserver observer : observers) {
      assertEquals(1, observer.updatedCount);
    }
  }

  @Test
  public void testNotifyObservers_whenNoObserversExist() {
    CellModel cellModel = new CellModel(new AliveState());
    assertDoesNotThrow(() -> cellModel.notifyObservers());
  }

  @Test
  public void testRemoveObservers() {
    CellModel cellModel = new CellModel(new AliveState());
    TestObserver[] observers = new TestObserver[20];

    // add observers
    for (int i = 0; i < observers.length; i++) {
      observers[i] = new TestObserver();
      cellModel.addObserver(observers[i]);
    }

    // remove observers
    for (Observer observer : observers) {
      cellModel.removeObserver(observer);
    }

    // notify all observers in cellModel (none
    // should exist in cellModel be none) and
    // assert that each observer wasn't notified
    cellModel.notifyObservers();
    for (TestObserver observer : observers) {
      assertEquals(0, observer.updatedCount);
    }
  }

  @Test
  public void testRemovingObserver_thatDoesNotExist_inCellModelObservers() {
    CellModel cellModel = new CellModel(new AliveState());
    Observer observer = new TestObserver();
    assertDoesNotThrow(() -> cellModel.removeObserver(observer));
  }

  @Test
  public void testThatObserversAreNotified_when_setCurrentState_called() {
    CellModel cellModel = new CellModel(new AliveState());
    TestObserver observer = new TestObserver();
    cellModel.addObserver(observer);

    cellModel.setCurrentState(new DeadState());
    assertEquals(1, observer.getUpdatedCount());
  }

  @Test
  public void test_getCurrentStateEnum() {
    CellModel cellModel = new CellModel(new AliveState());
    assertEquals(GameOfLifeCellState.ALIVE, cellModel.getCurrentStateEnum());
  }

  @Test
  public void test_getCurrentState() {
    CellModel cellModel = new CellModel(new AliveState());
    assertTrue(cellModel.getCurrentState() instanceof AliveState);

    // code below fails because we haven't added a .equals method to cell state classes:
    // assertTrue(new Alive().equals(cellModel.getCurrentState()));

  }


}
