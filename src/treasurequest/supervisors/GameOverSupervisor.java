package treasurequest.supervisors;

import treasurequest.domains.GameFactory;
import treasurequest.domains.TreasureQuestGame;
import treasurequest.supervisors.views.GameOverView;
import treasurequest.supervisors.views.ResultType;
import treasurequest.supervisors.views.ViewNames;

import java.time.Duration;

/**
 * Réagit aux événements de haut-niveau de sa vue et lui fournit des données à afficher.
 *
 * ITERATION 3
 * CONDITIONS DE FIN DE PARTIE :
 * - L'ensemble des trésors a été trouvé
 * - Le joueur a fait banqueroute si :
 * le montant du joueur est égal à 0
 * l'ensemble des cases non-creusées ont un montant inférieur au montant du joueur
 *
 * CTT POUR DETERMINER LA PLUS GRANDE ZONE CREUSEE PAR LE JOUEUR : (Se situe dans la classe ProfilPlayerFactory avec la méthode biggerZone)
 * Une première boucle va boucler sur l'ensemble des cases de la carte. Cette méthode est en O(n) avec n l'ensemble des coordonnées creusées par le joueur
 * Dans cette boucle :
 * - La méthode contains est en O(1)
 * - La méthode compareTo est en O(1)
 * - La méthode exploreZone qui :
 * - add la cordonnée de la case courante est en O(1)
 * Une deuxième boucle qui va boucler sur l'ensemble de coordonnées en O(n - 1) avec n l'ensemble des coordonnées creusées par le joueur et -1 la case courante
 * Dans cette boucle :
 * - La méthode isAdjacent est en O(1)
 * - La méthode get est en O(1)
 * - La méthode getCaseType est en O(1)
 * - Un appel récursif à la méthode exploreZone qui est en O(n - 1) avec n (dans le pire des cas si l'ensemble des cases creusés par le joeuur ne sont pas adjacente) l'ensemble des coordonnées creusées par le joueur et -1 la case courante
 * - La méthode fusionateAt est en O(1)
 *
 * Pour résumer la complexité de la méthode biggerZone est en O(n^3) avec n l'ensemble des coordonnées creusées par le joueur
 * */
public class GameOverSupervisor {
	private final GameFactory gameFactory;
	private GameOverView view;
	private TreasureQuestGame game;

	/**
	 * Construit un superviseur pour l'écran de fin de partie.
	 * @param gameFactory la fabrique de jeu
	 */
	public GameOverSupervisor(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
	}

	public void setView(GameOverView view) {
		if(view == null) {
			return;
		}
		
		this.view = view;
	}
	
	/**
	 * Méthode appelée par la vue quand une navigation vers elle est en cours.
	 * 
	 * @param fromView la vue d'origine. Correspond a priori à une constante définie dans ViewNames.
	 * */
	public void onEnter(String fromView) {
		if (ViewNames.PLAY_GAME.equals(fromView)) {
			game = gameFactory.getCurentGame();
			draw();
		}
	}

	private void draw() {
		view.clearPanels();
		view.addPanel(ResultType.GAIN, "Vous avez gagné " + game.getGainsPlayer() + "P");
		view.addPanel(ResultType.LOSS, "Vous avez perdu " + game.getExpensesPlayer() + "P");
		Duration duration = game.getTimeGame();
		long minutes = duration.toMinutes();
		long seconds = duration.minusMinutes(minutes).getSeconds();
		String durationString = String.format("%02d:%02d", duration.toMinutes(), seconds);
		view.addPanel(ResultType.DURATION, "Vous avez joué " + durationString);
		view.addPanel(ResultType.valueOf(game.getProfilPlayer().name()), "Votre profil est \"" + game.getProfilPlayer() + "\"");
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite retourner au menu principal.
	 * */
	public void onGoToMain() {
		view.goTo(ViewNames.MAIN_MENU);
	}
}
