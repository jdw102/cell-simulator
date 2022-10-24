package cellsociety.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.controller.Controller;
import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.DukeApplicationTest;

public class InfoPopUpTest extends DukeApplicationTest {

  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private ResourceBundle myResources;
  private DisplayView view;
  private Button infoButton;

  @Override
  public void start(Stage primaryStage) {
    view = new DisplayView(DEFAULT_LANGUAGE, primaryStage,
        event -> System.out.println("New Window"));
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
    infoButton = lookup("#InfoButton").query();
  }

  @BeforeEach
  void openPopUp() {
    clickOn(infoButton);
  }

  @Test
  void testInfoTitleEdit() {
    String expected = "test";
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
    TextArea titleField = lookup("#DescriptionTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(titleField).clickOn(titleField).clickOn(titleField).press(KeyCode.BACK_SPACE)
        .write(expected);
    sleep(500);
    assertEquals(expected, titleField.getText());
  }

  @Test
  void testInfoTextAreaSave() {
    String expected = "test";
    TextArea description = lookup("#DescriptionTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(description).clickOn(description).clickOn(description).press(KeyCode.BACK_SPACE)
        .write(expected);
    DialogPane dp = lookup("#InfoPane").query();
    Button saveButton = (Button) dp.lookupButton(ButtonType.OK);
    clickOn(saveButton);
    sleep(500);
    clickOn(infoButton);
    sleep(500);
    assertEquals(expected, description.getText());
  }

  @ParameterizedTest
  @CsvSource({
      "#TitleTextField",
      "#AuthorTextField"
  })
  void testInfoTextFieldSave(String id) {
    String expected = "test";
    TextField description = lookup(id).query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(description).clickOn(description).clickOn(description).press(KeyCode.BACK_SPACE)
        .write(expected);
    DialogPane dp = lookup("#InfoPane").query();
    Button saveButton = (Button) dp.lookupButton(ButtonType.OK);
    clickOn(saveButton);
    sleep(500);
    clickOn(infoButton);
    sleep(500);
    assertEquals(expected, description.getText());
  }

  @Test
  void testInfoCancel() {
    String expected = "A blank simulation... Feel free to change!";
    TextArea description = lookup("#DescriptionTextField").query();
    Button editButton = lookup("#EditInfoButton").query();
    clickOn(editButton);
    clickOn(description).clickOn(description).clickOn(description).press(KeyCode.BACK_SPACE)
        .write("test");
    DialogPane dp = lookup("#InfoPane").query();
    Button cancelButton = (Button) dp.lookupButton(ButtonType.CANCEL);
    clickOn(cancelButton);
    sleep(500);
    clickOn(infoButton);
    sleep(500);
    assertEquals(expected, description.getText());
  }
}
