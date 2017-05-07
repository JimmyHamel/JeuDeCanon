package fenetres;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import interfaces.GenerationListener;
import objetsDessinable.Ennemies;
import objetsDessinable.Joueur;
import objetsDessinable.Ressort;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La fenêtre qui rend le niveau éditable fonctionnel
 * 
 * @author Mitchell Mastromonaco
 *
 */
public class FenetreEditable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1669223996219433294L;
	private JPanel contentPane;
	private String[] type = new String[] {"simple", "lessSimple", "medium", "sine", "smart", "gitgud"};
	private JComboBox<String> cbxTypes;
	private JComboBox<String> cbxEnnemies1;
	private JSpinner spnLength;
	private JSpinner spnWidth;
	private JSpinner spnVit;
	private JSpinner spnNbr;
	private JComboBox<String> cbxTypes3;
	private JSpinner spnLeg;
	private JSpinner spnWid;
	private JSpinner spnVitSpe;
	private JSpinner spnNbu;
	private JSpinner spnLong;
	private JSpinner spnLarg;
	private JSpinner spnSpd;
	private JSpinner spnNum;
	private double largeurMonde;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private Joueur player;
	private double hauteurMonde;
	private JPanel panelEnnemies;
	private JTabbedPane tabbedPane;
	private double largeurMur;
	private JSpinner spnAmpli;
	private JSpinner spnOmega;
	private JSpinner spnForce;
	private JSpinner spnAmpli2;
	private JSpinner spnOmega2;
	private JSpinner spnForce2;
	private double phiDroit;
	private double phiGauche;
	private JSpinner spnMassTank;
	private JSpinner spnLargTank;
	private JSpinner spnMassVAir;
	private JSpinner spnFrotSol;
	private JSpinner spnHautTank;
	private JSpinner spnForceMov;
	private JSpinner spnConstantG;
	private JSpinner spnMassBalle;
	private JSpinner spnForceMax;
	private JSpinner spnCoeffAir;
	private JLabel lblEnnemies;
	private JLabel lblRessorts;
	private JLabel lblJoueur;


	/**
	 * Create the frame.
	 */
	public FenetreEditable() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Editable Interface");

		setResizable(false);

		setBounds(100, 100, 584, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(64, 25, 440, 399);
		contentPane.add(tabbedPane);

		panelEnnemies = new JPanel();
		tabbedPane.addTab("Ennemies", null, panelEnnemies, null);
		panelEnnemies.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 440, 60);
		panelEnnemies.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Premier set", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);

		lblEnnemies = new JLabel("Ennemies");
		lblEnnemies.setBounds(10, 71, 500, 50);
		lblEnnemies.setVisible(false);
		panelEnnemies.add(lblEnnemies);

		cbxEnnemies1 = new JComboBox<String>();
		cbxEnnemies1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				switch((String)cbxEnnemies1.getSelectedItem()){
				case "simple":
					lblEnnemies.setText("<html><body><b><i>simple</b></i> : Le plus simple des ennemis. <br>Il va de droite \u00E0 gauche et descend quand il atteind une extr\u00E9mit\u00E9.</html></body>");
					break;
				case "lessSimple":
					lblEnnemies.setText("<html><body><b><i>lessSimple</b></i> : Moins simple que simple.<br>Il a les m\u00EAmes mouvements de base que simple, mais il tire une balle al\u00E9atoirement.</html></body>");
					break;
				case "medium":
					lblEnnemies.setText("<html><body><b><i>medium</b></i> : Un ennemi qui poss\u00E8de les m\u00EAmes mouvements que simple.<br>Cependant, lorsqu'il est \u00E0 votre position en x, il descend vers vous et tire une balle.</html></body>");
					break;
				case "sine":
					lblEnnemies.setText("<html><body><b><i>sine</b></i> : \u00C0 ne pas utiliser. Cet ennemi n'est qu'un concept.</html></body>");
					break;
				case "smart":
					lblEnnemies.setText("<html><body><b><i>smart</b></i> : Un ennemi style boss.<br>Cet ennemi est trop complexe pour d\u00E9crire.</html></body>");
					break;
				case "gitgud":				
					lblEnnemies.setText("<html><body><b><i>gitgud</b></i> : Cet ennemi monte de haut vers le bas. <br>Lorsqu'il touche un mur, il va de l'autre bord.</html></body>");	
					break;
				}
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		//		"simple", "lessSimple", "medium", "sine", "smart", "gitgud"
		cbxEnnemies1.setBounds(6, 33, 111, 20);
		panel.add(cbxEnnemies1);
		cbxEnnemies1.setModel(new DefaultComboBoxModel<String>(type));

		JLabel lblTypeDenn = new JLabel("Type:");
		lblTypeDenn.setBounds(6, 16, 111, 14);
		panel.add(lblTypeDenn);

		spnLength = new JSpinner();
		spnLength.setBounds(150, 33, 38, 20);
		panel.add(spnLength);
		spnLength.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));

		spnWidth = new JSpinner();
		spnWidth.setBounds(225, 33, 38, 20);
		panel.add(spnWidth);
		spnWidth.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));

		JLabel lblLongueur = new JLabel("Longueur:");
		lblLongueur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La longueur d'un ennemi du premier set en <i>m</i>. <br>Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblLongueur.setBounds(125, 16, 63, 14);
		panel.add(lblLongueur);
		lblLongueur.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblLargeur = new JLabel("Hauteur:");
		lblLargeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La hauteur d'un ennemi du premier set en <i>m</i>. <br>Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblLargeur.setBounds(217, 16, 46, 14);
		panel.add(lblLargeur);
		lblLargeur.setHorizontalAlignment(SwingConstants.RIGHT);

		spnVit = new JSpinner();
		spnVit.setBounds(300, 33, 46, 20);
		panel.add(spnVit);
		spnVit.setModel(new SpinnerNumberModel(0.50, 0.01, 5.0, 0.01));

		JLabel lblVitesse = new JLabel("Vitesse:");
		lblVitesse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La vitesse d'un ennemi du premier set en <i>m/s</i>. <br> Une valeur entre 0,01 et 5,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblVitesse.setBounds(300, 16, 46, 14);
		panel.add(lblVitesse);
		lblVitesse.setHorizontalAlignment(SwingConstants.RIGHT);

		spnNbr = new JSpinner();
		spnNbr.setBounds(375, 33, 38, 20);
		panel.add(spnNbr);
		spnNbr.setModel(new SpinnerNumberModel(0, 0, 12, 1));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La nombre d'ennemi qu'il y aura dans le premier set. <br> Une valeur entre 1 et 12.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblNombre.setBounds(367, 16, 46, 14);
		panel.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 125, 440, 60);
		panelEnnemies.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Deuxi\u00E8me set", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		cbxTypes = new JComboBox<String>();
		cbxTypes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				switch((String)cbxTypes.getSelectedItem()){
				case "simple":
					lblEnnemies.setText("<html><body><b><i>simple</b></i> : Le plus simple des ennemis. <br>Il va de droite \u00E0 gauche et descend quand il atteind une extr\u00E9mit\u00E9.</html></body>");
					break;
				case "lessSimple":
					lblEnnemies.setText("<html><body><b><i>lessSimple</b></i> : Moins simple que simple.<br>Il a les m\u00EAmes mouvements de base que simple, mais il tire une balle al\u00E9atoirement.</html></body>");
					break;
				case "medium":
					lblEnnemies.setText("<html><body><b><i>medium</b></i> : Un ennemi qui poss\u00E8de les m\u00EAmes mouvements que simple.<br>Cependant, lorsqu'il est \u00E0 votre position en x, il descend vers vous et tire une balle.</html></body>");
					break;
				case "sine":
					lblEnnemies.setText("<html><body><b><i>sine</b></i> : \u00C0 ne pas utiliser. Cet ennemi n'est qu'un concept.</html></body>");
					break;
				case "smart":
					lblEnnemies.setText("<html><body><b><i>smart</b></i> : Un ennemi style boss.<br>Cet ennemi est trop complexe pour d\u00E9crire.</html></body>");
					break;
				case "gitgud":				
					lblEnnemies.setText("<html><body><b><i>gitgud</b></i> : Cet ennemi monte de haut vers le bas. <br>Lorsqu'il touche un mur, il va de l'autre bord.</html></body>");	
					break;
				}
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		cbxTypes.setModel(new DefaultComboBoxModel<String>(type));
		cbxTypes.setBounds(6, 33, 111, 20);
		panel_1.add(cbxTypes);

		JLabel label = new JLabel("Type:");
		label.setBounds(6, 16, 111, 14);
		panel_1.add(label);

		spnLong = new JSpinner();
		spnLong.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));
		spnLong.setBounds(150, 33, 38, 20);
		panel_1.add(spnLong);

		spnLarg = new JSpinner();
		spnLarg.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));
		spnLarg.setBounds(225, 33, 38, 20);
		panel_1.add(spnLarg);

		JLabel label_1 = new JLabel("Longueur:");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La longueur d'un ennemi du deuxième set en <i>m</i>. <br> Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(125, 16, 63, 14);
		panel_1.add(label_1);

		JLabel lblHauteur_1 = new JLabel("Hauteur:");
		lblHauteur_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La hauteur d'un ennemi du deuxième set en <i>m</i>. <br>Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblHauteur_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHauteur_1.setBounds(217, 16, 46, 14);
		panel_1.add(lblHauteur_1);

		spnSpd = new JSpinner();
		spnSpd.setModel(new SpinnerNumberModel(0.50, 0.01, 5.0, 0.01));
		spnSpd.setBounds(300, 33, 46, 20);
		panel_1.add(spnSpd);

		JLabel label_3 = new JLabel("Vitesse:");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La vitesse d'un ennemi du deuxième set en <i>m/s</i>. <br> Une valeur entre 0,01 et 5,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(300, 16, 46, 14);
		panel_1.add(label_3);

		spnNum = new JSpinner();
		spnNum.setModel(new SpinnerNumberModel(0, 0, 12, 1));
		spnNum.setBounds(375, 33, 38, 20);
		panel_1.add(spnNum);

		JLabel label_4 = new JLabel("Nombre:");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La nombre d'ennemi qu'il y aura dans le deuxième set. <br> Une valeur entre 1 et 12.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(367, 16, 46, 14);
		panel_1.add(label_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 250, 440, 60);
		panelEnnemies.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Troisi\u00E8me set", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		cbxTypes3 = new JComboBox<String>();
		cbxTypes3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				switch((String)cbxTypes3.getSelectedItem()){
				case "simple":
					lblEnnemies.setText("<html><body><b><i>simple</b></i> : Le plus simple des ennemis. <br>Il va de droite \u00E0 gauche et descend quand il atteind une extr\u00E9mit\u00E9.</html></body>");
					break;
				case "lessSimple":
					lblEnnemies.setText("<html><body><b><i>lessSimple</b></i> : Moins simple que simple.<br>Il a les m\u00EAmes mouvements de base que simple, mais il tire une balle al\u00E9atoirement.</html></body>");
					break;
				case "medium":
					lblEnnemies.setText("<html><body><b><i>medium</b></i> : Un ennemi qui poss\u00E8de les m\u00EAmes mouvements que simple.<br>Cependant, lorsqu'il est \u00E0 votre position en x, il descend vers vous et tire une balle.</html></body>");
					break;
				case "sine":
					lblEnnemies.setText("<html><body><b><i>sine</b></i> : \u00C0 ne pas utiliser. Cet ennemi n'est qu'un concept.</html></body>");
					break;
				case "smart":
					lblEnnemies.setText("<html><body><b><i>smart</b></i> : Un ennemi style boss.<br>Cet ennemi est trop complexe pour d\u00E9crire.</html></body>");
					break;
				case "gitgud":				
					lblEnnemies.setText("<html><body><b><i>gitgud</b></i> : Cet ennemi monte de haut vers le bas. <br>Lorsqu'il touche un mur, il va de l'autre bord.</html></body>");	
					break;
				}
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		cbxTypes3.setModel(new DefaultComboBoxModel<String>(type));
		cbxTypes3.setBounds(6, 33, 111, 20);
		panel_2.add(cbxTypes3);

		JLabel label_5 = new JLabel("Type:");
		label_5.setBounds(6, 16, 111, 14);
		panel_2.add(label_5);

		spnLeg = new JSpinner();
		spnLeg.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));
		spnLeg.setBounds(150, 33, 38, 20);
		panel_2.add(spnLeg);

		spnWid = new JSpinner();
		spnWid.setModel(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.1));
		spnWid.setBounds(225, 33, 38, 20);
		panel_2.add(spnWid);

		JLabel label_6 = new JLabel("Longueur:");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La longueur d'un ennemi du troisième set en <i>m</i>. <br> Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(125, 16, 63, 14);
		panel_2.add(label_6);

		JLabel lblHauteur_2 = new JLabel("Hauteur:");
		lblHauteur_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La hauteur d'un ennemi du troisième set en <i>m</i>. <br>Une valeur entre 0,5 et 12,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		lblHauteur_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHauteur_2.setBounds(217, 16, 46, 14);
		panel_2.add(lblHauteur_2);

		spnVitSpe = new JSpinner();
		spnVitSpe.setModel(new SpinnerNumberModel(0.50, 0.01, 5.00, 0.01));
		spnVitSpe.setBounds(300, 33, 46, 20);
		panel_2.add(spnVitSpe);

		JLabel label_8 = new JLabel("Vitesse:");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La vitesse d'un ennemi du troisième set en <i>m/s</i>. <br> Une valeur entre 0,01 et 5,0.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(300, 16, 46, 14);
		panel_2.add(label_8);

		spnNbu = new JSpinner();
		spnNbu.setModel(new SpinnerNumberModel(0, 0, 12, 1));
		spnNbu.setBounds(375, 33, 38, 20);
		panel_2.add(spnNbu);

		JLabel label_9 = new JLabel("Nombre:");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnnemies.setText("<html><body>La nombre d'ennemi qu'il y aura dans le troisième set. <br> Une valeur entre 1 et 12.</html></body>");
				lblEnnemies.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEnnemies.setVisible(false);
			}
		});
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(367, 16, 46, 14);
		panel_2.add(label_9);

		JButton btnGenerer = new JButton("G\u00E9n\u00E9rer");
		btnGenerer.setBounds(175, 334, 89, 23);
		panelEnnemies.add(btnGenerer);


		btnGenerer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(GenerationListener ecout: OBJETS_ENREGISTRES.getListeners(GenerationListener.class)){
					ArrayList<Ennemies> ennemies1 = new ArrayList<Ennemies>();

					Color colo = new Color((int)(Math.random()*255+1), (int)(Math.random()*255+1), (int)(Math.random()*255+1));
					for(int i = 0; i<(int)spnNbr.getValue(); i++){
						ennemies1.add(new Ennemies((largeurMonde)/((int)spnNbr.getValue() + 1)*(i) + largeurMur + (double)spnLength.getValue()/2, hauteurMonde - hauteurMonde/4, (double)spnLength.getValue(), (double)spnWidth.getValue(), (String)cbxEnnemies1.getSelectedItem(), (double)spnVit.getValue(), player,  colo));
					}

					colo = new Color((int)(Math.random()*255+1), (int)(Math.random()*255+1), (int)(Math.random()*255+1));
					for(int i = 0; i<(int)spnNum.getValue(); i++){
						ennemies1.add(new Ennemies((largeurMonde)/((int)spnNum.getValue() + 1)*(i)  + largeurMur + (double)spnLong.getValue()/2, hauteurMonde - hauteurMonde/2, (double)spnLong.getValue(), (double)spnLarg.getValue(), (String)cbxTypes.getSelectedItem(), (double)spnSpd.getValue(), player,  colo));
					}

					colo = new Color((int)(Math.random()*255+1), (int)(Math.random()*255+1), (int)(Math.random()*255+1));
					for(int i = 0; i<(int)spnNbu.getValue(); i++){
						ennemies1.add(new Ennemies((largeurMonde)/((int)spnNbu.getValue() + 1)*(i) + largeurMur + (double)spnLeg.getValue()/2, hauteurMonde - 3*hauteurMonde/4, (double)spnLeg.getValue(), (double)spnWid.getValue(), (String)cbxTypes3.getSelectedItem(), (double)spnVitSpe.getValue(), player,  colo));
					}

					ecout.ennemiesGeneration(ennemies1);
				}
			}
		});

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Ressorts", null, panel_4, null);
		panel_4.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ressort de droite", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(19, 15, 331, 68);
		panel_4.add(panel_3);
		panel_3.setLayout(null);
		
		lblRessorts = new JLabel("Ressorts");
		lblRessorts.setBounds(19, 250, 384, 73);
		lblRessorts.setVisible(false);
		panel_4.add(lblRessorts);

		JLabel lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La longueur du ressort de droite en <i>m</i>. <br> Une valeur entre 1,0 et 10,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblAmplitude.setBounds(6, 16, 69, 14);
		panel_3.add(lblAmplitude);
		lblAmplitude.setHorizontalAlignment(SwingConstants.RIGHT);

		spnAmpli = new JSpinner();
		spnAmpli.setBounds(31, 41, 44, 20);
		panel_3.add(spnAmpli);
		spnAmpli.setModel(new SpinnerNumberModel(5.0, 1.0, 10.0, 0.1));

		JLabel lblFrequence = new JLabel("Fr\u00E9quence angulaire:");
		lblFrequence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La fréquence angulaire du système masse-ressort de droite en <i>rad/s</i>. <br> Une valeur entre 1,0 et 15,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblFrequence.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrequence.setBounds(97, 16, 103, 14);
		panel_3.add(lblFrequence);

		spnOmega = new JSpinner();
		spnOmega.setBounds(156, 41, 44, 20);
		panel_3.add(spnOmega);
		spnOmega.setModel(new SpinnerNumberModel(10.0, 1.0, 15.0, 0.1));

		spnForce = new JSpinner();
		spnForce.setBounds(281, 41, 44, 20);
		panel_3.add(spnForce);
		spnForce.setModel(new SpinnerNumberModel(150.0, 1.0, 250.0, 1.0));

		JLabel lblForceInitiale = new JLabel("Force initiale:");
		lblForceInitiale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La force initiale qui compresse le ressort de droite en <i>N</i>. <br> Une valeur entre 1,0 et 250,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblForceInitiale.setBounds(246, 16, 79, 14);
		panel_3.add(lblForceInitiale);
		lblForceInitiale.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ressort de gauche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(19, 159, 331, 68);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblAmplit = new JLabel("Amplitude:");
		lblAmplit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La longueur du ressort de gauche en <i>m</i>. <br> Une valeur entre 1,0 et 10,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblAmplit.setBounds(6, 16, 69, 14);
		panel_5.add(lblAmplit);
		lblAmplit.setHorizontalAlignment(SwingConstants.RIGHT);

		spnAmpli2 = new JSpinner();
		spnAmpli2.setBounds(31, 41, 44, 20);
		panel_5.add(spnAmpli2);
		spnAmpli2.setModel(new SpinnerNumberModel(5.0, 1.0, 10.0, 0.1));

		JLabel lblOmega = new JLabel("Fr\u00E9quence angulaire:");
		lblOmega.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La fréquence angulaire du système masse-ressort de gauche en <i>rad/s</i>. <br> Une valeur entre 1,0 et 15,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblOmega.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOmega.setBounds(97, 16, 103, 14);
		panel_5.add(lblOmega);

		spnOmega2 = new JSpinner();
		spnOmega2.setBounds(156, 41, 44, 20);
		panel_5.add(spnOmega2);
		spnOmega2.setModel(new SpinnerNumberModel(10.0, 1.0, 15.0, 0.1));

		JLabel lblForce = new JLabel("Force initiale:");
		lblForce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRessorts.setText("<html><body>La force initiale qui compresse le ressort de gauche en <i>N</i>. <br> Une valeur entre 1,0 et 250,0.</html></body>");
				lblRessorts.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRessorts.setVisible(false);
			}
		});
		lblForce.setBounds(246, 16, 79, 14);
		panel_5.add(lblForce);
		lblForce.setHorizontalAlignment(SwingConstants.RIGHT);

		spnForce2 = new JSpinner();
		spnForce2.setBounds(281, 41, 44, 20);
		panel_5.add(spnForce2);
		spnForce2.setModel(new SpinnerNumberModel(150.0, 1.0, 250.0, 1.0));

		JButton btnGnrer = new JButton("G\u00E9n\u00E9rer");
		btnGnrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(GenerationListener ecout: OBJETS_ENREGISTRES.getListeners(GenerationListener.class)){
					Ressort ressortGauche =  new Ressort(largeurMur, largeurMur, (double)spnAmpli2.getValue(), largeurMur, (double)spnOmega2.getValue(), phiGauche, (double)spnForce2.getValue(), 5, "left");
					Ressort ressortDroit = new Ressort(largeurMonde-largeurMur-(double)spnAmpli.getValue(), largeurMur, (double)spnAmpli.getValue(), largeurMur, (double)spnOmega.getValue(), phiDroit, (double)spnForce.getValue(), 5, "right");
					ecout.ressortsGeneration(ressortGauche, ressortDroit);
				} //YOU TWAT
			}
		});
		btnGnrer.setBounds(175, 334, 89, 23);
		panel_4.add(btnGnrer);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Joueur et valeurs physiques", null, panel_6, null);
		panel_6.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Char d'assaut", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(24, 14, 382, 68);
		panel_6.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblMasseTank = new JLabel("Masse:");
		lblMasseTank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La masse du char d'assaut qui est contrôlé par le joueur en <i>kg</i>. <br> Une valeur entre 1,0 et 99 999,0.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblMasseTank.setBounds(6, 16, 60, 14);
		panel_7.add(lblMasseTank);
		lblMasseTank.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblLargeur_1 = new JLabel("Longueur:");
		lblLargeur_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La longueur du char d'assaut qui est contrôlé par le joueur en <i>m</i>. <br> Une valeur entre 1,0 et 10,0.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblLargeur_1.setBounds(96, 16, 60, 14);
		panel_7.add(lblLargeur_1);
		lblLargeur_1.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblHauteur = new JLabel("Hauteur:");
		lblHauteur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La hauteur du char d'assaut qui est contrôlé par le joueur en <i>m</i>. <br> Une valeur entre 1,0 et 10,0.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblHauteur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHauteur.setBounds(210, 16, 46, 14);
		panel_7.add(lblHauteur);

		JLabel lblForceDeMouvement = new JLabel("Force de mouvement:");
		lblForceDeMouvement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La force de déplacement du char d'assaut qui est contrôlé par le joueur en <i>N</i>. <br> Une valeur entre 100,0 et 9 999 999,0.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblForceDeMouvement.setBounds(271, 16, 105, 14);
		panel_7.add(lblForceDeMouvement);

		spnMassTank = new JSpinner();
		spnMassTank.setBounds(16, 41, 50, 20);
		panel_7.add(spnMassTank);
		spnMassTank.setModel(new SpinnerNumberModel(1000.0, 1.0, 99999.0, 1.0));

		spnLargTank = new JSpinner();
		spnLargTank.setBounds(116, 41, 40, 20);
		panel_7.add(spnLargTank);
		spnLargTank.setModel(new SpinnerNumberModel(5.0, 1.0, 10.0, 0.1));

		spnHautTank = new JSpinner();
		spnHautTank.setBounds(216, 41, 40, 20);
		panel_7.add(spnHautTank);
		spnHautTank.setModel(new SpinnerNumberModel(5.0, 1.0, 10.0, 0.1));

		spnForceMov = new JSpinner();
		spnForceMov.setBounds(316, 41, 60, 20);
		panel_7.add(spnForceMov);
		spnForceMov.setModel(new SpinnerNumberModel(30000.0, 100.0, 9999999.0, 1.0));

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBounds(275, 90, 125, 180);
		panel_6.add(panel_9);
		panel_9.setLayout(null);

		JLabel lblMasseDeLa = new JLabel("Masse:");
		lblMasseDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La masse de la balle tirée par le char d'assaut en <i>kg</i>. <br> Une valeur entre 0,001 et 10,000.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblMasseDeLa.setBounds(26, 72, 72, 14);
		panel_9.add(lblMasseDeLa);
		lblMasseDeLa.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCoefficientDeTraine = new JLabel("Coefficient de traîn\u00E9e:");
		lblCoefficientDeTraine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>Le coefficient de traînée de la balle. <br> Une valeur entre 0,000 et 2,000.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblCoefficientDeTraine.setBounds(6, 128, 113, 14);
		panel_9.add(lblCoefficientDeTraine);
		lblCoefficientDeTraine.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblForceMaximale = new JLabel("Force maximale:");
		lblForceMaximale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La force maximale à laquelle la balle peut être lancée en <i>N</i>. <br> Une valeur entre 1,0 et 9 999 999,0.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblForceMaximale.setBounds(23, 16, 78, 14);
		panel_9.add(lblForceMaximale);
		lblForceMaximale.setHorizontalAlignment(SwingConstants.CENTER);

		spnForceMax = new JSpinner();
		spnForceMax.setBounds(35, 41, 55, 20);
		panel_9.add(spnForceMax);
		spnForceMax.setModel(new SpinnerNumberModel(1000.0, 1.0, 9999999.0, 1.0));

		spnMassBalle = new JSpinner();
		spnMassBalle.setBounds(35, 97, 55, 20);
		panel_9.add(spnMassBalle);
		spnMassBalle.setModel(new SpinnerNumberModel(0.041, 0.001, 10.0, 0.001));

		spnCoeffAir = new JSpinner();
		spnCoeffAir.setBounds(35, 153, 55, 20);
		panel_9.add(spnCoeffAir);
		spnCoeffAir.setModel(new SpinnerNumberModel(0.002, 0.000, 2.000, 0.001));

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Valeurs physiques", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBounds(18, 90, 169, 180);
		panel_6.add(panel_8);
		panel_8.setLayout(null);

		spnFrotSol = new JSpinner();
		spnFrotSol.setBounds(57, 41, 55, 20);
		panel_8.add(spnFrotSol);
		spnFrotSol.setModel(new SpinnerNumberModel(1.270, 0.010, 1.750, 0.001));

		spnMassVAir = new JSpinner();
		spnMassVAir.setBounds(57, 97, 55, 20);
		panel_8.add(spnMassVAir);
		spnMassVAir.setModel(new SpinnerNumberModel(1.217, 0.000, 15.000, 0.001));

		JLabel lblCoefficientDeFrottement = new JLabel("Coefficient de frottement du sol:");
		lblCoefficientDeFrottement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>Le coefficient de frottement cinétique entre le sol et les roues du char d'assaut. <br> Une valeur entre 0,010 et 1,750.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblCoefficientDeFrottement.setBounds(6, 16, 157, 14);
		panel_8.add(lblCoefficientDeFrottement);
		lblCoefficientDeFrottement.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMasseVolumiqueDe = new JLabel("Masse volumique de l'air:");
		lblMasseVolumiqueDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La masse volumique de l'atmosphère en <i>kg/m<sup>3</sup></i>. <br> Une valeur entre 1,000 et 100,000.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblMasseVolumiqueDe.setBounds(25, 72, 119, 14);
		panel_8.add(lblMasseVolumiqueDe);
		lblMasseVolumiqueDe.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblConstanteGravitationnelle = new JLabel("Constante gravitationnelle:");
		lblConstanteGravitationnelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblJoueur.setText("<html><body>La gravité de surface en <i>m/s<sup>2</sup></i>. <br> Une valeur entre 0,000 et 15,000.</html></body>");
				lblJoueur.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblJoueur.setVisible(false);
			}
		});
		lblConstanteGravitationnelle.setBounds(19, 128, 131, 14);
		panel_8.add(lblConstanteGravitationnelle);
		lblConstanteGravitationnelle.setHorizontalAlignment(SwingConstants.RIGHT);

		spnConstantG = new JSpinner();
		spnConstantG.setBounds(57, 153, 55, 20);
		panel_8.add(spnConstantG);
		spnConstantG.setModel(new SpinnerNumberModel(9.800, 1.000, 100.000, 0.001));

		JButton btnGnrera = new JButton("G\u00E9n\u00E9rer");
		btnGnrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(GenerationListener ecout: OBJETS_ENREGISTRES.getListeners(GenerationListener.class)){
					double massTank = (double)spnMassTank.getValue();
					double largeurTank = (double)spnLargTank.getValue();
					double hautTank = (double)spnHautTank.getValue();
					double forceMov = (double)spnForceMov.getValue();
					double coeffSol = (double)spnFrotSol.getValue();
					double massVAir = (double)spnMassVAir.getValue();
					double g = (double)spnConstantG.getValue();
					double forceMax = (double)spnForceMax.getValue();
					double massBall = (double)spnMassBalle.getValue();
					double coeffAir = (double)spnCoeffAir.getValue();
					ecout.playerPhysicsGeneration(massTank, largeurTank, hautTank, forceMov, coeffSol, massVAir, g, forceMax, massBall, coeffAir);
				}
			}
		});
		btnGnrera.setBounds(175, 334, 89, 23);
		panel_6.add(btnGnrera);
		
		lblJoueur = new JLabel("Joueur");
		lblJoueur.setBounds(24, 281, 382, 42);
		panel_6.add(lblJoueur);

	}

	public void addEasyListener(GenerationListener objEcout) {
		OBJETS_ENREGISTRES.add(GenerationListener.class, objEcout);		
	}

	public void setEverything(double largeurMonde, double hauteurMonde, Joueur player, double largeurMur, double phiDroit, double phiGauche){
		this.hauteurMonde = hauteurMonde;
		this.largeurMonde = largeurMonde;
		this.player = player;
		this.largeurMur = largeurMur;
		this.phiDroit = phiDroit;
		this.phiGauche = phiGauche;
	}
}