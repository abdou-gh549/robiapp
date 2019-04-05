package exercices;

import java.awt.Dimension;
import commands.OperationsArithmetiques;
import graphicLayer.Morph;
import graphicLayer.OnCommandeListiner;
import graphicLayer.World;
import utils.CommandManager;
import utils.CommandManager.RobotGetter;
import utils.ConditionInstruction;
import utils.RobotManager;
import utils.RobotManager.AfterCreatRobotListinner;
import utils.RobotManager.AfterDeleteRobotListinner;

/**
 * Class répondant aux contraintes de l'exercice7, l'exercice8, 
 * l'exercice9, l'exercice10, l'exercice11.
 * @author
 *
 */
public class Exercice7_8_9_10_11 implements OnCommandeListiner,AfterDeleteRobotListinner,AfterCreatRobotListinner,RobotGetter{
	
	// la fenetre principale
	public World world = new World("Robi world", new Dimension(500,500));

	RobotManager robotManager;
	CommandManager commandManager;
	
	/**
	 * initialise et lance la fenetre de l'application
	 */
	public void run() {
		robotManager = new RobotManager();
		robotManager.setOnAfterDeletRobotListinner(this);
		robotManager.setOnAfterCreatRobotListinner(this);
		commandManager = new CommandManager(this);
		world.addCommandeListiner(this);
		world.open();
	}
	
	@Override
	public Morph getRobot(String nom) {
		return robotManager.getRobot(nom);
	}
	
	private boolean isInstruction(String inp) {
		return ConditionInstruction.isConditionelInstruction(inp) || OperationsArithmetiques.isOperationArithmetique(inp);
	}

	/**
	 * Traitement de la commande saisie
	 */
	@Override
	public void onCommandeSaisie(String inp) {
		String result = null;
		// Si nous ne somme pas en cour de création d'une macro
		// et que la commande est une instruction
		if(!commandManager.enCourCreationMacro() && isInstruction(inp)){
			// Si c'est une instruction onditionnelle
			if (ConditionInstruction.isConditionelInstruction(inp)) {
				ConditionInstruction instruction = new ConditionInstruction(inp);
				result = instruction.runInstruction(commandManager);
			}else {// Si c'est une instruction arithmetique
				result = commandManager.execArithmiticInstruction(inp);
			}
		}
		// Si la commande commence par "new" ou "delete"
		else if(inp.startsWith("new") || inp.startsWith("delete")) {
			robotManager.execCommand(inp);
		}
		// Si aucun des cas précédent alors c'est une "simple" commande
		else {
			result = commandManager.execCommand(inp);
			world.repaint();
		}
		System.out.println(result);
	}
	
	// Callback après suppression d'un Morph
	@Override
	public void afterDeleteRobot(Morph robot) {
		commandManager.deleteMacroOfRobot(robot);
		world.removeMorph(robot);
		world.repaint();
	}

	// Callback après creation d'un Morph
	@Override
	public void afterCreatRobot(Morph robot) {
		if(robot.getContainer() == null) {
			world.addMorph(robot);
		}
		robot.setPrintEtat(world::afficheEtat);
		world.repaint();
	}

/* ---------------------------------------------------------------------- */
	public static void main(String args[]) {
		Exercice7_8_9_10_11 exo = new Exercice7_8_9_10_11();
		exo.run();
	}



}
