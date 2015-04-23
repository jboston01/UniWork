import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements MouseListener, Runnable {

	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Thread thread;
	public static int health = 150;

	private BufferedImage background = null;
	public static PLAYER_STATE playerState;

	private Menu menu;

	public static GAME_STATE state = GAME_STATE.MENU;
	public static int WIDTH, HEIGHT;

	Player player;
	Hero hero;
	Villain villain;

	public String sleepBtn = "Sleep";

	public Rectangle fightButton = new Rectangle(220, 150, 150, 50);
	public Rectangle sleepButton = new Rectangle(470, 150, 150, 50);
	public Rectangle ignoreButton = new Rectangle(720, 150, 150, 50);
	public Rectangle textArea = new Rectangle(200, 5, 700, 120);

	private BufferedImage img;

	private void init() {

		try {
			img = ImageIO.read(new File("C:/Users/Josh/workspace/Platformer/res/backgrounmd21.png"));
		} catch (IOException e) {
		}

		WIDTH = getWidth();
		HEIGHT = getHeight();
		menu = new Menu();

		addKeyListener(new KeyInput(this));
		this.addMouseListener(new Menu());
		this.addMouseListener(new Game());

		hero = new Hero(100, 600);
		villain = new Villain(100, 600);
	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		this.requestFocus();
		init();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			render();

		}
	}

	public void render() {

		BufferStrategy strat = this.getBufferStrategy();
		if (strat == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = strat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(img, 0, 250, null);

		if (state == GAME_STATE.GAME) {
			if (playerState == PLAYER_STATE.HERO || playerState == PLAYER_STATE.ASLEEP) {
				hero.render(g);
			}
			if (playerState == PLAYER_STATE.VILLAIN || playerState == PLAYER_STATE.ASLEEP) {
				villain.render(g);
			}

			g.setColor(Color.GRAY);
			g.fillRect(5, 5, 150, 40);

			g.setColor(Color.GREEN);
			g.fillRect(5, 5, health, 40);

			g.setColor(Color.GRAY);
			g.fillRect(5, 50, 150, 40);

			g.setColor(Color.RED);
			g.fillRect(5, 50, 150, 40);

			Font fnt0 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("Energy", 5, 35);
			g.drawString("Actions", 5, 80);

			Font fnt1 = new Font("arial", Font.BOLD, 20);
			g.setFont(fnt1);
			g.drawString("Player State:", 5, 120);
			g.drawString(playerState.toString(), 30, 150);

			if (playerState == PLAYER_STATE.HERO) {

				g.setFont(fnt0);
				g.drawString("Fight", fightButton.x + 35, fightButton.y + 33);
				((Graphics2D) g).draw(fightButton);

				g.drawString(sleepBtn, sleepButton.x + 35, sleepButton.y + 33);
				((Graphics2D) g).draw(sleepButton);

				g.drawString("Ignore", ignoreButton.x + 30, ignoreButton.y + 33);
				((Graphics2D) g).draw(ignoreButton);

				((Graphics2D) g).draw(textArea);

			}

			g.dispose();
			strat.show();

		} else if (state == GAME_STATE.MENU) {
			menu.render(g);
			g.dispose();
			strat.show();
		} else if (state == GAME_STATE.PAUSE) {

		}
	}

	public void update() {
		if (state == GAME_STATE.GAME) {
			if (playerState == PLAYER_STATE.HERO) {
				hero.tick();
			}
			if (playerState == PLAYER_STATE.VILLAIN) {
				villain.tick();
			}

		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			hero.setVelX(5);
		} else if (key == KeyEvent.VK_LEFT) {
			hero.setVelX(-5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			villain.setVelX(5);
		} else if (key == KeyEvent.VK_LEFT) {
			villain.setVelX(-5);
		}

		if (key == KeyEvent.VK_ESCAPE) {
			state = GAME_STATE.PAUSE;
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			hero.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			hero.setVelX(0);
		}

		if (key == KeyEvent.VK_RIGHT) {
			villain.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			villain.setVelX(0);
		}

	}

	public static void main(String[] args) {
		new Window(920, 680, "Platformer", new Game());

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
		System.out.println("Pressedf");

		if (state == GAME_STATE.GAME || state== GAME_STATE.PAUSE) {
			if (mouseX >= sleepButton.x && mouseX <= sleepButton.x + 150) {
				if (mouseY >= sleepButton.y && mouseY <= sleepButton.y + 50) {
					if (playerState != PLAYER_STATE.ASLEEP && playerState != PLAYER_STATE.HUMAN) {
						playerState = PLAYER_STATE.ASLEEP;
						sleepBtn = "Awaken";
						System.out.println("Completed");

						state = GAME_STATE.PAUSE;
					}
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
