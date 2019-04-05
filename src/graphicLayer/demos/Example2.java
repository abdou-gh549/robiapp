
package graphicLayer.demos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphicLayer.Morph;
import graphicLayer.Oval;
import graphicLayer.World;

public class Example2 {

	public static void main(String[] args) {
		World w = new World("Un essai de Morphs", new Dimension(800, 600));
		Morph container = new Morph(Color.white, new Point(30,30), new Dimension(400,300));
		w.addMorph(container);
		Morph subContainer = new Morph(Color.red, new Point(50,50), new Dimension(100,100));
		container.addSubmorph(subContainer);
		Morph insideMorph1 = new Morph(Color.gray, new Point(50,50), new Dimension(40,30));
		Morph insideMorph2 = new Morph(Color.red, new Point(360,270), new Dimension(40,30));
		subContainer.addSubmorph(insideMorph1);
		container.addSubmorph(insideMorph2);
		w.addMorph(new Oval(Color.yellow, insideMorph1.getGlobalPosition(), new Dimension(40,30)));
		w.addMorph(new Oval(Color.yellow, insideMorph2.getGlobalPosition(), new Dimension(40,30)));
		w.open();
	}

}
