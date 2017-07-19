package tankgame;

import java.awt.image.BufferedImage;


public class Missile extends Tank{
 
    protected BufferedImage copyImg;
    protected int ZERO = 0;    
    private Tank tk;
    private Wall w;
    
    public Missile(int x, int y, BufferedImage img) {
        super(x, y, img);
//        this.x = tk.x;
  //      this.y = tk.y;
        copyImg = img;
    }
    
    public void moveUp() {
        this.y -= this.velY;
    }
    
    public void moveDown() {
        this.y += this.velY;
    }
    
    public void moveLeft() {
        this.x -= this.velX;
    }
    
    public void moveRight() {
        this.x += this.velX;
    }
    
    
    //shoot according to the direction
    //loop it to continue going
    //reload the bullet and the image
    //finish the shoot process once it hits the wall/tank
    public void shoot()
    {
        int i = 0;
        
        while(i != 20)
        {
            this.x += 10;
            i++;
            
        }
         
    }        
    
    public void getStats()
    {
    }
    
    public boolean isHit()
    {
        return false;
    }
    
    public void reload()
    {
        
    }
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public ObjectID getID() {
        return this.objID;
    }
        
}
