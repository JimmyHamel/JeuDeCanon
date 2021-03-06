package objetsDessinable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import interfaces.Dessinable;
import utilite.Profil;

/**
 * Objet dessinable repr�sentant le menu principale avec 4 boutons.
 * @author Jimmy
 * Date de cr�ation : 8 f�vrier 2016
 */
public class Menu implements Dessinable {
	private BoutonPersonnalise[] listeBoutons;
	private Profil profilCourant;
	/**
	 * constructeur de la classe menu
	 */

	public Menu() {
		this.listeBoutons = new BoutonPersonnalise[4];
		this.creerRepresentationGeometrique();
		profilCourant = new Profil("Default",14,1);
	}
	/**
	 * Methode dessinant l'objet menue
	 * @param g2d = composant graphique
	 * @param mat = matrice de transformation en onde r�elle
	 */

	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		for (BoutonPersonnalise k : this.listeBoutons) {
			k.dessiner(g2d, mat);
		}
		g2d.drawString("Profil : " + this.profilCourant.getNom(), 850, 25);
		g2d.drawImage(this.profilCourant.getImage(),850,50,this.profilCourant.getImage().getWidth(null),this.profilCourant.getImage().getHeight(null),null);
	}

	/**
	 * M�thodes cr�ant les zone contenant les boutons du menu
	 */
	@Override
	public void creerRepresentationGeometrique() {
		this.listeBoutons[0] = new BoutonPersonnalise(100, 150, 200, 80,"Demarrer", "Actif");
		this.listeBoutons[1] = new BoutonPersonnalise(100, 300, 200, 80,"CreationProfil", "Actif");
		this.listeBoutons[2] = new BoutonPersonnalise(100, 450, 200, 80, "SelectionDeNiveaux", "Actif");
		this.listeBoutons[3] = new BoutonPersonnalise(100, 600, 200, 80, "Quitter", "Actif");
		this.listeBoutons[0].updateBoutonImage();
		this.listeBoutons[1].updateBoutonImage();
		this.listeBoutons[2].updateBoutonImage();




	}
	/**
	 * methode permetant de savoir si l'utilisateur a click� sur un bouton et retourne le bouton click� s'Il a lieu
	 * @param x= position souris en x du click
	 * @param y = position souris en y du click
	 * @return nom du bouton clicke
	 */

	public String clickHandler(int x, int y) {
		if (this.listeBoutons[0].getBoiteDeCollision().contains(x, y)) return "jouer";

		else if(this.listeBoutons[1].getBoiteDeCollision().contains(x,y))	return "profil";

		else if(this.listeBoutons[2].getBoiteDeCollision().contains(x,y)) return "selectionNiveau";
		else if(this.listeBoutons[3].getBoiteDeCollision().contains(x,y)) return "quitter";
		else return "pasUnBouton";

	}

	/**
	 * Methode changeant l'�tat des images de boutons par "actif" si le bouton est survoll� par la souris
	 * @param x = position en x de la souris
	 * @param y = position en y de la souris
	 */
	public void mouseMovingHandler(int x , int y){
		if (this.listeBoutons[0].getBoiteDeCollision().contains(x, y))
			this.listeBoutons[0].setEtat("Survole");

		else {
			this.listeBoutons[0].setEtat("Actif");
			if(this.listeBoutons[1].getBoiteDeCollision().contains(x,y))
				this.listeBoutons[1].setEtat("Survole");


			else{
				this.listeBoutons[1].setEtat("Actif");

				if(this.listeBoutons[2].getBoiteDeCollision().contains(x,y))
					this.listeBoutons[2].setEtat("Survole");

				else{
					this.listeBoutons[2].setEtat("Actif");
					if(this.listeBoutons[3].getBoiteDeCollision().contains(x,y))
						this.listeBoutons[3].setEtat("Survole");
					else this.listeBoutons[3].setEtat("Actif");
				}
			}

		}


	}
	/**
	 * Methode retournant le profil courant
	 * @return =  profil courant
	 */
	public Profil getProfilCourant() {
		return profilCourant;
	}
	/**
	 * Methode permettant de modifier le profil courant
	 * @param profilCourant = nouveau profil courant
	 */
	public void setProfilCourant(Profil profilCourant) {
		this.profilCourant = profilCourant;
	}



}
