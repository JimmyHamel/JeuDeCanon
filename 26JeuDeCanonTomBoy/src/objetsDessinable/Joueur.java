package objetsDessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utilite.SVector3d;
/**
 * La classe Joueur cr�e un objet qui est le personnage principal du jeu. Cet objet g�re
 * la physique du programme et beaucoup des objets virtuels � l'int�rieur de l'application.
 * 
 * @author Mitchell Mastromonaco 
 * Date de cr�ation : 8 f�vrier 2016
 */
public class Joueur extends Body {

	private Bullet balle;
	private Body barrel;
	private double angle; // en degr�s
	private final double MAX_ANGLE = 100;
	private double coeffAir, coeffSol;
	private double massVAir, massBall, massTank;
	private double vitesseInitialeBall, forceDeMov, maxForceCanon;
	private double speedTank = 0, speedBallX = 0, speedBallY = 0;
	private boolean shooting = false;
	private boolean magnetisme;
	private Ennemies ennemieMagnetique;
	private double chargeEnnemie;
	private double accelMagnX, accelMagnY;
	private double forceMagnX, forceMagnY;
	private SVector3d  champMagnetique;
	private SVector3d forceMagnetique;
	private double forceFrictionAirX, forceFrictionAirY;
	private double longueurMonde;


	/**
	 * Le constructeur de Joueur. Comme objet d�riv� de Body, il a la position
	 * en x et en y et sa longueur et sa largeur. Les autres param�tres sont
	 * utilis�s pour le calcul de position de la balle. Ce constructeur utilise
	 * une variable de qualit� qui d�termine beaucoup des variables toutes
	 * seules. Ce constructeur n'utilise aucun Image pour dessiner l'objet de
	 * type Joueur.
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses pour le joueur en m.
	 * @param posY
	 *            La position sur l'axe des ordonn�es pour le joueur en m.
	 * @param longueurReelle
	 *            La longueur r�elle du joueur en m.
	 * @param largeurReelle
	 *            La largeur r�elle du joueur en m.
	 * @param coeffAir
	 *            Le coefficient de train�e. Ce coefficient n'a pas de grandeur
	 *            physique.
	 * @param massVAir
	 *            La masse volumique de l'air dans lequel la balle se d�placera
	 *            en kg/m^3.
	 * @param qualite
	 *            Un nombre entre 1 et 5 qui d�montre la qualit� du char o� 1
	 *            est le pire et 5 est le meilleur.
	 * @param champMagnetique Le champ magn�tique exerc�e par un ennemi sur la balle du Joueur en T.
	 * @param chargeEnnemie La charge magn�tique que poss�de les ennemis en C.
	 */
	public Joueur(double posX, double posY, double longueurReelle, double largeurReelle, double coeffAir,
			double massVAir, int qualite,double champMagnetique, double chargeEnnemie, double longueurMonde) {
		super(posX, posY, longueurReelle, largeurReelle);
		this.angle = 90;
		this.coeffAir = coeffAir;
		this.massVAir = massVAir;
		this.longueurMonde = longueurMonde;
		quality(qualite);
		maxForceCanon = vitesseInitialeBall;
		this.champMagnetique = new SVector3d(0,0,champMagnetique);
		this.forceMagnetique = new SVector3d(0,0,0);
		this.chargeEnnemie = chargeEnnemie;

		creerRepresentationGeometrique();
	}

	/**
	 * Cette m�thode permet � la classe de dessiner un objet dans un contexte de
	 * graphiques en deux dimensions et avec une certaine matrice de
	 * transformation.
	 * 
	 * @param g2d
	 *            Ce param�tre est le contexte graphique o� l'objet d'une classe
	 *            dessinable voudra �tre dessin�e.
	 * @param mat
	 *            Ce param�tre est toutes les transformations qui sont
	 *            appliqu�es � l'objet qui impl�mente la classe Dessinable.
	 */
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matMC = (AffineTransform) mat.clone();
		Color couleurAvant = g2d.getColor();

		if (shooting) {
			g2d.setColor(Color.GRAY);
			this.balle.dessiner(g2d, matMC);
		}


