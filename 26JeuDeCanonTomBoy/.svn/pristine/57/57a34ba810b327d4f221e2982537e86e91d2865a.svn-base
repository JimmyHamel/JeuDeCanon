package objetsDessinable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import interfaces.Dessinable;
import utilite.SVector3d;
/**
 * Une classe qui permet de créer un objet rond avec un position en vecteur tridimensionel
 * ou en position traditionnelle.
 * 
 * @author Mitchell Mastromonaco
 * Date de création: 23 mars 2016
 */
public class Bullet implements Dessinable{
	private double rayon;
	private SVector3d position;
	private Ellipse2D.Double circle;
	private ArrayList<Point2D.Double> graphePosition = new ArrayList<Point2D.Double>();
	/**
	 * Constructeur permettant d'initialiser un objet de type Bullet avec une position traditionnelle.
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses pour l'objet de type Bullet
	 *            en m.
	 * @param posY
	 *            La position sur l'axe des ordonnées pour l'objet de type Bullet
	 *            en m.
	 * @param rayon Le rayon de l'objet de type Bullet en  m.
	 */
	public Bullet(double posX, double posY, double rayon){
		this.position = new SVector3d(posX, posY, 0);
		this.rayon = rayon;
		creerRepresentationGeometrique();
	}

	/**
	 * Constructeur permettant d'initialiser un objet de type Bullet avec une position en vecteur tridimensionnel.
	 * 
	 * @param position Le vecteur tridimensionnel qui détermine la position de l'objet.
	 * @param rayon Le rayon de l'objet de type Bullet en  m. 
	 */
	public Bullet(SVector3d position, double rayon){
		this.position = position;
		this.rayon = rayon;
		creerRepresentationGeometrique();
	}

	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matMC = new AffineTransform(mat);

		this.graphePosition.add(new Point2D.Double(position.getX(), position.getY()));

		g2d.fill(matMC.createTransformedShape(circle));
	}

	@Override
	public void creerRepresentationGeometrique() {
		circle = new Ellipse2D.Double(position.getX(), position.getY(), rayon, rayon);
	}

	/**
	 * Cette méthode retourne la valeur de la position sur l'axe des abscisses de la balle en m.
	 * 
	 * @return La position sur l'axe des abscisses de la balle en m.
	 */
	public double getPosX() {
		return position.getX();
	}

	/**
	 * Cette méthode fixe la valeur de la position sur l'axe des abscisses de la balle en m.
	 * 
	 * @param posX La nouvelle position sur l'axe des abscisses de la balle en m.
	 */
	public void setPosX(double posX) {
		this.position.setX(posX);
		this.creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la valeur de la position sur l'axe des ordonnées de la balle en m.
	 * 
	 * @return La position sur l'axe des ordonnées de la balle en m.
	 */
	public double getPosY() {
		return position.getY();
	}

	/**
	 * Cette méthode fixe la valeur de la position sur l'axe des ordonnées de la balle en m
	 * 
	 * @param posY La nouvelle position sur l'axe des ordonnées de la balle en m
	 */
	public void setPosY(double posY) {
		this.position.setY(posY);
		this.creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la valeur du rayon de la balle en m.
	 * 
	 * @return Le rayon de la balle en m.
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Cette méthode fixe la valeur du rayon de la balle en m.
	 * 
	 * @param rayon Le nouveau rayon de la balle en m.
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
		this.creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne le cerle qui compose l'objet de type Bullet.
	 * 
	 * @return Le cercle qui compose l'objet de type Bullet.
	 */
	public Ellipse2D.Double getCircle() {
		return circle;
	}

	/**
	 * Cette méthode fixe le cerle qui compose l'objet de type Bullet.
	 * 
	 * @param bullet Le nouveau cerle qui compose l'objet de type Bullet.
	 */
	public void setCircle(Ellipse2D.Double bullet) {
		this.circle = bullet;
		this.creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne le vecteur tridimensionnel de position de l'objet de type Bullet.
	 * 
	 * @return Le vecteur tridimensionnel de position de l'objet de type Bullet.
	 */
	public SVector3d getPosition() {
		return position;
	}

	/**
	 * Cette méthode fixe le vecteur tridimensionnel de position de l'objet de type Bullet.
	 * 
	 * @param position Le nouveau vecteur tridimensionnel de position de l'objet de type Bullet.
	 */
	public void setPosition(SVector3d position) {
		this.position = position;
		this.creerRepresentationGeometrique();
	}

	public ArrayList<Point2D.Double> getGraphePosition() {
		return graphePosition;
	}

	public void setGraphePosition(ArrayList<Point2D.Double> graphePosition) {
		this.graphePosition = graphePosition;
	}
}
