package cellsociety.view;

import cellsociety.Observer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView implements Observer {
  private Pane cell;
  private final int BORDER_SIZE = 1;
  public CellView(double width, double height){
    Rectangle r2 = new Rectangle(width - BORDER_SIZE, height - BORDER_SIZE);
    r2.setFill(Color.WHITE);
    cell = new Pane(r2);
    cell.getStyleClass().add("cell-pane");
  }
  @Override
  public void update() {

  }
  public Pane getCell(){
    return cell;
  }
}
