package commands;

import graphicLayer.Morph;

/**
 * Retourne le resultat du test de supériorité entre deux entiers.
 * @author
 *
 */
public class Superieur extends OperationsArithmetiques{

	public Superieur(Morph target) {
		super(target);
	}

	@Override
	String calculResultat(int x, int y) {
		return String.valueOf(x > y);
	}

	@Override
	public String getName() {
		return "> ";
	}
	
}
