package sauvegardeProfils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utilite.Profil;

/**
 * 
 * @author Jimmy
 *  Classe permettant de sauvegarder et charger des profils utilisateurs
 *
 */
public class SauvegardeProfils {

	/**
	 * Methode static permettant de sauvegarder un profil Si Il n'y a pas de
	 * dossier du nom de TomBoy sur le bureau, la méthode en créé un et met le
	 * fichier text a l'intérieur.
	 * 
	 * @param profil = profil devant etre sauvegarder
	 */

	public static void save(Profil profil) {
		try {

			File folder = new File(System.getProperty("user.home") + "/Desktop/TomBoy");
			folder.mkdirs();

			if (!folder.exists()) {
				folder.createNewFile();
			}

			File file = new File(System.getProperty("user.home") + "/Desktop/TomBoy/" + profil.getNom() + ".tomboy");

			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(profil.getNom() + "+" + profil.getNiveau() + "+" + profil.getIndexImage());
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cette méthode tokenise les fichier text de sauvegarde et retourne un
	 * profil a propos des informations tokenisées.
	 * 
	 * @param fichier = fichier .txt d'un profil de joueur
	 * @return =  Le profil correspondant aux informations tokenisees
	 */
	public static Profil loadProfil(File fichier)  {
		FileReader fr;
		BufferedReader bf;
		Profil profil = null;
		try {
			fr = new FileReader(fichier);
			bf = new BufferedReader(fr);
			String profilString = bf.readLine();
			int indexPremier = profilString.indexOf("+");
			int indexDernier = profilString.lastIndexOf("+");
			String nom = profilString.substring(0, indexPremier);
			int niveau = Integer.parseInt(profilString.substring(indexPremier + 1, indexDernier));
			int image = Integer.parseInt(profilString.charAt(profilString.length() - 1)+"");
			profil = new Profil(nom,niveau, image);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return profil;

	}
}
