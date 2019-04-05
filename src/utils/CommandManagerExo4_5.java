package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import commands.*;
import graphicLayer.Morph;
import tools.Tools;

/**
 * Class qui gère les commandes du fichier Exercice7_8_9_10_11.
 * @author
 *
 */
public class CommandManagerExo4_5 {
	Set<Command> commandeSet = new HashSet<Command>();

	RobotGetter robotGetter;

	public CommandManagerExo4_5(RobotGetter robotGetter) {

		this.robotGetter = robotGetter;
		
		intitMapCommande();
	}

	/**
	 * Initalise les commandes disponibles
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
	 * Elle sépare les arguments de la commande.
	 * @param inp Commante inscrite par l'utilisateur.
	 * @return Resultat de la commande.
	 */
	public String execCommand(String inp) {
		String[] cmd = Tools.tokenize(inp);
		return treatCommand(cmd);
	}
	
	
	public interface RobotGetter{
		public Morph getRobot(String nom);
	}

}
