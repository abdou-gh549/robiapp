package exercices;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graphicLayer.Morph;
import graphicLayer.World;

/**
 * Class répondant aux contraintes de l'exercice2.
 * @author
 *
 */
public class Exercice2 implements KeyListener {
	
	// la fenetre principale
	World world;
	Morph robi;
	
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public Exercice2() {
		world = new World("Robi world", new Dimension(500,500));
		robi = new Morph();
		world.addKeyListener(this);
		world.addMorph(robi);
		world.disableAdvanceInterface();
	}
	
	public void run() {
		world.open();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// move up
		case KeyEvent.VK_UP:
			robi.moveUp(1);
		break;
		// move right
		case KeyEvent.VK_RIGHT:
			robi.moveRight(1);
		break;
		// move left
		case KeyEvent.VK_LEFT:
			robi.moveLeft(1);
		break;
		// move down
		case KeyEvent.VK_DOWN:
			robi.moveDown(1);
		break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
