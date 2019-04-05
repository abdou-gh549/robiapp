package commands;

import graphicLayer.Morph;

/**
 * Retourne le resultat de la comparaison d'infériorité entre deux entiers.
 * @autor
 *
 */
public class Inferieur extends OperationsArithmetiques{

	public Inferieur(Morph target) {
		super(target);
	}

	@Override
	String calculResultat(int x, int y) {
		return String.valueOf(x < y);
	}
	
	@Override
	public String getName() {
		return "< ";
	}

}
