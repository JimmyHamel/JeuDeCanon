package utilite;


import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

//Cette classe permet de creer des matrices permettant de convertir entre unites du monde reel et pixels
//Elle est typiquement utilisee par des composants personnalises ou la geometrie est exprimee en unites du monde reeel.
//Auteur: C.Houle 
 

public class ModelePhysique {
	private double hautUnitesReelles;
	private double largUnitesReelles;
	private double largPixels;
	private double hautPixels;
	private double xOrigUnitesReelle;
	private double yOrigUnitesReelle;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private AffineTransform matMondeVersComposant, matComposantVersMonde;
	private boolean inverserEnY = false;
	
	/* Constructeur: creation du modele et generation des matrices de transformation.
	 * 
	 * largPixels Largeur en pixels du composant
	 * hautPixels Hauteur en pixels du composant
	 * xOrigUnitesReelle Origine en x en unite reelles
	 * yOrigUnitesReelle Origine en y en unite reelles
	 * largUnitesReelles Largeur totale en unites reelles
	 * hautUnitesReelles Hauteur totale en unites reelles
	 */
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double largUnitesReelles, double hautUnitesReelles) {
		//avec ce constructeur, on specifie meme la hauteur en unites reelles (il pourrait donc y avoir une distortion)
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.xOrigUnitesReelle = xOrigUnitesReelle;
		this.yOrigUnitesReelle = yOrigUnitesReelle;
		this.largUnitesReelles = largUnitesReelles;	
		this.hautUnitesReelles = hautUnitesReelles;
		
		initialierLeReste();
	}
	
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double largUnitesReelles) {		
		//avec ce constructeur, on specifie seulement la largeutr en unites reelles: la hauteur sera calulee de maniere a n'introduire
		//aucune distortion (les cercles resteront des cercles!)
		double ratioEnPixels = hautPixels/largPixels;
		this.hautUnitesReelles = largUnitesReelles * ratioEnPixels;
		
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.xOrigUnitesReelle = xOrigUnitesReelle;
		this.yOrigUnitesReelle = yOrigUnitesReelle;
		this.largUnitesReelles = largUnitesReelles;	
		
		initialierLeReste();
	}
	
	
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double largUnitesReelles, boolean inverserEnY) {		
		//avec ce constructeur, on specifie seulement la largeutr en unites reelles: la hauteur sera calulee de maniere a n'introduire
		//aucune distortion (les cercles resteront des cercles!)
		double ratioEnPixels = hautPixels/largPixels;
		this.hautUnitesReelles = largUnitesReelles * ratioEnPixels;
		
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.xOrigUnitesReelle = xOrigUnitesReelle;
		this.yOrigUnitesReelle = yOrigUnitesReelle;
		this.largUnitesReelles = largUnitesReelles;	
		this.inverserEnY = inverserEnY;
		
		initialierLeReste();
	}
	
	private void initialierLeReste() {
		pixelsParUniteX = largPixels / largUnitesReelles;
		pixelsParUniteY = hautPixels / hautUnitesReelles ;

		AffineTransform mat = new AffineTransform();  //donne une matrice identite
		
		mat.scale( pixelsParUniteX, pixelsParUniteY ); 
		mat.translate(xOrigUnitesReelle, yOrigUnitesReelle);
		
		if (inverserEnY)
			mat.scale(1, -1);
		
		this.matMondeVersComposant = mat;
		
		//on cree l'inverse de la matrice (pourrait parfois etre utile)
		try {
			this.matComposantVersMonde = mat.createInverse();
		} 
		catch (NoninvertibleTransformException e) {
			System.out.println("Erreur: impossible d'inverser la matrice " );
		} 
	}//fin methode privee
	
	// Ci-dessous, plusieurs getters utilitaires
	
	public AffineTransform getMatMondeVersComposant() {
		//retourner une copie de la matrice -- ainsi le programmeur pourra la modifier sans affecter l'original
		return new AffineTransform(matMondeVersComposant);
	}

	public AffineTransform getMatComposantVersMonde() {
		//retourner une copie de la matrice -- ainsi le programmeur pourra la modifier sans affecter l'original
		return new AffineTransform(matComposantVersMonde);
	} 

	public double getHautUnitesReelles() {
		return hautUnitesReelles;
	}

	public double getLargUnitesReelles() {
		return largUnitesReelles;
	}


	public double getPixelsParUniteX() {
		return pixelsParUniteX;
	}

	public double getPixelsParUniteY() {
		return pixelsParUniteY;
	}
	
	public void setInverserEnY(boolean inverserEnY) {
		this.inverserEnY = inverserEnY;
		
		AffineTransform mat = new AffineTransform();
		mat.scale( pixelsParUniteX, pixelsParUniteY );
		mat.translate(xOrigUnitesReelle, yOrigUnitesReelle);
		
		if (inverserEnY)
			mat.scale(1, -1);
		
		this.matMondeVersComposant = mat;
		
		//on cree l'inverse de la matrice (pourrait parfois etre utile)
		try {
			this.matComposantVersMonde = mat.createInverse();
		} 
		catch (NoninvertibleTransformException e) {
			System.out.println("Erreur: impossible d'inverser la matrice " );
		} 
	}

}
