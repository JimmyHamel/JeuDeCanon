package fenetres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import composantsGraphiques.MenuSelectionDeNiveaux;
import interfaces.ChoixNiveauListener;

/**
 * Cette fenetre permet de choisir le niveau que le joueur voudra jouer
 * @author Jimmy
 * Date de creation : 8 fevrier 2016
 *
 */
public class FenetreSelectionNiveaux extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuSelectionDeNiveaux menuSelectionDeNiveaux;
	private JButton btnPrecedent;
	private JButton btnSuivant;


	

	/**
	 * Constructeur de la fenetre de selection de niveau
	 */
	public FenetreSelectionNiveaux() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Sélection du niveau");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});

		setBounds(100, 100, 839, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuSelectionDeNiveaux = new MenuSelectionDeNiveaux();
		menuSelectionDeNiveaux.setBounds(0, 0, 823, 492);
		menuSelectionDeNiveaux.setFocusable(true);
		
		contentPane.add(menuSelectionDeNiveaux);
		menuSelectionDeNiveaux.setLayout(null);
		menuSelectionDeNiveaux.addChoixNiveauListener(new ChoixNiveauListener(){

			@Override
			public void ChangementNiveau(String niveauSelectionne) {
				setVisible(false);
			}
			
		});
				

		
		btnSuivant = new JButton("Suivant");
		btnSuivant.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int page = menuSelectionDeNiveaux.suivant();
				if (page == 5)btnSuivant.setEnabled(false);
				btnPrecedent.setEnabled(true);
			}
		});
		btnSuivant.setBounds(583, 403, 122, 50);
		menuSelectionDeNiveaux.add(btnSuivant);
		
		btnPrecedent = new JButton("Pr\u00E9c\u00E9dent");
		btnPrecedent.setEnabled(false);
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page = menuSelectionDeNiveaux.precedent();
				if(page == 1)btnPrecedent.setEnabled(false);
				btnSuivant.setEnabled(true);
			}
		});
		btnPrecedent.setBounds(114, 403, 122, 50);
		menuSelectionDeNiveaux.add(btnPrecedent);
	}
	/**
	 * Methode retournant le nom du niveau selectionne
	 * @return = nom du niveau selectionne
	 */
	public String getNomNiveauSelectionne(){
		return this.menuSelectionDeNiveaux.getNomNiveauSelectionne();
	}
	/**
	 * Methode modifiant le niveau selectionne
	 * @param niveau =  nouveau niveau selectionne
	 */
	public void setNiveau(String niveau){
		this.menuSelectionDeNiveaux.setNiveau(niveau);
	}
	/**
	 * Methode permettant de modifier le niveau maximum du profil courant
	 * @param niveau =  niveau maximum du profil courant
	 */
	public void setNiveauProfil(int niveau) {
		this.menuSelectionDeNiveaux.setNiveauProfil(niveau);
		
	}
}
