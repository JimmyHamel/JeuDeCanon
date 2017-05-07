package composantsGraphiques;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import utilite.ModelePhysique;

/**
 * Graphique de la position de la balle
 * @author Jimmy
 * Date de création : 1 mars 2016
 */
public class Graphique extends JPanel{

	private static final long serialVersionUID = 1L;
	private ModelePhysique model;
	private final double LONGUEUR_MONDE_REEL = 200;
	private double hauteurMonde; 
	private boolean firstTime = true;
	private AffineTransform mat;
	private ArrayList<Point2D.Double> points;
	Color couleur;
	private Font fGrapes = new Font("Serif", Font.PLAIN, 10);

	/**
	 * Constructeur du graphique
	 */
	public Graphique(){
		couleur = Color.CYAN;
		setBackground(Color.WHITE);
		points = new ArrayList<Point2D.Double>();
	}

	/**
	 * Methode affichante le graphique
	 * @param g = composant graphique
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if(firstTime ){
			hauteurMonde = 151;
			model =  new ModelePhysique(getWidth(), getHeight(), 0, hauteurMonde, LONGUEUR_MONDE_REEL, true);
			mat = model.getMatMondeVersComposant();
		}
		for(int i = 0; i<=LONGUEUR_MONDE_REEL; i+=10){
			if(i==0)
				g2d.setStroke(new BasicStroke(10));
			else
				g2d.setStroke(new BasicStroke(1));
			g2d.draw(mat.createTransformedShape(new Line2D.Double(0, i, LONGUEUR_MONDE_REEL, i)));
			g2d.draw(mat.createTransformedShape(new Line2D.Double(i, 0, i, hauteurMonde)));
		}
		int y=(int)this.hauteurMonde;
		int x=0;
		g2d.setFont(fGrapes);
		g2d.setColor(new Color(200,200,200,250));
		for(int i=0; i<=getWidth(); i+=getWidth()/(this.LONGUEUR_MONDE_REEL/10)){
			if(y!=0)
				g2d.drawString(y+"", 5, i);
			if(x!=0)
				g2d.drawString(x+"", i, getHeight()-5);
			y-=10;
			x+=10;
		}

		if(points.size() >= 2){
			g2d.setColor(this.couleur);
			g2d.setStroke(new BasicStroke(3));
			Iterator<Point2D.Double> it = points.iterator();
			Point2D.Double pointDebut = it.next();
			Point2D.Double pointFin = it.next();
			g2d.draw(mat.createTransformedShape(new Line2D.Double(pointDebut, pointFin)));

			while(it.hasNext()){
				pointDebut = pointFin;
				pointFin = it.next();
				g2d.draw(mat.createTransformedShape(new Line2D.Double(pointDebut, pointFin)));
			}
		}

	}
/**
 * Methode modifiant la liste de coordonne
 * @param listePoint = nouvelle lsite de points pour le graphique
 */

	public void setArrayPoint(ArrayList<Double> listePoint){
		if(listePoint != null && listePoint.size() > 2){
		this.points = listePoint;
		repaint();
		}
	}
	/**
	 * Methode permettant de modifier la couleur du graphique
	 * @param nouvelleCouleur = la nouvelle couleur du graphique
	 */
	
	public void setCouleur(Color nouvelleCouleur){
		this.couleur = nouvelleCouleur;
		System.out.println();
	}


}
