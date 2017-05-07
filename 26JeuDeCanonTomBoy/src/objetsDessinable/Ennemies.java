package objetsDessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * La classe  Ennemies permet de créer plusieurs types d'adversaire qui interagissent avec le niveau
 * dans lequel ils sont placés.
 * 
 * @author Mitchell Mastromonaco 
 * Date de création : 8 février 2016
 */
public class Ennemies extends Body {

	private String typeOfEnemy;
	private boolean shooting = false;
	private Bullet bullet;
	private ArrayList<Body> snake = new ArrayList<Body>();
	private Joueur target;
	private int loop = 0, loopUpDown = 0;
	private final int NUMBER_OF_LINKS = 5;
	private final double SPEED, AMPLITUDE = 1.5;
	private Color couleur;

	/**
	 * Le constructeur de base d'un objet de type Ennemies. Ce constructeur permet de spécifier un type d'ennemi,
	 * la constante de vitesse de l'ennemi
	 * 
	 * @param posX
	 *            La position sur l'axe des abscisses pour l'objet de type Body
	 *            en m.
	 * @param posY
	 *            La position sur l'axe des ordonnées pour l'objet de type Body
	 *            en m.
	 * @param longueurReelle
	 *            La longueur réelle pour l'objet de type Body en m.
	 * @param largeurReelle
	 *            La largeur réelle pour l'objet de type Body en m.
	 * @param type Le type d'ennemi voulu. Valeurs valides: simple, lessSimple, medium, sine, smart, gitgud.
	 * @param vitesse La constante de vitesse qu'aura un objet de type Ennemies en m/s
	 * @param target L'objet de type Joueur que l'objet de type Ennemies a comme cible pour attaquer
	 * @param couleur La couleur de l'objet de type Ennemies.
	 */
	public Ennemies(double posX, double posY, double longueurReelle, double largeurReelle, String type, double vitesse,
			Joueur target, Color couleur) {
		super(posX, posY, longueurReelle, largeurReelle);
		this.typeOfEnemy = type;
		this.SPEED = vitesse;
		this.target = target;
		this.setCouleur(couleur);
		if(type.equals("sine")){
			for (int l = 0; l<NUMBER_OF_LINKS; l++){
				this.snake.add(new Body(super.getPosX()+ (super.getLargeurReelle() / NUMBER_OF_LINKS) * l, super.getPosY(), super.getLargeurReelle() / NUMBER_OF_LINKS, super.getLargeurReelle() / NUMBER_OF_LINKS));
			}
		}
		this.bullet = new Bullet(0,0, (super.getLargeurReelle()+super.getLongueurReelle())/4);
	}

	/**
	 * La méthode de mouvement d'un objet de type Ennemies. Dépendant du type, l'objet
	 * bouge de manière différente et possède des réactions différentes faces aux actions du Joueur.
	 * 
	 * @param widthReelle La largeur en valeur réelle de l'espace de jeu en m.
	 * @param heightReelle La hauteur en valeur réelle de l'espace de jeu en m.
	 * @param largeurMur la largeur des limites de l'espace de jeu en m.
	 * @param deltaT La différene de temps en s.
	 */
	public void move(double widthReelle, double heightReelle, double largeurMur, double deltaT) {
		if(super.getPosY()<largeurMur)
			super.setPosY(largeurMur);
		if(super.getPosY() != largeurMur){
			switch (typeOfEnemy) {
			case "simple":
				simpleMovement(widthReelle);
				break;
			case "lessSimple":
				simpleMovement(widthReelle);
				if ((int)(Math.random() * 14) == 3 ) {
					startShooting();
				}
				shoot(heightReelle, largeurMur);
				break;
			case "medium":
				if (super.getPosX() == target.getPosX()) {
					super.setPosY(super.getPosY() - SPEED * 2.5);
					startShooting();
				} else
					simpleMovement(widthReelle);
				shoot(heightReelle, largeurMur);
				break;
			case "sine":  
				Iterator<Body> it =  this.snake.iterator();
				while (it.hasNext()){
					Body temp = it.next();
					if(!this.target.isShooting()){
						if (temp.getPosX() + SPEED + this.getLargeurReelle() >= widthReelle) {
							temp.setPosX(0);
						}
						temp.setPosX(temp.getPosX() + SPEED);
						temp.setPosY(temp.getPosY() + (this.AMPLITUDE) * Math.sin(temp.getPosX()));
					} else {
						temp.setPosY(temp.getPosY() - SPEED/2);
						temp.setPosX(temp.getPosX() + (this.AMPLITUDE) * Math.sin(temp.getPosY()));
					}
					super.setPosX(temp.getPosX());
					super.setPosY(temp.getPosY());
				}
				break;
			case "smart": 
				if(this.target.isShooting()){
					double randomSpeed = this.SPEED*(Math.random()+1);
					if(super.getPosY()>=heightReelle/2.0){
						if(super.getPosY() + randomSpeed + super.getLongueurReelle() >= heightReelle - largeurMur)
							simpleMovement(widthReelle);
						else 
							super.setPosY(super.getPosY() + randomSpeed);
					} else{
						super.setPosY(super.getPosY() - this.SPEED*(Math.random()+1)*0.5);
						if(super.getPosX() + randomSpeed + super.getLargeurReelle()/2 < widthReelle || super.getPosX()-randomSpeed > 0)
							super.setPosX(super.getPosX()+(randomSpeed*2.5*Math.pow(-1, (int)(Math.random()*2+1))));
					}
				}else{
					double newPosition = super.getPosX() + this.target.getSpeedTank()*deltaT;
					if ((this.target.getSpeedTank()<0.2 && this.target.getSpeedTank()>-0.2) || newPosition + this.getLongueurReelle() >= widthReelle || newPosition <= 0){
						super.setPosX(super.getPosX());
					} else {
						if (Math.abs(this.target.getSpeedTank())>0.2)
							if(Math.abs(this.target.getSpeedTank())>25)
								super.setPosY(super.getPosY() - SPEED);
							else
								super.setPosX(newPosition);
					}
				}
				break;
			case "gitgud":
				if(super.getPosY() - this.SPEED*2 <= largeurMur){
					allegerGitgud(widthReelle, heightReelle, largeurMur);
				} else
					if(super.getPosY() + this.SPEED + super.getLongueurReelle() >= heightReelle - largeurMur){
						allegerGitgud(widthReelle, heightReelle, largeurMur);
					}
				if (loopUpDown % 2 == 0)
					super.setPosY(super.getPosY() + SPEED*1.5);
				else
					super.setPosY(super.getPosY() - SPEED*1.5);
				break;
			default:
				System.out.println("You are bad and you should feel bad.");
			}
		} else{
			if (super.getPosX() + SPEED + this.getLargeurReelle() >= widthReelle) {
				loop++;
			} else {
				if (super.getPosX() - SPEED <= 0) {
					loop++;
				}
			}
			if (loop % 2 == 0)
				super.setPosX(super.getPosX() + SPEED);
			else
				super.setPosX(super.getPosX() - SPEED);
		}

	}

