package SmartRocketProg.Objects;

import SmartRocketProg.MovingGrafics.MovingGraficsScreen;
import SmartRocketProg.MovingGrafics.Sprite;
import SmartRocketProg.MovingGrafics.Vector2D;
import SmartRocketProg.Support.Picimplement;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Created by gabriel on 02.12.16.
 */
public class Target extends Sprite {
    public Target(MovingGraficsScreen movingGraficsScreen){
        super(30,30);
        movingGraficsScreen.addSprite(this);
        setPosition(new Vector2D(MovingGraficsScreen.width / 2, MovingGraficsScreen.height * 0.1));
    }

    @Override public Node createView() {
        return Picimplement.targetPic(getPrefHeight(), 1, Color.WHITE, Color.WHITE.deriveColor(1, 1, 1, 0.3));
    }

    @Override public boolean checkIfCollided(Sprite sprite) {
        return sprite.calcDist(this) < getHeight() / 2;
    }
}
