package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS　#1
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;  // public for Player to access #3
    public final int maxScreenCol = 16;  // public for TileManager to access #4
    public final int maxScreenRow = 12;  // public #4
    public final int screenWidth = tileSize * maxScreenCol;  // public #4
    public final int screenHeight = tileSize * maxScreenRow;  // public #4

    int FPS = 60;

    // initialize game components
    TileManager tileM = new TileManager(this);  // #4
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();  // #9,10
    Sound se = new Sound();  // #9/10
    public CollisionChecker collisionChecker = new CollisionChecker(this);  // #6
    public AssetSetter assetSetter = new AssetSetter(this);  // for setting objects #7
    public MessagePresenter messagePresenter = new MessagePresenter(this);  // for on-screen messages #10
    Thread gameThread;  // for looping #2

    // ENTITY & OBJECT
    Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];  // #7

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  // improve game speed
        this.addKeyListener(keyH); // to recognize key input
        this.setFocusable(true); // to receive key input
    }

    public void setupGame () {
        assetSetter.setObject();
        playMusic(0);  // background music #9
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // block to loop the game #2
    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);  // draw tiles before player! #4
        // go through obj[] Array #7
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        player.draw(g2);
        messagePresenter.draw(g2);  // AFTER everything! #10
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(int i) {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}