	/**
	 * Cette méthode fait que l'objet de type Ennemies tire une balle.
	 */
	private void startShooting() {
		if (!shooting) {
			bullet.setPosX(super.getPosX());
			bullet.setPosY(super.getPosY());
			shooting = true;
		}
	}

	/**
	 * Cette méthode privée permet de simplifier le code de la méthode move.
	 * C'est un mouvement d'aller-retour.
	 * 
	 * @param widthReelle La largeur en valeur réelle de l'espace de jeu en m.
	 */
	private void simpleMovement(double widthReelle) {
		if (super.getPosX() + SPEED + this.getLargeurReelle()/2.0 >= widthReelle) {
			loop++;
			super.setPosY(super.getPosY() - super.getLargeurReelle());
		} else {
			if (super.getPosX() - SPEED - this.getLargeurReelle() <= 0) {
				loop++;
				super.setPosY(super.getPosY() - super.getLargeurReelle());
			}
		}
		if (loop % 2 == 0)
			super.setPosX(super.getPosX() + SPEED);
		else
			super.setPosX(super.getPosX() - SPEED);
	}

	/**
	 * Cette méthode permet d'alléger l'écriture du case gitgud dans la méthode move.
	 * 
	 * @param widthReelle La largeur en valeur réelle de l'espace de jeu en m.
	 * @param heightReelle La hauteur en valeur réelle de l'espace de jeu en m.
	 * @param largeurMur la largeur des limites de l'espace de jeu en m.
	 */
	private void allegerGitgud(double widthReelle, double heightReelle, double largeurMur){
		if (super.getPosX() + SPEED + this.getLargeurReelle() >= widthReelle) {
			loop++;
		} else {
			if (super.getPosX() - SPEED <= 0) {
				loop++;
			}
		}
		loopUpDown++;
		if (loop % 2 == 0)
			super.setPosX(super.getPosX() + super.getLongueurReelle()/2.0);
		else
			super.setPosX(super.getPosX() - super.getLongueurReelle()/2.0);
	}

	/**
	 * Cette méthode détermine l'emplacement de la balle que tire certains types d'ennemis.
	 * Seulement une balle peut être tiré à la fois. 
	 * 
	 * @param heightReelle La hauteur en valeur réelle de l'espace de jeu en m.
	 * @param largeurMur la largeur des limites de l'espace de jeu en m.
	 */
	private void shoot(double heightReelle, double largeurMur){
		if (shooting) {
			bullet.setPosY(bullet.getPosY() - SPEED * 3.5);
			if (bullet.getPosY() <= largeurMur){
				shooting = false;
				bullet.setPosY(heightReelle);
			}
		}
	}

	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		Color couleurAvant = g2d.getColor();
		g2d.setColor(couleur);
		if (!this.typeOfEnemy.equals("sine"))
			super.dessiner(g2d, mat);
		else{
			Iterator<Body> it = this.snake.iterator();
			while(it.hasNext())
				it.next().dessiner(g2d, mat);
		}
		if (shooting) {
			g2d.setColor(Color.GREEN.brighter().brighter());
			bullet.dessiner(g2d, mat);
		}
		g2d.setColor(couleurAvant);
	}

	/**
	 * Cette méthode retourne la couleur de l'objet de type Ennemies.
	 * 
	 * @return La couleur de l'objet de type Ennemies
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Cette méthode fixe la couleur de l'objet de type Ennemies.
	 * 
	 * @param couleur La nouvelle couleur de l'objet de type Ennemies
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Cette méthode retourne si l'objet de type Ennemies et est en train de tirer une balle ou non.
	 * 
	 * @return Si l'objet de type Ennemies et est en train de tirer une balle ou non.
	 */
	public boolean isShooting(){
		return this.shooting;
	}

	/**
	 * Cette méthode retourne la balle que l'ennemi tire. 
	 * 
	 * @return La balle que l'ennemi tire.
	 */
	public Bullet getBullet() {
		return bullet;
	}

}
