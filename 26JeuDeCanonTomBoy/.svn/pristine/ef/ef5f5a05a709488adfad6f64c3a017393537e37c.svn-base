package objetsDessinable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import interfaces.Dessinable;

/**
 * La classe Body est une super-classe utilisée pour les objets de type
 * Ennemies, Joueur et n'importe quel objet qui veut se dessiner comme étant un carré avec une position et des valeurs réelles de longueur et largeur.
 * Elle permet aussi d'avoir des images rectangulaires avec
 * une boîte de collision.
 * 
 * @author Mitchell Mastromonaco 
 * Date de création : 8 février 2016
 */
public class Body implements Dessinable {

	private Image image = null;
	
	private Rectangle2D.Double boiteDeCollision, carre;
	private double posX, posY, longueurReelle, largeurReelle;

	/**
	 * Le constructeur de Body. Lorsque le Body est prêt. Ce constructeur
	 * n'utilise pas de fichier Image pour dessiner l'objet.
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses pour l'objet de type Body
	 *            en m.
	 * @param posY
	 *            La position sur l'axe des ordonnées pour l'objet de type Body
	 *            en m.
	 * @param longueurReelle
	 *            La longueur réelle pour l'objet de type Body en m.
	 * @param largeurReelle
	 *            La largeur réelle pour l'objet de type Body en m.
	 */
	public Body(double posX, double posY, double longueurReelle, double largeurReelle) {
		this.posX = posX;
		this.posY = posY;
		this.longueurReelle = longueurReelle;
		this.largeurReelle = largeurReelle;
		creerRepresentationGeometrique();
		
	
	}

	/**
	 * Cette méthode permet à la classe de dessiner un objet dans un contexte de
	 * graphiques en deux dimensions et avec une certaine matrice de
	 * transformation.
	 * 
	 * @param g2d
	 *            Ce paramètre est le contexte graphique où l'objet d'une classe
	 *            dessinable voudra être dessinée.
	 * @param mat
	 *            Ce paramètre est toutes les transformations qui sont
	 *            appliquées à l'objet qui implémente la classe Dessinable.
	 */
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matMC;
		if(mat!=null)
			matMC = new AffineTransform(mat);
		else 
			matMC = null;
		if (image != null) {
			g2d.drawImage(image, (int)this.posX, (int)this.posY, (int)this.longueurReelle, (int)this.largeurReelle, null);
			
		} else
			g2d.fill(matMC.createTransformedShape(carre));
	}
	
	/**
	 * Cette méthode crée une représentation géométrique de l'objet dessinable.
	 */
	@Override
	public void creerRepresentationGeometrique() {
		if (image != null)
			image = (BufferedImage) image.getScaledInstance((int) longueurReelle, (int) (largeurReelle), Image.SCALE_SMOOTH);
		else
			carre = new Rectangle2D.Double(posX, posY, this.longueurReelle, this.largeurReelle);
		this.boiteDeCollision = new Rectangle2D.Double(posX, posY, this.longueurReelle, this.largeurReelle);
	}

	/**
	 * Cette méthode retourne la valeur de la position sur l'axe des abscisses
	 * de l'objet de type Body en m.
	 * 
	 * @return La position sur l'axe des abscisses de l'objet de type Body en m.
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * Cette méthode fixe la valeur de la position sur l'axe des abscisses de
	 * l'objet de type Body en m.
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses de l'objet de type Body en
	 *            m.
	 */
	public void setPosX(double posX) {
		this.posX = posX;
		creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la valeur de la position sur l'axe des ordonnées
	 * de l'objet de type Body en m.
	 * 
	 * @return La position sur l'axe des ordonnées de l'objet de type Body en m.
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * Cette méthode fixe la valeur de la position sur l'axe des ordonnées de
	 * l'objet de type Body en m.
	 * 
	 * @param posY
	 *            La position sur l'axe des ordonnées de l'objet de type Body en
	 *            m.
	 */
	public void setPosY(double posY) {
		this.posY = posY;
		creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la boîte de collision de l'objet de type Body.
	 * 
	 * @return La boîte de collision de l'objet de type Body.
	 */
	public Rectangle2D.Double getBoiteDeCollision() {
		return boiteDeCollision;
	}

	/**
	 * Cette méthode fixe la boîte de collision de l'objet de type Body.
	 * 
	 * @param boiteDeCollision
	 *            La boîte de collision de l'objet de type Body.
	 */
	public void setBoiteDeCollision(Rectangle2D.Double boiteDeCollision) {
		this.boiteDeCollision = boiteDeCollision;
	}

	/**
	 * Cette méthode retourne la représentation graphique de l'objet de type
	 * Body.
	 * 
	 * @return La représentation graphique de l'objet de type Body.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Cette méthode fixe la représentation graphique de l'objet de type Body.
	 * 
	 * @param image
	 *            La représentation graphique de l'objet de type Body.
	 */
	public void setImage(Image image) {
		this.image = image;
		creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la valeur de la longueur réelle pour l'objet de
	 * type Body en m.
	 * 
	 * @return La longueur réelle pour l'objet de type Body en m.
	 */
	public double getLongueurReelle() {
		return longueurReelle;
	}

	/**
	 * Cette méthode fixe la valeur de la longueur réelle pour l'objet de type
	 * Body en m.
	 * 
	 * @param longueurReelle
	 *            La longueur réelle pour l'objet de type Body en m.
	 */
	public void setLongueurReelle(double longueurReelle) {
		this.longueurReelle = longueurReelle;
		creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode retourne la valeur de la largeur réelle pour l'objet de
	 * type Body en m.
	 * 
	 * @return La largeur réelle pour l'objet de type Body en m.
	 */
	public double getLargeurReelle() {
		return largeurReelle;
	}

	/**
	 * Cette méthode fixe la valeur de la largeur réelle pour l'objet de type
	 * Body en m.
	 * 
	 * @param largeurReelle
	 *            La largeur réelle pour l'objet de type Body en m.
	 */
	public void setLargeurReelle(double largeurReelle) {
		this.largeurReelle = largeurReelle;
		creerRepresentationGeometrique();
	}
	
	/**
	 * Cette méthode retourne le rectangle qu'affiche la classe Body.
	 * 
	 * @return Le rectangle qu'affiche la classe Body.
	 */
	public Rectangle2D.Double getCarre() {
		return carre;
	}

	/**
	 * Cette méthode fixe le rectangle qu'affiche la classe Body.
	 * 
	 * @param carre Le nouveau carré qu'affichera la classe Body
	 */
	public void setCarre(Rectangle2D.Double carre) {
		this.carre = carre;
	}
	
	/**
	 * Une méthode  permettant de rechercher dans le fichier ressources un
	 * objet de type Image.
	 * 
	 * @param fileName = Le nom de l'image recherchée dans le fichier ressources.
	 * 
	 */
	public  void loadImage(String fileName) {
		URL fich = getClass().getClassLoader().getResource(fileName + ".jpg"); 
		
		if (fich == null) {
			JOptionPane.showMessageDialog(null, "Fichier d'image introuvable!");
		} else {
			try {
				this.image =  ImageIO.read(fich);
			} catch (IOException e) {
				System.out.println("Erreur pendant la lecture du fichier d'image");
			}
		}

	}

}
