package composantsGraphiques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import interfaces.ChoixNiveauListener;
import objetsDessinable.BoutonPersonnalise;

/**
 * Panel permetant de choisir un niveau en particulier
 * @author Jimmy
 * Date de création : 8 février 2016
 */
public class MenuSelectionDeNiveaux extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int nbPages = 5;
	private int page;
	private BoutonPersonnalise image1;
	private BoutonPersonnalise image2;
	private BoutonPersonnalise image3;
	private String niveauSelectionne;
	private int niveauProfil;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	
	
	/**
	 * Constructeur de la classe MenuSelectionDeNiveau
	 */


	public MenuSelectionDeNiveaux() {
		this.page = 1;
		this.niveauSelectionne = 1 + "";
		this.niveauProfil = 14;
		gestionSouris();

	}	
	
	/**
	 * Methode dessinan les boutons représentant les differents niveaux dans le JPanel
	 * Les niveaux non debloque par le profil courant sont bloque et représenté par une image de cadenas
	 * Les autres sont représenaté par une image contenant leur numero correspondant
	 * Si un niveau est selectionne, l'etat de l'image sera different des autres
	 *@param g = composant graphique du panel
	 */
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.DARK_GRAY);
		int espace = getWidth()/7;
		
		int niveauInt1 = (1 + (page-1) * 3);
		
		int niveauInt2 = niveauInt1 + 1;
		int niveauInt3 = niveauInt2 + 1;
		String nomImage1 = "niveau"+(1 + (page-1) * 3);
		String nomImage2 = "niveau" +(2 + (page-1) * 3);
		String nomImage3 = "";
		if(this.niveauProfil < niveauInt1)
			nomImage1 = "locked";
		
		if(this.niveauProfil < niveauInt2)
			nomImage2 = "locked";
		if(page == 5 )
			nomImage3 = "niveauEditable";
		else{
			if(niveauProfil >= niveauInt3)
			nomImage3 = "niveau" + (3 + (page-1) * 3);
			else
				nomImage3 = "locked";
		}
		
		if(this.niveauSelectionne.equals(niveauInt1 + "")) 
			image1 = new BoutonPersonnalise(espace, getHeight() / 4, espace, getHeight() / 2,nomImage1,"Choisie");
		else
			image1 = new BoutonPersonnalise(espace, getHeight() / 4, espace, getHeight() / 2,nomImage1);
		if(this.niveauSelectionne.equals(niveauInt2 + "")) 
			image2 = new BoutonPersonnalise(espace * 3, getHeight() / 4, espace, getHeight() / 2, nomImage2, "Choisie");
		else
			image2 = new BoutonPersonnalise(espace * 3, getHeight() / 4, espace, getHeight() / 2, nomImage2);
		
		if((this.niveauSelectionne.equals("niveau_editable") && this.page == 5 )|| this.niveauSelectionne.equals(niveauInt3 + ""))
			image3 = new BoutonPersonnalise(espace * 5, getHeight() / 4, espace , getHeight() / 2 , nomImage3, "Choisie");
		else
			image3 = new BoutonPersonnalise(espace * 5, getHeight() / 4, espace , getHeight() / 2 , nomImage3);
		image1.updateBoutonImage();
		image2.updateBoutonImage();
		image3.updateBoutonImage();
		image1.dessiner(g2d, null);;
		image2.dessiner(g2d, null);
		image3.dessiner(g2d, null);
		if(page == this.nbPages)
		g2d.setColor(Color.CYAN);
		
	}
	/**
	 * Methode retourner le nom du niveau selectionne
	 * @return = nom du niveau selectionne
	 */

	public String getNomNiveauSelectionne() {
		return this.niveauSelectionne;
		
	}

	/**
	 * Methode changeant la page courrante a la page precedante
	 * @return le nouveau numero de page courant
	 */
	public int precedent() {
		page--;
		repaint();
		return page;
	}
	/**
	 * Methode changeant la page courrante a la page suivante
	 * @return = le nouveau numero de page courant
	 */

	public int suivant() {
		page++;
		repaint();
		return page;
	}
	/**
	 * Ajout d'un ecouteur pour les changement de niveau
	 * @param objEcout = objet de type ChoixNiveauListener
	 */
	
	public void addChoixNiveauListener(ChoixNiveauListener objEcout){
		OBJETS_ENREGISTRES.add(ChoixNiveauListener.class, objEcout);
	}

	/**
	 * Methode permettant de modifier le niveau selectiojje
	 * @param niveau = nouveau niveau selectionne
	 */
	public void setNiveau(String niveau) {
		this.niveauSelectionne = niveau;
	}
	
	/**
	 * Methode modifiant le niveau max
	 * @param niveau = nouveau niveau max
	 */
	public void setNiveauProfil(int niveau) {
		this.niveauProfil = niveau;
	}
	
	/**
	 * Methode ajoutant des mouseListeners pour detecter les clicks et choisir des niveaux
	 */
	private void gestionSouris(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String temp = niveauSelectionne;
				boolean niveauTropHaut = false;
				if(image1.getBoiteDeCollision().contains(arg0.getX(), arg0.getY())){
					niveauSelectionne =  1 + (page-1) * 3 + "";
					if(Integer.parseInt(niveauSelectionne) <= niveauProfil){
					if(!niveauSelectionne.equals(temp)){
					if(JOptionPane.showConfirmDialog(null,"Vous avez sélectionné le niveau : " + niveauSelectionne,"Choix", 
							JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
						niveauSelectionne = temp;
					
					}
					else 
						JOptionPane.showMessageDialog(null, "Le niveau " + niveauSelectionne + " est déjà choisie.");
					}else{
						JOptionPane.showMessageDialog(null, "Ce niveau n'est pas débloqué");
						niveauSelectionne = temp;
						niveauTropHaut = true;
					}
						
						
				}else{
					if(image2.getBoiteDeCollision().contains(arg0.getX(), arg0.getY())){
						niveauSelectionne = 2 + (page-1) * 3 + "";
						
						if(Integer.parseInt(niveauSelectionne) <= niveauProfil){
						
						if(!niveauSelectionne.equals(temp)){


					if(JOptionPane.showConfirmDialog(null,"Vous avez sélectionné le niveau : " + niveauSelectionne,"Choix", 
							JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
						niveauSelectionne = temp;
						}
						else	
							JOptionPane.showMessageDialog(null, "Le niveau " + niveauSelectionne + " est déjà choisie.");
						
						}else{
							JOptionPane.showMessageDialog(null, "Ce niveau n'est pas débloqué");
							niveauSelectionne = temp;
							niveauTropHaut = true;

						}
							

					
				}
					else if(image3.getBoiteDeCollision().contains(arg0.getX(), arg0.getY())){
						if(page == 5)
							niveauSelectionne = "niveau_editable";
						else 
							niveauSelectionne =  3 + (page-1) * 3 + "";
						int niveau =  3 + (page - 1) * 3;
						if(niveauSelectionne.equals("niveau_editable") || niveau <= niveauProfil){
						
						if(!niveauSelectionne.equals(temp)){
							
						
							
						if(JOptionPane.showConfirmDialog(null,"Vous avez sélectionné le niveau : " + niveauSelectionne,"Choix", 
								JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
							niveauSelectionne = temp;

						
						}
						else
							JOptionPane.showMessageDialog(null, "Le niveau " + niveauSelectionne + " est déjà choisie.");
						
							}else{
								JOptionPane.showMessageDialog(null, "Ce niveau n'est pas débloqué");
								niveauSelectionne = temp;
								niveauTropHaut = true;

							}
							
						
					
						
						
					}
				}
				if(!niveauSelectionne.equals(temp) && !niveauTropHaut){
				for(ChoixNiveauListener ecout : OBJETS_ENREGISTRES.getListeners(ChoixNiveauListener.class))
					ecout.ChangementNiveau(niveauSelectionne);
				}
			}
		});
	}

}
