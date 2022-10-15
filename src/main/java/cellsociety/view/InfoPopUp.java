package cellsociety.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import static cellsociety.ViewUtils.*;

public class InfoPopUp {
    private Dialog<InfoText> dialog;
    private TextField titleField;
    private TextField authorField;
    private TextArea descriptionField;
    private ButtonType saveInfoButton;
    private ButtonType cancelInfoButton;
    private Button editInfoButton;
    private InfoText infoText;
    private final int SPACING = 5;

    public InfoPopUp(InfoText text, String title, String styleSheet){
        infoText = text;
        dialog = new Dialog<>();
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
        dialog.setTitle(title);
        makeFields(text);
        makeButtons();
        VBox box = makeVBox();
        dialog.setResultConverter(new Callback<ButtonType, InfoText>() {
            @Override
            public InfoText call(ButtonType param) {
                if (param.getButtonData() == ButtonBar.ButtonData.APPLY){
                    updateText();
                }
                else if (param.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                    toggleFields(true);
                }
                return null;
            }
        });
        dialog.getDialogPane().setContent(box);
    }
    private void makeButtons(){
        saveInfoButton = makeButtonType("SaveInfoButton", ButtonBar.ButtonData.APPLY);
        cancelInfoButton = makeButtonType("CancelInfoButton", ButtonBar.ButtonData.CANCEL_CLOSE);
        editInfoButton = makeButton("EditInfoButton", event -> toggleFields(false));
        dialog.getDialogPane().getButtonTypes().addAll(saveInfoButton, cancelInfoButton);
    }
    private void makeFields(InfoText text){
        titleField = makeTextField("AuthorTextField", text.getTitle());
        authorField = makeTextField("AuthorTextField", text.getTitle());
        descriptionField = makeTextArea("AuthorTextField", text.getTitle());
        attachTooltip("TitleLabel", titleField);
        attachTooltip("AuthorLabel", authorField);
        attachTooltip("DescriptionLabel", descriptionField);
        titleField.getStyleClass().add("info-text-field");
        authorField.getStyleClass().add("info-text-field");
        descriptionField.getStyleClass().add("info-text-area");
        toggleFields(true);
    }
    public void open(){
        titleField.setText(infoText.getTitle());
        authorField.setText(infoText.getAuthor());
        descriptionField.setText(infoText.getDescription());
        dialog.showAndWait();
    }
    private VBox makeVBox(){
        Label topLabel = makeLabel("InfoPopUpHeader");
        topLabel.getStyleClass().add("info-header");
        Region padderRegion = new Region();
        padderRegion.prefWidthProperty().bind(editInfoButton.widthProperty());
        HBox topBox = new HBox(padderRegion, topLabel, editInfoButton);
        topBox.getStyleClass().add("info-header-container");

        Label titleLabel = makeLabel("TitleLabel");
        Label authorLabel = makeLabel("AuthorLabel");
        Label descriptionLabel = makeLabel("DescriptionLabel");
        titleLabel.getStyleClass().add("info-text-label");
        authorLabel.getStyleClass().add("info-text-label");
        descriptionLabel.getStyleClass().add("info-text-label");

        VBox box = new VBox(topBox, titleLabel, titleField, authorLabel, authorField, descriptionLabel, descriptionField);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(SPACING);
        return box;
    }
    private void toggleFields(boolean b){
        titleField.setDisable(b);
        authorField.setDisable(b);
        descriptionField.setDisable(b);
    }
    private void updateText(){
        infoText.setTitle(titleField.getText());
        infoText.setAuthor(authorField.getText());
        infoText.setDescription(descriptionField.getText());
    }
    public void changeInfoText(InfoText text){
        infoText = text;
    }
}

