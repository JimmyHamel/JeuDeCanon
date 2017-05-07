package interfaces;

import java.util.ArrayList;
import java.util.EventListener;

import objetsDessinable.Ennemies;
import objetsDessinable.Ressort;

/**
 * Cet �couteur personnalis� permet de g�n�rer des ennemis, des ressorts et la physique du Joueur lorsqu'indiqu� dans une classe. 
 * 
 * @author Mitchell Mastromonaco
 * Date de cr�ation: 1 avril 2016
 */
public interface GenerationListener extends EventListener{
	/**
	 * Cette m�thode g�n�re un ArrayList d'objets de type Ennemies
	 * @param ennemies un ArrayList d'objets de type Ennemies
	 */
	public void ennemiesGeneration(ArrayList<Ennemies> ennemies);
	/**
	 * Cette m�thode g�n�re deux ressort, un � droite de l'�cran et l'autre � gauche. 
	 * @param ressortGauche Le ressort de gauche.
	 * @param ressortDroit Le ressort de droite.
	 */
	public void ressortsGeneration(Ressort ressortGauche, Ressort ressortDroit);
	/**
	 * 
	 * @param massTank La masse du char d'assaut en kg. 
	 * @param largeurTank  La largeur r�elle du char d'assaut en m.
	 * @param hautTank La hauteur r�elle du char d'assaut en m. 
	 * @param forceMov La force de mouvement du char d'assaut en N.
	 * @param coeffSol Le coefficient de frottement cin�tique du sol.
	 * @param massVAir La masse volumique de l'air.
	 * @param g La constante gravitationnelle en m/s<sup>2</sup>
	 * @param forceMax La force maximale que peut exerc�e le canon sur la balle en m/s.
	 * @param massBall La masse de la balle en kg.
	 * @param coeffAir Le coefficient de train�e de la balle. 
	 */
	public void playerPhysicsGeneration(double massTank, double largeurTank, double hautTank, double forceMov, double coeffSol, double massVAir, double g, double forceMax, double massBall, double coeffAir);
}
