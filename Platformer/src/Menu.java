import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener {

	public Rectangle heroButton = new Rectangle((40), 500, 150, 50);
	public Rectangle villainButton = new Rectangle(Game.WIDTH - 190, 500, 150, 50);
	public Rectangle quitButton = new Rectangle((Game.WIDTH / 2) - 100, 350, 150, 50);

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Hero or Villain", (Game.WIDTH / 4) + 40, 100);

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Hero", heroButton.x + 40, heroButton.y + 33);
		g2d.draw(heroButton);
		g.drawString("Villain", villainButton.x + 30, villainButton.y + 33);
		g2d.draw(villainButton);
		g.drawString("Quit", quitButton.x + 40, quitButton.y + 33);
		g2d.draw(quitButton);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		int mouseX = arg0.getX();
		int mouseY = arg0.getY();
		

		if (Game.state == GAME_STATE.MENU) {
			if (mouseX >= heroButton.x && mouseX <= heroButton.x + 150) {
				if (mouseY >= heroButton.y && mouseY <= heroButton.y + 50) {
					// Launch hero
					Game.state = GAME_STATE.GAME;
					Game.playerState = PLAYER_STATE.HERO;
				}
			}

			if (mouseX >= villainButton.x && mouseX <= villainButton.x + 150) {
				if (mouseY >= villainButton.y && mouseY <= villainButton.y + 50) {
					// Launch Villain
					Game.state = GAME_STATE.GAME;
					Game.playerState = PLAYER_STATE.VILLAIN;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
