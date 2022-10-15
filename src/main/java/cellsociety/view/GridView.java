package cellsociety.view;

import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridView {
    private GridPane grid;
    private CellView[][] cells;
    private int numRows;
    private int numCols;
    public GridView(int rows, int cols, double width, double height){
        setDimensions(rows, cols);
        grid = new GridPane();
        grid.getStyleClass().add("cell-grid-pane");
        cells = new CellView[rows][cols];
        createCells(width, height);
    }
    private void createCells(double width, double height){
        double rectHeight = height / numRows;
        double rectWidth = width / numCols;
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                CellView newCell = new CellView(rectWidth, rectHeight);
                cells[i][j] = newCell;
                GridPane.setRowIndex(newCell.getCell(), i);
                GridPane.setColumnIndex(newCell.getCell(), j);
                grid.getChildren().addAll(newCell.getCell());
            }
        }
    }
    public GridPane getGrid(){
        return grid;
    }
    public void resizeGrid(double width, double height){
        grid.getChildren().removeAll(grid.getChildren());
        createCells(width, height);
    }
    public void setDimensions(int rows, int cols){
        numCols = cols;
        numRows = rows;
    }
}
