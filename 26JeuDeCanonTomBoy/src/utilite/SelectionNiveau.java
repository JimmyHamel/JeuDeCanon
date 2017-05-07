package utilite;

import objetsDessinable.Niveaux;

/**
 * Classe ayant des m�thodes static retournant un niveau specifique selon le numero du niveau envoy� en parametre
 * @author Jimmy
 * Date de cr�ation : 8 f�vrier 2016
 */
public abstract class SelectionNiveau {

	private static double g, coeffTraine, masseAir, champMagnetique, charge;
	private static int qualite;

	public static Niveaux loadCreatedLevel(double longueurMonde, double hauteurMonde, int niveau) {

		switch(niveau){
		case 1:

			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_1" );
		case 2:
			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_2" );
		case 3:
			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_3" );
		case 4:
			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_4" );
		case 5:
			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_5" );
		case 6:
			g = 8.87;
			coeffTraine = 0.0025;
			masseAir = 0.67;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_6" );
		case 7:
			g = 8.87;
			coeffTraine = 0.0025;
			masseAir = 0.67;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_7" );
		case 8:
			g = 8.87;
			coeffTraine = 0.0025;
			masseAir = 0.67;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_8" );
		case 9:
			g = 8.87;
			coeffTraine = 0.0025;
			masseAir = 0.67;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_9" );
		case 10:
			g = 8.87;
			coeffTraine = 0.0025;
			masseAir = 0.67;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_10" );
		case 11:
			g = 24.7964;
			coeffTraine = 0.0025;
			masseAir = 0.16;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.4;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_11" );
		case 12:
			g = 24.7964;
			coeffTraine = 0.0025;
			masseAir = 0.16;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.4;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_12" );
		case 13:
			g = 24.7964;
			coeffTraine = 0.0025;
			masseAir = 0.16;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.4;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_13" );
		case 14:
			g = 24.7964;
			coeffTraine = 0.0025;
			masseAir = 0.16;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.4;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_14" );
		
		default:
			g = 9.8;
			coeffTraine = 0.0025;
			masseAir = 1.217;
			qualite = 1;
			champMagnetique = 0.001;
			charge = 0.8;

			return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
					masseAir,qualite, champMagnetique, charge, "niveau_1" ); 

		}
	}
	/**
	 * Methode retournant un niveau de base por le niveau editable
	 * @param longueurMonde = longueur du monde en valeur relle
	 * @param hauteurMonde = hauteur du monde en valeur reel
	 * @return = niveau avec des valeur de base pour le niveau editable
	 */
	public static Niveaux loadEditableLevel(double longueurMonde, double hauteurMonde) {
		g = 9.8;
		coeffTraine = 0.0025;
		masseAir = 1.217;
		qualite = 1;
		champMagnetique = 0.001;
		charge = 0.8;

		return new Niveaux(longueurMonde, hauteurMonde, g, coeffTraine, 
				masseAir,qualite, champMagnetique, charge, "niveau_editable"  ); 
	}
}
