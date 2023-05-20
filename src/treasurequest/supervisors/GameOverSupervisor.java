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
 * CONDITIONS :
 * - L'ensemble des trésors a été trouvé
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
