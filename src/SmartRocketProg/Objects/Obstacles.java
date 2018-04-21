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
public class Obstacles extends Sprite {
//    public Obstacles(MovingGraficsScreen movingGraficsScreen){
//        super(350,20);
//        movingGraficsScreen.addSprite(this);
//        setPosition(new Vector2D(MovingGraficsScreen.width / 2 , MovingGraficsScreen.height * 0.5));
//    }

    public Obstacles(MovingGraficsScreen movingGraficsScreen, int width, int heigth, int positionx, int positiony){
        super(width,heigth);
        movingGraficsScreen.addSprite(this);
        setPosition(new Vector2D(positionx , positiony));
    }

    @Override public Node createView() {
        return Picimplement.obstaclePic(getPrefWidth(), getPrefHeight(), 1, Color.WHITE, Color.WHITE.deriveColor(1, 1, 1, 0.3));
    }

    @Override public boolean checkIfCollided(Sprite sprite) {
        return sprite.getPosition().x > getPosition().x - getWidth() / 2 && sprite.getPosition().x < getPosition().x + getWidth() / 2 && sprite.getPosition().y > getPosition().y - getHeight() / 2 && sprite.getPosition().y < getPosition().y + getHeight() / 2;
    }
}
