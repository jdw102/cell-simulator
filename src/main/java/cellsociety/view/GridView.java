package cellsociety.view;

import javafx.scene.layout.GridPane;

/**
 * A class that contains the array of CellView objects.
 */
public class GridView extends DataView {

  private final GridPane grid;
  private int numRows;
  private int numCols;
  private double cellWidth;
  private double cellHeight;

  /**
   * Create a new view for the grid of cell.
   *
   * @param width  the width of the grid
   * @param height the height of the grid
   */
  public GridView(double width, double height) {
    super(width, height);
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
    super.setGridWidth(width);
    super.setGridHeight(height);
    cellWidth = super.getGridWidth() / numCols;
    cellHeight = super.getGridHeight() / numRows;
//    for (CellView c : super.cells) {
//      c.setDimensions(cellWidth, cellHeight);
//    }
    while (this.hasNext()) {
      CellView c = this.next();
      c.setDimensions(cellWidth, cellHeight);
    }
    this.resetIterator();
  }

  /**
   * A method to change the number of rows and columns of the grid, calculate the width and height
   * of each individual cell, and instantiate the cell array.
   */
  public void setDimensions(int rows, int cols) {
    cellWidth = super.getGridWidth() / cols;
    cellHeight = super.getGridHeight() / rows;
    numCols = cols;
    numRows = rows;
  }

  /**
   * Adds a cell view to the corresponding position in the grid pane, sets its dimensions, sets its
   * color resource bundle, and adds it to the array of cells.
   *
   * @param cellView the cell to add
   * @param rowIdx   the column index
   * @param colIdx   the row index
   */
  @Override
  public void addCell(CellView cellView, int rowIdx, int colIdx) {
    super.addCell(cellView, rowIdx, colIdx);
    cellView.setDimensions(cellWidth, cellHeight);
    grid.add(cellView.getCellPane(), colIdx, rowIdx);
  }

  /**
   * Clears the panes on the grid
   */
  @Override
  public void clear() {
    super.clear();
    grid.getChildren().removeAll(grid.getChildren());
  }

  public int getNumCols() {
    return numCols;
  }

  public int getNumRows() {
    return numRows;
  }
}
