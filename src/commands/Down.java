package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de morph qui déplace le morph vers le bas.
 * @author
 *
 */
public class Down extends Command{
	
	public Down(Morph target) {
		super(target);
	}
	
	@Override
	public String run() {
		target.moveDown(20);
		return "nil";
	}

	@Override
	public String run(String[] args) {
		if(args.length >= 2 && "max".equals(args[1])) {
			target.moveDown(99999999);
			return "nil";
		}
		//else
		try {
			int distance = Integer.parseInt(args[1]);
			target.moveDown(distance);
		}catch(NumberFormatException exp) {
			System.out.println("command erroné !!");
		}
		return "nil";
	}
	@Override
	public String getName() {
		return "d";
	}

}
