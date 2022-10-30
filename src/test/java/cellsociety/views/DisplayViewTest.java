package cellsociety.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.controller.Controller;
import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.DukeApplicationTest;

public class DisplayViewTest extends DukeApplicationTest {

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

  @ParameterizedTest
  @CsvSource({
      "GameOfLife",
      "Percolation"
  })
  void testSimSelector(String simType) {
    ComboBox<String> simSelector = lookup("#SimSelector").query();
    String expected = simType;
    select(simSelector, expected);
    assertEquals(expected, simSelector.getValue());
  }

  @Test
  void testStepButtonDisable() {
    boolean expected = true;
    Button playButton = lookup("#PlayButton").query();
    Button button = lookup("#ForwardButton").query();
    clickOn(playButton);
    sleep(500);
    assertEquals(expected, button.isDisable());
  }

  @Test
  void testPlayButtonToggle() {
    Image expected = new Image(myResources.getString("PauseButton"));
    Button playButton = lookup("#PlayButton").query();
    clickOn(playButton);
    sleep(500);
    assertEquals(expected.getUrl(), ((ImageView) playButton.getGraphic()).getImage().getUrl());
  }

  @ParameterizedTest
  @CsvSource({
      "0.5",
      "1.0",
      "1.5",
      "2.0",
      "2.5",
      "3.0",
      "3.5",
      "4.0"
  })
  void testSpeedSlider(String val) {
    double expected = Double.parseDouble(val);
    Slider s = lookup("#SpeedSlider").query();
    setValue(s, expected);
    sleep(500);
    assertEquals(expected, s.getValue());
  }

  @Test
  void testNoSpeedSliderValue() {
    double d = 5.0;
    double expected = 4.0;
    Slider s = lookup("#SpeedSlider").query();
    setValue(s, d);
    sleep(500);
    assertEquals(expected, s.getValue());
  }

  @Test
  void testSimulationReset() {
    File f = new File(getClass().getResource("/cellsociety/test_sims/display_test.sim").getPath());
    runAsJFXAction(() -> view.setupSimulation(f));
    sleep(1000);
    Button forwardButton = lookup("#ForwardButton").query();
    Button resetButton = lookup("#ResetButton").query();
    clickOn(forwardButton);
    clickOn(resetButton);
    sleep(500);
    Rectangle cell = lookup("#CellView[3][1]").query();
    Paint expected = Paint.valueOf("#FFFFFF");
    assertEquals(expected, cell.getFill());
  }

  @Test
  void testErrorMessage() {
    File f = new File(getClass().getResource("/cellsociety/test_sims/display_test.csv").getPath());
    String expected = "Must provide a file of type sim, but was provided a file of type csv";
    runAsJFXAction(() -> view.setupSimulation(f));
    DialogPane pane = lookup("#AlertPane").query();
    String errorMessage = pane.getContentText();
    sleep(500);
    assertEquals(expected, errorMessage);
  }

}
