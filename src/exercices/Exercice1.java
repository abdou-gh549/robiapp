package exercices;

import java.awt.Dimension;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

/**
 * Class répondant aux contraintes de l'exercice1.
 * @author
 *
 */
public class Exercice1 {
	
	// la fenetre principale
	World world;
	Morph robi;
	
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public Exercice1() {
		world = new World("Robi world", new Dimension(500,500));
		robi = new Morph();
		world.addMorph(robi);
		world.disableAdvanceInterface();
	}
	
	public void run() {
		world.open();
		//nécessité de lancer un tread car nous effectuons une boucle infinie
		new Thread(()->{
			while(true) {
				// move right
				while(robi.getX() + robi.getWidth() < world.getWidth()) {
					robi.moveRight(10);
					
					Tools.sleep(100);
				}
				// move down
				while(robi.getY() + robi.getHeight() < world.getHeight()) {
					robi.moveDown(10);
					Tools.sleep(100);
				}
				// move left
				while(robi.getX() > 0) {
					robi.moveLeft(10);
					Tools.sleep(100);

				}
				// move up
				while(robi.getY() > 0) {
					robi.moveUp(10);
					Tools.sleep(100);
				}
				// change color
				robi.randomColor();
			}
		}) .start();// lancement du thread
	}

}
