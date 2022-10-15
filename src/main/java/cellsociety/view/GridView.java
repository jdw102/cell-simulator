package cellsociety.view;

import cellsociety.model.GridModel;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class GridView {
    private GridPane grid;
    private CellView[][] cells;
    private int numRows;
    private int numCols;
    private GridModel model;
    public GridView(int rows, int cols, double width, double height, ResourceBundle colorOptions){
        setDimensions(rows, cols);
        grid = new GridPane();
        grid.getStyleClass().add("cell-grid-pane");
        cells = new CellView[rows][cols];
        createCells(width, height, colorOptions);
    }
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
    public void setDimensions(int rows, int cols){
        numCols = cols;
        numRows = rows;
    }
}
