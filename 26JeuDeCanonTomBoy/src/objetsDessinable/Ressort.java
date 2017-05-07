package objetsDessinable;

import java.awt.geom.Rectangle2D;

/**
 * Cette classe permet d'instancier des ressorts qui se mettent � chaque bout de l'�cran pour
 * permettre au char d'assaut d'avoir un amortisseur.
 * 
 * @author Mitchell Mastromonaco
 * Date de cr�ation : 28 mars 2016
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
	 *            La position sur l'axe des ordonn�es pour l'objet de type Ressort
	 *            en m.
	 * @param longueurReelle
	 *            La longueur r�elle pour l'objet de type Ressort en m.
	 * @param largeurReelle
	 *            La largeur r�elle pour l'objet de type Ressort en m.
	 * @param k La constante de rappel du ressort. 
	 * @param omega La fr�quence angulaire naturelle d�oscillation du syst�me masse-ressort en rad/s. 
	 * @param phi La constante de phase en rad.
	 * @param forceInit La force initiale qu'� le syst�me en N.
	 * @param leftOrRight Si le resssort est plac� � droite ou � gauche. Valeurs accept�es: left, right.
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
	 * Cette m�thode calcule la vitesse qu'aura un �l�ment donn� apr�s �tre rentr� en collision avec le ressort.
	 * Elle calcule aussi la position et la longueur du ressort.
	 * 
	 * @param deltaT La diff�rence de temps en s.
	 * @param speed La vitesse initiale de l'�l�ment donn� en m/s.
	 * @return La nouvelle vitesse de l'�l�ment donn� apr�s �tre rentr� en collision avec le ressort.
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
	 * Une petite m�thode private qui aide la m�thode creerRepresentationGeometrique � trouver l'emplacement exact
	 * du ressort en fonction de sa force initiale.
	 * 
	 * @param force La force exerc�e sur le ressort en N
	 * @return La longueur par laquelle le ressort est diminu� par cette force en m
	 */
	private double calculLongueur(double force){
		return - force / (this.MASS * Math.pow(this.omega, 2));
	}

	/**
	 * Cette m�thode retourne la constante de rappel. 
	 * @return La constante de rappel.
	 */
	public double getK() {
		return k;
	}
	
	/**
	 * Cette m�thode fixe la constante de rappel. 
	 * @param k La constante de rappel. 
	 */
	public void setK(double k) {
		this.k = k;
	}
}
