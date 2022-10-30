package cellsociety.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.controller.Controller;
import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ColorPopUpTest extends DukeApplicationTest {

  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private ResourceBundle myResources;
  private DisplayView view;

  @Override
  public void start(Stage primaryStage) {
    view = new DisplayView(DEFAULT_LANGUAGE, primaryStage);
    Controller controller = new Controller(view);
    view.setController(controller);
    // give the window a title
    primaryStage.setTitle(TITLE);
    //add our user interface components to Frame and show it
    primaryStage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    primaryStage.setMinHeight(MIN_SIZE.height);
    primaryStage.setMinWidth(MIN_SIZE.width);
    primaryStage.show();
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "languages.English");
  }

  @Test
  void testColorChange() {
    Color expected = Color.RED;
    Button colorButton = lookup("#ChangeColorButton").query();
    clickOn(colorButton);
    ColorPicker cp = lookup("#DEADColorPicker").query();
    setValue(cp, expected);
    sleep(500);
    Rectangle cell = lookup("#CellView[0][0]").query();
    assertEquals(cell.getFill(), expected);
  }
}
