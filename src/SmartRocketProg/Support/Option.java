package SmartRocketProg.Support;

import SmartRocketProg.Exeptions.NumberFormatExeptionDouble;
import SmartRocketProg.Exeptions.NumberFormatExeptionInt;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Created by gabriel on 07.12.16.
 */
public class Option extends GridPane {
    private TextField maxRocketsTxtFld;
    private TextField mutationRateTxtFld;
    private TextField dnaLength;
    private Button reset;
    private Button quit;
    private Label fps;
    private Slider slider;

    public Option(int width, int height){
        super();
        setAlignment(Pos.CENTER);
        setPrefSize(width, height);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(20, 20, 20, 20));

        //Überschrift
        Label title = new Label("Options");
        title.setFont(Font.font(33));
        add(title, 1, 1, 2, 1);

        Label maxRocketsLbl = new Label("Max Rockets");
        maxRocketsLbl.setMinWidth(120);
        add(maxRocketsLbl, 1,2);

        maxRocketsTxtFld = new TextField();
        maxRocketsTxtFld.setText("200");
        add(maxRocketsTxtFld, 2, 2);

        Label mutationRateLbl = new Label("Mutation Rate");
        mutationRateLbl.setMinWidth(120);
        add(mutationRateLbl, 1,3);

        mutationRateTxtFld = new TextField();
        mutationRateTxtFld.setText("0.005");
        add(mutationRateTxtFld, 2, 3);

        Label length = new Label("Length");
        length.setMinWidth(120);
        add(length, 1,4);

        dnaLength = new TextField();
        dnaLength.setText("400");
        add(dnaLength, 2, 4);

        slider = new Slider();
        slider.setMin(1);
        slider.setMax(10);
        slider.setValue(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(1);
        slider.setSnapToTicks(true);
        add(slider,1, 5, 2,1);

        HBox hBox = new HBox(10);
        reset = new Button("Reset");

        hBox.getChildren().add(reset);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        add(hBox, 2, 6);

        HBox hBoxTwo = new HBox(10);
        quit = new Button("Quit");

        hBoxTwo.getChildren().add(quit);
        hBoxTwo.setAlignment(Pos.BOTTOM_LEFT);
        add(hBoxTwo, 1, 6);

        fps = new Label("FPS: ");
        add(fps, 1, 7);
        fps.setVisible(false);
    }

    public void setFPS(int fpsInt) {
        fps.setVisible(true);
        fps.setText("FPS: " + Integer.toString(fpsInt));
    }

    public void setResetButtonAction(EventHandler<ActionEvent> event){
        reset.setOnAction(event);
    }

    public void setQuitButtonAction(EventHandler<ActionEvent> event){
        quit.setOnAction(event);
    }

    public int getSpeedSlider(){
        return (int) slider.getValue();
    }

    public int getMaxRocketsTxtFld(){
        try {
            return Integer.parseInt(maxRocketsTxtFld.getText());
        }catch (NumberFormatException e){
            throw new NumberFormatExeptionInt();
        }
    }

    public double getMutationRateTxtFld(){
        try {
            return Double.parseDouble(mutationRateTxtFld.getText());
        }catch (NumberFormatException e){
            throw new NumberFormatExeptionDouble();
        }
    }

    public int getDnaLength(){
        try {
            return Integer.parseInt(dnaLength.getText());
        }catch (NumberFormatException e){
            throw new NumberFormatExeptionInt();
        }
    }
}
