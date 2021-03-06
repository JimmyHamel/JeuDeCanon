package aaplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import composantsGraphiques.ComposantAnime;
import fenetres.FenetreInstruction;
import fenetres.FenetreOption;
import interfaces.OptionsToApplicationListener;
import interfaces.PhysicsListener;
import utilite.Profil;

/**
 * Fenetre principale contenant notre composant graphique affichant notre menue ou noter jeu(animations physique)
 * Elle peut �tre �tendu par la fenetre option
 * Si �tendu, plusieurs donn�es scientifiques a propos de l'animation du jeu.
 * @author Jimmy
 * Date de cr�ation : 8 f�vrier 2016
 */
public class App26JeuDeCanonTomBoy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FenetreOption options;
	private FenetreInstruction instructions;
	private final ComposantAnime composantAnime = new ComposantAnime();
	private final int OGWIDTH = 1000, SCIENCEWIDTH = 1500;
	private JLabel lblVitesseXBalle;
	private JLabel lblVitesseYBalle;
	private JLabel lblVitesseResultanteBalle;
	private JLabel lblVitesseDuTank;
	private JLabel lblAccelGravitationnelle;
	private JLabel lblForceMagnetiqueX;
	private JLabel lblForceMagnetiqueY;
	private JLabel lblForceMagnetiqueResultante;
	private JLabel lblAccelForceMagnetiqueX;
	private JLabel lblAccelForceMagnetiqueY;
	private JLabel lblAccelForceMagnetiqueResultante;
	private JLabel lblForceFrictionAir;
	private boolean isModeScience;
	private JFileChooser chooser;
	private JButton btnRevenirMenu;
	private JButton btnProchainNiveau;
	private JButton btnRecommencer;
	private JLabel lblFin;





	/**
	 *
	 * Launch the application.
	 *
	 * @param args J'en ai aucune id�e.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App26JeuDeCanonTomBoy frame = new App26JeuDeCanonTomBoy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App26JeuDeCanonTomBoy() {
		setTitle("TomBoy");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(null, "Voudriez-vous quitter l'application?", "Quitter...", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					setVisible(false);
					System.exit(0);
				}
			}
		});



		options = new FenetreOption();
		instructions = new FenetreInstruction();
		setBounds(0, 0, OGWIDTH, 800);
		this.setResizable(false);
		int espace = 25;

		TitledBorder titrePnlVitesses = 
				new TitledBorder(null,"Vitesses", TitledBorder.LEADING, TitledBorder.TOP, null, null);

		titrePnlVitesses.setTitleFont(titrePnlVitesses.getTitleFont().deriveFont(Font.BOLD + Font.ITALIC));

		TitledBorder titrePnlForces = 
				new TitledBorder(null,"Forces", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		titrePnlForces.setTitleFont(titrePnlForces.getTitleFont().deriveFont(Font.BOLD + Font.ITALIC));

		TitledBorder titrePnlAccel = 
				new TitledBorder(null,"Acc�l�rations", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		titrePnlAccel.setTitleFont(titrePnlForces.getTitleFont().deriveFont(Font.BOLD + Font.ITALIC));






		// Cr�ation des label contenant toutes les donn�es scientifiques(D�but)

		// vitesses(d�but)
		JPanel pnlVitesses = new JPanel();
		pnlVitesses.setBackground(Color.WHITE);
		pnlVitesses.setBorder(titrePnlVitesses);
		pnlVitesses.setBounds(OGWIDTH, espace - 10, 490, espace *  9);

		this.lblVitesseXBalle = new JLabel("Vitesse en x de la balle : ");
		this.lblVitesseYBalle = new JLabel("Vitesse en y de la balle : ");
		this.lblVitesseResultanteBalle = new JLabel("Vitesse r�sultante de la balle : ");
		this.lblVitesseDuTank  = new JLabel("Vitesse du tank : ");

		// vitesses(fin)

		// forces (d�but)
		JPanel pnlForces = new JPanel();
		pnlForces.setBackground(Color.WHITE);

		pnlForces.setBorder(titrePnlForces);
		pnlForces.setBounds(OGWIDTH, (espace  * 10 ), 490, espace * 9);


		this.lblForceMagnetiqueX = new JLabel("Force magn�tique en x : ");
		this.lblForceMagnetiqueY = new JLabel("Force magn�tique en y : ");
		this.lblForceMagnetiqueResultante = new JLabel("Force magn�tique r�sultante : ");
		this.lblForceFrictionAir = new JLabel("Force de friction de l'air : N");

		// forces (fin)

		// accelerations (debut)
		JPanel pnlAccel = new JPanel();
		pnlAccel.setBackground(Color.WHITE);
		pnlAccel.setBorder(titrePnlAccel);
		pnlAccel.setBounds(OGWIDTH, espace * 20 - 10, 490, 30 * 8);

		this.lblAccelForceMagnetiqueX = new JLabel("Acc�l�ration en x caus�e par la force magn�tique : ");
		this.lblAccelForceMagnetiqueY = new JLabel("Acc�l�ration en y caus�e par la force magn�tique : ");
		this.lblAccelForceMagnetiqueResultante = new JLabel("Acc�l�ration r�sultante caus�e par la force magn�tique : ");
		this.lblAccelGravitationnelle = new JLabel("Acc�l�ration gravitationelle : ");

		// accelerations (fin)

		// Cr�ation des label contenant toutes les donn�es scientifiques(Fin)


		//cr�ation d'un tableau local contenant tout les label des donn�es scientifiques(D�but)
		JLabel[] tableauScientifique = 
			{
					this.lblVitesseXBalle, this.lblVitesseYBalle,  this.lblVitesseResultanteBalle,
					this.lblVitesseDuTank,  this.lblForceMagnetiqueX, this.lblForceMagnetiqueY, 
					this.lblForceMagnetiqueResultante, this.lblForceFrictionAir, this.lblAccelForceMagnetiqueX, this.lblAccelForceMagnetiqueY,
					this.lblAccelForceMagnetiqueResultante, this.lblAccelGravitationnelle

			};
		//cr�ation d'un tableau local contenant tout les label des donn�es scientifiques(Fin)

		options.addOptionsToApplicationListener(new OptionsToApplicationListener() {
			@Override
			public void scienceOption(boolean science) {
				isModeScience = science;
				if (science) { //si l'application est en mode science
					setSize(new Dimension(SCIENCEWIDTH, getHeight())); // resize de l'application
					contentPane.add(pnlVitesses);
					contentPane.add(pnlForces);
					contentPane.add(pnlAccel);
					pnlVitesses.setLayout(null);
					pnlForces.setLayout(null);

					pnlAccel.setLayout(null);

					int k = 1;
					int f = 1;
					int a = 1;

					for(JLabel label : tableauScientifique){
						label.setBounds(OGWIDTH + 20, espace * k, 500, espace);
						//afficher tout les les labels dans l'extension de la fen�tre
						//contentPane.add(label);
						if( k <=7){
							pnlVitesses.add(label);
							label.setBounds(10, espace*k, 500, 30);
						}
						else if( k <= 9 + (2*3)){

							pnlForces.add(label);
							label.setBounds(10, espace*f, 500, 30);
							f += 2;

						}
						else {
							pnlAccel.add(label);
							label.setBounds(10, espace*a, 500, 30);
							a += 2;

						}

						k = k+2;
					}


				} else {
					setSize(new Dimension(OGWIDTH, getHeight())); // resize aux dimensions originales
				}
			}

			@Override
			public void calculOption(boolean euler, boolean eulerI, boolean rk4) {
				composantAnime.changeCalcul(euler, eulerI, rk4);
			}

			@Override
			public void cheatOption(String cheat) {
				switch(cheat){
				case "seizure":
					
					break;
				case "progress":
					
					break;
				}
			}
		});
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);

		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!instructions.isVisible())
					instructions.setVisible(true);
			}
		});
		mnAide.add(mntmInstructions);

		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!options.isVisible())
					options.setVisible(true);
			}
		});
		mnAide.add(mntmOptions);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Voudriez-vous quitter l'application?");
				if (result == JOptionPane.OK_OPTION) {
					setVisible(false);
					System.exit(0);
				}
			}
		});
		mnAide.add(mntmQuitter);

		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);

		JMenuItem mntmRevenirAuMenu = new JMenuItem("Quitter et revenir au menu");
		mntmRevenirAuMenu.setVisible(false);
		mntmRevenirAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				composantAnime.revenirAuMenu();
				if(composantAnime.getEditable().isVisible())
					composantAnime.getEditable().setVisible(false);
			}
		});
		mnActions.add(mntmRevenirAuMenu);

		JMenuItem mntmRecommencer = new JMenuItem("Recommencer");
		mnActions.add(mntmRecommencer);

		JMenuItem mntmPause = new JMenuItem("Pause");
		mnActions.add(mntmPause);

		this.chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Fichier Texte (.txt)", "tomboy"));



		JMenuItem mntmChargerProfil = new JMenuItem("Charger Profil");
		mntmChargerProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File currentDirectory = new File(System.getProperty("user.home") + "/Desktop/TomBoy");
				if (currentDirectory.exists())	chooser.setCurrentDirectory(currentDirectory);
				else chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));

				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					File fichier = chooser.getSelectedFile();
					composantAnime.loadFile(fichier);

				}
			}
		});

		mnActions.add(mntmChargerProfil);

		JMenu mnChoisirProfil = new JMenu("Choisir Profil");


		mnActions.add(mnChoisirProfil);

		JMenuItem item = new JMenuItem(composantAnime.getProfilCourant().getNom());


		mnChoisirProfil.add(item);

		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				composantAnime.setIndexProfilCourant(0);
			}
		});



		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mntmPause.getText().equals("Pause")){
					composantAnime.pause();
					mntmPause.setText("Resume");
				}
				else {
					composantAnime.resume();
					mntmPause.setText("Pause");
				}
			}
		});
		mntmPause.setVisible(false);
		mntmRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				composantAnime.resetNiveauCourant();
			}
		});
		mntmRecommencer.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		composantAnime.addPhysicsListener(new PhysicsListener() {
			public void checkBulletSpeedX(double bulletSpeedX) {
				if(isModeScience)lblVitesseXBalle.setText("Vitesse de la balle en x : " + String.format("%.2f", bulletSpeedX) + " m/s");
			}
			public void checkBulletSpeedY(double bulletSpeedY) {
				if(isModeScience)lblVitesseYBalle.setText("Vitesse de la balle en y : " + String.format("%.2f", bulletSpeedY) + " m/s");
			}

			public void checkTankSpeed(double tankSpeed) {
				if(isModeScience)
					if(Math.abs(tankSpeed)<0.18)lblVitesseDuTank.setText("Vitesse du tank : " + String.format("%.2f", 0.00) + " m/s");
					else lblVitesseDuTank.setText("Vitesse du tank : " + String.format("%.2f", Math.abs(tankSpeed)) + " m/s");
			}
			@Override
			public void checkAccelMagneticX(double accelMagneticX) {
				if(isModeScience)lblAccelForceMagnetiqueX.setText("<html><body>Acc�l�ration en x caus�e par la force magn�tique : " + String.format("%.2f",accelMagneticX) + " m/s<sup>2</sup></body></html>");

			}
			@Override
			public void checkAccelMagneticY(double accelMagneticY) {
				if(isModeScience)lblAccelForceMagnetiqueY.setText("<html><body>Acc�l�ration en Y caus�e par la force magn�tique : " + String.format("%.2f",accelMagneticY) + " m/s<sup>2</sup></body></html>");

			}
			@Override
			public void checkForceMagneticX(double forceMagneticX) {
				if(isModeScience)lblForceMagnetiqueX.setText("Force magn�tique en x : " + String.format("%.2f", forceMagneticX ) + " N");
			}
			@Override
			public void checkForceMagneticY(double forceMagneticY) {
				if(isModeScience)lblForceMagnetiqueY.setText("Force magn�tique en y : " + String.format("%.2f", forceMagneticY ) + " N");

			}
			@Override
			public void checkAccelMagneticTotal(double accelMagneticTotal) {
				if(isModeScience)lblAccelForceMagnetiqueResultante.setText("<html><body>Acc�l�ration r�sultante caus�e par la force magn�tique : " + String.format("%.2f", Math.abs(accelMagneticTotal)) + " m/s<sup>2</sup></body></html>");

			}
			@Override
			public void checkForceMagneticTotal(double accelMagneticTotal) {
				if(isModeScience)lblForceMagnetiqueResultante.setText("Force magn�tique r�sultante : " + String.format("%.2f", accelMagneticTotal) +  " N");

			}
			@Override
			public void accelGravitationelle(double accelGravita) {
				if(isModeScience)lblAccelGravitationnelle.setText("<html><body>Acc�l�ration gravitationelle : " + String.format("%.2f",accelGravita)  +" m/s<sup>2</sup></body></html>");
			}
			@Override
			public void forceFrictionAir(double frictionAir) {
				if(isModeScience)lblForceFrictionAir.setText("Force de friction de l'air : " +  String.format("%.2f",frictionAir)+ " N");

			}
			@Override
			public void isRunning(boolean running) {
				mntmRecommencer.setVisible(running);
				mntmPause.setVisible(running);
				mntmRevenirAuMenu.setVisible(running);
				mntmChargerProfil.setVisible(!running);
				mnChoisirProfil.setVisible(!running);
			}

			@Override
			public void checkBulletSpeedTotal(double bulletSpeedTotal) {
				lblVitesseResultanteBalle.setText("Vitesse r�sultante de la balle : " +  String.format("%.2f",bulletSpeedTotal)  + " m/s" );

			}

			@Override
			public void profilAdded(Profil profil) {
				JMenuItem item = new JMenuItem(profil.getNom());
				int pos = mnChoisirProfil.getItemCount() ;
				mnChoisirProfil.add(item);
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						composantAnime.setIndexProfilCourant(pos);
					}
				});


			}
			@Override
			public void fin(String reussite) {
				if(reussite.equals("reussie")){
					if(!(composantAnime.getNiveauCourant() == 14)){
						btnRevenirMenu.setVisible(true);
						btnProchainNiveau.setVisible(true);
						lblFin.setVisible(true);
						lblFin.setFont(new Font("Tahoma", Font.PLAIN, 95));
						lblFin.setText("Vous avez r�ussi!");
					}
					else
					{
						composantAnime.revenirAuMenu();
						JOptionPane.showMessageDialog(null, "Vous avez fini le jeu!");
						getToolkit().beep();
					}
				}
				else if(reussite.equals("echec")){
					btnRevenirMenu.setVisible(true);
					btnRecommencer.setVisible(true);
					lblFin.setVisible(true);
					lblFin.setFont(new Font("Tahoma", Font.PLAIN, 55));
					lblFin.setText("Vous avez perdu! Recommencer?");

				}


			}
			@Override
			public void positionBalle(ArrayList<Double> points) {
				options.updatePositionsGraphique(points);
			}

		});
		composantAnime.setBounds(0, 0, 994, 750);
		contentPane.add(composantAnime);
		composantAnime.setFocusable(true);
		composantAnime.setLayout(null);

		btnRevenirMenu = new JButton("Revenir au menu");
		btnRevenirMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				composantAnime.revenirAuMenu();
				btnRevenirMenu.setVisible(false);
				btnProchainNiveau.setVisible(false);
				btnRecommencer.setVisible(false);
				lblFin.setVisible(false);
			}
		});
		btnRevenirMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRevenirMenu.setBounds(96, 228, 353, 294);
		btnRevenirMenu.setVisible(false);
		composantAnime.add(btnRevenirMenu);

		btnProchainNiveau = new JButton("Prochain niveau");
		btnProchainNiveau.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnProchainNiveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				composantAnime.niveauSuivant();
				btnRevenirMenu.setVisible(false);
				btnProchainNiveau.setVisible(false);
				lblFin.setVisible(false);

			}
		});
		btnProchainNiveau.setBounds(545, 228, 353, 294);
		btnProchainNiveau.setVisible(false);
		composantAnime.add(btnProchainNiveau);

		btnRecommencer = new JButton("Recommencer");
		btnRecommencer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				composantAnime.resetNiveauCourant();
				btnRecommencer.setVisible(false);
				btnRevenirMenu.setVisible(false);
				lblFin.setVisible(false);
			}
		});
		btnRecommencer.setBounds(545, 228, 353, 294);
		btnRecommencer.setVisible(false);
		composantAnime.add(btnRecommencer);

		lblFin = new JLabel("Vous avez perdu. Recommencer?");
		lblFin.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblFin.setSize(826, 100);
		lblFin.setLocation(100, 104);
		lblFin.setVisible(false);
		composantAnime.add(lblFin);


	}
}
