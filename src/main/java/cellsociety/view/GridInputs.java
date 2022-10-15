package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import static cellsociety.ViewUtils.makeButton;

public class GridInputs {
    private HBox container;

    public GridInputs(){
        Button backwardButton = makeButton("BackwardButton", event -> System.out.println("Backward"));
        Button playButton = makeButton("PlayButton", event -> System.out.println("Play"));
        Button forwardButton = makeButton("ForwardButton", event -> System.out.println("Forward"));
        Slider slider = new Slider(0, 3, 1);
        Region padderRegion = new Region();
        padderRegion.prefWidthProperty().bind(slider.widthProperty());
        container = new HBox(padderRegion, backwardButton, playButton, forwardButton, slider);
        container.getStyleClass().add("grid-inputs-container");
    }
    public HBox getContainer(){
        return container;
    }
}
