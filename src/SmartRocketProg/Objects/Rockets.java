package SmartRocketProg.Objects;

import SmartRocketProg.Genetic.DNA;
import SmartRocketProg.MovingGrafics.MovingGraficsScreen;
import SmartRocketProg.MovingGrafics.Sprite;
import SmartRocketProg.MovingGrafics.Vector2D;
import SmartRocketProg.Support.Picimplement;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Created by gabriel on 01.12.16.
 */

public class Rockets extends Sprite {
    private boolean stuck = false;
    private boolean completed = false;
    private double fitness = 0;
    private DNA dna;
    private int arrived = 0;

    public Rockets(int width, int height){
        super(width, height);
        dna = new DNA();
        setPosition(new Vector2D(MovingGraficsScreen.width / 2, MovingGraficsScreen.height - 20));
    }

    public Rockets(int width, int height, DNA dna){
        super(width, height);
        this.dna = dna;
        reset();
    }

    public DNA getDna() {
        return dna;
    }
    public void setDna(DNA dna) {
        this.dna = dna;
    }

    public double getFitness() {
        return fitness;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }
    public boolean isStuck() {
        return stuck;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void moveRocket() {
        if (!stuck && !completed){
            moveSprite(dna.genes[DNA.getCount()]);
        }
    }

    public void reset(){
        setPosition(new Vector2D(MovingGraficsScreen.width / 2, MovingGraficsScreen.height - 10));
        setRotate(270);
        stuck = false;
        completed = false;
    }

    public void calcfitness(Sprite target, int bestArrived){
        fitness = 1 / calcDist(target);
        if (completed){
            fitness *= 10;
            fitness *= 10 / ((arrived - bestArrived) + 1);
        }else if(stuck){
            fitness /= 25;
        }
    }

    public int getArrived() {
        return arrived;
    }

    public void setArrived(int arrived) {
        this.arrived = arrived;
    }

    @Override public Node createView() {
        return Picimplement.rocketPic(getPrefWidth(), getPrefHeight(), 1, Color.RED, Color.RED.deriveColor(1, 1, 1, 0.3));
    }

    @Override public boolean checkIfCollided(Sprite sprite) {
        return false;
    }
}
