package pianoTiles;

import basicgraphics.SpriteComponent;
import basicgraphics.images.Painter;

import java.awt.*;

public class UI {
    SpriteComponent sc;
    public UI(SpriteComponent sc){
        this.sc = sc;
        Painter p = (g, d) -> {
            g.setColor(Color.black);
            g.drawString("Combo: ", 50,50);
        };
        sc.getScene().setPainter(p);
    }

    Painter p = (g, d) -> {
        g.setColor(Color.black);
        g.drawString("Combo: ", 50,50);
    };



    public void combo(int combo){
        Painter p = (g, d) -> {
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(combo+"", 50, 60);
        };
    }


}
