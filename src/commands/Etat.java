package commands;

import graphicLayer.Morph;

/**
 * Retourne le résultat de l'appelle la fonction qui fournit l'etat de target.
 * @author
 *
 */
public class Etat extends Command{

	public Etat(Morph target) {
		super(target);
	}
	@Override
	public String run() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String run(String[] args) {
		return target.getEtat();
	}

	@Override
	public String getName() {
		return "e";
	}
}
