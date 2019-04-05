package exercices;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import commands.Command;
import commands.Down;
import commands.Left;
import commands.Right;
import commands.Up;
import graphicLayer.Morph;
import graphicLayer.World;

/**
 * Class répondant aux contraintes de l'exercice3.
 * @author
 *
 */
public class Exercice3 implements KeyListener {
	
	// la fenetre principale
	World world;
	Morph robi;
	Map<Integer,Command > mapDesCommande = new HashMap<Integer,Command>();
	
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public Exercice3() {
		world = new World("Robi world", new Dimension(500,500));
		robi = new Morph();
		world.addKeyListener(this);
		world.addMorph(robi);
		world.disableAdvanceInterface();
		
		this.mapDesCommande.put(KeyEvent.VK_LEFT,new Left(robi));
		this.mapDesCommande.put(KeyEvent.VK_UP,new Up(robi));
		this.mapDesCommande.put(KeyEvent.VK_RIGHT,new Right(robi));
		this.mapDesCommande.put(KeyEvent.VK_DOWN,new Down(robi));
	}
	
	public void run() {
		world.open();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Command c = mapDesCommande.get(e.getKeyCode());
		if(c != null) {
			c.run();
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
