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
/**
 * A utils class that contains general methods to create certain UI components.
 */
public class ViewUtils {
    static ResourceBundle myResources;
    static final double ICON_SIZE = 30;
    public ViewUtils(ResourceBundle resources) {
        this.myResources = resources;
    }
    /**
     * @param property the id of the button, used to get label
     * @param handler the action on click
     * @return created button
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
     * @param property the id of the text field
     * @param defaultValue the default text
     * @return text field.
     */
    public static TextField makeTextField(String property, String defaultValue) {
        TextField textField = new TextField(property);
        textField.setText(defaultValue);
        textField.setId(property);
        return textField;
    }
    /**
     * @param property the id of the text area, used to get label.
     * @param defaultValue the default text displayed.
     * @return text area
     */
    public static TextArea makeTextArea(String property, String defaultValue) {
        TextArea textArea = new TextArea(property);
        textArea.setText(defaultValue);
        textArea.setId(property);
        return textArea;
    }
    /**
     * @param property the id of the label
     * @return label
     */
    public static Label makeLabel(String property){
        Label l = new Label(myResources.getString(property));
        l.setId(property);
        return l;
    }
    /**
     * @param property the id of button
     * @param type the type of button
     * @return button type
     */
    public static ButtonType makeButtonType(String property, ButtonBar.ButtonData type){
        ButtonType b = new ButtonType(myResources.getString(property), type);
        return b;
    }
    /**
     * Method to attach tooltip to node.
     * @param property the id of the tooltip
     * @param node the node
     */
    public static void attachTooltip(String property, Node node){
        Tooltip t = new Tooltip(myResources.getString(property));
        Tooltip.install(node, t);
    }
    /**
     * @param newProperty the id of the new graphic
     * @param b the button to be changed
     */
    public static void changeButtonGraphic(String newProperty, Button b){
        b.setGraphic(null);
        ImageView i = new ImageView(new Image(myResources.getString(newProperty)));
        i.setFitWidth(ICON_SIZE);
        i.setFitHeight(ICON_SIZE);
        b.setGraphic(i);
    }

}
