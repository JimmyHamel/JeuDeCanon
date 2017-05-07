package objetsDessinable;

import java.awt.geom.Rectangle2D;

/**
 * Cette classe permet d'instancier des ressorts qui se mettent à chaque bout de l'écran pour
 * permettre au char d'assaut d'avoir un amortisseur.
 * 
 * @author Mitchell Mastromonaco
 * Date de création : 28 mars 2016
 */
public class Ressort extends Body{

	private final double MASS = 1;
	private double omega, amplitude, phi, time, forceInit, k;
	private boolean firstTime = true;
	private String leftOrRight;

	/**
	 * Le constructeur de base d'un objet de type Ressort. 
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses pour l'objet de type Ressort
	 *            en m.
	 * @param posY
	 *            La position sur l'axe des ordonnées pour l'objet de type Ressort
	 *            en m.
	 * @param longueurReelle
	 *            La longueur réelle pour l'objet de type Ressort en m.
	 * @param largeurReelle
	 *            La largeur réelle pour l'objet de type Ressort en m.
	 * @param k La constante de rappel du ressort. 
	 * @param omega La fréquence angulaire naturelle d’oscillation du système masse-ressort en rad/s. 
	 * @param phi La constante de phase en rad.
	 * @param forceInit La force initiale qu'à le système en N.
	 * @param leftOrRight Si le resssort est placé à droite ou à gauche. Valeurs acceptées: left, right.
	 */
	public Ressort(double posX, double posY, double longueurReelle, double largeurReelle, double omega, double phi, double forceInit, double k, String leftOrRight) {
		super(posX, posY, longueurReelle, largeurReelle);
		this.amplitude = longueurReelle;
		this.omega = omega;
		this.k = k;
		this.phi = phi;
		this.forceInit = forceInit;
		this.leftOrRight = leftOrRight;
		creerRepresentationGeometrique();
	}

	/**
	 * Cette méthode calcule la vitesse qu'aura un élément donné après être rentré en collision avec le ressort.
	 * Elle calcule aussi la position et la longueur du ressort.
	 * 
	 * @param deltaT La différence de temps en s.
	 * @param speed La vitesse initiale de l'élément donné en m/s.
	 * @return La nouvelle vitesse de l'élément donné après être rentré en collision avec le ressort.
	 */
	public double forceRessort(double deltaT, double speed){
		this.time += deltaT;
		if(leftOrRight.equals("right")){

			double newLongueur = this.amplitude * Math.sin(this.omega * this.time + this.phi);
			if(newLongueur <= 1.1){
				newLongueur = 1.8;
			} 

			this.setPosX(this.getPosX() + (this.getLongueurReelle() - newLongueur));
			this.setLongueurReelle(newLongueur);
			return this.amplitude * this.omega * Math.cos(this.omega * this.time + this.phi) - speed;
		} else{
			if(leftOrRight.equals("left")){
				double newLongueur = -this.amplitude * Math.sin(this.omega * this.time + this.phi);
				if(newLongueur <= 1.1){
					newLongueur = 1.8;
				}

				this.setLongueurReelle(newLongueur);
				return +this.amplitude * this.omega * Math.cos(this.omega * this.time + this.phi) - speed;
			} else{
				System.out.println("If life gives you lemons, sell them since it's winter and that makes a lot of money.");
				return 0;
			}
		}
	}

	public void contract(String leftOrRight, double deltaT){
		if(leftOrRight.equals("right")){

			double newLongueur = this.amplitude * Math.sin(this.omega * this.time + this.phi);
			if(newLongueur <= 1.1){
				newLongueur = 1.8;
			} 

			this.setPosX(this.getPosX() + (this.getLongueurReelle() - newLongueur));
			this.setLongueurReelle(newLongueur);
		} else{
			if(leftOrRight.equals("left")){
				double newLongueur = -this.amplitude * Math.sin(this.omega * this.time + this.phi);
				if(newLongueur <= 1.1){
					newLongueur = 1.8;
				}

				this.setLongueurReelle(newLongueur);
			} else{
				System.out.println("If life gives you lemons, sell them since it's winter and that makes a lot of money.");
			}
		}
	}
	
	public void creerRepresentationGeometrique(){
		if(forceInit == 0 && !firstTime )
			super.creerRepresentationGeometrique();
		else{
			firstTime = false;
			if(leftOrRight.equals("left")){
				super.setCarre(new Rectangle2D.Double(this.getPosX(),this.getPosY(), this.getLongueurReelle() + calculLongueur(forceInit),this.getLargeurReelle()));
				super.setBoiteDeCollision(new Rectangle2D.Double(this.getPosX(),this.getPosY(), this.getLongueurReelle() + calculLongueur(forceInit),this.getLargeurReelle()));
			} else {
				if(leftOrRight.equals("right")){
					super.setCarre(new Rectangle2D.Double(this.getPosX()- calculLongueur(forceInit),this.getPosY(), this.getLongueurReelle() + calculLongueur(forceInit),this.getLargeurReelle()));
					super.setBoiteDeCollision(new Rectangle2D.Double(this.getPosX() - calculLongueur(forceInit),this.getPosY(), this.getLongueurReelle() + calculLongueur(forceInit),this.getLargeurReelle()));
				} else
					System.out.println("If life gives you lemons, make grape juice.");
			}
		}
	}
	
	/**
	 * Une petite méthode private qui aide la méthode creerRepresentationGeometrique à trouver l'emplacement exact
	 * du ressort en fonction de sa force initiale.
	 * 
	 * @param force La force exercée sur le ressort en N
	 * @return La longueur par laquelle le ressort est diminué par cette force en m
	 */
	private double calculLongueur(double force){
		return - force / (this.MASS * Math.pow(this.omega, 2));
	}

	/**
	 * Cette méthode retourne la constante de rappel. 
	 * @return La constante de rappel.
	 */
	public double getK() {
		return k;
	}
	
	/**
	 * Cette méthode fixe la constante de rappel. 
	 * @param k La constante de rappel. 
	 */
	public void setK(double k) {
		this.k = k;
	}
}
