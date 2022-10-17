package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.view.DisplayView;
import java.awt.Dimension;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.DukeApplicationTest;

public class DisplayViewTest extends DukeApplicationTest {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";

  @Override
  public void start(Stage primaryStage) {
    DisplayView view = new DisplayView(DEFAULT_LANGUAGE, primaryStage);
    // give the window a title
    primaryStage.setTitle(TITLE);
    //add our user interface components to Frame and show it
    primaryStage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    primaryStage.setMinHeight(MIN_SIZE.height);
    primaryStage.setMinWidth(MIN_SIZE.width);
    primaryStage.show();
  }

  @ParameterizedTest
  @CsvSource({
      "GameOfLife",
      "PredatorPrey",
      "RockPaperScissors",
      "Segregation",
      "SpreadingFire"
  })
  void testSimSelector(String simType) {

    ComboBox<String> simSelector = lookup("#SimSelector").query();
    String expected = simType;
    select(simSelector, expected);
    assertEquals(expected, simSelector.getValue());
  }

  @ParameterizedTest
  @CsvSource({
      "#ForwardButton",
      "#BackwardButton"
  })
  void testStepButtonDisable(String buttonId) {
    boolean expected = true;
    Button playButton = lookup("#PlayButton").query();
    Button button = lookup(buttonId).query();
    clickOn(playButton);
    sleep(500);
    assertEquals(expected, button.isDisable());
  }

}
