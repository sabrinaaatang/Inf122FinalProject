package org.openjfx.inf122finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TileSwap implements TileManipulator {
    private final int x1, y1, x2, y2;
    private final Board board;

    public TileSwap(Board board, int x1, int y1, int x2, int y2) {
        this.board = board;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void execute() {
        Tile tile1 = board.getTileAt(x1, y1);
        Tile tile2 = board.getTileAt(x2, y2);

        if (tile1 != null && tile2 != null) {
            animateSwap(tile1, tile2, () -> {
                Block tempBlock = tile1.getContainingBlock();
                tile1.setContainingBlock(tile2.getContainingBlock());
                tile2.setContainingBlock(tempBlock);
            });
        }
    }

    private void animateSwap(Tile tile1, Tile tile2, Runnable onComplete) {
        double deltaX = tile2.getLayoutX() - tile1.getLayoutX();
        double deltaY = tile2.getLayoutY() - tile1.getLayoutY();

        Timeline timeline = new Timeline();

        KeyValue kv1X = new KeyValue(tile1.translateXProperty(), deltaX);
        KeyValue kv1Y = new KeyValue(tile1.translateYProperty(), deltaY);
        KeyValue kv2X = new KeyValue(tile2.translateXProperty(), -deltaX);
        KeyValue kv2Y = new KeyValue(tile2.translateYProperty(), -deltaY);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), kv1X, kv1Y, kv2X, kv2Y);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            tile1.setTranslateX(0);
            tile1.setTranslateY(0);
            tile2.setTranslateX(0);
            tile2.setTranslateY(0);
            onComplete.run();
        });

        timeline.play();
    }
}
