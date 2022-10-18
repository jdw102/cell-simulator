package cellsociety.cellStates.gameOfLifeCellStates;

public class Dead extends GameOfLifeCellState {

  @Override
  public Enum getStateEnum() {
    return CellStates.DEAD;
  }
}