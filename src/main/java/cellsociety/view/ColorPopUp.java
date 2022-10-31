package cellsociety.view;

import java.util.ResourceBundle;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Creates a pop-up that allows colors of cell states to be edited.
 */
public class ColorPopUp {

  private final Dialog<ResourceBundle> dialog;
  private final GridView gridView;
  private final InputFactory inputFactory;
  private final BarView barView;

  /**
   * Creates new color pop up instance
   *
   * @param title      the title of the window
   * @param styleSheet the stylesheet
   * @param grid       the grid view that will have its colors edited
   * @param factory    an input factory
   */
  public ColorPopUp(String title, String styleSheet, GridView grid, InputFactory factory,
      BarView bar) {
    inputFactory = factory;
    barView = bar;
    gridView = grid;
    dialog = new Dialog<>();
    dialog.getDialogPane().getStylesheets()
        .add(getClass().getResource(styleSheet).toExternalForm());
    dialog.setTitle(title);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    inputFactory.changeButtonTypeLabel("CloseColorsButton",
        dialog.getDialogPane().lookupButton(ButtonType.CLOSE));
    dialog.getDialogPane().setId("ColorPane");
  }

  /**
   * Make box that contains the content of the pop-up, including color pickers and text.
   *
   * @param colors the state colors object that contains the color of each state
   */
  private VBox makeColorPickersBox(StateColors colors) {
    Label topLabel = inputFactory.makeLabel("ColorPopUpHeader");
    topLabel.getStyleClass().add("info-header");
    HBox topBox = new HBox(topLabel);
    topBox.getStyleClass().add("info-header-container");
    VBox box = new VBox(topBox);
    while (colors.hasNext()) {
      HBox colorBox = makeColorPickerBox(colors.next(), colors);
      box.getChildren().add(colorBox);
    }
    colors.resetIterator();
    box.getStyleClass().add("pop-up-content");
    return box;
  }

  /**
   * Opens the pop-up.
   */
  public void open() {
    dialog.showAndWait();
  }

  /**
   * Sets the state colors that will be displayed by the pop-up and edited by the user.
   *
   * @param colors the state colors object that contains the color of each state
   */
  public void setStateColors(StateColors colors) {
    VBox box = makeColorPickersBox(colors);
    dialog.getDialogPane().setContent(box);
  }

  /**
   * Sets the state colors that will be displayed by the pop-up and edited by the user.
   *
   * @param state  the state name
   * @param colors the state colors object that contains the color of each state
   */
  private HBox makeColorPickerBox(String state, StateColors colors) {
    Label colorLabel = new Label(state);
    colorLabel.getStyleClass().add("info-text-label");
    ColorPicker cp = new ColorPicker();
    cp.setValue(colors.getColor(state));
    cp.setOnAction(event -> changeColors(cp.getValue(), state, colors));
    cp.setId(state + "ColorPicker");
    cp.getStyleClass().add("state-color-picker");
    HBox colorBox = new HBox(colorLabel, cp);
    colorBox.getStyleClass().add("state-color-box");
    return colorBox;
  }

  /**
   * Updates the stylesheet of the pop-up.
   *
   * @param stylesheet the string referencing the path to the stylesheet
   */
  public void changeStyleSheet(String stylesheet) {
    dialog.getDialogPane().getStylesheets().clear();
    dialog.getDialogPane().getStylesheets().add(stylesheet);
  }

  private void changeColors(Color val, String state, StateColors colors) {
    colors.changeStateColor(state, val);
    gridView.updateCellColors();
    barView.updateColors();
  }
}
