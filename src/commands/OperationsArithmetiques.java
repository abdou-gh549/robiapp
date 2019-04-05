package commands;

import graphicLayer.Morph;

/**
 * Class abstraite de toutes les commandes arithmétiques.
 * Transforme les arguments d'une classe arithmétique en entier.
 * @author
 *
 */
abstract public class OperationsArithmetiques extends Command{

	public OperationsArithmetiques(Morph target) {
		super(target);
	}
	
	abstract String calculResultat(int x, int y);
	
	@Override
	public String run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String run(String[] args) {
		int x, y;
		String result = "nil";
		try {
			if(args[0].equals("nil")) {
				x = 0;
			}else {
				x = Integer.valueOf(args[0]);
			}
			if(args[1].equals("nil")) {
				y = 0;
			}else {
				y = Integer.valueOf(args[1]);
			}
			result = calculResultat(x, y);
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	static public boolean isOperationArithmetique(String inp ) {
		return inp.startsWith("< [" ) || inp.startsWith("+ [") || inp.startsWith("- [")  || inp.startsWith("> [")
				|| inp.startsWith("= [") ;
	}
	
	

}
