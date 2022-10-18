package cellsociety.controller;

import cellsociety.State;
import cellsociety.model.CellModel;
import cellsociety.view.CellView;
import cellsociety.view.GridView;

public class CellSpawner {

  private CellModel[][] myCellModels;
  private CellView[][] myCellViews;
  private int myNumRows;
  private int myNumCols;

  private GridView myGridView;

  private InitialStateReader myInitialStateReader;

  public CellSpawner(GridView gridView, InitialStateReader initialStateReader) {
    myInitialStateReader = initialStateReader;
    myGridView = gridView;

    setNumCols();
    setNumRows();

    myCellModels = new CellModel[myNumRows][myNumCols];
    myCellViews = new CellView[myNumRows][myNumCols];

    myGridView.setDimensions(myNumRows, myNumCols);
    initializeGrid();
  }

  public void initializeGrid() {

    for (int row = 0; row < myNumRows; row++) {
      for (int col = 0; col < myNumCols; col++) {

        initializeCell(row, col);

      }
    }
  }

  private void initializeCell(int row, int col) {
    State cellState = getState(row, col);
    myCellModels[row][col] = new CellModel(cellState);
    myCellViews[row][col] = new CellView(myCellModels[row][col]); // will resolve when merged
    myGridView.addCell(myCellViews[row][col], row, col);
    myCellModels[row][col].addObserver(myCellViews[row][col]);
    myCellModels[row][col].setCurrentState(cellState);
  }

  public CellModel getCell(int row, int col) {
    return myCellModels[row][col];
  }

  public int getNumRows() {
    return myNumRows;
  }

  public int getNumCols() {
    return myNumCols;
  }

  private State getState(int row, int col) {
    return myInitialStateReader.createStateInstance(row, col);
  }

  private void setNumRows() {
    myNumRows = myInitialStateReader.getNumRows();
  }

  private void setNumCols() {
    myNumCols = myInitialStateReader.getNumCols();
  }

}
