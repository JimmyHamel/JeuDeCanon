package utilite;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author Jimmy
 * Classe repr�sentant les diff�rent utilisateur du profil. Les objets 
 * profils contients une image, un chiffre repr�sentant le niveau ou l'utilisateur est rendu
 * et une chaine de caractere repr�sentant le nom du profil.
 * Date de cr�ation : 8 mars 2016
 * 
 */
public class Profil implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nom;
	private Image image;
	private int numeroImage;
	private int niveau;

	/**
	 * Constructeur de la classe profil
	 * @param nom = nom du profil
	 * @param niveau = niveau ou l'utilisateur est rendu
	 * @param numeroImage = numero de l'image de joueur choisie
	 */
	public Profil(String nom, int niveau,int numeroImage){
		this.nom = nom;
		this.niveau = niveau;
		this.numeroImage = numeroImage;
		readImage();
		this.image.getScaledInstance(this.image.getWidth(null),this.image.getHeight(null), Image.SCALE_SMOOTH);
	}
	/**
	 * Methode retournant le nom de la classe
	 * @return = nom du profil
	 */

	public String getNom() {
		return nom;
	}

	/**
	 * methode modifiant le nom de 
	 * @param nom = nouveau nom du profil
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Methode retournant l'image du profil
	 * @return = image du profil
	 */
	public Image getImage() {
		return image;
	}


	/**
	 * Methode retournant le niveau o� le profil est rendu
	 * @return = niveau ou le profil est rendu
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 *  Methode mofifiant le niveau ou le profil est rendu
	 * @param niveau = niveau ou le profil est rendu
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * Methode retournant le num�ro de l'image du joueur choisie
	 * @return Le num�ro de l'image du joueur choisi
	 */
	public int getIndexImage() {
		return this.numeroImage;
	}
	/**
	 * methode mofidifant Le num�ro de l'image de profil choisie
	 * @param indexImage Le num�ro de l'image de profil choisie
	 */
	public void setIndexImage(int indexImage) {
		this.numeroImage = indexImage;
	}

	/**
	 *  Methode lisant l'image du joueur choisie
	 */
	public void readImage(){
		URL fich = getClass().getClassLoader().getResource("imageJoueur" +this.numeroImage +  ".jpg"); 
		try {
			this.image =  ImageIO.read(fich);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
