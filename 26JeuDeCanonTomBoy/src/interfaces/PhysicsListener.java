package interfaces;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EventListener;

import utilite.Profil;
/**
 * Un écouteur qui permet la visualisation des paramètres physiquees d'une classe à une autre.
 * @author Mitchell Mastromonaco
 *
 */
public interface PhysicsListener extends EventListener{
	/**
	 * Cette méthode vérifie la vitesse du char d'assaut en m/s.
	 * @param tankSpeed La vitesse du char d'assaut en m/s.
	 */
	public void checkTankSpeed(double tankSpeed);
	/**
	 * Cette méthode vérifie la vitesse de la balle sur l'axe des ordonnées en m/s.
	 * @param bulletSpeedY La vitesse de la balle sur l'axe des ordonnées en m/s.
	 */
	public void checkBulletSpeedY(double bulletSpeedY);
	/**
	 * Cette méthode vérifie la vitesse de la balle sur l'axe des abscisses en m/s.
	 * @param bulletSpeedX La vitesse de la balle sur l'axe des abscisses en m/s.
	 */
	public void checkBulletSpeedX(double bulletSpeedX);
	/**
	 * Cette méthode vérifie le module de la vitesse de la balle en m/s.
	 * @param bulletSpeedTotal Le module de la vitesse de la balle en m/s.
	 */
	public void checkBulletSpeedTotal(double bulletSpeedTotal);
	/**
	 * Cette méthode vérifie l'accélération magnétique exercée sur la balle sur l'axe des abscisses en m/s<sup>2</sup>.
	 * @param accelMagneticX L'accélération magnétique exercée sur la balle sur l'axe des abscisses en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticX(double accelMagneticX);
	/**
	 * Cette méthode vérifie l'accélération magnétique exercée sur la balle sur l'axe des ordonnées en m/s<sup>2</sup>.
	 * @param accelMagneticY L'accélération magnétique exercée sur la balle sur l'axe des ordonnées en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticY(double accelMagneticY);
	/**
	 * Cette méthode vérifie le module de l'accélération exercée sur la balle en m/s<sup>2</sup>.
	 * @param accelMagneticTotal Le module de l'accélération exercée sur la balle en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticTotal(double accelMagneticTotal);
	/**
	 * Cette méthode vérifie la force magnétique exercée sur la balle sur l'axe des abscisses en N.
	 * @param forceMagneticX La force magnétique exercée sur la balle sur l'axe des abscisses en N.
	 */
	public void checkForceMagneticX(double forceMagneticX);
	/**
	 * Cette méthode vérifie la force magnétique exercée sur la balle sur l'axe des ordonnées en N.
	 * @param forceMagneticY La force magnétique exercée sur la balle sur l'axe des ordonnées en N.
	 */
	public void checkForceMagneticY(double forceMagneticY);
	/**
	 * Cette méthode vérifie le module de la force magnétique exercée sur la balle en N.
	 * @param ForceMagneticTotal Le module de la force magnétique exercée sur la balle en N.
	 */
	public void checkForceMagneticTotal(double ForceMagneticTotal);
	/**
	 * Cette méthode vérifie la constante gravitationnelle <i>g</i> en m/s<sup>2</sup>.
	 * @param accelGravita La constante gravitationnelle <i>g</i> en m/s<sup>2</sup>.
	 */
	public void accelGravitationelle(double accelGravita);
	/**
	 * Cette méthode vérifie la force de résistance de l'air exercée sur la balle en N.
	 * @param frictionAir La force de résistance de l'air exercée sur la balle en N.
	 */
	public void forceFrictionAir(double frictionAir);
	/**
	 * Cette méthode vérifie si le jeu est en marche.
	 * @param running Si le jeu est en marche.
	 */
	public void isRunning(boolean running);
	/**
	 * Cette méthode ajoute un profil au jeu. 
	 * @param profil Un nouveau profil.
	 */
	public void profilAdded(Profil profil);
	/**
	 * Cette méthode vérifie si la personne a réussie le jeu ou a perdu.
	 * @param reussite un String qui est soit reussi ou perdu.
	 */
	public void fin(String reussite);
	/**
	 * Cette méthode permet d'avoir un Arraylist de la position de la balle en x et y selon le temps. 
	 * @param points un ArayList de la position de la balle en x et y selon le temps. 
	 */
	public void positionBalle(ArrayList<Point2D.Double> points);
	

	

}
