package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;

import java.util.ResourceBundle;

import static cellsociety.ViewUtils.attachTooltip;
import static cellsociety.ViewUtils.makeButton;
/**
 * A class that contains all the inputs that control the grid views animation.
 */
public class GridInputs {
    private HBox container;
    private final double MAX_SPEED = 4.0;
    private final double MIN_SPEED = 0.5;
    private final double DEFAULT_SPEED = 1.0;
    private PlayButton playButton;
    /**
     * Create a new grid inputs container.
     */
    public GridInputs(){
        Button backwardButton = makeButton("BackwardButton", event -> System.out.println("Backward"));
        playButton = new PlayButton();
        Button forwardButton = makeButton("ForwardButton", event -> System.out.println("Forward"));
        attachTooltip("BackwardButtonTooltip", backwardButton);
        attachTooltip("ForwardButtonTooltip", forwardButton);
        VBox sliderBox = makeSliderBox();
        Region padderRegion = new Region();
        padderRegion.prefWidthProperty().bind(sliderBox.widthProperty());
        container = new HBox(padderRegion, backwardButton, playButton.getButton(), forwardButton, sliderBox);
        container.getStyleClass().add("grid-inputs-container");
    }
    /**
     * @return HBox containing all the components
     */
    public HBox getContainer(){
        return container;
    }
    /**
     * @param speedLabel the TextField that acts as the label
     * @return the slider that adjusts teh animation speed
     */
    private Slider makeSpeedSlider(TextField speedLabel){
        Slider s = new Slider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
        s.valueProperty().addListener((obs, oldval, newVal) -> {
            s.setValue(Math.round(newVal.doubleValue() * 2) / 2.0);
            speedLabel.setText("x" + Double.toString(s.getValue()));
        });
        s.setId("SpeedSlider");
        attachTooltip("SpeedSliderTooltip", s);
        return s;
    }
    /**
     * @return VBox containing the slider and label
     */
    private VBox makeSliderBox(){
        TextField speedLabel = new TextField("x" + Double.toString(DEFAULT_SPEED));
        speedLabel.setDisable(true);
        speedLabel.setId("SpeedLabel");
        speedLabel.getStyleClass().add("speed-text-field");
        Slider speedSlider = makeSpeedSlider(speedLabel);
        VBox box = new VBox(speedSlider, speedLabel);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
