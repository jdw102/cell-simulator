package cellsociety.view;

import cellsociety.Coordinate;
import cellsociety.Observer;
import cellsociety.model.CellModel;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * A view class that observes the cell model and changes color based on the state of the model.
 */
public class CellView implements Observer {

  private final int BORDER_SIZE = 1;
  private final Pane cellPane;
  private final Rectangle rectangle;
  private final CellModel model;
  private StateColors stateColors;
  private String stateName;
  private Coordinate coordinate;

  /**
   * Create a new instance of a cell view.
   */
  public CellView(CellModel cellModel) {
    model = cellModel;
    rectangle = new Rectangle();
    cellPane = new Pane(rectangle);
    cellPane.getStyleClass().add("cell-pane");
  }

  @Override
  public void update() {
    stateName = model.getCurrentStateEnum().toString();
    rectangle.setFill(stateColors.getColor(stateName));
  }

  /// get rid of these two getters, replace with methods that perform their use function.
  public Pane getCellPane() {
    return cellPane;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  /**
   * Sets the dimensions of the rectangle held within in the cell.
   *
   * @param width
   * @param height
   */
  public void setDimensions(double width, double height) {
    rectangle.setWidth(width - BORDER_SIZE);
    rectangle.setHeight(height - BORDER_SIZE);
  }

  /**
   * Sets the resource bundle to the appropriate one.
   *
   * @param colors the StateColors object that holds the colo of each state.
   */
  public void setStateColors(StateColors colors) {
    stateColors = colors;
  }

  /**
   * Required to create CSV file on saving. Called by display view on each cell view currently
   * contained in the grid view.
   *
   * @return the state name
   */
  public String getStateName() {
    return stateName;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coord) {
    coordinate = coord;
  }
}
