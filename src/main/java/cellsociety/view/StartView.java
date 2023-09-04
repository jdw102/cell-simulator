package cellsociety.view;

import static cellsociety.Main.DEFAULT_LANGUAGE_FOLDER;
import static cellsociety.Main.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.Main.DEFAULT_RESOURCE_PACKAGE;
import static cellsociety.Main.DEFAULT_STYLESHEET_FOLDER;

import java.io.File;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Creates starting view from which the language is selected and the app is started.
 *
 * @author Jerry Worthy
 */
public class StartView {

  public static final String START_STYLESHEET = "LightMode.css";
  public static final String START_LANGUAGE = "English";
  private final BorderPane root;
  private final ComboBox<String> languageSelector;
  private final InputFactory inputFactory;

  /**
   * Creates new start view instance.
   */
  public StartView() {
    root = new BorderPane();
    languageSelector = makeLanguageSelector("LanguageSelector");
    inputFactory = new InputFactory(ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE_FOLDER + START_LANGUAGE));
  }

  /**
   * Sets up the scene of the start view.
   *
   * @param sizeWidth  the width of the screen
   * @param sizeHeight the height of the screen
   * @param startApp   the event to open the application
   */
  public Scene setUpScene(int sizeWidth, int sizeHeight, EventHandler<ActionEvent> startApp) {
    Label languageLabel = inputFactory.makeLabel("LanguageSelectorLabel");
    languageLabel.getStyleClass().add("language-selector-label");
    Button startButton = inputFactory.makeButton("StartButton", startApp);
    startButton.getStyleClass().add("start-button");
    VBox box = new VBox(languageLabel, languageSelector, startButton);
    box.getStyleClass().add("start-box");
    root.setCenter(box);
    Scene scene = new Scene(root, sizeWidth, sizeHeight);
    scene.getStylesheets()
        .add(
            getClass().getResource(
                    DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + START_STYLESHEET)
                .toExternalForm());
    return scene;
  }

  /**
   * A method to create the language selection combobox using the language options list. It makes
   * the id the String name.
   *
   * @param name - The id of the combo box.
   * @return The combo box of language strings.
   */
  private ComboBox<String> makeLanguageSelector(String name) {
    File languageDirectory = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + DEFAULT_LANGUAGE_FOLDER).getPath());
    ComboBox<String> c = new ComboBox<>();
    String[] languageFiles = languageDirectory.list();
    if (languageFiles != null) {
      for (String f : languageFiles) {
        String s = f.split("\\.")[0];
        c.getItems().add(s);
      }
      c.setValue(c.getItems().get(0));
    }
    c.getStyleClass().add("language-combo-box");
    c.setId(name);
    return c;
  }

  public String getStartLanguage() {
    return languageSelector.getValue();
  }
}

