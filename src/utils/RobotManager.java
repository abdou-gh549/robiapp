package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import graphicLayer.Morph;
import tools.Tools;

/**
 * Class qui gère les robots.
 * @author
 *
 */
public class RobotManager {
	Map<String, Morph> mapDesRobi = new HashMap<String, Morph>(); 
	
	AfterDeleteRobotListinner afterDeleteRobotListinner;
	AfterCreatRobotListinner afterCreatRobotListinner;

	
	public void setOnAfterDeletRobotListinner(AfterDeleteRobotListinner afterDeleteRobotListinner) {
		this.afterDeleteRobotListinner = afterDeleteRobotListinner;
	}
	
	public void setOnAfterCreatRobotListinner(AfterCreatRobotListinner afterCreatRobotListinner) {
		this.afterCreatRobotListinner = afterCreatRobotListinner;
	}

	/**
	 * Créer et initialise un robot.
	 * @param args talbeau de string qui contiens la commande de creation d'un robot.
	 */
	public void creatRobi(String[] args) {
		// si le robot déja exist ne fait rien 
		if(mapDesRobi.containsKey(args[1])) {
			System.out.println("Robi déjà existant");
			return;
		}
		// si le conteneur est défini ajoute le robot dans le conteneur, sinon dans world
		Morph robi = new Morph();
		if(args.length >= 3 && mapDesRobi.containsKey(args[2])) {
			mapDesRobi.get(args[2]).addSubmorph(robi);
			robi.setContainer(mapDesRobi.get(args[2]));
		}
		mapDesRobi.put(args[1], robi);
		afterCreatRobotListinner.afterCreatRobot(robi);
		
		System.out.println(args[1] + "est en (" +robi.getX() + " , " + robi.getY() + " )");
	}

	/**
	 * Fonction appellée pour supprimer un Morph à partir de son nom.
	 * @param nom La clé de robot à supprimer.
	 */
	public void suppRobi(String nom) {

		if(mapDesRobi.containsKey(nom)) {
			Morph robi = mapDesRobi.get(nom);
			deleteMorph( robi);
		}else
			System.out.println("Robot inexistant");
	}
	
	/**
	 * Supprime un robot et ses fils( subMorph ).
	 * @param robi Robot que l'on veut supprimer.
	 */
	private void deleteMorph(Morph robi) {
		List<Morph> listrobi = robi.getListSubMorph();
		
		Iterator<Morph> it = listrobi.iterator();
		while(it.hasNext()) {
			Morph m = it.next();
			//set container null pour eviter la suppression de container apres
			m.setContainer(null);
			deleteMorph(m);
			// sup morph de la list des sub Morph
			it.remove();
		}

		Morph container = robi.getContainer();
		if(container != null)
			container.getListSubMorph().remove(robi);
		
		mapDesRobi.values().remove(robi);

		if(this.afterDeleteRobotListinner != null) {
			this.afterDeleteRobotListinner.afterDeleteRobot(robi);
		}
	}
	
	public Morph getRobot(String nomRobot) {
		return mapDesRobi.get(nomRobot);
	}

	public void execCommand(String inp) {
		String[] cmd = Tools.tokenize(inp);
		String firstArg = cmd[0];
		// Si l'utilisateur créer un nouveau robot
		if("new".equals(firstArg)) {
			creatRobi(cmd);
		}
		// Si l'utilisateur veut supprimer un robot
		else if("delete".equals(firstArg )) {
			suppRobi(cmd[1]);
		}
	}
	
	public interface AfterDeleteRobotListinner {
		public void afterDeleteRobot(Morph robot);
	}
	public interface AfterCreatRobotListinner {
		public void afterCreatRobot(Morph robot);
	}
}
