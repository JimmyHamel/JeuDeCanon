package fenetres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import interfaces.OptionsToApplicationListener;
/**
 * La fenêtre Options est une fenêtre où plusieurs options s'offrent à l'utilisateur. 
 * 
 * @author Mitchell Mastromonaco
 * Date de création: 16 février 2016
 */
public class FenetreOption extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCodeDeTriche;
	private JTextField textFieldTriche;
	private JLabel lblModeScientifique;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private boolean science, euler, eulerI, rk4;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	FenetreGraphique fGraphique = new FenetreGraphique();



	/**
	 * Create the frame.
	 */
	public FenetreOption() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Options");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodeDeTriche = new JLabel("Code de triche:");
		lblCodeDeTriche.setBounds(22, 11, 111, 14);
		contentPane.add(lblCodeDeTriche);

		textFieldTriche = new JTextField();
		textFieldTriche.setBounds(103, 11, 86, 20);
		contentPane.add(textFieldTriche);
		textFieldTriche.setColumns(10);

		JButton btnDmarrerLaTriche = new JButton("D\u00E9marrer la triche");
		btnDmarrerLaTriche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cheatAccepted = false;
				switch(textFieldTriche.getText()){
				case "seizure":
					cheatAccepted = true;
					break;
				case "progress":
					cheatAccepted = true;
					break;
				}
				if(cheatAccepted){
					JOptionPane.showMessageDialog(null, "Cette triche est acceptée.");
					for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
						ecout.cheatOption(textFieldTriche.getText());
					} 
					textFieldTriche.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "Cette triche n'est pas acceptée.");
					textFieldTriche.setText("");
				}
			}
		});
		btnDmarrerLaTriche.setBounds(237, 7, 119, 23);
		contentPane.add(btnDmarrerLaTriche);

		JLabel lblModes = new JLabel("Modes de calcul:  ");
		lblModes.setBounds(22, 93, 86, 14);
		contentPane.add(lblModes);

		lblModeScientifique = new JLabel("Mode scientifique:");
		lblModeScientifique.setBounds(22, 179, 111, 14);
		contentPane.add(lblModeScientifique);

		JRadioButton rdbtnActiver = new JRadioButton("Activer");
		rdbtnActiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				science = true;
				for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
					ecout.scienceOption(science);
				}
			}
		});
		buttonGroup.add(rdbtnActiver);
		rdbtnActiver.setBounds(150, 175, 109, 23);
		contentPane.add(rdbtnActiver);

		JRadioButton rdbtnDsactiver = new JRadioButton("D\u00E9sactiver");
		rdbtnDsactiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				science = false;
				for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
					ecout.scienceOption(science);
				}
			}
		});
		rdbtnDsactiver.setSelected(true);
		buttonGroup.add(rdbtnDsactiver);
		rdbtnDsactiver.setBounds(261, 175, 109, 23);
		contentPane.add(rdbtnDsactiver);

		JRadioButton rdbtnEuler = new JRadioButton("Euler");
		rdbtnEuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				euler = true;
				eulerI = false;
				rk4 = false;
				for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
					ecout.calculOption(euler, eulerI, rk4);
				}
			}
		});
		buttonGroup_1.add(rdbtnEuler);
		rdbtnEuler.setBounds(114, 89, 109, 23);
		contentPane.add(rdbtnEuler);

		JRadioButton rdbtnEulerInvers = new JRadioButton("Euler Invers\u00E9");
		rdbtnEulerInvers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				euler = false;
				eulerI = true;
				rk4 = false;
				for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
					ecout.calculOption(euler, eulerI, rk4);
				}
			}
		});
		buttonGroup_1.add(rdbtnEulerInvers);
		rdbtnEulerInvers.setBounds(225, 89, 109, 23);
		contentPane.add(rdbtnEulerInvers);

		JRadioButton rdbtnRongekuttaDordre = new JRadioButton("Runge-Kutta d'ordre 4");
		rdbtnRongekuttaDordre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				euler = false;
				eulerI = false;
				rk4 = true;
				for(OptionsToApplicationListener ecout: OBJETS_ENREGISTRES.getListeners(OptionsToApplicationListener.class)){
					ecout.calculOption(euler, eulerI, rk4);
				}
			}
		});
		rdbtnRongekuttaDordre.setSelected(true);
		buttonGroup_1.add(rdbtnRongekuttaDordre);
		rdbtnRongekuttaDordre.setBounds(134, 115, 142, 23);
		contentPane.add(rdbtnRongekuttaDordre);

		JLabel lblGraphiqueDePosition = new JLabel("Graphique de position:");
		lblGraphiqueDePosition.setBounds(22, 224, 111, 14);
		contentPane.add(lblGraphiqueDePosition);

		JRadioButton rdbtnActiver_1 = new JRadioButton("Activer");
		rdbtnActiver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGraphique.setVisible(true);
			}
		});
		buttonGroup_2.add(rdbtnActiver_1);
		rdbtnActiver_1.setBounds(150, 220, 86, 23);
		contentPane.add(rdbtnActiver_1);

		JRadioButton rdbtnDsactiver_1 = new JRadioButton("D\u00E9sactiver");
		rdbtnDsactiver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGraphique.setVisible(false);
			}
		});
		buttonGroup_2.add(rdbtnDsactiver_1);
		rdbtnDsactiver_1.setSelected(true);
		rdbtnDsactiver_1.setBounds(261, 220, 109, 23);
		contentPane.add(rdbtnDsactiver_1);
	}
	public void addOptionsToApplicationListener(OptionsToApplicationListener objEcout) {
		OBJETS_ENREGISTRES.add(OptionsToApplicationListener.class, objEcout);		
	}
	public void updatePositionsGraphique(ArrayList<Double> points) {
		this.fGraphique.updatePositionsGraphique(points);
	}

}

