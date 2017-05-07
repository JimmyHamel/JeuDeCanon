package composantsGraphiques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import fenetres.FenetreCreationDeProfils;
import fenetres.FenetreEditable;
import fenetres.FenetreSelectionNiveaux;
import interfaces.GenerationListener;
import interfaces.NewProfilListener;
import interfaces.PhysicsListener;
import objetsDessinable.Ennemies;
import objetsDessinable.Menu;
import objetsDessinable.Niveaux;
import objetsDessinable.Ressort;
import sauvegardeProfils.SauvegardeProfils;
import utilite.ModelePhysique;
import utilite.Profil;
import utilite.SelectionNiveau;

/**
 * Classe étant le composant graphique et animé contenant le menue principale et
 * l'animation de jeu.
 * 
 * @author Jimmy 
 * Date de création : 8 février 2016
 */
public class ComposantAnime extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Menu mainMenu;
	private boolean isPlaying;
	private Niveaux niveaux;
	private Thread proc;
	private boolean running;
	private ModelePhysique model;
	private boolean tankRight, tankLeft;
	private FenetreCreationDeProfils fenetreProfil;
	private FenetreSelectionNiveaux fenetreSelectionNiveaux;
	private final double LONGUEUR_MONDE_REEL = 200;
	private double hauteurMonde;
	private Rectangle2D.Double forceDuTir;
	private final double HAUTEUR_MAX_FORCE_DU_TIR = 50;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private boolean isALevelSelected;
	private ArrayList<Profil> listeProfils;
	private int indexProfilCourant;
	private boolean isModeSelectionAngle;
	private FenetreEditable editable;
	private boolean fin;
	private boolean magneticBlocIsLoading;
	private double tempBloc, tempBlocLoading;
	private static int SLEEP_TIME = 50;

	/**
	 * Constructeur du composant graphique anime
	 */
	public ComposantAnime() {
		setBackground(Color.WHITE);
		gestionEvenementClavierSouris();
		this.magneticBlocIsLoading = false;
		this.tempBlocLoading = 0;
		this.tempBloc = 0;
		this.isPlaying = false;
		this.running = false;
		this.fenetreProfil = new FenetreCreationDeProfils();
		editable = new FenetreEditable();
		this.listeProfils = new ArrayList<Profil>();
		this.mainMenu = new Menu();
		this.fenetreSelectionNiveaux = new FenetreSelectionNiveaux();
		this.isALevelSelected = false;
		this.indexProfilCourant = 0;
		this.isModeSelectionAngle = false;
		this.fin = false;
		this.listeProfils.add(new Profil("Default",14,1));
		addEcouteursPerso();


	}

	/**
	 * composant graphique méthode testant sous quelle état le
	 *            composant graphique est ( menu principale, jeu, ou selection
	 *            de niveaux) puis affiche l'état retournant true.
	 * @param g = composant graphique ou l'on dessine
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		hauteurMonde = getHeight() * this.LONGUEUR_MONDE_REEL / getWidth();

		model = new ModelePhysique(getWidth(), getHeight(), 0, hauteurMonde, this.LONGUEUR_MONDE_REEL, true);
		setBackground(Color.WHITE);
		if (!fin) {
			if (!this.isPlaying) { // si ne joue pas ou ne choisie pas de niveau

				this.mainMenu.dessiner(g2d, new AffineTransform()); // afficher
				// le menu
				// principale
				forceDuTir = new Rectangle2D.Double(0, 10, 5, HAUTEUR_MAX_FORCE_DU_TIR);
			}

			else if (this.isPlaying) { // si en mode jeu

				AffineTransform mat = model.getMatMondeVersComposant();

				g2d.setColor(Color.RED);
				this.niveaux.dessiner(g2d, mat);// dessiner le niveau

				g2d.setColor(Color.WHITE);
				forceDuTir.height = this.niveaux.getPlayer().ratioForce() * HAUTEUR_MAX_FORCE_DU_TIR;
				for (double i = 0; i < forceDuTir.height; i += 9) {
					g2d.setColor(g2d.getColor().darker());
				}
				g2d.fill(mat.createTransformedShape(forceDuTir));
			}
		}else{
			if(this.niveaux.isEchec())
				setBackground(new Color(255,0,0,150));
			else
				setBackground(new Color(0,0,255,150));
		}

	}

	@Override
	/**
	 * Methode permettant l'animation de notre joueur, nos ennemies et les
	 * projectiles.
	 * 
	 */
	public void run() {
		while (running) {

			if(this.magneticBlocIsLoading && !this.fin){
				this.tempBlocLoading += this.niveaux.getDeltaT();
				if(this.tempBlocLoading >= this.tempBloc){
					this.niveaux.creeObjetChamp();
					this.magneticBlocIsLoading = false;
					this.tempBloc = 0;
					this.tempBlocLoading = 0;
				}



			}
			else{
				if(!this.niveaux.isMagneticObjetIn() && !this.niveaux.getPlayer().isMagneticOn()){
					this.tempBloc = Math.random() * 5;
					this.magneticBlocIsLoading = true;
				}
			}

			iterationPhysique();

			repaint();

			for (PhysicsListener ecout : OBJETS_ENREGISTRES.getListeners(PhysicsListener.class)) {
				ecout.checkTankSpeed(this.niveaux.getPlayer().getSpeedTank());
				ecout.checkBulletSpeedY(this.niveaux.getPlayer().getSpeedBallY());
				ecout.checkBulletSpeedX(this.niveaux.getPlayer().getSpeedBallX());
				ecout.checkBulletSpeedTotal(Math.sqrt(Math.pow(this.niveaux.getPlayer().getSpeedBallX(), 2)
						+ Math.pow(this.niveaux.getPlayer().getSpeedBallY(), 2)));
				ecout.checkForceMagneticX(this.niveaux.getPlayer().getForceMagnX());
				ecout.checkForceMagneticY(this.niveaux.getPlayer().getForceMagnY());
				ecout.checkForceMagneticTotal(Math.sqrt(Math.pow(this.niveaux.getPlayer().getForceMagnX(), 2)
						+ Math.pow(this.niveaux.getPlayer().getForceMagnX(), 2)));
				ecout.checkAccelMagneticX(this.niveaux.getPlayer().getAccelMagnX());
				ecout.checkAccelMagneticY(this.niveaux.getPlayer().getAccelMagnY());
				ecout.checkAccelMagneticTotal(Math.sqrt(Math.pow(this.niveaux.getPlayer().getAccelMagnX(), 2)
						+ Math.pow(this.niveaux.getPlayer().getAccelMagnY(), 2)));
				ecout.isRunning(true);
				ecout.accelGravitationelle(niveaux.getG());
				ecout.forceFrictionAir(Math.sqrt(Math.pow(this.niveaux.getPlayer().getForceFrictionAirX(), 2)
						+ Math.pow(this.niveaux.getPlayer().getForceFrictionAirY(), 2)));

				if (this.niveaux.isReussite()) {
					ecout.fin("reussie");
					String niveauStr = this.fenetreSelectionNiveaux.getNomNiveauSelectionne();
					if (!niveauStr.equals("niveau_editable")) {
						int niveauInt = Integer.parseInt(niveauStr);
						if (niveauInt == this.listeProfils.get(this.indexProfilCourant).getNiveau()) {
							this.listeProfils.get(this.indexProfilCourant).setNiveau(niveauInt + 1);
							this.fenetreSelectionNiveaux.setNiveauProfil(niveauInt + 1);
						}
					}
					if (this.indexProfilCourant != 0)
						SauvegardeProfils.save(this.listeProfils.get(indexProfilCourant));
				} else if (this.niveaux.isEchec())
					ecout.fin("echec");
				if (this.niveaux.isReussite() || this.niveaux.isEchec()) {
					this.running = false;
					if(getNiveauCourant() !=14)
						fin = true;
					repaint();
				}
				ecout.positionBalle(this.niveaux.getPlayer().getBalle().getGraphePosition());
			}

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Méthode appelant les methode de mouvement de chaque composant du niveau
	 * et qui appele la méthode de vérification de colision
	 */

	private void iterationPhysique() {
		if (this.niveaux.isRessortGaucheIntersects())
			this.niveaux.mouvementJoueur("ressortGauche");
		else if (this.niveaux.isRessortDroitIntersects())
			this.niveaux.mouvementJoueur("ressortDroit");
		else if (tankRight)
			this.niveaux.mouvementJoueur("tankRight");
		else if (tankLeft)
			this.niveaux.mouvementJoueur("tankLeft");
		else if (!tankRight & !tankRight)
			this.niveaux.mouvementJoueur("tankDece");
		this.niveaux.mouvementEnemies(this.LONGUEUR_MONDE_REEL, this.hauteurMonde);
		if (this.niveaux.getPlayer().isShooting()) {
			this.niveaux.mouvementJoueur("bullet");
		}
		this.niveaux.testCollision();
	}

	/**
	 * Création du thread et départ de l'état d'animation du composant graphique
	 */
	public void start() {
		if (!running) {
			this.proc = new Thread(this);
			proc.start();
			this.running = true;
			isPlaying = true;
			fin = false;
		}
	}

	/**
	 * Création des méthodes évenementielles pour les touches du clavier et les
	 * clicks de la souris
	 */

	public void gestionEvenementClavierSouris() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isPlaying) {
					if (running) {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							tankLeft = true;
							break;
						case KeyEvent.VK_RIGHT:
							tankRight = true;
							break;
						case KeyEvent.VK_A:
							niveaux.getPlayer().setAngle(niveaux.getPlayer().getAngle() + 2.0);
							break;
						case KeyEvent.VK_D:
							niveaux.getPlayer().setAngle(niveaux.getPlayer().getAngle() - 2.0);
							break;
						case KeyEvent.VK_SPACE:
							niveaux.getPlayer().shoot();
							break;
						case KeyEvent.VK_E:
							niveaux.getPlayer().setForceDuCanon(niveaux.getPlayer().getForceDuCanon() + 5);
							break;
						case KeyEvent.VK_Q:
							niveaux.getPlayer().setForceDuCanon(niveaux.getPlayer().getForceDuCanon() - 5);
							break;
						case KeyEvent.VK_ESCAPE:

							if (niveaux.getPlayer().isShooting())
								niveaux.getPlayer().resetBalle();
							break;
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT)
					tankLeft = false;
				if (key == KeyEvent.VK_RIGHT)
					tankRight = false;

			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String boutonClick = mainMenu.clickHandler(arg0.getX(), arg0.getY());
				if (!isPlaying) {
					if (!boutonClick.equals("pasUnBouton")) {
						if (boutonClick.equals("jouer")) {
							setNiveauChoisie();
							if (niveaux != null) {
								isALevelSelected = true;
								repaint();
							}
							if (isALevelSelected) {
								repaint();
								start();
							}
							if (niveaux.getNomNiveau().equals("niveau_editable")) {
								editable.setEverything(LONGUEUR_MONDE_REEL, model.getHautUnitesReelles(),
										niveaux.getPlayer(), niveaux.getLARGEUR_MUR(), niveaux.getPhiDroit(),
										niveaux.getPhiGauche());
								editable.setLocation(getWidth(), 0);
								editable.setVisible(true);
							}
						} else if (boutonClick.equals("profil")) {
							fenetreProfil.setVisible(true);
						} else if (boutonClick.equals("selectionNiveau"))
							fenetreSelectionNiveaux.setVisible(true);
						else if (boutonClick.equals("quitter"))
							System.exit(0);
					}
				} else {
					niveaux.clickHandler(arg0.getX(), arg0.getY(), model);
					isModeSelectionAngle = !isModeSelectionAngle;
				}
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// methode permettant le changement de l'angle du canon avec la
				// souris
				if (isPlaying) {
					if (isModeSelectionAngle) {

						double y = model.getHautUnitesReelles() - (double) arg0.getY() / model.getPixelsParUniteY()
								- (niveaux.getPlayer().getPosY() + niveaux.getPlayer().getLargeurReelle());
						double x = (double) arg0.getX() / model.getPixelsParUniteX()
								- (niveaux.getPlayer().getPosX() + niveaux.getPlayer().getLongueurReelle() / 2.0);
						double angle = 0;
						if (y > 0) {
							angle = Math.atan(y / x);
							if (angle < 0)
								niveaux.getPlayer().setAngle(90 + Math.toDegrees(angle));
							else
								niveaux.getPlayer().setAngle(Math.toDegrees(angle) - 90);
						}
					}
				} else {
					mainMenu.mouseMovingHandler(arg0.getX(), arg0.getY());
					repaint();

				}
			}
		});

	}

	/**
	 * Ajout d'un ecouteur pour tout les donné physique de notre application
	 * 
	 * @param objEcout
	 *            = un ecouteur de type PhysicsListener
	 */
	public void addPhysicsListener(PhysicsListener objEcout) {
		OBJETS_ENREGISTRES.add(PhysicsListener.class, objEcout);
	}

	/**
	 * Cette méthode change la méthode de calcul utilisée pour calculer le
	 * déplacement du personnage.
	 * 
	 * @param euler
	 *            - Si la méthode de calcul d'Euler est utilisée ou non.
	 * @param eulerI
	 *            - Si la méthode de calcul d'Euler inversé est utilisée ou non.
	 * @param rk4
	 *            - Si la méthode de calcul de Runge-Kutta d'ordre 4 est
	 *            utilisée ou non.
	 */
	public void changeCalcul(boolean euler, boolean eulerI, boolean rk4) {
		this.niveaux.changeCalcul(euler, eulerI, rk4);

	}

	/**
	 * Methode permettant de modifier le niveau ou l'utilisateur est rendu
	 */

	public void setNiveauChoisie() {
		String nomNiveau = this.fenetreSelectionNiveaux.getNomNiveauSelectionne();
		Niveaux selection = null;
		if (nomNiveau.equals("niveau_editable"))
			selection = SelectionNiveau.loadEditableLevel(LONGUEUR_MONDE_REEL, hauteurMonde);
		else
			selection = SelectionNiveau.loadCreatedLevel(LONGUEUR_MONDE_REEL, hauteurMonde,
					Integer.parseInt(nomNiveau));
		this.niveaux = selection;
		start();
	}

	/**
	 * Methode permettant de recommencer un meme niveau
	 */

	public void resetNiveauCourant() {
		setNiveauChoisie();
		this.fin = false;
		repaint();
	}

	/**
	 * Methode permettant de mettre le jeu sur pause
	 */

	public void pause() {
		running = false;
	}

	/**
	 * Methode permettant de reprendre le jeu mis en pause
	 */
	public void resume() {
		start();
	}

	/**
	 * Methode retournant la liste des profils
	 * 
	 * @return = arrayList contenant tous les profils
	 */

	public ArrayList<Profil> getListeProfils() {
		return this.listeProfils;
	}

	/**
	 * Methode permettant de changer de profil
	 * 
	 * @param pos = position du profil dans la liste
	 */

	public void setIndexProfilCourant(int pos) {
		this.indexProfilCourant = pos;
		this.mainMenu.setProfilCourant(this.listeProfils.get(indexProfilCourant));
		this.fenetreSelectionNiveaux.setNiveauProfil(this.listeProfils.get(indexProfilCourant).getNiveau());
		this.fenetreSelectionNiveaux.setNiveau(this.listeProfils.get(indexProfilCourant).getNiveau() + "");
		repaint();
	}

	/**
	 * Methode permettant de revenir au menu principal pendant une session de
	 * jeu
	 */

	public void revenirAuMenu() {
		this.running = false;
		this.isPlaying = false;
		for (PhysicsListener ecout : OBJETS_ENREGISTRES.getListeners(PhysicsListener.class)) {
			ecout.isRunning(running);
		}
		this.fin = false;

		repaint();
	}
	/**
	 * Methode permetant de charger un fichier .tomBoy correspondant aux information du profil
	 * Apres lecture, un nouveau profil sera ajouté au arrayList de profils de la classe et un
	 *  evenement personnalise activera une methode dans la fenetre principale
	 * @param fichier = fichier .tomboy ayant les informations d'un profil
	 */

	public void loadFile(File fichier) {
		Profil temp = SauvegardeProfils.loadProfil(fichier);
		if (temp != null) {
			this.listeProfils.add(temp);
			this.indexProfilCourant++;
		}
		for (PhysicsListener ecout : OBJETS_ENREGISTRES.getListeners(PhysicsListener.class)) {
			ecout.profilAdded(listeProfils.get(indexProfilCourant));
		}
		this.fenetreSelectionNiveaux.setNiveauProfil(this.listeProfils.get(indexProfilCourant).getNiveau());

	}

	//Mitchell
	/**
	 * Methode retournant la fenetre d'edition du niveau editable
	 * @return = la fenetre d'edition du niveau editable
	 */

	public FenetreEditable getEditable() {
		return editable;
	}

	/**
	 * Methode permettant de passer au niveau suivant
	 */
	public void niveauSuivant() {
		String niveauStr = this.fenetreSelectionNiveaux.getNomNiveauSelectionne();
		if (!niveauStr.equals("niveau_editable")) {
			int niveauInt = Integer.parseInt(niveauStr);
			if (niveauInt != 14) {
				this.fenetreSelectionNiveaux.setNiveau(niveauInt + 1 + "");
				start();
				setNiveauChoisie();
			}
		}
	}

	/**
	 * Methode retournant le profil actif
	 * @return = le profil actif
	 */
	public Profil getProfilCourant() {
		return this.listeProfils.get(indexProfilCourant);
	}
	/**
	 * Methode retournant le niveau courant du joue par le joueur
	 * @return = le niveau courant joue par le joueur
	 */
	public int getNiveauCourant(){
		String niveauStr = this.fenetreSelectionNiveaux.getNomNiveauSelectionne();
		int niveauInt = -1;
		if(!niveauStr.equals("niveau_editable"))
			niveauInt = Integer.parseInt(niveauStr);
		return niveauInt;
	}
	/**
	 * Methode ajoutant les methodes d'ecouteurs personnalise de la classe
	 */

	public void addEcouteursPerso(){
		this.fenetreProfil.addNewProfilListener(new NewProfilListener() {

			@Override
			public void profilCree(boolean isAProfilCreated) {
				if (isAProfilCreated) {
					listeProfils.add(fenetreProfil.getProfilCree());
					indexProfilCourant = listeProfils.size() - 1;
					SauvegardeProfils.save(fenetreProfil.getProfilCree());
					fenetreProfil.setAProfilCreated(false);
					mainMenu.setProfilCourant(listeProfils.get(indexProfilCourant));
					repaint();
					for (PhysicsListener ecout : OBJETS_ENREGISTRES.getListeners(PhysicsListener.class)) {
						ecout.profilAdded(listeProfils.get(indexProfilCourant));
					}
					fenetreSelectionNiveaux.setNiveauProfil(1);
					fenetreSelectionNiveaux.setNiveau(1+"");
				}

			}

		});
		editable.addEasyListener(new GenerationListener() {
			@Override
			public void ennemiesGeneration(ArrayList<Ennemies> ennemies) {
				niveaux.setEnnemies(ennemies);

			}

			@Override
			public void ressortsGeneration(Ressort ressortGauche, Ressort ressortDroit) {
				niveaux.setRessort(ressortGauche, ressortDroit);
			}

			@Override
			public void playerPhysicsGeneration(double massTank, double largeurTank, double hautTank, double forceMov,
					double coeffSol, double massVAir, double g, double forceMax, double massBall, double coeffAir) {
				niveaux.getPlayer().setMassTank(massTank);
				niveaux.getPlayer().setLargeurReelle(hautTank);
				niveaux.getPlayer().setLongueurReelle(largeurTank);
				niveaux.getPlayer().setForceDeMov(forceMov);
				niveaux.getPlayer().setCoeffSol(coeffSol);
				niveaux.getPlayer().setMassVAir(massVAir);
				niveaux.getPlayer().setMaxForceCanon(forceMax);
				niveaux.getPlayer().setMassBall(massBall);
				niveaux.getPlayer().setCoeffAir(coeffAir);
				niveaux.setG(g);
			}

		});

	}


}
