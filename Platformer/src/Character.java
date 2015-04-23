import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class Character {

	protected float x, y;
	protected float velX = 0, velY = 0;
	

	public Character(float x, float y) {
		this.x = x;
		this.y = y;

	}

	public abstract void tick();

	public abstract void render(Graphics g);
	

	public abstract Rectangle getBounds();

	public abstract float getX();

	
	public abstract void setX(float x);

		
	public abstract float getVelX();

	
	public abstract void setVelX(float velX);

	
	
	

}
