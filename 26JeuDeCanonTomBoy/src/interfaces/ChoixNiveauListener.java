package interfaces;

import java.util.EventListener;
/**
 * Ecouteur personnalise detectant les changements de niveaux
 * @author Jimmy
 * Date de création : 1er mars 2016
 */

public interface ChoixNiveauListener extends EventListener {
	/**
	 * Ecouteur detectant un changement de niveau
	 * @param niveauSelectionne = nom du niveau pour lequel l'utilisateur a change
	 */
	public void ChangementNiveau(String niveauSelectionne);
	
}
