import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Hero extends Character {
	
	private float width = 32, height = 64;
	private double velX = 0;
	private PLAYER_STATE state = PLAYER_STATE.HERO;

	public Hero(float x, float y) {
		super(x, y);
	}

	@Override
	public void tick() {
		x += velX;

		if (x <= 0)
			x = 0;
		if (x >= 920 - 20)
			x = 920 - 20;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) this.x, (int) this.y, (int) width, (int) height);

		Graphics2D g2d = (Graphics2D) g;

		g2d.draw(getBounds());

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public float getX() {

		return x;
	}

	@Override
	public void setX(float x) {
		this.x = x;

	}

	@Override
	public float getVelX() {
		return (float) velX;
	}


	@Override
	public void setVelX(float velX) {
		this.velX = velX;

	}

}
