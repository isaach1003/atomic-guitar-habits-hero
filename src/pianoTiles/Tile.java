package pianoTiles;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends Sprite {
    public int pos = 0;
    public Tile(Scene scene, int w, int h){
        super(scene);
        BufferedImage image = BasicFrame.createImage(w,h);
        Graphics2D g = (Graphics2D) image.getGraphics();
        Picture tile = new Picture(image);

        g.setColor(new Color(120, 200, 255));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        setPicture(tile);
        freezeOrientation = true;

        //setX(350); setY(400);

    }

}
