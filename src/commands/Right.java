package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de Morph qui déplace target vers la droite.
 * @author
 *
 */
public class Right extends Command{

	public Right(Morph target) {
		super(target);
	}

	@Override
	public String run() {
		target.moveRight(20);
		return "nil";
	}

	@Override
	public String run(String[] args) {
		if(args.length >= 2 &&"max".equals(args[1])) {
			target.moveRight(9999999);
			return "nil";
		}
		try {
			int distance = Integer.parseInt(args[1]);
			target.moveRight(distance);
		}catch(NumberFormatException exp) {
			System.out.println("command erronée !!");
		}
		return "nil";

	}
	@Override
	public String getName() {
		return "r";
	}
}
