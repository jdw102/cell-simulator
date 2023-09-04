package cellsociety.view;

import cellsociety.Coordinate;
import cellsociety.Observer;
import cellsociety.model.CellModel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * A view class that observes the cell model and changes color based on the state of the model.
 *
 * @author Jerry Worthy
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
   *
   * @param cellModel The associated cell model that holds state information for this view
   */
  public CellView(CellModel cellModel) {
    model = cellModel;
    rectangle = new Rectangle();
    cellPane = new Pane(rectangle);
    cellPane.getStyleClass().add("cell-pane");
  }

  /**
   * Updates the colors of the cell view
   */
  @Override
  public void update() {
    stateName = model.getCurrentStateEnum().toString();
    rectangle.setFill(stateColors.getColor(stateName));

  }

  public Pane getCellPane() {
    return cellPane;
  }

  /**
   * Link an event handler to the cell view to be run when the cell view is clicked
   *
   * @param handler
   */
  public void attachOnClick(EventHandler<MouseEvent> handler) {
    rectangle.setOnMouseClicked(handler);
  }

  public void attachId(String id) {
    rectangle.setId(id);
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

  /**
   * Called in the simFileWriter in order to know where to write this cell's state value to the CSV
   *
   * @return The location coordinate of the cell
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Used to assign the coordinate location for this cell view so that it can correspond with its
   * model state
   *
   * @param coord
   */
  public void setCoordinate(Coordinate coord) {
    coordinate = coord;
  }
}
