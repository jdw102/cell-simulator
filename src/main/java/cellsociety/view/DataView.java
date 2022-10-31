package cellsociety.view;

import cellsociety.Coordinate;
import cellsociety.controller.Controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class DataView implements Iterator<CellView> {

  public List<CellView> cells;
  private double width;
  private double height;
  private StateColors stateColors;
  private Controller controller;
  private Iterator<CellView> iterator;

  /**
   * Create a new view for the grid of cell.
   *
   * @param width  the width of the grid
   * @param height the height of the grid
   */
  public DataView(double width, double height) {
    this.width = width;
    this.height = height;
    cells = new ArrayList<>();
  }

  public void clear() {
    cells.clear();
  }

  public StateColors getStateColors() {
    return stateColors;
  }

  public void setStateColors(StateColors colors) {
    stateColors = colors;
  }

  /**
   * Sets the controller of the grid view.
   */
  public void setController(Controller contr) {
    controller = contr;
  }

  public void resize(double width, double height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public CellView next() {
    return iterator.next();
  }

  public void resetIterator() {
    iterator = cells.iterator();
  }

  public void addCell(CellView cellView, int rowIdx, int colIdx) {
    Coordinate c = new Coordinate(colIdx, rowIdx);
    cellView.setStateColors(stateColors);
    cellView.attachId(
        String.format("CellView[%s][%s]", Integer.toString(rowIdx), Integer.toString(colIdx)));
    cellView.attachOnClick(event -> controller.changeCellState(c));
    cellView.setCoordinate(c);
    cells.add(cellView);
    iterator = cells.iterator();
  }

  /**
   * Updates all the cells to their correct colors.
   */
  public void updateCellColors() {
    for (CellView c : cells) {
      c.update();
    }
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public Node getNode() {
    return new HBox();
  }
}
