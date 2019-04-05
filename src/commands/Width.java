package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de Morph qui met à jour la largeur de target.
 * @author
 *
 */
public class Width extends Command {

	public Width(Morph target) {
		super(target);
	}

	@Override
	public String run() { return "nil";	}

	@Override
	public String run(String[] args) {
		if("max".equals(args[1])) {
			target.setWidth(9999999);
			
		}else
		{
			target.setWidth(Integer.parseInt(args[1]));
		}
		return "nil";
	}

	@Override
	public String getName() {
		return "w";
	}
}
