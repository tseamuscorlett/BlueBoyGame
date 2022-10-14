// #7
package object;
// this was deleted in #21, moving everything to Entity

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {  // encapsulate!!
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // #8 let's NOT change this
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
