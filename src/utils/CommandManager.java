package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
public class CommandManager {
	Set<Command> commandeSet = new HashSet<Command>();
	// true si la saisie est déstinée à la creation d'un macro sinon false
	private Boolean creationMacro = false;
	// contien le nom de macro en cours de création sinon null
	String macroName ;
	// contien le macro en cours de création sinon null
	Macro macro;
	
	RobotGetter robotGetter;

	public CommandManager(RobotGetter robotGetter) {
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
		this.commandeSet.add( new Etat(null));
		this.commandeSet.add(new Width(null));
		this.commandeSet.add( new Height(null));
		this.commandeSet.add( new GetX(null));
		this.commandeSet.add( new GetY(null));
		this.commandeSet.add(new GetColor(null));
		this.commandeSet.add( new Addition(null));
		this.commandeSet.add( new Soustraction(null));
		this.commandeSet.add( new Inferieur(null));
		this.commandeSet.add( new Superieur(null));
		this.commandeSet.add( new Egalite(null));	
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
	 * Créer une nouvelle macro à partir de la commande fournit en paramètre.
	 * @param cmd Commande insérée par l'utilisateur.
	 */
	private void creationDeMacro(String[] cmd) {
		String firstArg = cmd[0];
		// Si éguale à ")" alors la macro est terminée.
		if(")".equals(firstArg )) {
			if( macroName != null && macro != null && ! macro.isEmpty() && macro.getRobi() != null) {
				macro.setName(macroName);
				this.commandeSet.add( macro);
			}
			creationMacro = false;
			macroName = null;
			macro = null;		
		}
		// Si le nom n'as pas encore été édité.
		else if(macroName == null) {
			macroName = firstArg;
		}else {
			// Stocke la commande dans la macro.
			Command command = getCommandFromKeyCode(firstArg);
			if(command != null) {
				macro.addCommand(command, cmd);
			}
		}
	}
	
	/**
	 * Traitement d'une commande.
	 * @param cmd Commande insérée par l'utilisateur.
	 * @return Resultat de la commande.
	 */
	private String treatCommand(String [] cmd) {
		String result = null;
		try {
			// Si la commande est un entier.
			result = Integer.parseInt(cmd[0].trim()) + "";
		} catch(NumberFormatException e) {
			Morph robot = robotGetter.getRobot(cmd[0]);
			Command command = getCommandFromKeyCode(cmd[1]); 
			if(command != null && robot !=null) {
				result =  command.exec(robot,  Arrays.copyOfRange(cmd, 1, cmd.length));
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
		else if("(".equals(firstArg) && cmd.length >= 2) {
			creationMacro = true;
			macro = new Macro(null);
			macro.setRobi(robotGetter.getRobot(cmd[1]));
			result =  null;
		}else {// Si l'utilisateur a saisie une commande simple
			result = treatCommand(cmd);
		}
		return result;
	}
	
	/**
	 * Methode pour supprimer les macros d'un robot fournit en paramètre.
	 * @param robi Robot dont on veut supprimer les macros.
	 */
	public void deleteMacroOfRobot(Morph robi) {
		Iterator<Command> it = commandeSet.iterator();
		while (it.hasNext()) {
			Command c= it.next();
			if(c.isMacro()) {
				Macro m = (Macro)c;
				if(m.getRobi().equals(robi)) {
					it.remove();
				}
			}
		}
	}
	
	public boolean enCourCreationMacro() {
		return creationMacro;
	}
	
	/**
	 * Récupère l'opérateur d'une instruction.
	 * @param inst Commande insérée par l'utilisateur.
	 * @return L'opérateur de la commande.
	 */
	private String getOperateurInstArith(String inst) {
		String op = "";
		for(int i = 0; i<inst.length(); i++) {
			if(inst.charAt(i) == '[') {
				break;
			}else {
				op += inst.charAt(i);
			}
		}
		return op;
	}
	 
	/**
	 * Execute une commande arithmetique.
	 * @param inp Commande arothmétique insérée par l'utilisateur.
	 * @return Résultat de la commande.
	 */
	public String execArithmiticInstruction(String inp) {
		// Isole la condition et l'instruction de la commande
		String[] listeInst = DecodeurInstruction.decodeInstructions(inp);
		String[] arguments = new String[2];
		int i = 0;
		// Execute la condition et l'instruction.
		for(String inst:listeInst) {
			String res = execCommand(inst);
			if(res == null) {
				return null;
			}
			arguments[i] = res;
			i++;
		}
		// inst decode et replacer par des valeur
		Command command = getCommandFromKeyCode(getOperateurInstArith(inp)); 
		if(command != null) {
			return command.exec(null, arguments);
		}
		else return null;
	}
	
	public interface RobotGetter{
		public Morph getRobot(String nom);
	}

}
