package cellsociety.view;

import java.io.File;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * Utility class that contains general methods to create certain UI components.
 *
 * @author Jerry Worthy
 */
public final class InputFactory {

  // default to start in the data folder to make it easy on the user to find
  private final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  private final double ICON_SIZE = 30;
  private final ResourceBundle myResources;

  public InputFactory(ResourceBundle resources) {
    this.myResources = resources;
  }

  /**
   * @param property the id of the button, used to get label
   * @param handler  the action on click
   * @return created button
   */
  public Button makeButton(String property, EventHandler<ActionEvent> handler) {
    final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = myResources.getString(property);
    if (label.matches(IMAGE_FILE_SUFFIXES)) {
      ImageView i = new ImageView(new Image(label));
      i.setFitWidth(ICON_SIZE);
      i.setFitHeight(ICON_SIZE);
      result.setGraphic(i);
    } else {
      result.setText(label);
    }
    result.setId(property);
    result.setOnAction(handler);
    return result;
  }

  /**
   * Changes the label of a default button type.
   *
   * @param property the id of the button used to get the new label
   * @param button   the button to edit
   */
  public void changeButtonTypeLabel(String property, Node button) {
    ((Button) button).setText(myResources.getString(property));
  }

  /**
   * @param property     the id of the text field
   * @param defaultValue the default text
   * @return text field.
   */
  public TextField makeTextField(String property, String defaultValue) {
    TextField textField = new TextField(property);
    textField.setText(defaultValue);
    textField.setId(property);
    return textField;
  }

  /**
   * @param property     the id of the text area, used to get label.
   * @param defaultValue the default text displayed.
   * @return text area
   */
  public TextArea makeTextArea(String property, String defaultValue) {
    TextArea textArea = new TextArea(property);
    textArea.setText(defaultValue);
    textArea.setId(property);
    return textArea;
  }

  /**
   * @param property the id of the label
   * @return label
   */
  public Label makeLabel(String property) {
    Label l = new Label(myResources.getString(property));
    l.setId(property);
    return l;
  }

  /**
   * Method to attach tooltip to node.
   *
   * @param property the id of the tooltip
   * @param node     the node
   */
  public void attachTooltip(String property, Node node) {
    Tooltip t = new Tooltip(myResources.getString(property));
    Tooltip.install(node, t);
  }

  /**
   * @param newProperty the id of the new graphic
   * @param b           the button to be changed
   */
  public void changeButtonGraphic(String newProperty, Button b) {
    b.setGraphic(null);
    ImageView i = new ImageView(new Image(myResources.getString(newProperty)));
    i.setFitWidth(ICON_SIZE);
    i.setFitHeight(ICON_SIZE);
    b.setGraphic(i);
  }

  /**
   * @param extensionAccepted the file allowed extension
   * @return file chooser
   */
  public FileChooser makeChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(DATA_FILE_FOLDER));
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter("sim Files", extensionAccepted));
    return result;
  }

  public ComboBox<String> makeDataViewComboBox(Map<String, DataView> map, BorderPane borderPane) {
    ComboBox<String> comboBox = new ComboBox<>();
    for (String s : map.keySet()) {
      comboBox.getItems().add(s);
    }
    comboBox.setOnAction(event -> {
      borderPane.setCenter(map.get(comboBox.getValue()).getNode());
    });
    comboBox.getStyleClass().add("data-view-selector");
    comboBox.setId("DataViewSelector");
    return comboBox;
  }
}
