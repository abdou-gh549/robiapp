
package graphicLayer.demos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import graphicLayer.Morph;
import graphicLayer.Oval;
import graphicLayer.World;

public class Example1 {

	public static void main(String[] args) {
		Random x = new Random();
		World w = new World("Un essai de Morphs", new Dimension(800, 600));
		Dimension dim = new Dimension(5, 5);
		for (int i = 0; i < 2000; i++) {
			dim = new Dimension(x.nextInt(20), x.nextInt(20));
			w.addMorph(new Oval(new Color((int) (Math.random() * 0x1000000)), new Point(0, 0), dim));
		}
		w.open();
		while (true) {
			int worldWidth = (int) w.getWidth();
			int worldHeight = (int) w.getHeight();
			List<Morph> drawables = w.contents();
			for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
				iter.next().setPosition(new Point(x.nextInt(worldWidth), x.nextInt(worldHeight)));
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
