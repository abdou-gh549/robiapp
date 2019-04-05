package app;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exercices.Exercice1;
import exercices.Exercice7_8_9_10_11;
import exercices.Exercice2;
import exercices.Exercice3;
import exercices.Exercice4_5;
import exercices.Exercice6;

/**
 * Application
 * @author
 *
 */
public class RobiApplication {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	        	JFrame robiAppFenetre = new JFrame("robi app");
	      		JPanel panel = new JPanel();
	      		
	      		robiAppFenetre.setContentPane(panel);
	      		robiAppFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      		robiAppFenetre.setLocationRelativeTo(null);
	      		panel.setLayout(new BorderLayout());
	      	    Box verticalBox = Box.createVerticalBox();
	      	    panel.add(verticalBox, BorderLayout.CENTER);
	      	    verticalBox.add(Box.createVerticalStrut(5));
	      	    addExercice(verticalBox, 1, "1");
	      	    addExercice(verticalBox, 2, "2");
	      	    addExercice(verticalBox, 3, "3");
	      	    addExercice(verticalBox, 4, "4_5");
	      	    addExercice(verticalBox, 6, "6");
	      	    addExercice(verticalBox, 11,"7_8_9_10_11");
	      	    
	      	    robiAppFenetre.pack();
	      	    robiAppFenetre.setVisible(true);
	          }
	      });
		
	}
	
	
	static void addExercice(Box box, int numExercice, String nom) {
		JButton exercieButton = new JButton("Exercice " + nom);
		exercieButton.addActionListener(e ->{
			switch (numExercice) {
			case 1:
				Exercice1 exo = new Exercice1();
				exo.run();
				
				break;
				
			case 2:
				Exercice2 exo2 = new Exercice2();
				exo2.run();
				
				break;		
			case 3:
				Exercice3 exo3 = new Exercice3();
				exo3.run();
	
				break;
						
			case 4:
				Exercice4_5 exo4_5 = new Exercice4_5();
				exo4_5.run();
	
				break;
			case 6:
				Exercice6 exo6 = new Exercice6();
				exo6.run();
	
				break;
			case 11:
				Exercice7_8_9_10_11 exercice7_8_9_10_11 = new Exercice7_8_9_10_11();
				exercice7_8_9_10_11.run();
			default:
				break;
			}
		});
		
		box.add(exercieButton);
		box.add(Box.createVerticalStrut(5));
	}
}
