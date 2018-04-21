package SmartRocketProg.MovingGrafics;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by gabriel on 01.12.16.
 */
public class MovingGraficsScreen extends Scene{
    private Pane groundBackground;
    public static double width = 0;
    public static double height = 0;
    private GridPane root = new GridPane();

    public MovingGraficsScreen(GridPane root, double width, double height) {
        super(root, width, height);

        this.root = root;

        BorderPane border = new BorderPane();
        MovingGraficsScreen.width = width;
        MovingGraficsScreen.height = height;

        groundBackground = new Layer(width, height);

        Pane layerPane = new Pane();
        layerPane.getChildren().addAll(groundBackground);

        border.setCenter(layerPane);

        root.add(border, 1, 1);
    }

    public MovingGraficsScreen(GridPane root, double width, double height, GridPane option, Color color) {
        super(root, width + 300, height);

        BorderPane border = new BorderPane();
        MovingGraficsScreen.width = width;
        MovingGraficsScreen.height = height;

        groundBackground = new Layer(width, height);

        groundBackground.setBackground(new Background(new BackgroundFill(color, null, null)));

        border.setCenter(groundBackground);

        root.add(border, 1, 1);
        root.add(option, 2, 1);
    }

    public void addSprite(Region rg) {
        groundBackground.getChildren().add(rg);
    }

    public void delSprite(Region rg) {
        groundBackground.getChildren().remove(rg);
    }

}
