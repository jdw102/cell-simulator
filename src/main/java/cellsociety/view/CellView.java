package cellsociety.view;

import cellsociety.Observer;
import cellsociety.model.CellModel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;
/**
 * A view class that observes the cell model and changes color based on the state of the model.
 */
public class CellView implements Observer {
  private Pane cellPane;
  private Rectangle rectangle;
  private final int BORDER_SIZE = 1;
  private CellModel model;
  private ResourceBundle colorOptions;
  /**
   * Creates a new instance of the CellView
   * @param width the width of cell
   * @param height the height of the cell
   * @param colors the resource bundle containing the colors for each state of the cell
   */
  public CellView(double width, double height, ResourceBundle colors){
    colorOptions = colors;
    rectangle = new Rectangle(width - BORDER_SIZE, height - BORDER_SIZE);
    rectangle.setFill(Color.WHITE);
//    rectangle.setFill((Paint) colorOptions.getObject(model.getName().name()));
    cellPane = new Pane(rectangle);
    cellPane.getStyleClass().add("cell-pane");
  }
  @Override
  public void update() {
    rectangle.setFill((Paint) colorOptions.getObject(model.getName().name()));
  }
  public Pane getCellPane(){
    return cellPane;
  }
  public Rectangle getRectangle(){
    return rectangle;
  }
}
