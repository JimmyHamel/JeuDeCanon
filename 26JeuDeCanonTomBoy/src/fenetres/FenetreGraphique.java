package fenetres;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import composantsGraphiques.Graphique;
/**
 * Cette classe est une fenêtre contenant un graphique de la position x en fonction de la position y de la balle du joueur
 * @author Jimmy
 * Date de création : 1er mars 2016
 */
public class FenetreGraphique extends JFrame {
	
	private static final long serialVersionUID = 7761966118376009968L;
	private JPanel contentPane;
	Graphique graphique;
	private JTabbedPane tabbedPane;
	private JColorChooser colorChooser;

	/**
	 * Create the frame.
	 */
	public FenetreGraphique() {
		setAlwaysOnTop(true);
		setBounds(500, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Position X en fonction de Y");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 484, 361);
		contentPane.add(tabbedPane);
		
		graphique = new Graphique();
		graphique.setBounds(0, 0, 484, 361);
		
		colorChooser = new JColorChooser();
		
		tabbedPane.addTab("Graphique", null, graphique, null);
		tabbedPane.addTab("Couleur", colorChooser);
		
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				graphique.setCouleur(colorChooser.getColor());
				
			}
		});
		

	}
/**
 * Methode permettant de modifier les poitns du graphique
 * @param points = liste de points pour le graphique
 */
	public void updatePositionsGraphique(ArrayList<Double> points) {
		this.graphique.setArrayPoint(points);
	}
}
