package SmartRocketProg.Genetic;

import SmartRocketProg.MovingGrafics.MovingGraficsScreen;
import SmartRocketProg.MovingGrafics.Sprite;
import SmartRocketProg.Objects.Obstacles;
import SmartRocketProg.Objects.Rockets;
import SmartRocketProg.Objects.Target;

import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

/**
 * Created by gabriel on 01.12.16.
 */
public class Population {
    private List<Rockets> rockets = new LinkedList<>();
    private List<Rockets> newRockets;
//    private Rockets newRocketsArray[];
    private double maxfit;
    private double gesFitness;
    private static int maxRokets = 100;
    private SplittableRandom random = new SplittableRandom();

    public Population(MovingGraficsScreen movingGraficsScreen) {
        for (int i = 0; i < maxRokets; i++) {
            rockets.add(new Rockets(20, 14));
        }
        addToScreen(movingGraficsScreen);
    }

    public static void setMaxRokets(int maxRokets) {
        Population.maxRokets = maxRokets;
    }
    public static int getMaxRokets() {
        return maxRokets;
    }

    private void evaluate(Target target){
        maxfit = 0;
        gesFitness = 0;
        int bestArrived = DNA.getDnaLength();

        for (Rockets rocket: rockets) {
            if (target.checkIfCollided(rocket)) {
                rocket.setCompleted(true);
                if (rocket.getArrived() < bestArrived){
                    bestArrived = rocket.getArrived();
                }
            }
        }

        for (Rockets rocket : rockets) {
            rocket.calcfitness(target, bestArrived);
        }

        rockets.forEach(rocketsElement -> {
            if (rocketsElement.getFitness() > maxfit) {
                maxfit = rocketsElement.getFitness();
            }
        });

        rockets.forEach(rocketsElement -> {
            rocketsElement.setFitness(rocketsElement.getFitness() / maxfit);
            rocketsElement.setFitness(Math.pow(rocketsElement.getFitness(), 4));
            gesFitness += rocketsElement.getFitness();
        });
    }

    private void naturalSelection(MovingGraficsScreen movingGraficsScreen){
        newRockets = new LinkedList<>();
        for (Rockets ignored : rockets) {
            DNA parentA = getParentDNA();
            DNA parentB = getParentDNA();
            DNA child = parentA.crosover(parentB);
            child.mutation();
            newRockets.add(new Rockets(20, 14, child));
        }
        delFromScreen(movingGraficsScreen);
        rockets = newRockets;
        addToScreen(movingGraficsScreen);
    }

    private DNA getParentDNA(){
        double randNr = random.nextDouble() * gesFitness;
        int index = 0;
        while (randNr >= 0){
            randNr -= rockets.get(index).getFitness();
            index++;
        }
        return rockets.get(index - 1).getDna();
    }

    private void addToScreen(MovingGraficsScreen movingGraficsScreen){
        rockets.forEach(movingGraficsScreen::addSprite);
    }

    private void delFromScreen(MovingGraficsScreen movingGraficsScreen){
        rockets.forEach(movingGraficsScreen::delSprite);
    }

    public void reset(Target target, MovingGraficsScreen movingGraficsScreen){
        DNA.resetCount();
        evaluate(target);
        naturalSelection(movingGraficsScreen);
        rockets.forEach(Rockets::reset);
    }

    public void fullReset(MovingGraficsScreen movingGraficsScreen){
        rockets.forEach(movingGraficsScreen::delSprite);
        DNA.resetCount();
        if (maxRokets > rockets.size()) {
            for (int i = rockets.size(); i < maxRokets; i++) {
                rockets.add(new Rockets(20, 14));
            }
        } else {
            for (int i = rockets.size(); i > maxRokets; i--) {
                rockets.remove(i - 1);
            }
        }

        rockets.forEach(rocketsElement -> {
            rocketsElement.setDna(new DNA());
            movingGraficsScreen.addSprite(rocketsElement);
            rocketsElement.reset();
        });
    }

    public void performMovement(Target target, Obstacles objekts[]){
        rockets.forEach(rocketsElement -> {
            rocketsElement.moveRocket();
            if (target.checkIfCollided(rocketsElement)){
                rocketsElement.setStuck(true);
            }

            for (Obstacles obstacles : objekts) {
                if (obstacles.checkIfCollided(rocketsElement)){
                    rocketsElement.setStuck(true);
                }
            }

            if (rocketsElement.getPosition().x > 600 || rocketsElement.getPosition().x < 0 || rocketsElement.getPosition().y > 600){
                rocketsElement.setStuck(true);
            }
        });
    }
}
