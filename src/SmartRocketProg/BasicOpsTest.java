package SmartRocketProg;


import SmartRocketProg.Exeptions.NumberFormatExeptionDouble;
import SmartRocketProg.Exeptions.NumberFormatExeptionInt;
import SmartRocketProg.Genetic.DNA;
import SmartRocketProg.Genetic.Population;
import SmartRocketProg.Genetic.PopulationArray;
import SmartRocketProg.MovingGrafics.MovingGraficsScreen;
import SmartRocketProg.Objects.Obstacles;
import SmartRocketProg.Objects.Target;
import SmartRocketProg.Support.Menue;
import SmartRocketProg.Support.Option;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BasicOpsTest extends Application {

    private MovingGraficsScreen movingGraficsScreen;
    private Status status = Status.STARTED;
//    private Population pop;
    private PopulationArray pop;
    private Target targ;
    private Obstacles obstacles[] = new Obstacles[3];
    private Menue menuePane;
    private Option optionPane;

    @Override public void start(Stage stage) {
        menuePane = new Menue(300, 600);
        menuePane.setButtonAction(event -> startSimulation(stage));
        Scene menue = new Scene(menuePane);
        stage.setScene(menue);
        stage.setTitle("Smart Rockets");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("./IconRocket.png")));
        stage.setResizable(false);
        stage.show();

    }

    private void optionpanle(Stage stage){

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 300) / 2 - 300);
        stage.setY((screenBounds.getHeight() - 600) / 2);

        optionPane = new Option(300, 600);

        movingGraficsScreen = new MovingGraficsScreen(new GridPane(), 600, 600, optionPane, Color.BLACK);

        stage.setScene(movingGraficsScreen);
        stage.show();

//        pop = new Population(movingGraficsScreen);
        pop = new PopulationArray(movingGraficsScreen);
        targ = new Target(movingGraficsScreen);
        obstacles[0] = new Obstacles(movingGraficsScreen, 350, 20, 300, 300);
        obstacles[1] = new Obstacles(movingGraficsScreen, 100, 20, 500, 400);
        obstacles[2] = new Obstacles(movingGraficsScreen, 100, 20, 100, 200);
        optionPane.setResetButtonAction(e -> reset());
        optionPane.setQuitButtonAction(e -> quit());
    }

    private void quit(){
        Platform.exit();
        System.exit(0);
    }

    public void startSimulation(Stage stage){
        setNumbers();
        optionpanle(stage);
        simulation();
    }

    public void setNumbers(){
        try {
//            Population.setMaxRokets(menuePane.getMaxRocketsTxtFld());
            PopulationArray.setMaxRokets(menuePane.getMaxRocketsTxtFld());
        }catch (NumberFormatExeptionInt e){
//            Population.setMaxRokets(100);
            PopulationArray.setMaxRokets(100);
        }

        try {
            DNA.setMutationrate(menuePane.getMutationRateTxtFld());
        }catch (NumberFormatExeptionDouble e){
            DNA.setMutationrate(0.01);
        }

        try {
            DNA.setDnaLength(menuePane.getDnaLength());
        }catch (NumberFormatExeptionDouble e){
            DNA.setDnaLength(400);
        }
    }

    private void reset(){
        status = Status.STOPED;
        setOptions();
        pop.fullReset(movingGraficsScreen);
        status = Status.STARTED;
    }

    private void setOptions(){
        try {
//            Population.setMaxRokets(optionPane.getMaxRocketsTxtFld());
            PopulationArray.setMaxRokets(optionPane.getMaxRocketsTxtFld());
        }catch (NumberFormatExeptionInt e){
//            Population.setMaxRokets(100);
            PopulationArray.setMaxRokets(100);
        }

        try {
            DNA.setMutationrate(optionPane.getMutationRateTxtFld());
        }catch (NumberFormatExeptionDouble e){
            DNA.setMutationrate(0.01);
        }

        try {
            DNA.setDnaLength(optionPane.getDnaLength());
        }catch (NumberFormatExeptionInt e){
            DNA.setDnaLength(400);
        }
    }

    private void simulation(){
        // FPS
        final long hal = System.currentTimeMillis();

        new AnimationTimer() {
            //FPS
            int fps = 0, last = 1000;
            long ha;
            int speed = 1;

            @Override public void handle(long now) {

                for (int i = 0; i < speed && DNA.getCount() <= DNA.getDnaLength() - 1; i++) {
                    pop.performMovement(targ, obstacles);
                    DNA.countIncrement();
                }

                speed = optionPane.getSpeedSlider();

                if (DNA.getCount() > DNA.getDnaLength() - 1) {
                    pop.reset(targ, movingGraficsScreen);
                }

                //FPS
                ha = System.currentTimeMillis();
                if ((ha - hal) > last) {
                    optionPane.setFPS(fps);
                    fps = 0;
                    last += 1000;
                }
                fps++;

                if (status == Status.STOPED){
                    this.stop();
                    while (status == Status.STOPED){
                        if (status != Status.STOPED){
                            this.start();
                        }
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


