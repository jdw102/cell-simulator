package cellsociety.view;

import cellsociety.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * A class that contains a dialog with editable components relating to the
 * simulation information.
 */
public class InfoPopUp {
    private final Dialog<InfoText> dialog;
    private final int SPACING = 5;
    private final ViewUtils viewUtils;
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
    public InfoPopUp(InfoText text, String title, String styleSheet, ViewUtils utils) {
        viewUtils = utils;
        infoText = text;
        dialog = new Dialog<>();
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
        dialog.setTitle(title);
        makeFields(text);
        makeButtons();
        VBox box = makeVBox();
        dialog.setResultConverter(createDialogCallback());
        dialog.getDialogPane().setContent(box);
    }

    private Callback<ButtonType, InfoText> createDialogCallback() {
        Callback<ButtonType, InfoText> cb = new Callback<ButtonType, InfoText>() {
            @Override
            public InfoText call(ButtonType param) {
                if (param.getButtonData() == ButtonBar.ButtonData.APPLY) {
                    updateText();
                } else if (param.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    toggleFields(true);
                }
                return null;
            }
        };
        return cb;
    }

    /**
     * Creates the three button inputs.
     */
    private void makeButtons() {
        saveInfoButton = viewUtils.makeButtonType("SaveInfoButton", ButtonBar.ButtonData.APPLY);
        cancelInfoButton = viewUtils.makeButtonType("CancelInfoButton", ButtonBar.ButtonData.CANCEL_CLOSE);
        editInfoButton = viewUtils.makeButton("EditInfoButton", event -> toggleFields(false));
        dialog.getDialogPane().getButtonTypes().addAll(saveInfoButton, cancelInfoButton);
    }

    /**
     * Creates the two text fields and one text area.
     */
    private void makeFields(InfoText text) {
        titleField = viewUtils.makeTextField("AuthorTextField", text.getTitle());
        authorField = viewUtils.makeTextField("AuthorTextField", text.getTitle());
        descriptionField = viewUtils.makeTextArea("AuthorTextField", text.getTitle());
        viewUtils.attachTooltip("TitleLabel", titleField);
        viewUtils.attachTooltip("AuthorLabel", authorField);
        viewUtils.attachTooltip("DescriptionLabel", descriptionField);
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
        Label topLabel = viewUtils.makeLabel("InfoPopUpHeader");
        topLabel.getStyleClass().add("info-header");
        Region padderRegion = new Region();
        padderRegion.prefWidthProperty().bind(editInfoButton.widthProperty());
        HBox topBox = new HBox(padderRegion, topLabel, editInfoButton);
        topBox.getStyleClass().add("info-header-container");

        Label titleLabel = viewUtils.makeLabel("TitleLabel");
        Label authorLabel = viewUtils.makeLabel("AuthorLabel");
        Label descriptionLabel = viewUtils.makeLabel("DescriptionLabel");
        titleLabel.getStyleClass().add("info-text-label");
        authorLabel.getStyleClass().add("info-text-label");
        descriptionLabel.getStyleClass().add("info-text-label");

        VBox box = new VBox(topBox, titleLabel, titleField, authorLabel, authorField, descriptionLabel, descriptionField);
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

