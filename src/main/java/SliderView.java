import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import components.SliderLabel;

public class SliderView extends Application {

    final double mainSliderMaxValue = 100.00;

    final Slider mainSlider = new Slider(0, mainSliderMaxValue, 40.0);

    final SliderLabel mainSliderValueLabel = new SliderLabel(Double.toString(mainSlider.getValue()));
    final Label javaInfoLabel = new Label(
        "Hello, JavaFX " + System.getProperty("javafx.version") + ", running on Java " + System.getProperty("java.version") + "."
    );

    public void setupSlider() {
        mainSlider.setMajorTickUnit(mainSliderMaxValue);
        mainSlider.setBlockIncrement(10);
        mainSlider.setShowTickLabels(true);

        mainSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number> ov,
                Number oldVal, Number newVal) {
                    mainSliderValueLabel.updateText(String.format("%.2f", newVal));
                }
        });
    }

    @Override
    public void start(Stage stage) {
        setupSlider();

        VBox vb = new VBox(5, javaInfoLabel, mainSliderValueLabel, mainSlider);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10));
        vb.setSpacing(10);

        Scene scene = new Scene(vb, 640, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}