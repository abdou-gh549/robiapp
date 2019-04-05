package commands;

import graphicLayer.Morph;

/**
 * Retourne le résultat de l'appelle de la fonction de Morph 
 * qui fournis la couleur de target.
 * @author
 *
 */
public class GetColor extends Command {

	public GetColor(Morph target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		return target.getColor();
	}

	@Override
	public String run(String[] args) {
		// TODO Auto-generated method stub
		return run();
	}
	@Override
	public String getName() {
		return "color";
	}

}
