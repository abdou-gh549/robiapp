package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import commands.*;
import graphicLayer.Morph;
import tools.Tools;

/**
 * Class qui gère les commandes du fichier Exercice6.
 * @author
 *
 */
public class CommandManagerExo6 {
	Set<Command> commandeSet = new HashSet<Command>();

	RobotGetter robotGetter;
	// true si la saisie est déstinée à la creation d'un macro sinon false
	private Boolean creationMacro = false;	
	// contien le nom de macro en cours de création sinon null
	String macroName ;
	// contien le macro en cours de création sinon null
	Macro macro;
	public CommandManagerExo6(RobotGetter robotGetter) {
		this.robotGetter = robotGetter;
		intitMapCommande();
	}

	/**
	 * Initalise les commandes disponibles.
	 */
	private void intitMapCommande() {
		this.commandeSet.add(new Left( null));
		this.commandeSet.add(new Up(null));
		this.commandeSet.add(new Right(null));
		this.commandeSet.add(new Down(null));
		this.commandeSet.add(new ColorChange(null));
	}
	
	/**
	 * Retroune la commande correspondant à la commande fournit en paramètre.
	 * @param commande Commande insérée par l'utilisateur.
	 * @return Command.
	 */
	public Command getCommandFromKeyCode(String commande) {
		List<Command> listCommande = commandeSet.stream().filter(c -> c.equals(commande) ).collect(Collectors.toList());
		if(listCommande.size() == 0)
			return null;
		else return listCommande.get(0);
	}
	
	/**
	 * Traitement d'une commande.
	 * @param cmd Commande insérée par l'utilisateur.
	 * @return Resultat de la commande.
	 */
	private String treatCommand(String [] cmd) {
		String result = null;
		try {
			// si la commande est un entier
			result = Integer.parseInt(cmd[0].trim()) + "";
		} catch(NumberFormatException e) {
			Morph robot = robotGetter.getRobot(null);
			Command command = getCommandFromKeyCode(cmd[0]); 
			if(command != null && robot !=null) {
				result =  command.exec(robot,  cmd);
			}
	    }
		return result;
	}
	
	/**
	 * Methode qui receptionne la Commande directement en provenance d'Exercice.
	 * Puis répartit selon le type de la commande.
	 * @param inp Commante inscrite par l'utilisateur.
	 * @return Resultat de la commande.
	 */
	public String execCommand(String inp) {
		String[] cmd = Tools.tokenize(inp);
		String firstArg = cmd[0];

		// Si l'utilisateur à commencé la saisie d'un macro
		String result = null;
		if(creationMacro) {
			creationDeMacro(cmd);
		}
		// Si l'uilisateur veut quitter l'application
		else if ("stop".equals(firstArg)) {
			System.exit(0);
		}
		// Si l'utilisateur commence la création d'un macro
		else if("(".equals(firstArg) ) {
			creationMacro = true;
			macro = new Macro(null);
			macro.setRobi(robotGetter.getRobot(null));
			result =  null;
		}else {//Si l'utilisateur a saisie une commande simple
			result = treatCommand(cmd);
		}
		return result;
	}
	
	/**
	 * Créer une nouvelle macro à partir de la commande fournit en paramètre.
	 * @param cmd Commande insérée par l'utilisateur.
	 */
	private void creationDeMacro(String[] cmd) {
		String firstArg = cmd[0];
		if(")".equals(firstArg )) {
			if( macroName != null && macro != null && ! macro.isEmpty()) {
				macro.setName(macroName);
				this.commandeSet.add( macro);
			}
			creationMacro = false;
			macroName = null;
			macro = null;		
		}
		else if(macroName == null) {
			macroName = firstArg;
		}else {
			Command command = getCommandFromKeyCode(firstArg);
			if(command != null) {
				macro.addCommand(command, cmd);
			}
		}
	}
	
	
	public interface RobotGetter{
		public Morph getRobot(String nom);
	}

}
