package commands;

import graphicLayer.Morph;

/**
 * Retourne le résultat de l'égaliter de deux entier.
 * @author
 *
 */
public class Egalite extends OperationsArithmetiques{

	public Egalite(Morph target) {
		super(target);
	}

	@Override
	String calculResultat(int x, int y) {
		return String.valueOf(x == y);
	}

	@Override
	public String getName() {
		return "= ";
	}
}
