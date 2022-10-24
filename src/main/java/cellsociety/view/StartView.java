package cellsociety.view;

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

public class StartView {

  private static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";
  private static final String DEFAULT_LANGUAGE_FOLDER = "languages/";
  private static final String START_LANGUAGE = "English";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private static final String STYLESHEET = "default.css";
  private BorderPane root;
  private ComboBox<String> languageSelector;
  private InputFactory inputFactory;


  public StartView() {
    root = new BorderPane();
    languageSelector = makeLanguageSelector("LanguageSelector");
    inputFactory = new InputFactory(ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE_FOLDER + START_LANGUAGE));
  }

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
        .add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  /**
   * A method to create the language selection combobox using the language options list. It makes
   * the id the String name.
   *
   * @param name - The id of the combo box.
   * @return The combo box of language strings.
   * @author Jerry Worthy
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

