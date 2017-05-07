package composantsGraphiques;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import objetsDessinable.BoutonPersonnalise;

/**
 * Classe permettant la sélection d'une image de joueur dans la fenetre de création de profil
 * @author Jimmy
 * Date de création : 8 février 2016
 */
public class SelectionImageDeJoueur extends JPanel {

	private static final long serialVersionUID = 1067327917546089612L;
	private BoutonPersonnalise image1;
	private BoutonPersonnalise image2;
	private BoutonPersonnalise image3;
	private BoutonPersonnalise imageChoisie;
	private boolean premiereFois;


	/**
	 * Constructeur du panel SelectionImageDeJoueur et ajout du mouseListener permettant de 
	 * detecter les clics dans les zones d'images
	 * Si les clicks est dans une zone, l'image correspondante a la zone est selectionne
	 */
	public SelectionImageDeJoueur() {
		premiereFois = true;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(image1.getBoiteDeCollision().contains(arg0.getX(), arg0.getY()))
					imageChoisie = image1;
				else if(image2.getBoiteDeCollision().contains(arg0.getX(), arg0.getY()))
					imageChoisie = image2;
				else if(image3.getBoiteDeCollision().contains(arg0.getX(), arg0.getY()))
					imageChoisie = image3;
				
				repaint();
			}
			
		});


		

	}
	/**
	 * Methode permettant de dessiner des boutons personnalise correspondant au images selectionnables
	 * Si l'image est selectionne, sont etat sera modifie a "actif" et l'image change pour la meme image mais avec un fond bleu
	 * @param g = composante graphique
	 */
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		double espace = getWidth()/7.0;
		if(this.premiereFois){
		image1 = new BoutonPersonnalise(espace, getHeight() / 4.0,espace, getHeight() / 2.0,"imageJoueur1");
		image2 = new BoutonPersonnalise(espace * 3, getHeight() / 4, espace , getHeight() / 2, "imageJoueur2");
		image3 = new BoutonPersonnalise(espace * 5, getHeight() / 4, espace , getHeight() / 2 , "imageJoueur3");
		this.premiereFois = false;
		}else{
			this.image1.setEtat("");
			this.image2.setEtat("");
			this.image3.setEtat("");
		
		}
		
		
		if(this.imageChoisie !=null){
			this.imageChoisie.setEtat("Choisie");
		}
		
		image1.updateBoutonImage();
		image2.updateBoutonImage();
		image3.updateBoutonImage();

		image1.dessiner(g2d, null);
		image2.dessiner(g2d, null);
		image3.dessiner(g2d, null);
		
	}
	
	/**
	 * Retourne le numero de l'image choisie
	 * @return le numero de l'image choisie
	 */
	public int getNumeroImageChoisie(){
		return Integer.parseInt(this.imageChoisie.getNom().charAt(this.imageChoisie.getNom().length() - 1) + "");
	}
	
	/**
	 * Methode retournant un boolean decrivant si il y a une image choisie ou pas
	 * @return = boolean true si une image est selectionne, false sinon
	 */
	public boolean isAnImageSelected(){
		return this.imageChoisie != null;
	}
	public void resetImageChoisie() {
		this.imageChoisie = null;
	}

}
