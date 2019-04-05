package exercices;

import java.awt.Dimension;

import graphicLayer.Morph;
import graphicLayer.OnCommandeListiner;
import graphicLayer.World;
import utils.CommandManagerExo6.RobotGetter;
import utils.CommandManagerExo6;

/**
 * Class répondant aux contrinates de l'exercice6.
 * @author
 * 
 */
public class Exercice6 implements RobotGetter,OnCommandeListiner{
	
	// la fenetre principale
	World world;
	Morph robi;
	CommandManagerExo6 commandManager;
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public Exercice6() {
		world = new World("Robi world", new Dimension(500,500));
		robi = new Morph();
		commandManager = new CommandManagerExo6(this);
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
		Exercice6 exo4 = new Exercice6();
		exo4.run();
	}

}
