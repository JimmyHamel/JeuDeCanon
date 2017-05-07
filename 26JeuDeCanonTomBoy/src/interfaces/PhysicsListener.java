package interfaces;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EventListener;

import utilite.Profil;
/**
 * Un �couteur qui permet la visualisation des param�tres physiquees d'une classe � une autre.
 * @author Mitchell Mastromonaco
 *
 */
public interface PhysicsListener extends EventListener{
	/**
	 * Cette m�thode v�rifie la vitesse du char d'assaut en m/s.
	 * @param tankSpeed La vitesse du char d'assaut en m/s.
	 */
	public void checkTankSpeed(double tankSpeed);
	/**
	 * Cette m�thode v�rifie la vitesse de la balle sur l'axe des ordonn�es en m/s.
	 * @param bulletSpeedY La vitesse de la balle sur l'axe des ordonn�es en m/s.
	 */
	public void checkBulletSpeedY(double bulletSpeedY);
	/**
	 * Cette m�thode v�rifie la vitesse de la balle sur l'axe des abscisses en m/s.
	 * @param bulletSpeedX La vitesse de la balle sur l'axe des abscisses en m/s.
	 */
	public void checkBulletSpeedX(double bulletSpeedX);
	/**
	 * Cette m�thode v�rifie le module de la vitesse de la balle en m/s.
	 * @param bulletSpeedTotal Le module de la vitesse de la balle en m/s.
	 */
	public void checkBulletSpeedTotal(double bulletSpeedTotal);
	/**
	 * Cette m�thode v�rifie l'acc�l�ration magn�tique exerc�e sur la balle sur l'axe des abscisses en m/s<sup>2</sup>.
	 * @param accelMagneticX L'acc�l�ration magn�tique exerc�e sur la balle sur l'axe des abscisses en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticX(double accelMagneticX);
	/**
	 * Cette m�thode v�rifie l'acc�l�ration magn�tique exerc�e sur la balle sur l'axe des ordonn�es en m/s<sup>2</sup>.
	 * @param accelMagneticY L'acc�l�ration magn�tique exerc�e sur la balle sur l'axe des ordonn�es en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticY(double accelMagneticY);
	/**
	 * Cette m�thode v�rifie le module de l'acc�l�ration exerc�e sur la balle en m/s<sup>2</sup>.
	 * @param accelMagneticTotal Le module de l'acc�l�ration exerc�e sur la balle en m/s<sup>2</sup>.
	 */
	public void checkAccelMagneticTotal(double accelMagneticTotal);
	/**
	 * Cette m�thode v�rifie la force magn�tique exerc�e sur la balle sur l'axe des abscisses en N.
	 * @param forceMagneticX La force magn�tique exerc�e sur la balle sur l'axe des abscisses en N.
	 */
	public void checkForceMagneticX(double forceMagneticX);
	/**
	 * Cette m�thode v�rifie la force magn�tique exerc�e sur la balle sur l'axe des ordonn�es en N.
	 * @param forceMagneticY La force magn�tique exerc�e sur la balle sur l'axe des ordonn�es en N.
	 */
	public void checkForceMagneticY(double forceMagneticY);
	/**
	 * Cette m�thode v�rifie le module de la force magn�tique exerc�e sur la balle en N.
	 * @param ForceMagneticTotal Le module de la force magn�tique exerc�e sur la balle en N.
	 */
	public void checkForceMagneticTotal(double ForceMagneticTotal);
	/**
	 * Cette m�thode v�rifie la constante gravitationnelle <i>g</i> en m/s<sup>2</sup>.
	 * @param accelGravita La constante gravitationnelle <i>g</i> en m/s<sup>2</sup>.
	 */
	public void accelGravitationelle(double accelGravita);
	/**
	 * Cette m�thode v�rifie la force de r�sistance de l'air exerc�e sur la balle en N.
	 * @param frictionAir La force de r�sistance de l'air exerc�e sur la balle en N.
	 */
	public void forceFrictionAir(double frictionAir);
	/**
	 * Cette m�thode v�rifie si le jeu est en marche.
	 * @param running Si le jeu est en marche.
	 */
	public void isRunning(boolean running);
	/**
	 * Cette m�thode ajoute un profil au jeu. 
	 * @param profil Un nouveau profil.
	 */
	public void profilAdded(Profil profil);
	/**
	 * Cette m�thode v�rifie si la personne a r�ussie le jeu ou a perdu.
	 * @param reussite un String qui est soit reussi ou perdu.
	 */
	public void fin(String reussite);
	/**
	 * Cette m�thode permet d'avoir un Arraylist de la position de la balle en x et y selon le temps. 
	 * @param points un ArayList de la position de la balle en x et y selon le temps. 
	 */
	public void positionBalle(ArrayList<Point2D.Double> points);
	

	

}
