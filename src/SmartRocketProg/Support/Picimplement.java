package SmartRocketProg.Support;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

/**
 * Created by gabriel on 03.12.16.
 */
public class Picimplement {

    public static ImageView targetPic(double radius, double strokeWidth, Paint strokeColor, Paint fill){

        Circle circle = new Circle((radius / 2) - 2 * strokeWidth);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        int imageWidth = (int)radius;
        int imageHeight = (int)radius;

        WritableImage writableImage = new WritableImage(imageWidth, imageHeight);
        setPref(circle, strokeWidth, strokeColor, fill).snapshot(parameters, writableImage);

        return new ImageView(writableImage);
    }

    public static ImageView obstaclePic(double width, double height, double strokeWidth, Paint strokeColor, Paint fill){

        double obstacleWidth = width - 2 * strokeWidth;
        double obstacleHeight = height - 2 * strokeWidth;

        Polygon obstacle = new Polygon(0, 0, 0, obstacleHeight, obstacleWidth, obstacleHeight, obstacleWidth, 0);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        int imageWidth = (int)width;
        int imageHeight = (int)height;

        WritableImage writableImage = new WritableImage(imageWidth, imageHeight);
        setPref(obstacle, strokeWidth, strokeColor, fill).snapshot(parameters, writableImage);

        return new ImageView(writableImage);
    }

    public static ImageView rocketPic(double width, double height, double strokeWidth, Paint strokeColor, Paint fill){

        double rocketWidth = width - 2 * strokeWidth;
        double rocketHeight = height - 2 * strokeWidth;

        Polygon rocket = new Polygon(0, 0, rocketWidth, rocketHeight / 2, 0, rocketHeight, rocketWidth * 0.1, rocketHeight / 2);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        int imageWidth = (int) width;
        int imageHeight = (int) height;

        WritableImage writableImage = new WritableImage(imageWidth, imageHeight);
        setPref(rocket, strokeWidth, strokeColor, fill).snapshot(parameters, writableImage);

        return new ImageView(writableImage);
    }

    private static Shape setPref(Shape shape, double strokeWidth, Paint strokeColor, Paint fill ){
        shape.setStrokeLineJoin(StrokeLineJoin.MITER);
        shape.setStrokeLineCap(StrokeLineCap.SQUARE);
        shape.setStroke(strokeColor);
        shape.setFill(fill);
        shape.setStrokeWidth(strokeWidth);
        return shape;
    }
}
