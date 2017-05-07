package objetsDessinable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

/**
 * Cette classe sert de bouton personnaliser. Ainsi, elle peut representer
 *  plusieurs etats d'une image et contient une zone d'image non afficher
 * @author Jimmy
 * date de création : 26 mars 2016
 *
 */
public class BoutonPersonnalise implements Dessinable {
	
	Body bouton;
	String nom;
	String etat;
	/**
	 * Constructeur de la classe BoutonPersonnalise
	 * @param x = position en x du bouton
	 * @param y = position en y du bouton
	 * @param longueur = longueur du bouton
	 * @param largeur = largeur du bouton
	 * @param nomBouton = nom du bouton
	 * @param etatIni = etat du bouton
	 */
	
	public BoutonPersonnalise(double x, double y, double longueur, double largeur,String nomBouton, String etatIni){
		this.bouton = new Body(x,y,longueur,largeur);
		this.nom = nomBouton;
		this.etat = etatIni;
		creerRepresentationGeometrique();
	}
	/**
	 * Deuxieme constructeur sans état initial
	 * @param x = position en x du bouton
	 * @param y = position en y du bouton
	 * @param longueur = longueur du bouton
	 * @param largeur = largeur du bouton
	 * @param nomBouton = nom du bouton
	 */
	public BoutonPersonnalise(double x, double y, double longueur, double largeur,String nomBouton){
		this.bouton = new Body(x,y,longueur,largeur);
		this.nom = nomBouton;
		this.etat = "";
		creerRepresentationGeometrique();
	}
	/**
	 * Methode permettant l'ajout ou le changement de l'image du bouton personnalise
	 */
	
	public void updateBoutonImage(){
		this.bouton.loadImage(this.nom + this.etat);
	}

	/**
	 * Methode permettant de dessiner le bouton
	 */
	
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		this.bouton.dessiner(g2d, mat);
	}
	/**
	 * Methode permettant de donner l'image initiale du bouton
	 */

	/**
	 * Methode initalisant l'image du bouton
	 */
	@Override
	public void creerRepresentationGeometrique() {
		updateBoutonImage();
	}
	/**
	 * Methode retournant la zone ou le bouton se trouve
	 * @return = zone ou le bouton se trouve
	 */

	public Rectangle2D.Double getBoiteDeCollision(){
		return this.bouton.getBoiteDeCollision();
	}
	
	/**
	 * Methode changeant l'etat du bouton
	 * @param nouvelEtat =  nouvel etat du bouton
	 */
	
	
	public void setEtat(String nouvelEtat){
		this.etat = nouvelEtat;
		updateBoutonImage();
	}
	/**
	 * Methode retournant le nom du bouton
	 * @return = nom du bouton
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Methode modifiant le nom du bouton
	 * @param nom = nouveau nom pour le bouton
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
