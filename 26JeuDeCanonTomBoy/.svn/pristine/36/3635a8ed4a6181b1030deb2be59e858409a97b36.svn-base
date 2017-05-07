package interfaces;
import java.util.EventListener;
/**
 * Un écouteur personnalisé qui est utilisé dans le cadre de FentreOptions pour que l'application principal puisse savoir ce qu'il fait. 
 * @author Mitchell Mastromonaco
 *
 */
public interface OptionsToApplicationListener extends EventListener{
	/**
	 * Cette méthode vérifie si le mode scientifique devrait être utilisé ou non. 
	 * @param science si le mode scientifique devrait être utilisé ou non. 
	 */
	public void scienceOption(boolean science);
	/**
	 * Cette méthode vérifie si l'une des trois méthodes de calcul est en utilisation. 
	 * @param euler Si la méthode d'Euleer est utilisée.
	 * @param eulerI Si la méthode d'Euler inversé est utilisée. 
	 * @param rk4 Si la méthode de Runge-Kutta d'ordre 4 est utilisée. 
	 */
	public void calculOption(boolean euler, boolean eulerI, boolean rk4);
	/**
	 * Cette méthode retourne la triche qui sera exécutée.
	 * @param cheat Le nom de la triche utilisée.
	 */
	public void cheatOption(String cheat);
}
	