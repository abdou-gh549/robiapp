package commands;

import graphicLayer.Morph;

/**
 * Appelle la fonction de Morph qui deplace target sur la gauche.
 * @author
 *
 */
public class Left extends Command{

	public Left(Morph target) {
		super(target);
	}

	@Override
	public String run() {
		target.moveLeft(20);
		return "nil";
	}

	@Override
	public String run(String[] args) {
		if(args.length >= 2 && "max".equals(args[1])) {
			target.moveLeft(9999999);
			return "nil";
		}
		//else
		try {
			int distance = Integer.parseInt(args[1]);
			target.moveLeft(distance);
		}catch(NumberFormatException exp) {
			System.out.println("command erronée !!");
		}
		return "nil";
	}
	@Override
	public String getName() {
		return "l";
	}
}
