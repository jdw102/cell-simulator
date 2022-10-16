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
    private double gridWidth;
    private double gridHeight;
    private double cellWidth;
    private double cellHeight;
    /**
     * Create a new view for the grid of cell.
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public GridView(double width, double height){
        gridWidth = width;
        gridHeight = height;
        grid = new GridPane();
        grid.getStyleClass().add("cell-grid-pane");
    }
//    /**
//     * A method to set up the grid pane that will display the cell views.
//     */
//    private void createCells(double width, double height, ResourceBundle colorOptions){
//        double rectHeight = height / numRows;
//        double rectWidth = width / numCols;
//        for (int i = 0; i < numRows; i++){
//            for (int j = 0; j < numCols; j++){
//                CellView newCell = new CellView(rectWidth, rectHeight, colorOptions);
//                cells[i][j] = newCell;
//                GridPane.setRowIndex(newCell.getCellPane(), i);
//                GridPane.setColumnIndex(newCell.getCellPane(), j);
//                grid.getChildren().addAll(newCell.getCellPane());
//            }
//        }
//    }
    public GridPane getGrid(){
        return grid;
    }
    /**
     * A method to resize each pane in the grid pane to fit the new window dimensions.
     */
    public void resizeGrid(double width, double height){
        if (cells != null) {
            gridWidth = width;
            gridHeight = height;
            double rectHeight = height / numRows;
            double rectWidth = width / numCols;
            for (CellView[] row: cells){
                for (CellView c: row){
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
    public void setDimensions(int rows, int cols){
        cellWidth = gridWidth / rows;
        cellHeight = gridHeight / cols;
        numCols = cols;
        numRows = rows;
        cells = new CellView[rows][cols];
        resizeGrid(gridWidth, gridHeight);
    }
    public void addCell(CellView cellView, int i, int j){
        cells[i][j] = cellView;
    }
    public double getCellWidth(){
        return cellWidth;
    }
    public double getCellHeight(){
        return cellHeight;
    }

}
