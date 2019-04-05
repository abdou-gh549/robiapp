package exercices;

import java.awt.Dimension;

import graphicLayer.Morph;
import graphicLayer.OnCommandeListiner;
import graphicLayer.World;
import utils.CommandManagerExo4_5;
import utils.CommandManagerExo4_5.RobotGetter;

/**
 * Class répondant aux contraintes de l'exercice4 et l'exercice5.
 * @author
 *
 */
public class Exercice4_5 implements RobotGetter,OnCommandeListiner{
	
	// la fenetre principale
	World world;
	Morph robi;
	CommandManagerExo4_5 commandManager;
	
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public Exercice4_5() {
		world = new World("Robi world", new Dimension(500,500));
		robi = new Morph();
		commandManager = new CommandManagerExo4_5(this);
		world.addMorph(robi);
		world.addCommandeListiner(this);
	
	}
	
	public void run() {
		world.open();
	}

	@Override
	public Morph getRobot(String nom) {
		return robi;
	}
	

	@Override
	public void onCommandeSaisie(String inp) {
		commandManager.execCommand(inp);
		
	}

	
	public static void main(String[] args) {
		Exercice4_5 exo4 = new Exercice4_5();
		exo4.run();
	}

}