		matMC.rotate(Math.toRadians(angle), barrel.getPosX() + barrel.getLongueurReelle() / 2, barrel.getPosY());
		g2d.setColor(couleurAvant.darker());
		barrel.dessiner(g2d, matMC);
		g2d.setColor(couleurAvant);
		super.dessiner(g2d, mat);
		
	}

	/**
	 * Cette m�thode cr�e une repr�sentation g�om�trique de l'objet dessinable.
	 */
	@Override
	public void creerRepresentationGeometrique() {
		super.creerRepresentationGeometrique();

		barrel = new Body(this.getPosX() + this.getLongueurReelle() / 2 - this.getLargeurReelle() / 6,
				this.getPosY() + this.getLargeurReelle(), this.getLargeurReelle() / 3, this.getLongueurReelle());

		if (!shooting) {
			if (super.getImage() != null) {
				balle = new Bullet( this.getPosX() + this.getLongueurReelle() / 2, this.getPosY(),
						this.getLargeurReelle() / 3);
			} else {
				balle = new Bullet( this.getPosX() + this.getLongueurReelle() / 2, this.getPosY(),
						this.getLargeurReelle() / 3);
			}
		}
	}




	/**
	 * Cette m�thode permet de d�terminer le coefficient de friction du sol, la
	 * masse de la balle, la masse du char d'assaut, la vitesse initiale de la
	 * balle et la force de mouvement du char d'assaut. Ces donn�es sont alors
	 * identifi�es de mani�re arbitraire d�pendant de la valeur du param�tre.
	 * 
	 * @param qualite
	 *            Un nombre entre 1 et 5 qui d�termine la qualit� de l'objet de
	 *            type Joueur avec 1 �tant le pire et 5 le meilleur
	 */
	private void quality(int qualite) {
		switch (qualite) {
		case 1:
			coeffSol = 1.37;
			massBall = 0.0419249;
			massTank = 1000;
			vitesseInitialeBall = 1000;
			forceDeMov = 40000;
			break;
		}
	}

	/**
	 * Cette m�thode permet le calcul de d�placement � l'aide de la m�thode de
	 * calcul d'Euler. Cette m�thode propose de calculer la position avec la
	 * vitesse initiale et une acc�l�ration constante. Aussi, elle d�termine la
	 * valeur de la vitesse apr�s ceci comme �tant la vitesse finale.
	 * 
	 * @param g
	 *            la constante d'acc�l�ration gravitationnelle en m/s^2
	 * @param deltaT
	 *            la diff�rence de temps entre la position initiale et la
	 *            position finale en s
	 * @param type
	 *            L'objet pour lequel la m�thode de calcul est utilis�e. Un
	 *            choix entre tankRight (pour le d�placement du char d'assaut �
	 *            la droite), tankLeft (pour le d�placement du char d'assaut �
	 *            la gauche), tankDece (pour le d�placement du char d'assaut
	 *            lors d'une d�c�l�ration) et bullet (pour le d�placement de la
	 *            balle que tire le char d'assaut).
	 * @param k La constante de rappel du ressort.
	 */
	public void euler(double g, double deltaT, double k, String type) {
		double accel;

		switch (type) {
		case "tankRight":
		case "ressortDroit":
		case "ressortGauche":
		case "tankLeft":
			accel = accel(speedTank, g, k, type);
			super.setPosX(super.getPosX() + speedTank * deltaT);
			speedTank = speedTank + accel * deltaT;

			break;
		case "tankDece":
			accel = accel(speedTank, g, k, type);
			super.setPosX(super.getPosX() + speedTank * deltaT);
			speedTank = speedTank + accel * deltaT;
			break;
		case "bullet":
			accel = accel(speedBallX, g, k, "x");
			balle.setPosX(balle.getPosX() + speedBallX * deltaT);
			speedBallX = speedBallX + accel * deltaT;
			accel = accel(speedBallY, g, k, "y");
			balle.setPosY(balle.getPosY() + speedBallY * deltaT);
			speedBallY = speedBallY + accel * deltaT;
			break;
		}
	}

	/**
	 * Cette m�thode permet le calcul de d�placement � l'aide de la m�thode de
	 * calcul d'Euler invers�. Cette m�thode permet de calculer la position
	 * finale avec la vitesse finale et une acc�l�ration constante. Elle
	 * d�termine la valeur de la vitesse finale pour le calcul de position.
	 * 
	 * @param g
	 *            la constante d'acc�l�ration gravitationnelle en m/s^2
	 * @param deltaT
	 *            la diff�rence de temps entre la position initiale et la
	 *            position finale en s
	 * @param type
	 *            L'objet pour lequel la m�thode de calcul est utilis�e. Un
	 *            choix entre tankRight (pour le d�placement du char d'assaut �
	 *            la droite), tankLeft (pour le d�placement du char d'assaut �
	 *            la gauche), tankDece (pour le d�placement du char d'assaut
	 *            lors d'une d�c�l�ration) et bullet (pour le d�placement de la
	 *            balle que tire le char d'assaut).
	 * @param k La constante de rappel du ressort.
	 */
	public void eulerI(double g, double deltaT, double k, String type) {
		double accel;

		switch (type) {
		case "tankRight":
		case "ressortDroit":
		case "ressortGauche":
		case "tankLeft":
			accel = accel(speedTank, g, k, type);
			speedTank = speedTank + accel * deltaT;
			super.setPosX(super.getPosX() + speedTank * deltaT);
			break;
		case "tankDece":
			accel = accel(speedTank, g, k, type);
			speedTank = speedTank + accel * deltaT;
			super.setPosX(super.getPosX() + speedTank * deltaT);
			break;
		case "bullet":
			accel = accel(speedBallX, g, k, "x");
			speedBallX = speedBallX + accel * deltaT;
			balle.setPosX(balle.getPosX() + speedBallX * deltaT);
			accel = accel(speedBallY, g, k, "y");
			speedBallY = speedBallY + accel * deltaT;
			balle.setPosY(balle.getPosY() + speedBallY * deltaT);
			break;
		}
	}

	/**
	 * Cette m�thode permet le calcul de d�placement � l'aide de la m�thode de
	 * calcul de Runge-Kutta d'ordre 4. Cette m�thode de calcul utilise la
	 * m�thode d'Euler, mais elle utilise plusieurs calculs interm�diaires pour
	 * am�liorer la pr�cision finale de la vitesse en utilisant une partie de
	 * chaque calcul interm�diaire pour calculer la posotion finale et la
	 * vitesse finale.
	 * 
	 * @param g
	 *            la constante d'acc�l�ration gravitationnelle en m/s^2
	 * @param deltaT
	 *            la diff�rence de temps entre la position initiale et la
	 *            position finale en s
	 * @param type
	 *            L'objet pour lequel la m�thode de calcul est utilis�e. Un
	 *            choix entre tankRight (pour le d�placement du char d'assaut �
	 *            la droite), tankLeft (pour le d�placement du char d'assaut �
	 *            la gauche), tankDece (pour le d�placement du char d'assaut
	 *            lors d'une d�c�l�ration) et bullet (pour le d�placement de la
	 *            balle que tire le char d'assaut).
	 * @param k La constante de rappel du ressort.
	 */
	public void rk4(double g, double deltaT, double k, String type) {
		double accel, speed1, accel1, speedMid, accelMid, speed2, accel2;
		if (deltaT > 0) {
			switch (type) {
			case "tankRight":
			case "ressortDroit":
			case "ressortGauche":
			case "tankLeft":
				accel = accel(speedTank, g, k, type);
				speed1 = speedTank + accel * deltaT;
				accel1 = accel(speed1, g, k, type);
				speedMid = speedTank + (0.75 * accel + 0.25 * accel1) * deltaT * 0.5;
				accelMid = accel(speedMid, g, k, type);
				speed2 = speedTank + (0.5 * accel + 0.5 * accel1) * deltaT;
				accel2 = accel(speed2, g, k, type);
				super.setPosX(super.getPosX() + speedTank * deltaT
						+ 0.5 * ((1.0 / 3.0) * accel + (2.0 / 3.0) * accelMid) * Math.pow(deltaT, 2));
				speedTank = speedTank + ((1.0 / 6.0) * accel + (4.0 / 6) * accelMid + (1.0 / 6.0) * accel2) * deltaT;

				break;
			case "tankDece":
				accel = accel(speedTank, g, k, type);
				speed1 = speedTank + accel * deltaT;
				accel1 = accel(speed1, g, k, type);
				speedMid = speedTank + (0.75 * accel + 0.25 * accel1) * deltaT * 0.5;
				accelMid = accel(speedMid, g, k, type);
				speed2 = speedTank + (0.5 * accel + 0.5 * accel1) * deltaT;
				accel2 = accel(speed2, g, k, type);
				super.setPosX(super.getPosX() + speedTank * deltaT
						+ 0.5 * ((1.0 / 3.0) * accel + (2.0 / 3.0) * accelMid) * Math.pow(deltaT, 2));
				speedTank = speedTank + ((1.0 / 6.0) * accel + (4.0 / 6) * accelMid + (1.0 / 6.0) * accel2) * deltaT;
				break;
			case "bullet":
				accel = accel(speedBallY, g, k, "y");
				speed1 = speedBallY + accel * deltaT;
				accel1 = accel(speed1, g, k, "y");
				speedMid = speedBallY + (0.75 * accel + 0.25 * accel1) * deltaT * 0.5;
				accelMid = accel(speedMid, g, k, "y");
				speed2 = speedBallY + (0.5 * accel + 0.5 * accel1) * deltaT;
				accel2 = accel(speed2, g, k, "y");
				balle.setPosY(balle.getPosY() + speedBallY * deltaT
						+ 0.5 * ((1.0 / 3) * accel + (2.0 / 3.0) * accelMid) * Math.pow(deltaT, 2));
				speedBallY = speedBallY + ((1.0 / 6) * accel + (4.0 / 6.0) * accelMid + (1.0 / 6.0) * accel2) * deltaT;
				accel = accel(speedBallX, g, k, "x");
				speed1 = speedBallX + accel * deltaT;
				accel1 = accel(speed1, g, k, "x");
				speedMid = speedBallX + (0.75 * accel + 0.25 * accel1) * deltaT * 0.5;
				accelMid = accel(speedMid, g, k, "x");
				speed2 = speedBallX + (0.5 * accel + 0.5 * accel1) * deltaT;
				accel2 = accel(speed2, g, k, "x");
				balle.setPosX(balle.getPosX() + speedBallX * deltaT
						+ 0.5 * ((1.0 / 3) * accel + (2.0 / 3.0) * accelMid) * Math.pow(deltaT, 2));
				speedBallX = speedBallX + ((1.0 / 6) * accel + (4.0 / 6.0) * accelMid + (1.0 / 6.0) * accel2) * deltaT;
				break;
			}
		} else
			System.out.println("You suck...");
	}

	/**
	 * Cette m�thode calcule plusieurs acc�l�rations d�pendant du type de l'objet.
	 * Si le type de l'objet est <i>tankRight</i>, l'acc�l�ration sera<br> 
	 * <b> a = ( <i>F</i>m - <i>C</i>s * <i>g</i> * <i>M</i>t) / <i>M</i>t </b>
	 * o� <b><i>F</i>m</b> est la force de mouvement du tank,
	 * <b><i>C</i>s</b> est le coefficieint de frottement cin�tique du sol,
	 * <b><i>g</i></b> est la constante d'acc�l�ration gravitationnelle et
	 * <b><i>M</i>t</b> est la masse du char d'assaut.<br> 
	 * Si le type de l'objet est <i>tankleft</i>, l'acc�l�ration sera calcul�e comme pr�c�demment, sauf
	 * que les signes sont invers�s sur la parenth�se et sur <b><i>C</i>s</b>.<br> 
	 * Si le type de l'objet est <i>tankDece</i>, l'acc�l�ration sera<br>
	 * <b> a = -( vit / |vit| ) * <i>C</i>s * <i>g</i> </b>
	 * o� <b><i>C</i>s</b> est le coefficieint de frottement cin�tique du sol,
	 * <b><i>g</i></b> est la constante d'acc�l�ration gravitationnelle et
	 * <b><i>vit</i></b> est la vitesse initiale de l'objet.<br>
	 * Si le type de l'objet est <i>x</i> ou <i>y</i>, l'acc�l�ration d�pendra de la force magn�tique et
	 * de la force de friction de l'air.
	 * 
	 * @param vit - La vitesse initiale de l'objet
	 * @param g -
	 *            La constante d'acc�l�ration gravitationnelle en m/s^2
	 * @param type -  L'objet pour lequel l'acc�l�ration est calcul�e. Un
	 *            choix entre tankRight (pour l'acc�l�ration du char d'assaut �
	 *            la droite), tankLeft (pour l'acc�l�ration du char d'assaut �
	 *            la gauche), tankDece (pour l'acc�l�ration du char d'assaut
	 *            lors d'une d�c�l�ration), x (pour l'acc�l�ration de la
	 *            balle que tire le char d'assaut en x) et y (pour l'acc�l�ration de la
	 *            balle que tire le char d'assaut en y).
	 * @param k La constante de rappel du ressort.
	 * @return <b>a</b> - L'acc�l�ration de l'objet d�fini par le type.
	 */
	private double accel(double vit, double g, double k, String type) {
		if (vit != 0) {
			switch (type) {
			case "tankRight":
				return Math.abs((forceDeMov - coeffSol * g * massTank) / massTank) - (vit / Math.abs(vit)) * Math.pow(vit, 2) * 0.01;
			case "tankLeft":
				return -Math.abs((-forceDeMov + coeffSol * g * massTank) / massTank) - (vit / Math.abs(vit)) * Math.pow(vit, 2) * 0.01;
			case "tankDece":
				return -(vit / Math.abs(vit)) * coeffSol * g - (vit / Math.abs(vit)) * Math.pow(vit, 2) * 0.01;
			case "x":
				this.forceFrictionAirX = -(0.5 * coeffAir * Math.pow(vit, 2) * massVAir * (vit / Math.abs(vit)));
				if (this.magnetisme) {
					checkForceMagnetique(type);
					this.accelMagnX = this.forceMagnX / this.massBall;
					double accelResultante = (forceFrictionAirX + this.forceMagnetique.getX()) / this.massBall;
					return accelResultante;
				} else
					return -(0.5 * coeffAir * Math.pow(vit, 2) * massVAir * (vit / Math.abs(vit))) / massBall;
			case "y":
				this.forceFrictionAirY = (0.5 * coeffAir * Math.pow(vit, 2) * massVAir * (vit / Math.abs(vit)));
				if (magnetisme) {
					checkForceMagnetique(type);
					this.accelMagnY = this.forceMagnY / this.massBall;

					double accelResultante = (-g - (0.5 * coeffAir * Math.pow(vit, 2) * massVAir * (vit / Math.abs(vit))) + this.forceMagnetique.getY()) / this.massBall;
					return accelResultante;
				} else
					return -g - (0.5 * coeffAir * Math.pow(vit, 2) * massVAir * (vit / Math.abs(vit))) / massBall;
			case "ressortDroit":
				return -k / this.massTank * (-this.getPosX()+this.longueurMonde);
			case "ressortGauche":
				return k / this.massTank * this.getPosX();
			default:
				System.out.println("How did you fuck this up?!");
				return 0;
			}
		} else {
			switch (type) {
			case "tankRight":
				return (forceDeMov / massTank);
			case "tankLeft":
				return -(forceDeMov / massTank);
			case "tankDece":
				return 0;
			case "x":
				return 0;
			case "y":
				return -g - (0.5 * coeffAir * Math.pow(vit, 2) * massVAir) / massBall;
			default:
				System.out.println("How did you fuck this up?!");
				return 0;
			}
		}
	}

	// Jimmy
	/**
	 * methode retournant la force magn�tique effectu� par un ennemie �tant en
	 * mode champ magnetique
	 * 
	 * @param direction = direction (x ou y ) de l'acc�l�ration de la balle
	 *            recherch�e. 
	 */
	private void checkForceMagnetique(String direction) {

		double rayonX = this.ennemieMagnetique.getPosX() - this.balle.getPosX();
		double rayonY = this.ennemieMagnetique.getPosY() - this.balle.getPosY();

		SVector3d rayonVec = new SVector3d(rayonX,rayonY, 0);

		double rayon = rayonVec.modulus();
		double courant = this.chargeEnnemie;
		double mu = 4 * Math.pow(10, -7);
		double muPi = mu/(2*Math.PI);
		double courantChamp = courant / rayon;
		double b = muPi * courantChamp;

		this.champMagnetique = rayonVec.normalize().cross(new SVector3d(0,0,1));


		this.champMagnetique.normalize();
		this.champMagnetique.multiply(b);


		SVector3d vitesse3d = new SVector3d(this.speedBallX, this.speedBallY, 0);
		this.forceMagnetique = champMagnetique.cross(new SVector3d(0,0,1)).multiply(vitesse3d.modulus()).multiply(-this.chargeEnnemie);

		this.forceMagnX = forceMagnetique.getX();

		this.forceMagnY = forceMagnetique.getY();

	}


	/**
	 * Cette m�thode lance la balle du canon � une vitesse �quivalente � la <b>vitesseInitialeBall</b>.
	 * La balle est plac�e au d�nut du canon et seulement une balle peut �tre lanc�e � la fois.
	 * 
	 */
	public void shoot() {
		if (!shooting) {
			this.speedBallX = this.vitesseInitialeBall * Math.sin(Math.toRadians(-angle));
			this.speedBallY = this.vitesseInitialeBall * Math.cos(Math.toRadians(angle));
			this.balle.setPosX(this.barrel.getPosX());
			this.balle.setPosY(this.barrel.getPosY());
			shooting = true;
		}
	}

	/**
	 * Cette m�thode retourne l'objet Body <b>balle</b>.
	 * 
	 * @return L'objet Body <b>balle</b>.
	 */
	public Bullet getBalle() {
		return balle;
	}

	/**
	 * Cette m�thode fixe l'objet Body <b>balle</b>.
	 * 
	 * @param balle -  L'objet Body <b>balle</b>
	 */
	public void setBalle(Bullet balle) {
		this.balle = balle;
	}

	/**
	 * Cette m�thode retourne la vitesse du char d'assaut en m/s.
	 * 
	 * @return La vitesse du char d'assaut en m/s
	 */
	public double getSpeedTank() {
		return speedTank;
	}

	/**
	 * Cette m�thode fixe la vitesse du char d'assaut en m/s.
	 * 
	 * @param speedTank - La vitesse du char d'assaut en m/s
	 */
	public void setSpeedTank(double speedTank) {
		this.speedTank = speedTank;
	}

	/**
	 * Cette m�thode retourne la vitesse de la balle sur l'axe des abscisses en m/s.
	 * 
	 * @return La vitesse de la balle sur l'axe des abscisses en m/s
	 */
	public double getSpeedBallX() {
		return speedBallX;
	}

	/**
	 * Cette m�thode fixe la vitesse de la balle sur l'axe des abscisses en m/s.
	 * 
	 * @param speedBallX - La nouvelle vitesse de la balle sur l'axe des abscisses
	 */
	public void setSpeedBallX(double speedBallX) {
		this.speedBallX = speedBallX;
	}

	/**
	 * Cette m�thode retourne la vitesse de la balle sur l'axe des ordonn�es en m/s.
	 * 
	 * @return La vitesse de la balle sur l'axe des ordonn�es en m/s
	 */
	public double getSpeedBallY() {
		return speedBallY;
	}

	/**
	 * Cette m�thode fixe la vitesse de la balle sur l'axe des ordonn�es en m/s.
	 * 
	 * @param speedBallY - La vitesse de la balle sur l'axe des ordonn�es en m/s
	 */
	public void setSpeedBallY(double speedBallY) {
		this.speedBallY = speedBallY;
	}

	/**
	 * Cette m�thode retourne l'angle d'inclinaison du canon du char d'assaut en degr�s.
	 * 
	 * @return L'angle d'inclinaison du canon du char d'assaut en degr�s
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Cette m�thode fixe l'angle d'inclinaison du canon du char d'assaut en degr�s.<br>
	 * Si l'angle est sup�rieur � <b>MAX_ANGLE</b>, l'angle d'inclinaison sera fix� �
	 * <b>MAX_ANGLE</b>.
	 * 
	 * @param angle - La nouvelle angle d'inclinaison du canon du char d'assaut.
	 */
	public void setAngle(double angle) {
		if (Math.abs(angle) > MAX_ANGLE)
			this.angle = (angle / Math.abs(angle)) * MAX_ANGLE;
		else
			this.angle = angle;
	}

	/**
	 * Cette m�thode retourne si le canon est en train de lancer une balle.
	 * 
	 * @return si le canon est en train de lancer une balle
	 */
	public boolean isShooting() {
		return this.shooting;
	}

	/**
	 * Cette m�thode d�cide si le canon lance une balle ou non.
	 * 
	 * @param shooting - si le canon lance une balle ou non
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	/**
	 * Remets la balle � sa position initiale et fixe ses valeurs de vitesse � 0.
	 * Permet le char d'assaut de relancer la balle.
	 * 
	 */
	public void resetBalle() {
		this.balle = new Bullet( this.getPosX() + this.getLongueurReelle() / 2, this.getPosY(),
				this.getLargeurReelle() / 3);
		this.speedBallX = 0;
		this.speedBallY = 0;
		this.forceFrictionAirX = 0;
		this.forceFrictionAirY = 0;
		this.accelMagnX = 0;
		this.accelMagnY = 0;
		this.forceMagnX = 0;
		this.forceMagnY = 0;
		this.forceFrictionAirX = 0;
		this.forceFrictionAirY = 0;
		this.forceMagnetique.multiply(0);
		shooting = false;
	}

	/**
	 * Cette m�thode retourne la force de tir du canon.
	 * 
	 * @return La force de tir du canon
	 */
	public double getForceDuCanon() {
		return vitesseInitialeBall;
	}

	/**
	 * Cette m�thode fixe la force de tir du canon.
	 * 
	 * @param forceDuCanon - La nouvelle force de tir du canon
	 */
	public void setForceDuCanon(double forceDuCanon) {
		if (forceDuCanon > maxForceCanon)
			this.vitesseInitialeBall = maxForceCanon;
		else if (forceDuCanon < 0)
			this.vitesseInitialeBall = 0;
		else
			this.vitesseInitialeBall = forceDuCanon;
	}

	/**
	 * Cette m�thode retourne le ratio de force entre la force du canon courante et la force maximale du canon.
	 * 
	 * @return le ratio entre la force courante du canon et la force maximale du canon
	 */
	public double ratioForce() {
		return vitesseInitialeBall / maxForceCanon;
	}

	/**
	 * Cette m�thode d�termine si un ennemi donn� peut �mettre une force magn�tique ou non.
	 * 
	 * @param ennemie L'ennemi qui est magn�tique ou non.
	 * @param isMagnetic Si l'ennemi est magn�tique, cette valeur est true.
	 */
	public void setMagnetisme(Ennemies ennemie, boolean isMagnetic) {
		this.magnetisme = isMagnetic;
		this.ennemieMagnetique = ennemie;
		if (!magnetisme) {
			this.accelMagnX = 0;
			this.accelMagnY = 0;
			this.forceMagnX = 0;
			this.forceMagnY = 0;
			this.forceFrictionAirX = 0;
			this.forceFrictionAirY = 0;
			this.forceMagnetique.multiply(0);
		}
	}

	// Jimmy
	/**
	 * Methode retournant l'ennemie qui a un effet de magnetisme sur la balle du
	 * joueur
	 * 
	 * @return = l'ennemie ayant un effet magnetisme sur la balle du joueur
	 */
	public Ennemies getEnnemieMagnetique() {
		return this.ennemieMagnetique;
	}

	/**
	 * M�thode retournant l'acc�l�ration en x sur la balle caus� par la force
	 * magn�tique d'un ennemie
	 * 
	 * @return = l'acc�l�ration en x sur la balle caus� par la force magn�tique
	 *         d'un ennemie
	 */
	public double getAccelMagnX() {
		return accelMagnX;
	}

	// Jimmy
	/**
	 * m�thode retournant l'acc�l�ration en y sur la balle caus� par la force
	 * magn�tique d'un ennemie
	 * 
	 * @return = l'acc�l�ration en y sur la balle caus� par la force magn�tique
	 *         d'un ennemie
	 */
	public double getAccelMagnY() {

		return this.accelMagnY;
	}

	// Jimmy
	/**
	 * M�thode retournant la force magn�tique en x appliqu� sur la balle par un
	 * ennemie
	 * 
	 * @return = la force magn�tique en x appliqu� sur la balle par un ennemie
	 */
	public double getForceMagnX() {
		return forceMagnX;
	}

	// Jimmy
	/**
	 * M�thode retournant la force magn�tique en y appliqu� sur la balle par un
	 * ennemie
	 * 
	 * @return = la force magn�tique en y appliqu� sur la balle par un ennemie
	 */
	public double getForceMagnY() {
		return forceMagnY;
	}

	// Jimmy
	/**
	 * m�thode retournant une valeur bool�enne permetant de savoir si la balle
	 * recoit une force magn�tique par un ennemie
	 * 
	 * @return = une valeur bool�enne permetant de savoir si la balle recoit une
	 *         force magn�tique par un ennemie
	 */
	public boolean isMagneticOn() {
		return this.magnetisme;
	}

	/**
	 * Cette m�thode retourne la valeur de la masse du char d'assaut en kg.
	 * 
	 * @return La masse du char d'assaut en kg
	 */
	public double getMassTank() {
		return massTank;
	}

	/**
	 * Cette m�thode fixe la valeur de la masse du char d'assaut en kg.
	 * 
	 * @param massTank La masse du char d'assaut en kg
	 */
	public void setMassTank(double massTank) {
		this.massTank = massTank;
	}

	/**
	 * Methode retournant la force de friction de l'air en x
	 * @return = force de friction de l'air en x
	 */
	public double getForceFrictionAirX() {
		return forceFrictionAirX;
	}

	/**
	 * Methode retournant la force de friction de l'air en y
	 * @return = force de friction de l'air en y
	 */
	public double getForceFrictionAirY() {
		return forceFrictionAirY;
	}

	/**
	 * methode retournant la force maximum du canon
	 * @return = valeur de la force maximum du canon
	 */
	public double getMaxForceCanon() {
		return maxForceCanon;
	}

	/**
	 * Methode modifiant la force maximum du canon
	 * @param maxForceCanon = nouvelle valeur pour la force maximum du canon
	 */
	public void setMaxForceCanon(double maxForceCanon) {
		this.maxForceCanon = maxForceCanon;
		if (this.vitesseInitialeBall>maxForceCanon){
			vitesseInitialeBall = maxForceCanon;
		}
	}

	/**
	 * Methode retournant le coefficient de frottement du sol
	 * @return = valeur du coefficient de frottement du sol
	 */
	public double getCoeffSol() {
		return coeffSol;
	}

	/**
	 * Methode modifiant le coefficient de frottement du sol
	 * @param coeffSol = nouvelle valeur du coefficient de frottement du sol
	 */
	public void setCoeffSol(double coeffSol) {
		this.coeffSol = coeffSol;
	}

	/**
	 * Methode retournant le coefficant de frottement de l'air
	 * @return = valeur du coefficient de frottement de l'air
	 */
	public double getCoeffAir() {
		return coeffAir;
	}

	/**
	 * Methode modifiant le coefficient de frottement de l'air
	 * @param coeffAir = nouvelle valeur du coefficient de frottement de l'air
	 */
	public void setCoeffAir(double coeffAir) {
		this.coeffAir = coeffAir;
	}

	/**
	 * Methode retournant la masse de l'air
	 * @return = valeur de la masse de l'air
	 */
	public double getMassVAir() {
		return massVAir;
	}

	/**
	 * Methode modifiant la masse de l'air
	 * @param massVAir = nouvelle valeur de la masse de l'air
	 */
	public void setMassVAir(double massVAir) {
		this.massVAir = massVAir;
	}

	/**
	 * Methode retournant la masse de la balle en kg
	 * @return = valeur de la masse de la balle en kg
	 */
	public double getMassBall() {
		return massBall;
	}

	/**
	 * Methode modifiant la masse de la balle en kg
	 * @param massBall = nouvelle valeur de la masse de la balle en kg
	 */
	public void setMassBall(double massBall) {
		this.massBall = massBall;
	}

	/**
	 * Methode modifiant la force de mouvement du char d'assaut en N
	 * @param forceDeMov = nouvelle valeur de la force de mouvement du char d'assaut en N
	 * 
	 */
	public void setForceDeMov(double forceDeMov) {
		this.forceDeMov = forceDeMov;
	}

}
