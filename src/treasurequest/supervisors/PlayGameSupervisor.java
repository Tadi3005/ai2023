package treasurequest.supervisors;

import treasurequest.domains.*;
import treasurequest.supervisors.views.PlayGameView;
import treasurequest.supervisors.views.SpriteType;
import treasurequest.supervisors.views.TileType;
import treasurequest.supervisors.views.ViewNames;

/**
 * Réagit aux événements utilisateurs de sa vue en mettant à jour une partie en cours et fournit à sa vue les données à afficher.
 * */
public class PlayGameSupervisor {
	private final GameFactory gameFactory;
	private TreasureQuestGame game;
	private PlayGameView view;

	/**
	 * Construit un superviseur pour l'écran de jeu.
	 * @param gameFactory la fabrique de jeu
	 */
	public PlayGameSupervisor(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
	}

	/**
	 * Définit la vue de ce superviseur à {@code view}.
	 * */
	public void setView(PlayGameView view) {
		if(view == null) {
			return;
		}

		this.view = view;
	}

	/**
	 * Méthode appelée juste avant que la vue de ce superviseur soit affichée à l'écran.
	 *
	 * Le superviseur affiche les données de départ du jeu (cout de la case active, nombre de trésors, bourse du joueur, etc.).
	 * Il dessine également les cases et indique quelle case est active.
	 *
	 * ITERATION 1
	 * POST-CONDITIONS :
	 * - La map n'est pas NULL
	 * - Le joueur n'est pas NULL
	 * - La case active est au milieu de la map
	 * - Le nombre de trésors correspond à 10% du nombre de cases creusables de la map
	 * - Le nombre de trésors est de minimum 1
	 * - La bourse du joueur est de minimum 0
	 * - L'ensemble des cases ne sont pas creusées
	 * */
	public void onEnter(String fromView) {
		if (ViewNames.MAIN_MENU.equals(fromView)) {
			// Obtenir le dernier jeu créé
			this.game = gameFactory.getCurentGame();
			// Dessiner l'écran de jeu
			draw();
		}
	}

	/**
	 * Dessine l'écran de jeu.
	 */
	private void draw() {
		// Nettoyer la vue
		view.clearTiles();

		// Placer les cases
		drawMap(game);

		// Placer la case active
		view.setActiveCase(game.getCoordinateActive().getRow(), game.getCoordinateActive().getCol());

		// Afficher les données du jeu
		viewDataOf(game);
	}

	/**
	 * Affiche les données du jeu.
	 * @param game le jeu
	 */
	private void viewDataOf(TreasureQuestGame game) {
		// Afficher la bourse du joueur
		view.setPlayerCoins("Pièces d'or : " + game.getPlayerAmount());

		// Afficher le nombre de trésors
		view.setTreasuresCount("Trésors restants : " + game.getTreasureCount());

		// Afficher le type de la case active
		view.setActiveCaseType("Type de la case : " + game.getCaseTypeAt(game.getCoordinateActive()));

		// Afficher le cout de la case active
		view.setActiveCaseCost("Coût de la case : " + game.getCaseCostAt(game.getCoordinateActive()) + "P");
	}

	/**
	 * Dessine les cases du jeu.
	 * @param game le jeu
	 */
	private void drawMap(TreasureQuestGame game) {
		for (var coordinate : game) {
			Case caseGame = game.getCaseAt(coordinate);
			view.setTileAt(TileType.valueOf(caseGame.getCaseType().name()), coordinate.getRow(), coordinate.getCol());

			// Dessiner le sprite de la case
			if (caseGame.isDug()) {
				view.setSpriteAt(SpriteType.DUG, coordinate.getRow(), coordinate.getCol());

				// Si la case est un trésor
				if (caseGame.isTreasure()) {
					view.setSpriteAt(SpriteType.TREASURE, coordinate.getRow(), coordinate.getCol());
				}

				if (caseGame.hasOrientation()) {
					view.setSpriteAt(SpriteType.valueOf(caseGame.getOrientation().name()), coordinate.getRow(), coordinate.getCol());
				}
			}
		}
	}

	/**
	 * Tente de déplacer la case active de {@code deltaRow} lignes et de {@code deltaRow} colonnes.
	 *
	 * Cette méthode doit vérifier que les coordonnées calculées correspondent bien à une case du terrain.
	 * */
	public void onMove(int deltaRow, int deltaCol) {
		game.movePlayer(deltaRow, deltaCol);
		draw();
	}

	/**
	 * Tente de creuser la case active et met à jour l'affichage en conséquence.
	 *
	 * Ne fais rien si la case active a déjà été creusee ou si elle est de type Eau.
	 *
	 * ITERATION 2
	 * POST-CONDITIONS :
	 * - Le montant de la bourse du joueur est diminué du cout de la case active
	 * - La bourse du joueur se voit augmentée du montant du trésor si la case active est un trésor (montant aléatoire entre 10 et 20) et de 0 sinon
     * Formule : bourse = bourse - cout de la case active + montant (montant de 0 si la case n'est pas un trésor et montant aléatoire entre 10 et 20 si la case est un trésor)
	 * - Le nombre de trésors est diminué de 1 si la case active est un trésor
	 * - La case active est creusée
	 * */
	public void onDig() {
		game.dig();
		draw();

		if (game.isGameOver()) {
			game.stopTimer();
			game.setProfilPlayer();
			view.goTo(ViewNames.GAME_OVER);
		}
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite interrompre la partie.
	 *
	 * Ce superviseur demande à sa vue de naviguer vers le menu principal.
	 * */
	public void onStop() {
		game.stopTimer();
		view.goTo(ViewNames.MAIN_MENU);
	}

}
