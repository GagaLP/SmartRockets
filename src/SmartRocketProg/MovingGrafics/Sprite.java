package SmartRocketProg.MovingGrafics;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Created by gabriel on 01.12.16.
 */

public abstract class Sprite extends Region {
    private Vector2D position, acceleration = new Vector2D(), velocity = new Vector2D();

    public Sprite(int width, int height){
        setPrefSize(width, height);
        getChildren().add(createView());
    }

    public Vector2D getPosition() {
        return position;
    }

    public abstract Node createView();
    public abstract boolean checkIfCollided(Sprite sprite);

    public void setPosition(Vector2D position) {
        this.position = position;
        relocate(this.position.x - this.getPrefWidth() / 2, this.position.y - this.getPrefHeight() / 2);
    }

    public void moveSprite(Vector2D force){
        applyForce(force);

        velocity.add(acceleration);
        position.add(velocity);
        acceleration.multiply(0);
        setRotate(Math.toDegrees(velocity.heading2D()));

        setPosition(position);
    }

    public void applyForce(Vector2D force){
        acceleration.add(force);
    }

    public double calcDist(Sprite objekt){
        return Vector2D.subtract(position, objekt.getPosition()).magnitude();
    }
}
