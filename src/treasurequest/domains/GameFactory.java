package treasurequest.domains;

/**
 * Représente la création d'un jeu
 */
public interface GameFactory {
    /**
     * Créer un jeu
     */
    void createGame();

    /**
     * Retourne le jeu courant
     * @return le jeu courant
     */
    TreasureQuestGame getCurentGame();

    /**
     * Créer un jeu avec une carte aléatoire
     */
    void createRandomGame();
}
