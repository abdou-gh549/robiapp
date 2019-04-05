package commands;

import graphicLayer.Morph;

/**
 * Retourne le résultat de la somme de deux entiers.
 * @author
 *
 */
public class Addition extends OperationsArithmetiques{

	public Addition(Morph target) {
		super(target);
	}

	@Override
	String calculResultat(int x, int y) {
		return String.valueOf(x + y);
	}

	@Override
	public String getName() {
		return "+ ";
	}

}
