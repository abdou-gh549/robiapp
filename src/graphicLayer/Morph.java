

package graphicLayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tools.Observable;
import tools.Observer;

public class Morph implements Observable {
	protected Morph container;
	protected World world;
	protected Rectangle bounds;
	protected Color color;
	protected List<Morph> submorphs = new ArrayList<Morph>();
	protected List<Observer> observers = new ArrayList<Observer>();

	public Morph(Color color, Point pos, Dimension dim) {
		this(pos, dim);
		this.color = color;
	}
	
	public Morph(Point pos, Dimension dim) {
		this(dim);
		setPosition(pos);
	}
	public Morph(Dimension dim) {
		this.bounds = new Rectangle(dim);
	}
	
	public Morph() {
		this.bounds = new Rectangle(0,0, 20,20);
		this.color = Color.blue;
	}
	
	public List<Morph> getListSubMorph(){
		return submorphs;
	}
	public void setWorld(World w) {
		world = w;
	}

	public Morph getContainer() {
		return container;
	}
	public void setContainer(Morph aContainer) {
		container = aContainer;
	}
	
	public void setColor (Color c) {
		color = c;
		if (world != null)
			world.repaint();		
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
		g.setColor(c);
		if (submorphs.isEmpty()) {
			return;
		}
		Shape currentClip = g.getClipBounds();
		g.setClip(getX(), getY(), getWidth(), getHeight());
		g.translate(getX(), getY());
		Iterator<Morph> itor = submorphs.iterator();
		while (itor.hasNext()) {
			Morph m = itor.next();
			m.draw(g);
		}
		g.translate(-getX(), -getY());
		g.setClip(currentClip);
	}

	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}
	public Rectangle getGlobalBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * Mise à jour de la longueur d'un robot
	 * @param width nouvelle longueur du robot
	 */
	public void setWidth(Integer width) {
		// Si la nouvelle longueur est supérieur à la distance qui sépare son abscisse du coter droit de son conteneur
		if(width + getX() > getContainerWidth() ) {
			this.setBounds(new Rectangle(getX(), getY(), (getContainerX() + getContainerWidth()) - getX() , this.getHeight()));
		}
		else{
			this.setBounds(new Rectangle(getX(), getY(), width, this.getHeight()));
		}
	}
	
	/**
	 * Mise à jour de la largeur d'un robot
	 * @param height nouvelle longueur du robot
	 */
	public void setHeight(Integer height) {
		// Si la nouvelle largeur est supérieur à la distance qui sépare son ordonnée du coter bas de son conteneur
		if(height + getY() > getContainerHeight()) {
			this.setBounds(new Rectangle(getX(), getY(), this.getWidth(), (getContainerY() + getContainerHeight()) - getY()));
		}
		else{
			this.setBounds(new Rectangle(getX(), getY(), this.getWidth(), height));
		}
	}

	public void addSubmorph(Morph m) {
		if (submorphs.contains(m))
			return;
		submorphs.add(m);
		m.setContainer(this);
		m.setWorld(world);
	}

	public Point getPosition() {
		Point p = bounds.getLocation();
		return p;
	}
	
	public Point getGlobalPosition() {
		Point p = new Point(bounds.getLocation());
		if (container == null) {
			return p;
		}
		p.translate(container.getGlobalPosition().x, container.getGlobalPosition().y);
		return p;
	}

	public void setPosition(Point p) {
		bounds.x = p.x;
		bounds.y = p.y;
		if (world != null)
			world.repaint();
		notifyObservers();	
	}

	public int getWidth() {
		return (int) bounds.getWidth();
	}
	public int getHeight() {
		return (int) bounds.getHeight();
	}
	
	public void setX(int x) {
		Point p = getPosition();
		setPosition(new Point(x, p.y));
	}

	public int getX() {
		return (getPosition().x);
	}

	public void setY(int y) {
		Point p = getPosition();
		setPosition(new Point(p.x, y));
	}

	public int getY() {
		return (getPosition().y);
	}

	/**
	 * Déplacement vers la droite du robot
	 * @param gap distance en pixel sur laquelle il vas se déplacer
	 */
	public void moveRight(int gap) {
		// Si la distance met le robot en dehors du conteneur
		if ( ! (this.getX() + gap + this.getWidth() > getContainerWidth() +  getContainerX())) {
	 		setX(getX() + gap);
 		}else {
 			setX(getContainerWidth() - getWidth());
 		}
		
	}

	/**
	 * Déplacement vers la gauche du robot
	 * @param gap distance en pixel sur laquelle il vas se déplacer
	 */
	public void moveLeft(int gap) {
		// Si la distance met le robot en dehors du conteneur
		if( getX() - gap > getContainerX()) {
			setX(getX() - gap);
    	}else {
    		setX( 0 );
    	}
	}

	/**
	 * Déplacement vers le haut du robot
	 * @param gap distance en pixel sur laquelle il vas se déplacer
	 */
	public void moveUp(int gap) {
		// Si la distance met le robot en dehors du conteneur
		if(! (getY() - gap < getContainerY())) {
			setY(getY() - gap);
    	}else {
    		setY( 0 );
    	}
	}

	/**
	 * Déplacement vers le bas du robot
	 * @param gap distance en pixel sur laquelle il vas se déplacer
	 */
	public void moveDown(int gap) {
		// Si la distance met le robot en dehors du conteneur
		if(! (getY() + gap+ getHeight() > getContainerHeight() + getContainerY())) {
			setY(getY() + gap);
    	}else {
    		setY(getContainerHeight() - getHeight());
    	}
	}
	

	@Override
	public void notifyObservers() {
		if (observers == null) return;
		Iterator<Observer> itor = observers.iterator();
		while (itor.hasNext()) {
			itor.next().updateFrom(this);
		}
	}

	@Override
	public void register(Observer obs) {
		if (! observers.contains(obs)) observers.add(obs);
	}

	@Override
	public void unregister(Observer obs) {
		if (observers.contains(obs)) 
			observers.remove(obs);	
	}
	
	/**
	 * Fonction qui renvoie la largeur du conteneur
	 * @return Integer
	 */
	public int getContainerWidth() {
		return (container == null) ? world.getWidth() : container.getWidth() ;
	}
	/**
	 * Fonction qui renvoie la longueur du conteneur
	 * @return Integer
	 */
	public int getContainerHeight() {
		return (container == null) ? world.getHeight() : container.getHeight() ;
	}

	/**
	 * Fonction qui renvoie la l'abscisse du conteneur
	 * @return Integer
	 */
	public int getContainerX() {
		return (container == null) ? world.getX() : container.getX() ;
	}
	
	/**
	 * Fonction qui renvoie la l'ordonnée du conteneur
	 * @return Integer
	 */
	public int getContainerY() {
		return (container == null) ? world.getY() : container.getY() ;
	}
	
	/**
	 * Retourne la couleur 
	 * @return String
	 */
	public String getColor() {
		return String.valueOf(color.getRGB());
	}

	/**
	 * Fonction qui réscupére les données d'un robot
	 * @return String
	 */
	public String getEtat () {
		String etat= null;
		etat = "x=" + bounds.x + " y=" + bounds.y + " b=" + color.getBlue() + " r=" + color.getRed() + " g=" + color.getGreen();
		return etat;
	}
	
	PrintEtat printEtat;
	public void setPrintEtat(PrintEtat printEtat) {
		this.printEtat = printEtat;
	}
	
	/**
	 * Fonction qui affiche les données d'un robot en utilisant l'interface setPrintEtat
	 */
	public void afficherEtat() {
		if(printEtat != null) {
			printEtat.afficheEtat(getEtat());
		}
	}
	
	/**
	 * Fonction qui attribue à un robot une couleur aléatoire
	 */
	public void randomColor() {
		setColor(new Color ((int) (Math.random() * 0x1000000)));
	}

	public interface PrintEtat{
		void afficheEtat(String s);
	}
	
}
