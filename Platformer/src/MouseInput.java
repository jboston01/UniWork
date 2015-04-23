import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

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

		// public Rectangle playButton = new Rectangle((Game.WIDTH/2)-70
		// ,150,150,50);
		// public Rectangle helpButton = new Rectangle((Game.WIDTH/2)-70
		// ,250,150,50);
		// public Rectangle quitButton = new Rectangle((Game.WIDTH/2)-70
		// ,350,150,50);
		int mouseX = arg0.getX();
		int mouseY = arg0.getY();

		if (Game.state == GAME_STATE.MENU) {
			if (mouseX >= (Game.WIDTH / 2) - 70 && mouseX <= Game.WIDTH / 2 - 70 + 150) {
				if (mouseY >= 150 && mouseY <= 200) {
					// Play Pressed
					Game.state = GAME_STATE.GAME;
				}
			}

			if (mouseX >= (Game.WIDTH / 2) - 70 && mouseX <= Game.WIDTH / 2 - 70 + 150) {
				if (mouseY >= 350 && mouseY <= 400) {
					// Play Pressed

					System.exit(1);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
