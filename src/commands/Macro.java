package commands;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import graphicLayer.Morph;

/**
 * Contient une macro du robot morph. Le nom de la macro est stocker dans name.
 * @author
 *
 */
public class Macro extends Command{
	Map<Command, String[]> mapDesCommand = new LinkedHashMap<Command, String[]>();
	Morph robi;
	String name;
	
	public Macro(Morph target) {
		super(target);
	}
	/**
	 * la function invoque toutes les fonctions des commands stoquées dans le macro
	 */
	@Override
	public String run() {
		String result= "nil";
		if(robi != null && robi == target) {
		  Iterator<Map.Entry<Command, String[]>> it = mapDesCommand.entrySet().iterator();
		  while(it.hasNext()) {
			  Map.Entry<Command, String[]> pair = it.next();
			  result = pair.getKey().exec(robi, pair.getValue());
		  }
		}
		return result;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String run(String[] args) {
		return run();
	}

	/**
	 * 
	 * @param c la commande a exécuter dans le macro
	 * @param args les arguments de la commande c
	 */
	public void addCommand(Command c, String [] args) {
		mapDesCommand.put(c, args);
	}
	
	public void setRobi(Morph robi) {
		this.robi = robi;
	}
	
	public Morph getRobi() {
		return this.robi;
	}
	public boolean isEmpty() {
		return mapDesCommand.isEmpty();
	}
	@Override
	public String getName() {
		return name;
	}

	public boolean isCommand() { return false;}
	
	public boolean isMacro() { return true;}
}
