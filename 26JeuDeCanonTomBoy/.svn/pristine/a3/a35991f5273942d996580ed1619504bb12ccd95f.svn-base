package interfaces;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
/**
 * L'interface Dessinable implémente les méthodes pour n'importe quelle classe qui voudrait être dessiner.
 * @author Mitchell Mastromonaco
 * Date de création :  8 février 2016
 */
public interface Dessinable {
	/**
	 * Cette méthode permet à la classe de dessiner un objet dans un contexte de graphiques en deux dimensions et avec une certaine matrice de transformation.
	 * @param g2d Ce paramètre est le contexte graphique où l'objet d'une classe dessinable voudra être dessinée.
	 * @param mat Ce paramètre est toutes les transformations qui sont appliquées à l'objet qui implémente la classe Dessinable.
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat);
	/**
	 * Cette méthode crée une représentation géométrique de l'objet dessinable.
	 */
	public void creerRepresentationGeometrique();
}
