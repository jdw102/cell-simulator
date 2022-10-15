package cellsociety;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.imageio.ImageIO;
import java.util.List;
import java.util.ResourceBundle;

public class ViewUtils {
    static ResourceBundle myResources;
    static final double ICON_SIZE = 30;
    public ViewUtils(ResourceBundle resources) {
        this.myResources = resources;
    }


    /**
     * @param property - The id of the button, used to get label.
     * @param handler - The action on click.
     * @return Created button.
     */
    public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
        final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));

        Button result = new Button();
        String label = myResources.getString(property);
        if (label.matches(IMAGE_FILE_SUFFIXES)) {
            ImageView i = new ImageView(new Image(label));
            i.setFitWidth(ICON_SIZE);
            i.setFitHeight(ICON_SIZE);
            result.setGraphic(i);
        }
        else {
            result.setText(label);
        }
        result.setOnAction(handler);
        return result;
    }

    /**
     * @param property - The id of the text field.
     * @param defaultValue - The default text.
     * @param handler - The action on enter.
     * @return Created text field.
     */
    public static TextField makeTextField(String property, String defaultValue) {
        TextField textField = new TextField(property);
        textField.setText(defaultValue);
        textField.setId(property);
        return textField;
    }
    public static TextArea makeTextArea(String property, String defaultValue) {
        TextArea textArea = new TextArea(property);
        textArea.setText(defaultValue);
        textArea.setId(property);
        return textArea;
    }
    public static Label makeLabel(String property){
        Label l = new Label(myResources.getString(property));
        l.setId(property);
        return l;
    }
    public static ButtonType makeButtonType(String property, ButtonBar.ButtonData type){
        ButtonType b = new ButtonType(myResources.getString(property), type);
        return b;
    }
    public static void attachTooltip(String property, Node node){
        Tooltip t = new Tooltip(myResources.getString(property));
        Tooltip.install(node, t);
    }

}
