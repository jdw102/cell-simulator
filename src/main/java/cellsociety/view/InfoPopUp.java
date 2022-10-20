package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * A class that contains a dialog with editable components relating to the simulation information.
 */
public class InfoPopUp {

  private final Dialog<InfoText> dialog;
  private final int SPACING = 5;
  private final InputFactory inputFactory;
  private TextField titleField;
  private TextField authorField;
  private TextArea descriptionField;
  private ButtonType saveInfoButton;
  private ButtonType cancelInfoButton;
  private Button editInfoButton;
  private InfoText infoText;

  /**
   * Create the scene to display the view components.
   *
   * @param text       the InfoText object containing the title, author, and description strings
   * @param title      the title of the pop-up dialog
   * @param styleSheet the CSS styling of the pop-up
   */
  public InfoPopUp(InfoText text, String title, String styleSheet, InputFactory utils) {
    inputFactory = utils;
    infoText = text;
    dialog = new Dialog<>();
    dialog.getDialogPane().getStylesheets()
        .add(getClass().getResource(styleSheet).toExternalForm());
    dialog.setTitle(title);
    makeFields(text);
    makeButtons();
    VBox box = makeVBox();
    dialog.setResultConverter(createDialogCallback());
    dialog.getDialogPane().setContent(box);
    dialog.getDialogPane().setId("InfoPane");
  }

  /**
   * Creates the call back that is triggered when the dialog is closed. If save is clicked, the
   * InfoText record is updated. Else nothing is saved, either way the fields are disabled.
   */
  private Callback<ButtonType, InfoText> createDialogCallback() {
    Callback<ButtonType, InfoText> cb = new Callback<ButtonType, InfoText>() {
      @Override
      public InfoText call(ButtonType param) {
        if (param == ButtonType.OK) {
          updateText();
        }
        toggleFields(true);
        return null;
      }
    };
    return cb;
  }

  /**
   * Creates the three button inputs.
   */
  private void makeButtons() {
    editInfoButton = inputFactory.makeButton("EditInfoButton", event -> toggleFields(false));
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
  }

  /**
   * Creates the two text fields and one text area.
   */
  private void makeFields(InfoText text) {
    titleField = inputFactory.makeTextField("TitleTextField", text.getTitle());
    authorField = inputFactory.makeTextField("AuthorTextField", text.getAuthor());
    descriptionField = inputFactory.makeTextArea("DescriptionTextField", text.getDescription());
    inputFactory.attachTooltip("TitleLabel", titleField);
    inputFactory.attachTooltip("AuthorLabel", authorField);
    inputFactory.attachTooltip("DescriptionLabel", descriptionField);
    titleField.getStyleClass().add("info-text-field");
    authorField.getStyleClass().add("info-text-field");
    descriptionField.getStyleClass().add("info-text-area");
    toggleFields(true);
  }

  /**
   * Opens the dialog with the updated text.
   */
  public void open() {
    titleField.setText(infoText.getTitle());
    authorField.setText(infoText.getAuthor());
    descriptionField.setText(infoText.getDescription());
    dialog.showAndWait();
  }

  /**
   * Create the VBox that contains the content to be displayed.
   *
   * @return VBox containing all the components
   */
  private VBox makeVBox() {
    Label topLabel = inputFactory.makeLabel("InfoPopUpHeader");
    topLabel.getStyleClass().add("info-header");
    Region padderRegion = new Region();
    padderRegion.prefWidthProperty().bind(editInfoButton.widthProperty());
    HBox topBox = new HBox(padderRegion, topLabel, editInfoButton);
    topBox.getStyleClass().add("info-header-container");

    Label titleLabel = inputFactory.makeLabel("TitleLabel");
    Label authorLabel = inputFactory.makeLabel("AuthorLabel");
    Label descriptionLabel = inputFactory.makeLabel("DescriptionLabel");
    titleLabel.getStyleClass().add("info-text-label");
    authorLabel.getStyleClass().add("info-text-label");
    descriptionLabel.getStyleClass().add("info-text-label");

    VBox box = new VBox(topBox, titleLabel, titleField, authorLabel, authorField, descriptionLabel,
        descriptionField);
    box.setAlignment(Pos.CENTER);
    box.setSpacing(SPACING);
    return box;
  }

  /**
   * Disables or enables the text fields.
   *
   * @param b the boolean
   */
  private void toggleFields(boolean b) {
    titleField.setDisable(b);
    authorField.setDisable(b);
    descriptionField.setDisable(b);
  }

  /**
   * Updates the text fields' text.
   */
  private void updateText() {
    infoText.setTitle(titleField.getText());
    infoText.setAuthor(authorField.getText());
    infoText.setDescription(descriptionField.getText());
  }

  /**
   * Updates the InfoText object used by the pop-up.
   *
   * @param text the InfoText object containing the title, author, and description strings
   */
  public void changeInfoText(InfoText text) {
    infoText = text;
  }
}

