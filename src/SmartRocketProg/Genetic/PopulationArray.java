package SmartRocketProg.Genetic;

import SmartRocketProg.MovingGrafics.MovingGraficsScreen;
import SmartRocketProg.Objects.Obstacles;
import SmartRocketProg.Objects.Rockets;
import SmartRocketProg.Objects.Target;

import java.util.SplittableRandom;

/**
 * Created by gabriel on 21.04.18.
 * Copyright © 2018 gabriel. All rights reserved.
 */
public class PopulationArray {
    private Rockets rockets[];
    private Rockets newRockets[];
    private double maxfit;
    private double gesFitness;
    private static int maxRokets = 100;
    private SplittableRandom random = new SplittableRandom();

    public PopulationArray(MovingGraficsScreen movingGraficsScreen) {
        rockets = new Rockets[maxRokets];
        for (int i = 0; i < maxRokets; i++) {
            rockets[i] = new Rockets(20, 14);
        }
        addToScreen(movingGraficsScreen);
    }

    public static void setMaxRokets(int maxRokets) {
        PopulationArray.maxRokets = maxRokets;
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
                if (bestArrived > rocket.getArrived()){
                    bestArrived = rocket.getArrived();
                }
            }
        }

        System.out.println(bestArrived);

        for (Rockets rocket: rockets) {
            rocket.calcfitness(target, bestArrived);
            if (rocket.getFitness() > maxfit) {
                maxfit = rocket.getFitness();
            }
        }

        for (Rockets rocket: rockets) {
            rocket.setFitness(rocket.getFitness() / maxfit);
            rocket.setFitness(Math.pow(rocket.getFitness(), 4));
            gesFitness += rocket.getFitness();
        }
    }

    private void naturalSelection(MovingGraficsScreen movingGraficsScreen){
        newRockets = new Rockets[rockets.length];
        for (int i = 0; i < rockets.length; i++) {
            DNA parentA = getParentDNA();
            DNA parentB = getParentDNA();
            DNA child = parentA.crosover(parentB);
            child.mutation();
            newRockets[i] = new Rockets(20, 14, child);
        }

        delFromScreen(movingGraficsScreen);
        rockets = newRockets;
        addToScreen(movingGraficsScreen);
    }

    private DNA getParentDNA(){
        double randnr = random.nextDouble() * gesFitness;
        int index = 0;
        while (randnr >= 0){
            randnr -= rockets[index].getFitness();
            index++;
        }
        return rockets[index - 1].getDna();
    }

    private void addToScreen(MovingGraficsScreen movingGraficsScreen){
        for (Rockets rocket: rockets) {
            movingGraficsScreen.addSprite(rocket);
        }
    }

    private void delFromScreen(MovingGraficsScreen movingGraficsScreen){
        for (Rockets rocket: rockets) {
            movingGraficsScreen.delSprite(rocket);
        }
    }

    public void reset(Target target, MovingGraficsScreen movingGraficsScreen){
        DNA.resetCount();
        evaluate(target);
        naturalSelection(movingGraficsScreen);
        for (Rockets rocket: rockets) {
            rocket.reset();
        }
    }

    public void fullReset(MovingGraficsScreen movingGraficsScreen){
//        int rocketsCount = rockets.length;
        delFromScreen(movingGraficsScreen);
        DNA.resetCount();
        rockets = new Rockets[maxRokets];

//        if (maxRokets > rockets.length) {
//            for (int i = rockets.length; i < maxRokets; i++) {
//                rockets.add(new Rockets(20, 14));
//            }
//        } else {
//            for (int i = rockets.size(); i > maxRokets; i--) {
//                rockets.remove(i - 1);
//            }
//        }
        for (int i = 0; i < maxRokets; i++) {
            rockets[i] = new Rockets(20, 14);
            rockets[i].setDna(new DNA());
            movingGraficsScreen.addSprite(rockets[i]);
            rockets[i].reset();
        }
    }

    public void performMovement(Target target, Obstacles objekts[]){
        for (Rockets rocket : rockets) {
            if (!rocket.isStuck()){
                rocket.moveRocket();
                if (target.checkIfCollided(rocket)){
                    rocket.setArrived(DNA.getCount());
                    rocket.setStuck(true);
                }

                for (Obstacles obstacles : objekts) {
                    if (obstacles.checkIfCollided(rocket)){
                        rocket.setStuck(true);
                    }
                }

                if (rocket.getPosition().x > 600 || rocket.getPosition().x < 0 || rocket.getPosition().y > 600){
                    rocket.setStuck(true);
                }
            }
        }
    }
}
