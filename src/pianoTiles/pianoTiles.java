package pianoTiles;

import basicgraphics.*;
import basicgraphics.images.BackgroundPainter;
import basicgraphics.images.Painter;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class pianoTiles {
    static int combo = 0;

    public static void main(String[] args) {
        BasicFrame bf = new BasicFrame("Piano Tiles");
        SpriteComponent sc = new SpriteComponent();
        System.out.println(sc.getWidth() + ", " + sc.getFullSize().getWidth());
        Dimension dim = new Dimension(800, 400);
        sc.setPreferredSize(dim);
        bf.createSingletonLayout(sc);
        UI ui = new UI(sc);
        ui.combo(0);

        ClockWorker.addTask(sc.moveSprites());
        ClockWorker.initialize(10);

        int w = 75; int h = 75;


        Painter p = (g, d) -> {
                g.setColor(new Color(40, 30, 20));
                g.fillRect(350,0,d.width,d.height);
                g.setColor(Color.white);
                g.fillRect(350+w*4,0,d.width,d.height);
                g.setColor(Color.black);
                g.drawString("Combo: ", 50,50);
        };
        sc.getScene().setPainter(p);



        int tileCount = 10; /*KEEP ABOVE 10*/
        Tile[] tiles = new Tile[tileCount];
        //Tile test = new Tile(sc.getScene());

        ClockWorker.addTask(new Task(1){
           @Override
           public void run() {
               if (iteration() == 1){ //just to wait so sc has time to update (sc is used in create tile)
                   for(int i=0; i<tileCount; i++){
                       createTile(sc, tiles, i, w, h);
                   }
               }
           }
        });


        for(int i=0; i<tileCount; i++){
            createTile(sc, tiles, i, w, h);
        }

        bf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A: /*System.out.println("A");*/ check(ui, sc, tiles, w, h, 0); break;
                    case KeyEvent.VK_S: /*System.out.println("S");*/ check(ui, sc, tiles, w, h, 1); break;
                    case KeyEvent.VK_D: /*System.out.println("D");*/ check(ui, sc, tiles, w, h, 2); break;
                    case KeyEvent.VK_F: /*System.out.println("F");*/ check(ui, sc, tiles, w, h, 3); break;
                }
            }
        });

        //boolean fail = false;
        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                if ((int)tiles[0].getY()/10 == /*stopping point*/(int)(sc.getHeight()-h*2)/10) {
                    for(Tile t : tiles) {
                        t.setVelY(0);
                    }
                }

            }});
        ClockWorker.addTask(new Task(1){
            @Override
            public void run() {
                ClockWorker.addTask(new Task() {
                    double window = sc.getHeight();
                    @Override
                    public void run() {
                        if(window != sc.getHeight()) {
                            window = sc.getHeight();
                            for(int i = 0; i<tileCount; i++) {
                                tiles[i].setY(window-h*2-h*i);
                            }
                        }
                    }
                });
            }
        });


        bf.show();

    }

    public static void createTile(SpriteComponent sc, Tile[] tiles, int i, int w, int h){
        int pos = (int)(Math.random()*4);
        tiles[i] = new Tile(sc.getScene(), w, h);
        tiles[i].pos = pos;
        tiles[i].setX(350+(w*pos));
        /*tiles[i].setY(400-(h*i));*/
        tiles[i].setY(i < 1 ? sc.getHeight()-h*2/*offset from bottom*/ : tiles[i-1].getY() - h);

    }

    public static void check(UI ui, SpriteComponent sc, Tile[] tiles, int w, int h, int pos){
        if(tiles[0].pos == pos){
            //System.out.println("correct!");
            tiles[0].destroy();
            for(int i=0; i<tiles.length-1; i++){
                tiles[i] = tiles[i+1];
            }

            createTile(sc, tiles, tiles.length-1, w, h);
            for (Tile tile : tiles) {
                tile.setVelY(5);
            }
            combo++;
        } else {
            System.out.println(combo);
            combo = 0;
        }
    }
}