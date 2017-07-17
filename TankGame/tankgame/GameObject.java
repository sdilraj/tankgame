package tankgame;

public abstract class GameObject {
    private int x, y;
    private ObjectID objID;
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract int getX();
    public abstract int getY();
    
    public abstract void setX();
    public abstract void setY();
    
    public abstract ObjectID getID();
}
