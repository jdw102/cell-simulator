package cellsociety.controller;

import cellsociety.Coordinate;
import cellsociety.State;
import cellsociety.model.CellModel;
import cellsociety.view.CellView;
import cellsociety.view.DisplayView;


public class CellSpawner {

  private final CellModel[][] myCellModels;
  private final CellView[][] myCellViews;
  private final InitialStateReader myInitialStateReader;
  private int myNumRows;
  private int myNumCols;

  /**
   * Initializes parallel cell model/view data structures for a given simulation
   */
  public CellSpawner(DisplayView displayView, InitialStateReader initialStateReader) {
    myInitialStateReader = initialStateReader;

    setNumCols();
    setNumRows();

    myCellModels = new CellModel[myNumRows][myNumCols];
    myCellViews = new CellView[myNumRows][myNumCols];

    displayView.setGridDimensions(myNumRows, myNumCols);
    initializeGrid(displayView);
  }


  /**
   * Method to iterate over each cell
   */
  private void initializeGrid(DisplayView displayView) {
    for (int row = 0; row < myNumRows; row++) {
      for (int col = 0; col < myNumCols; col++) {
        initializeCell(displayView, row, col);
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
  private void initializeCell(DisplayView displayView, int row, int col) {
    Coordinate cellCoord = new Coordinate(row, col);

    State cellState = getState(cellCoord);
    myCellModels[row][col] = new CellModel(cellState);
    myCellViews[row][col] = new CellView(myCellModels[row][col]);
    displayView.addCellView(myCellViews[row][col], row, col);
    myCellModels[row][col].addObserver(myCellViews[row][col]);
    myCellModels[row][col].setCurrentState(cellState);
  }

  /**
   * Method to allow outside classes to access a particular cell model
   *
   * @param coord Coordinate of the cell to be retrieved
   */
  public CellModel getCell(Coordinate coord) {
    return myCellModels[coord.x()][coord.y()];
  }

  /**
   * Method for testing to make sure all CellViews are instantiated.
   *
   * @return the CellView at a specified location
   */
  protected CellView getCellView(Coordinate coord) {
    return myCellViews[coord.x()][coord.y()];
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
   * @param coord the x,y value in the [x][y] coordinate of the data structure
   */
  private State getState(Coordinate coord) {
    return myInitialStateReader.createStateInstance(coord);
  }

  private void setNumRows() {
    myNumRows = myInitialStateReader.getNumRows();
  }

  private void setNumCols() {
    myNumCols = myInitialStateReader.getNumCols();
  }

}
