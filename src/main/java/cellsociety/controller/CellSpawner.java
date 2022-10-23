package cellsociety.controller;

import cellsociety.State;
import cellsociety.model.CellModel;
import cellsociety.view.CellView;
import cellsociety.view.GridView;


public class CellSpawner {

  private final CellModel[][] myCellModels;
  private final CellView[][] myCellViews;
  private int myNumRows;
  private int myNumCols;

  private final GridView myGridView;

  private final InitialStateReader myInitialStateReader;

  /**
   * Initializes parallel cell model/view data structures for a given simulation
   */
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


  /**
   * Method to iterate over each cell
   */
  private void initializeGrid() {

    for (int row = 0; row < myNumRows; row++) {
      for (int col = 0; col < myNumCols; col++) {

        initializeCell(row, col);

      }
    }
  }

  /**
   * Method to set the initial states of each cell. Links individual cell models to their cell
   * views.
   *
   * @param row the x value in the [x][y] coordinate of the data structure
   * @param col the y value in the [x][y] coordinate of the data structure
   */
  private void initializeCell(int row, int col) {
    State cellState = getState(row, col);
    myCellModels[row][col] = new CellModel(cellState);
    myCellViews[row][col] = new CellView(myCellModels[row][col]);
    myGridView.addCell(myCellViews[row][col], row, col);
    myCellModels[row][col].addObserver(myCellViews[row][col]);
    myCellModels[row][col].setCurrentState(cellState);
  }

  /**
   * Method to allow outside classes to access a particular cell model
   *
   * @param row the x value in the [x][y] coordinate of the data structure
   * @param col the y value in the [x][y] coordinate of the data structure
   */
  public CellModel getCell(int row, int col) {
    return myCellModels[row][col];
  }

  /**
   * Method for testing to make sure all CellViews are instantiated.
   * @param row
   * @param col
   * @return the CellView at a specified location
   */
  protected CellView getCellView(int row, int col) {
    return myCellViews[row][col];
  }

  /**
   * Method to obtain the length of the cell model/view grid
   */
  public int getNumRows() {
    return myNumRows;
  }

  /**
   * Method to obtain the width of the cell model/view grid
   */
  public int getNumCols() {
    return myNumCols;
  }

  /**
   * Method to obtain the state of the cell model/view grid. Uses abstraction through
   * InitialStateReader to translate the state value, e.g. 0 or 1, to a state instance.
   *
   * @param row the x value in the [x][y] coordinate of the data structure
   * @param col the y value in the [x][y] coordinate of the data structure
   */
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
