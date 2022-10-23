package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Observable;
import cellsociety.Observer;
import cellsociety.cellStates.Alive;
import cellsociety.cellStates.Dead;
import cellsociety.cellStates.GameOfLifeCellState;
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
    CellModel cellModel = new CellModel(new Alive());
    TestObserver observer = new TestObserver();
    cellModel.notifyObservers();

    assertEquals(1, observer.getUpdatedCount());
  }

  @Test
  public void testNotifyObservers_onManyObservers() {
    CellModel cellModel = new CellModel(new Alive());
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
    CellModel cellModel = new CellModel(new Alive());
    assertDoesNotThrow(() -> cellModel.notifyObservers());
  }

  @Test
  public void testRemoveObservers() {
    CellModel cellModel = new CellModel(new Alive());
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
    CellModel cellModel = new CellModel(new Alive());
    Observer observer = new TestObserver();
    assertDoesNotThrow(() -> cellModel.removeObserver(observer));
  }

  @Test
  public void testThatObserversAreNotified_when_setCurrentState_called() {
    CellModel cellModel = new CellModel(new Alive());
    TestObserver observer = new TestObserver();
    cellModel.addObserver(observer);

    cellModel.setCurrentState(new Dead());
    assertEquals(1, observer.getUpdatedCount());
  }

  @Test
  public void test_getCurrentStateEnum() {
    CellModel cellModel = new CellModel(new Alive());
    assertEquals(GameOfLifeCellState.ALIVE, cellModel.getCurrentStateEnum());
  }

  @Test
  public void test_getCurrentState() {
    CellModel cellModel = new CellModel(new Alive());
    assertTrue(cellModel.getCurrentState() instanceof Alive);

    // code below fails because we haven't added a .equals method to cell state classes:
    // assertTrue(new Alive().equals(cellModel.getCurrentState()));

  }


}
