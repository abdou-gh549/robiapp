package commands;

import graphicLayer.Morph;

/**
 * Retourne le resultat de la soustraction entre deux entier.
 * @author
 *
 */
public class Soustraction extends OperationsArithmetiques{

	public Soustraction(Morph target) {
		super(target);
	}

	@Override
	String calculResultat(int x, int y) {
		return String.valueOf(x - y);
	}
	@Override
	public String getName() {
		return "- ";
	}
}
