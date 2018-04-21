package SmartRocketProg.Support;

import SmartRocketProg.Exeptions.NumberFormatExeptionDouble;
import SmartRocketProg.Exeptions.NumberFormatExeptionInt;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Created by gabriel on 09.12.16.
 */
public class Menue extends GridPane {
    private TextField maxRocketsTxtFld;
    private TextField mutationRateTxtFld;
    private TextField dnaLengthTxtFld;
    private Button start;

    public Menue(int width, int height){
        super();
        setAlignment(Pos.CENTER);
        setPrefSize(width, height);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(20, 20, 20, 20));

        Label title = new Label("Menü");
        title.setFont(Font.font(30));
        add(title, 1, 1);

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

        Label dnaLengthLbl = new Label("DNA Length");
        dnaLengthLbl.setMinWidth(120);
        add(dnaLengthLbl, 1,4);

        dnaLengthTxtFld = new TextField();
        dnaLengthTxtFld.setText("400");
        add(dnaLengthTxtFld, 2, 4);

        HBox hBox = new HBox(10);
        start = new Button("Go");

        hBox.getChildren().add(start);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        add(hBox, 2, 5);
    }

    public void setButtonAction(EventHandler<ActionEvent> event){
        start.setOnAction(event);
    }

    public int getMaxRocketsTxtFld(){
        try {
            return Integer.parseInt(maxRocketsTxtFld.getText());
        }catch (NumberFormatException e){
            throw new NumberFormatExeptionInt();
        }
    }

    public int getDnaLength(){
        try {
            return Integer.parseInt(dnaLengthTxtFld.getText());
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
}
