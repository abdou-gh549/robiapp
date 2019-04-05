package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de Morph qui met à jour la hauteur de target.
 * @author
 *
 */
public class Height extends Command {

	public Height(Morph target) {
		super(target);
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		return "nil";
	}

	@Override
	public String run(String[] args) {
		// TODO Auto-generated method stub
		if("max".equals(args[1])) {
			target.setHeight(9999999);
		}
		else 
		{
			target.setHeight(Integer.parseInt(args[1]));
		}
		return "nil";
	}
	
	@Override
	public String getName() {
		return "h";
	}

}
