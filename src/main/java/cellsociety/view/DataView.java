package cellsociety.view;

import cellsociety.Coordinate;
import cellsociety.controller.Controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public abstract class DataView implements Iterator<CellView> {

  private final String DEFAULT_COLORS_PACKAGE = "cellsociety.sim_colors.";
  public List<CellView> cells;
  private double gridWidth;
  private double gridHeight;
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
    gridWidth = width;
    gridHeight = height;
    cells = new ArrayList<>();
  }

  public void setSimType(String type) {
    stateColors = new StateColors(ResourceBundle.getBundle(DEFAULT_COLORS_PACKAGE + type));
  }

  public void clear() {
    cells.clear();
  }

  public StateColors getStateColors() {
    return stateColors;
  }


  /**
   * Sets the controller of the grid view.
   */
  public void setController(Controller contr) {
    controller = contr;
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
    cellView.getRectangle()
        .setId("CellView" + "[" + rowIdx + "]" + "[" + colIdx + "]");
    cellView.getCellPane()
        .setOnMouseClicked(event -> controller.changeCellState(c));
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

  public double getGridWidth() {
    return gridWidth;
  }

  public void setGridWidth(double width) {
    gridWidth = width;
  }

  public double getGridHeight() {
    return gridHeight;
  }

  public void setGridHeight(double height) {
    gridHeight = height;
  }
}
