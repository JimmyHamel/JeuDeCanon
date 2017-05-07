package fenetres;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import composantsGraphiques.SelectionImageDeJoueur;
import interfaces.NewProfilListener;
import utilite.Profil;
/**
 * Fênetre secondaire permettant de créer un profil de joueur
 * @author Jimmy
 * Date de création : 8 février 2016
 */
public class FenetreCreationDeProfils extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNom;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private boolean isAProfilCreated;
	private Profil profilCree;
	private JButton btnCreation;
	private SelectionImageDeJoueur selectionImageDeJoueur;


	/**
	 * Launch the application.
	 */
	public FenetreCreationDeProfils() {
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		setTitle("Création d'un profil");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				selectionImageDeJoueur.resetImageChoisie();
				txtNom.setText("");
				setVisible(false);

			}
		});

		txtNom = new JTextField();
		txtNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnCreation.doClick();
			}
		});
		txtNom.setBounds(130, 360, 510, 52);
		contentPane.add(txtNom);
		txtNom.setColumns(10);

		JLabel lblEntrezLeNom = new JLabel("Entrez le nom de votre profil");
		lblEntrezLeNom.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblEntrezLeNom.setBounds(147, 290, 492, 46);
		contentPane.add(lblEntrezLeNom);

		selectionImageDeJoueur = new SelectionImageDeJoueur();
		selectionImageDeJoueur.setBounds(0, 106, 794, 156);
		contentPane.add(selectionImageDeJoueur);

		btnCreation = new JButton("Cr\u00E9ation");
		btnCreation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String probleme = "";
				boolean validName = isTheNameValid();
				boolean isVide = txtNom.getText().equals("");
				boolean imageSelected = selectionImageDeJoueur.isAnImageSelected();
				if(!isVide && validName && imageSelected){
					isAProfilCreated = true;
					profilCree = new Profil(txtNom.getText(), 1, selectionImageDeJoueur.getNumeroImageChoisie());
					JOptionPane.showMessageDialog(null, "Profil créé !");
					txtNom.setText("");
					setVisible(false);
					selectionImageDeJoueur.resetImageChoisie();
					for(NewProfilListener ecout: OBJETS_ENREGISTRES.getListeners(NewProfilListener.class)){
						ecout.profilCree(isAProfilCreated);
					}

				}else {
					isAProfilCreated = false;

					if(isVide)
						probleme = "Vous n'avez pas saisie de nom";
					else 
						if(!validName){
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Le nom saisie n'est pas valide","Erreur", JOptionPane.ERROR_MESSAGE);
							txtNom.setText("");
						}

					if(!imageSelected)
						probleme += " et vous n'avez pas choisie votre image";

					if(!imageSelected && !isVide){
						probleme = "Vous n'avez pas choisie votre image";
					}
					if(!imageSelected || isVide){
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, probleme,"Erreur", JOptionPane.ERROR_MESSAGE);
					}


				}
			}

		});
		btnCreation.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCreation.setBounds(130, 436, 510, 52);
		contentPane.add(btnCreation);


		JLabel lblChoisissezVotreImage = new JLabel("Choisissez votre image de profil ");
		lblChoisissezVotreImage.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblChoisissezVotreImage.setBounds(111, 19, 573, 76);
		contentPane.add(lblChoisissezVotreImage);
	}
	/**
	 * Ajout d'un ecouteur pour la creation de nouveaux profils
	 * @param objEcout = objet de type NewProfilListener
	 */
	public void addNewProfilListener(NewProfilListener objEcout) {
		OBJETS_ENREGISTRES.add(NewProfilListener.class, objEcout);		
	}
	/**
	 * Methode retournant le profil cree par l'utilisateur
	 * @return = dernier profil cree
	 */
	public Profil getProfilCree() {
		return profilCree;
	}
	/**
	 * Methode retournant une valeur boolean true si un profil a deja ete cree, false sinon
	 * @return = une valeur boolean true si un profil a deja ete cree, false sinon
	 */
	public boolean isAProfilCreated() {
		return isAProfilCreated;
	}
	/**
	 * Methode permettant de modifier la valeur boolean correspond a si un profil a deja ete cree ou pas
	 * @param isAProfilCreated = la nouvelle valeur boolean correspondant a si un profil a ete cree ou pas
	 */
	public void setAProfilCreated(boolean isAProfilCreated) {
		this.isAProfilCreated = isAProfilCreated;
	}

	private boolean isTheNameValid(){
		boolean valid = true;
		for(char character : this.txtNom.getText().toCharArray()){
			if(!(character >= 48 && character <= 57) && !(character >= 65 &&character <= 90) && !(character >= 97 &&character <= 122))
				valid = false;
		}	
		return valid;
	}
}
