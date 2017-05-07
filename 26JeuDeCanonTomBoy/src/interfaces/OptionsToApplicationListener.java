package interfaces;
import java.util.EventListener;
/**
 * Un �couteur personnalis� qui est utilis� dans le cadre de FentreOptions pour que l'application principal puisse savoir ce qu'il fait. 
 * @author Mitchell Mastromonaco
 *
 */
public interface OptionsToApplicationListener extends EventListener{
	/**
	 * Cette m�thode v�rifie si le mode scientifique devrait �tre utilis� ou non. 
	 * @param science si le mode scientifique devrait �tre utilis� ou non. 
	 */
	public void scienceOption(boolean science);
	/**
	 * Cette m�thode v�rifie si l'une des trois m�thodes de calcul est en utilisation. 
	 * @param euler Si la m�thode d'Euleer est utilis�e.
	 * @param eulerI Si la m�thode d'Euler invers� est utilis�e. 
	 * @param rk4 Si la m�thode de Runge-Kutta d'ordre 4 est utilis�e. 
	 */
	public void calculOption(boolean euler, boolean eulerI, boolean rk4);
	/**
	 * Cette m�thode retourne la triche qui sera ex�cut�e.
	 * @param cheat Le nom de la triche utilis�e.
	 */
	public void cheatOption(String cheat);
}
	