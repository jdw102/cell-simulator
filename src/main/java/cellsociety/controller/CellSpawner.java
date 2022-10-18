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
    myCellModels[row][col] = new CellModel(cellState); // need init state
    myCellViews[row][col] = new CellView();
    myCellModels[row][col].addObserver(myCellViews[row][col]);
    myGridView.addCell(myCellViews[row][col], row, col); // need add method in mygridview
  }

  public CellModel getCell(int row, int col) {
    return myCellModels[row][col];
  }

  public int getNumRows() {
    return myNumRows;
  }

  public int getNumCols() {
    return myNumRows;
  }

  private State getState(int row, int col) {
    return myInitialStateReader.getState(row, col);
  }

  private void setNumRows() {
    myNumRows = myInitialStateReader.getNumRows();
  }

  private void setNumCols() {
    myNumCols = myInitialStateReader.getNumCols();
  }

}
