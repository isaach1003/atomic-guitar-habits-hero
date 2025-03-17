package pianoTiles;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fret extends Sprite {
    public Fret(Scene scene, int w, int h){
        super(scene);
        BufferedImage image = BasicFrame.createImage(w,h);
        Graphics2D g = (Graphics2D) image.getGraphics();
        Picture fret = new Picture(image);

        g.setColor(new Color(50, 50, 60));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        setPicture(fret);
        freezeOrientation = true;
    }
}
