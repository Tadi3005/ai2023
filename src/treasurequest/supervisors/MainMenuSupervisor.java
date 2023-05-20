package treasurequest.supervisors;

import java.util.List;

import treasurequest.domains.GameFactory;
import treasurequest.supervisors.views.*;


/**
 * Fournit les données qu'une vue représenter le menu principal doit afficher.
 * 
 * Réagit aux événements de haut niveau signalé par sa vue.
 * */
public class MainMenuSupervisor {
	private final GameFactory gameFactory;
	private static final int NEW_GAME = 0;
	private static final int ALEAT_GAME = 1;
	private static final int EXIT_ITEM = 2;
	private final static String NEW_GAME_TEXT = "Nouvelle partie";
	private final static String ALEAT_GAME_TEXT = "Partie aléatoire";
	private final static String EXIT_TEXT = "Quitter";
	private MainMenuView view;

	/**
	 * Construit un superviseur pour le menu principal.
	 * @param gameFactory la fabrique de jeu
	 */
	public MainMenuSupervisor (GameFactory gameFactory) {
		this.gameFactory = gameFactory;
	}

	public void setView(MainMenuView view) {
		if(view == null) {
			return;
		}

		this.view = view;
		this.view.setItems(List.of(NEW_GAME_TEXT, ALEAT_GAME_TEXT, EXIT_TEXT));
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur sélectionne un item.
	 *
	 * @param itemPos la position de l'item sélectionné. {@code item in [0; items.length[}
	 * */
	public void onItemSelected(int itemPos) {

		if(EXIT_ITEM == itemPos) {
			view.confirmExit();
		}

		if (ALEAT_GAME == itemPos) {
			gameFactory.createRandomGame();
			view.goTo(ViewNames.PLAY_GAME);
		}

		if(NEW_GAME == itemPos) {
			gameFactory.createGame();
			view.goTo(ViewNames.PLAY_GAME);
		}
	}
}
