package SmartRocketProg.Genetic;

import SmartRocketProg.MovingGrafics.Vector2D;

import java.util.SplittableRandom;

/**
 * Created by gabriel on 01.12.16.
 */
public class DNA {
    public Vector2D genes[];
    private static int count = 0;
    private static double mutationrate = 0.01;
    private static int dnaLength = 400;
    private SplittableRandom random = new SplittableRandom();

    public DNA(){
        genes = new Vector2D[dnaLength];
        for (int i = 0; i < DNA.dnaLength; i++) {
            genes[i] = new Vector2D(5);
            genes[i].limit(0.1);
        }
    }

    public DNA(Vector2D genes[]){
        this.genes = genes;
    }

    public DNA(int dnaLength){
        DNA.dnaLength = dnaLength;
        genes = new Vector2D[dnaLength];
        for (int i = 0; i < DNA.dnaLength; i++) {
            genes[i] = new Vector2D(1);
            genes[i].limit(0.2);

        }
    }

    public static void setDnaLength(int dnaLength) {
        DNA.dnaLength = dnaLength;
    }
    public static int getDnaLength() {
        return dnaLength;
    }

    public static void setMutationrate(double mutationrate) {
        DNA.mutationrate = mutationrate;
    }
    public static double getMutationrate() {
        return mutationrate;
    }

    public DNA crosover(DNA dna){
        Vector2D newGenes[] = new Vector2D[dnaLength];
        int mid = (int)Math.floor(random.nextDouble() * dnaLength);

        for (int i = 0; i < dnaLength; i++) {
            if (mid < i){
                newGenes[i] = this.genes[i];
            } else {
                newGenes[i] = dna.genes[i];
            }
        }
        return new DNA(newGenes);
    }

    public void mutation(){
        for (int i = 0; i < dnaLength; i++) {
            if (random.nextDouble() < mutationrate){
                genes[i] = new Vector2D(1);
                genes[i].limit(0.2);
            }
        }
    }

    public static void countIncrement(){
        count++;
    }

    protected static void resetCount(){
        count = 0;
    }

    public static int getCount() {
        return count;
    }
}
