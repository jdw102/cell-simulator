package cellsociety;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.DukeApplicationTest;

public class DisplayViewTest extends DukeApplicationTest {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private ResourceBundle myResources;

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
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
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
  void testInfoTitleEdit() {
    String expected = "test";
    Button infoButton = lookup("#InfoButton").query();
    clickOn(infoButton);
    TextField titleField = lookup("#TitleTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(titleField).clickOn(titleField).clickOn(titleField).press(KeyCode.BACK_SPACE)
        .write(expected);
    sleep(500);
    assertEquals(expected, titleField.getText());
  }

  @Test
  void testInfoAuthorEdit() {
    String expected = "test";
    Button infoButton = lookup("#InfoButton").query();
    clickOn(infoButton);
    TextField titleField = lookup("#AuthorTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(titleField).clickOn(titleField).clickOn(titleField).press(KeyCode.BACK_SPACE)
        .write(expected);
    sleep(500);
    assertEquals(expected, titleField.getText());
  }

  @Test
  void testInfoDescriptionEdit() {
    String expected = "test";
    Button infoButton = lookup("#InfoButton").query();
    clickOn(infoButton);
    TextArea titleField = lookup("#DescriptionTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(titleField).clickOn(titleField).clickOn(titleField).press(KeyCode.BACK_SPACE)
        .write(expected);
    sleep(500);
    assertEquals(expected, titleField.getText());
  }
//  @Test
//  void testInfoSave() {
//    String expected = "test";
//    Button infoButton = lookup("#InfoButton").query();
//    clickOn(infoButton);
//    TextArea titleField = lookup("#DescriptionTextField").query();
//    Button editButton = lookup("#EditInfoButton").query();
//    clickOn(editButton);
//    clickOn(titleField).clickOn(titleField).clickOn(titleField).press(KeyCode.BACK_SPACE)
//        .write(expected);
//    DialogPane dp = lookup("#InfoPane").query();
//    Node saveButton = dp.lookupButton(ButtonType.APPLY);
//    clickOn(saveButton);
//    sleep(500);
//    assertEquals(expected, titleField.getText());
//  }
}
