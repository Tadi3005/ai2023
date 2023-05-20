package treasurequest;

import javax.swing.SwingUtilities;

import treasurequest.domains.GameFactory;
import treasurequest.domains.Player;
import treasurequest.domains.TreasureQuestGameFactory;
import treasurequest.supervisors.*;
import treasurequest.supervisors.views.ViewNames;
import treasurequest.swing.*;

/**
 * Construit l'application et affiche la fenêtre principale.
 * 
 * @author Nicolas Hendrikx
 *
 */
public class Program {
	/**
	 * Point d'entrée de l'application.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Program::buildAndRun);

	}

	private static void buildAndRun() {
		String fileMap = "resources/maps/map-sample-test.txt";
		Player player = new Player();
		GameFactory gameFactory = new TreasureQuestGameFactory(fileMap, player);

		var menuSupervisor = new MainMenuSupervisor(gameFactory);
		var playSupervisor = new PlayGameSupervisor(gameFactory);
		var endSupervisor = new GameOverSupervisor(gameFactory);
		MainWindow main = new MainWindow("B1UE09 - Treasure Quest",
				new MainMenuSwingView(menuSupervisor),
				new PlayGameSwingView(playSupervisor),
				new GameOverSwingView(endSupervisor));

		main.start(ViewNames.MAIN_MENU);
	}
}
