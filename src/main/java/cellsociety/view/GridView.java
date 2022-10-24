package cellsociety.view;

import cellsociety.Coordinate;
import cellsociety.controller.Controller;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;

/**
 * A class that contains the array of CellView objects.
 */
public class GridView {

  private final String DEFAULT_COLORS_PACKAGE = "cellsociety.sim_colors.";
  private final GridPane grid;
  private CellView[][] cells;
  private int numRows;
  private int numCols;
  private double gridWidth;
  private double gridHeight;
  private double cellWidth;
  private double cellHeight;
  private StateColors stateColors;
  private Controller controller;

  /**
   * Create a new view for the grid of cell.
   *
   * @param width  the width of the grid
   * @param height the height of the grid
   */
  public GridView(double width, double height, Controller contr) {
    controller = contr;
    gridWidth = width;
    gridHeight = height;
    grid = new GridPane();
    grid.getStyleClass().add("cell-grid-pane");
  }

  public GridPane getGrid() {
    return grid;
  }

  /**
   * A method to resize each pane in the grid pane to fit the new window dimensions.
   */
  public void resizeGrid(double width, double height) {
    if (cells != null) {
      gridWidth = width;
      gridHeight = height;
      cellWidth = gridWidth / numCols;
      cellHeight = gridHeight / numRows;
      for (CellView[] row : cells) {
        for (CellView c : row) {
          c.setDimensions(cellWidth, cellHeight);
        }
      }
    }
  }

  /**
   * A method to change the number of rows and columns of the grid, calculate the width and height
   * of each individual cell, and instantiate the cell array.
   */
  public void setDimensions(int rows, int cols) {
    cellWidth = gridWidth / cols;
    cellHeight = gridHeight / rows;
    numCols = cols;
    numRows = rows;
    cells = new CellView[rows][cols];
  }

  /**
   * Adds a cell view to the corresponding position in the grid pane, sets its dimensions, sets its
   * color resource bundle, and adds it to the array of cells.
   */
  public void addCell(CellView cellView, int i, int j) {
    cellView.setDimensions(cellWidth, cellHeight);
    cellView.setStateColors(stateColors);
    cellView.getCellPane()
        .setOnMouseClicked(event -> controller.changeCellState(new Coordinate(i, j)));
    cellView.getRectangle()
        .setOnMouseClicked(event -> controller.changeCellState(new Coordinate(i, j)));
    cellView.getRectangle()
        .setId("CellView" + "[" + i + "]" + "[" + j + "]");
    cells[i][j] = cellView;
    grid.add(cellView.getCellPane(), j, i);
  }

  /**
   * Sets the color resource bundle that will be passed to each cell view.
   *
   * @param type the type of simulation used to retrieve the correct property file
   */
  public void setSimType(String type) {
    stateColors = new StateColors(ResourceBundle.getBundle(DEFAULT_COLORS_PACKAGE + type));
  }

  public void clearGrid() {
    grid.getChildren().removeAll(grid.getChildren());
  }

  public StateColors getStateColors() {
    return stateColors;
  }

  public void updateCellColors() {
    for (CellView[] row : cells) {
      for (CellView c : row) {
        c.update();
      }
    }
  }
}
