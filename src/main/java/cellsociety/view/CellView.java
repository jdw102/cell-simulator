package cellsociety.view;

import cellsociety.Observer;
import cellsociety.model.CellModel;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A view class that observes the cell model and changes color based on the state of the model.
 */
public class CellView implements Observer {

  private final int BORDER_SIZE = 1;
  private final Pane cellPane;
  private final Rectangle rectangle;
  private final CellModel model;
  private ResourceBundle colorBundle;

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
    rectangle.setFill((Paint) colorBundle.getObject(model.getCurrentState().toString()));
  }

  public Pane getCellPane() {
    return cellPane;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  /**
   * Sets the dimensions of the rectangle held within in the cell.
   */
  public void setDimensions(double width, double height) {
    rectangle.setWidth(width - BORDER_SIZE);
    rectangle.setHeight(height - BORDER_SIZE);
  }

  /**
   * Sets the resource bundle to the appropriate one.
   */
  public void setColorBundle(ResourceBundle colors) {
    colorBundle = colors;
  }
}
