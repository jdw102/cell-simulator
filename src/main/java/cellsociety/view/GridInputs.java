package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;

import static cellsociety.ViewUtils.attachTooltip;
import static cellsociety.ViewUtils.makeButton;

public class GridInputs {
    private HBox container;
    private final double MAX_SPEED = 4.0;
    private final double MIN_SPEED = 0.5;
    private final double DEFAULT_SPEED = 1.0;

    public GridInputs(){
        Button backwardButton = makeButton("BackwardButton", event -> System.out.println("Backward"));
        Button playButton = makeButton("PlayButton", event -> System.out.println("Play"));
        Button forwardButton = makeButton("ForwardButton", event -> System.out.println("Forward"));
        attachTooltip("BackwardButtonTooltip", backwardButton);
        attachTooltip("PlayButtonTooltip", playButton);
        attachTooltip("ForwardButtonTooltip", forwardButton);
        VBox sliderBox = makeSliderBox();
        Region padderRegion = new Region();
        padderRegion.prefWidthProperty().bind(sliderBox.widthProperty());
        container = new HBox(padderRegion, backwardButton, playButton, forwardButton, sliderBox);
        container.getStyleClass().add("grid-inputs-container");
    }
    public HBox getContainer(){
        return container;
    }
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
