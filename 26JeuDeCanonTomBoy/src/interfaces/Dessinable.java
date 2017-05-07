package interfaces;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
/**
 * L'interface Dessinable impl�mente les m�thodes pour n'importe quelle classe qui voudrait �tre dessiner.
 * @author Mitchell Mastromonaco
 * Date de cr�ation :  8 f�vrier 2016
 */
public interface Dessinable {
	/**
	 * Cette m�thode permet � la classe de dessiner un objet dans un contexte de graphiques en deux dimensions et avec une certaine matrice de transformation.
	 * @param g2d Ce param�tre est le contexte graphique o� l'objet d'une classe dessinable voudra �tre dessin�e.
	 * @param mat Ce param�tre est toutes les transformations qui sont appliqu�es � l'objet qui impl�mente la classe Dessinable.
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat);
	/**
	 * Cette m�thode cr�e une repr�sentation g�om�trique de l'objet dessinable.
	 */
	public void creerRepresentationGeometrique();
}
