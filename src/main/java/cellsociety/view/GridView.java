package cellsociety.view;

import cellsociety.model.GridModel;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;
/**
 * A class that contains the array of CellView objects.
 */
public class GridView {
    private GridPane grid;
    private CellView[][] cells;
    private int numRows;
    private int numCols;
    /**
     * Create a new view for the grid of cell.
     * @param rows the number of rows of the grid
     * @param cols the number of cols of the grid
     * @param width the width of the grid
     * @param height the height of the grid
     * @param colorOptions the resource bundle containing the colors for each state
     */
    public GridView(int rows, int cols, double width, double height, ResourceBundle colorOptions){
        setDimensions(rows, cols);
        grid = new GridPane();
        grid.getStyleClass().add("cell-grid-pane");
        cells = new CellView[rows][cols];
        createCells(width, height, colorOptions);
    }
    /**
     * A method to set up the grid pane that will display the cell views.
     */
    private void createCells(double width, double height, ResourceBundle colorOptions){
        double rectHeight = height / numRows;
        double rectWidth = width / numCols;
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                CellView newCell = new CellView(rectWidth, rectHeight, colorOptions);
                cells[i][j] = newCell;
                GridPane.setRowIndex(newCell.getCellPane(), i);
                GridPane.setColumnIndex(newCell.getCellPane(), j);
                grid.getChildren().addAll(newCell.getCellPane());
            }
        }
    }
    public GridPane getGrid(){
        return grid;
    }
    /**
     * A method to resize each pane in the grid pane to fit the new window dimensions.
     */
    public void resizeGrid(double width, double height){
        double rectHeight = height / numRows;
        double rectWidth = width / numCols;
        for (CellView[] row: cells){
            for (CellView c: row){
                c.getRectangle().setWidth(rectWidth);
                c.getRectangle().setHeight(rectHeight);
            }
        }
    }
    /**
     * A method to change the number of rows and columns of the grid.
     */
    public void setDimensions(int rows, int cols){
        numCols = cols;
        numRows = rows;
    }
}
