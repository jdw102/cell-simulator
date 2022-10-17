package cellsociety.view;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;

/**
 * A class that contains the array of CellView objects.
 */
public class GridView {

  private final String DEFAULT_COLORS_PACKAGE = "cellsociety.simcolors";
  private final GridPane grid;
  private CellView[][] cells;
  private int numRows;
  private int numCols;
  private double gridWidth;
  private double gridHeight;
  private double cellWidth;
  private double cellHeight;
  private ResourceBundle colorBundle;

  /**
   * Create a new view for the grid of cell.
   *
   * @param width  the width of the grid
   * @param height the height of the grid
   */
  public GridView(double width, double height) {
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
      double rectHeight = height / numRows;
      double rectWidth = width / numCols;
      for (CellView[] row : cells) {
        for (CellView c : row) {
          c.getRectangle().setWidth(rectWidth);
          c.getRectangle().setHeight(rectHeight);
        }
      }
      cellHeight = rectHeight;
      cellWidth = rectWidth;
    }
  }

  /**
   * A method to change the number of rows and columns of the grid.
   */
  public void setDimensions(int rows, int cols) {
    cellWidth = gridWidth / rows;
    cellHeight = gridHeight / cols;
    numCols = cols;
    numRows = rows;
    cells = new CellView[rows][cols];
  }

  public void addCell(CellView cellView, int i, int j) {
    cellView.setDimensions(cellWidth, cellHeight);
    cellView.setColorBundle(colorBundle);
    cells[i][j] = cellView;
    grid.add(cellView.getCellPane(), i, j);
  }

  public void setSimType(String type) {
    colorBundle = ResourceBundle.getBundle(DEFAULT_COLORS_PACKAGE + type);
  }
}
