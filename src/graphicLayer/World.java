package graphicLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class World extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private List<Morph> drawables = new LinkedList<Morph>();
	private List<KeyListener> keyListeners = new LinkedList<KeyListener>();
	
	// Zone de saisie
	JTextField commandeTextField;
	// Zone d'historique des commandes
	JTextArea historiqueDesCommandes;
	JScrollPane scrollPan;
	private OnCommandeListiner onCommandeListiner;
	JPanel panel;
	
	String name = "";
	
	public World(String name, Dimension dim) {
		this.name = name;
		super.addKeyListener(this);
		this.setPreferredSize(dim);
		
	    panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
		panel.add(creatTextField(), BorderLayout.SOUTH);
		historiqueDesCommandes = new JTextArea();
		historiqueDesCommandes.setEditable(false);
		historiqueDesCommandes.setColumns(15);

		scrollPan = new JScrollPane(historiqueDesCommandes);
		panel.add(scrollPan, BorderLayout.EAST);
	}

	public List<Morph> contents() {
		return drawables;
	}

	public void open() {
		JFrame frame = new JFrame(name);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		};
		frame.addWindowListener(wa);
		frame.setContentPane(panel);

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		requestFocus();
	}

	public void addMorph(Morph d) {
		drawables.add(d);
		d.setWorld(this);
	}

	public void removeMorph(Morph d) {
		drawables.remove(d);
		d.setWorld(null);
		d.setContainer(null);
	}

	public void disableAdvanceInterface() {
		historiqueDesCommandes.setVisible(false);
		commandeTextField.setVisible(false);
		scrollPan.setVisible(false);
	}
	public void paint(Graphics g) {
		super.paint(g);
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			iter.next().draw(g);
		}
	}

	public void addKeyListener(KeyListener k) {
		keyListeners.add(k);
	}

	public void clear() {
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			iter.next().setWorld(null);
		}
		drawables.clear();
	}

	public List<Morph> find(Point p) {
		List<Morph> l = new ArrayList<Morph>();
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			Morph element = iter.next();
			if (element.getBounds().contains(p)) {
				l.add(element);
			}
		}
		return l;
	}
	
	/**
	 * Zone de saisie des commandes et mise à jour de l'historique des commandes
	 * @return commandeTextField
	 */
	private JTextField creatTextField()
    {
		commandeTextField= new JTextField();
		commandeTextField.setVisible(true);
		commandeTextField.addActionListener(e->{
			String cmd = commandeTextField.getText();
			commandeTextField.setText("");

			if(onCommandeListiner != null) {
				historiqueDesCommandes.append(cmd + "\n");
				onCommandeListiner.onCommandeSaisie(cmd);
			}
		});
		
        return commandeTextField;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyReleased(e);
		}
	}
	
	public void addCommandeListiner(OnCommandeListiner onCommandeListiner) {
		this.onCommandeListiner = onCommandeListiner;
	}
	
	public void afficheEtat(String etat) {
		historiqueDesCommandes.append(etat + "\n");
	}

}