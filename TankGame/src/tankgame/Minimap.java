
package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Minimap extends GameObject {
    
    private BufferedImage map;
    private final int size = 100;
    
    public Minimap (ObjectID id, int x, int y, Image Img) {
        super(id, x, y, Img);
    }
    
    private Image tileset;
    
    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(Img, x, y, null);
    }

    @Override
    public Rectangle checkBounds() {
        return null;
        
    }

    @Override
    public boolean collide() {
        return false;
        
        }
    
    
    /*
{
        map = new BufferedImage(size, size, BufferedImage. TYPE_INT_RGB);
    }
    
    public void createMap(Tile[] [] tiles) {
        Graphics g = map.getGraphics();
        for (int x = 0; x <tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++);
            {
                tiles[x] [y].draw(g,0,0);
            }
        }
        g.dispose();
        map = Textue.scale(size, size, map);
    }
    public void draw(Graphics g) 
    {
        g.drawImage(map, Main.width - size + Frame.transx, Frame.transy, null);
    }
    */
    
    
}
