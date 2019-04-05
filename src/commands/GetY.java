package commands;

import graphicLayer.Morph;

/**
 * Retroune le résultat de l'appelle de la fonction dans Morph qui permet de récupérer
 * l'ordonnée d'un morph.
 * @author 
 *
 */
public class GetY extends Command{

	public GetY(Morph target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		return target.getY() + "";
	}

	@Override
	public String run(String[] args) {
		// TODO Auto-generated method stub
		return run();
	}
	@Override
	public String getName() {
		return "y";
	}
}
