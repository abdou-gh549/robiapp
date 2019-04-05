package commands;

import graphicLayer.Morph;
import tools.Tools;

/**
 * Appelle la fonction de Morph qui modifie la couleur d'un morph
 * @author
 *
 */
public class ColorChange extends Command{
	
	public ColorChange(Morph target) {
		super(target);
	}

	@Override
	public String run() {
		target.randomColor();
		return "nil";
	}

	@Override
	public String run(String[] args) {
		String res = "nil";
		if(args.length == 1) {
			res = run();
		}else if(args.length == 2) {
			target.setColor(Tools.getColorByName(args[1]));
			res = args[1];
		}
		return res;
	}
	@Override
	public String getName() {
		return "c";
	}
	
	
}
