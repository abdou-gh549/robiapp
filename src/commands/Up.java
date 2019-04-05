package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de Morph qui deplace le morph vers le haut.
 * @author
 *
 */
public class Up extends Command{
	public Up(Morph target) {
		super(target);
	}

	@Override
	public String run() {
		target.moveUp(20);
		return "nil";
	}

	@Override
	public String run(String[] args) {
		if(args.length >= 2 &&"max".equals(args[1])) {
			target.moveUp(2999990);
			return "nil";
		}
		try {
			int distance = Integer.parseInt(args[1]);
			target.moveUp(distance);
		}catch(NumberFormatException exp) {
			System.out.println("command erronée !!");
		}
		return "nil";
	}
	@Override
	public String getName() {
		return "u";
	}

}
