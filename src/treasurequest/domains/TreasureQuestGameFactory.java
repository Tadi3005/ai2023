package treasurequest.domains;

/**
 * Représente la création du jeu TreasureQuest
 * ITERATION 1 :
 * CALCUL DE CTT du placement de trésors
 * - setUpTreasures va appeler une fois la méthode shuffle qui est en O(n)
 * - setUpTreasures va appeler N fois la méthode get qui est en O(1)
 * - setUpTreasures va appeler N fois la méthode isDigable qui est en O(1)
 * - setUpTreasures va appeler N fois la méthode setAmountTreasure qui est en O(1)
 * avec N le nombre de cases de la carte
 * donc setUpTreasures est en O(n) + N * (O(1) + O(1) + O(1)) = O(n)
 */
public class TreasureQuestGameFactory implements GameFactory {
    private TreasureQuestGame curentGame;
    private final String fileReader;
    private final Player player;

    /**
     * Construit la création du jeu Treasure Quest.
     *
     * @param fileReader le fichier de la carte du jeu
     */
    public TreasureQuestGameFactory (String fileReader, Player player) {
        this.fileReader = fileReader == null ? "" : fileReader;
        this.player = player == null ? new Player() : player;
    }

    /**
     * Créer le jeu TreasureQuest
     */
    @Override
    public void createGame() {
        this.curentGame = new TreasureQuestGame(new CaseMapFactory(fileReader, false).getCaseMap(), this.player);
    }

    /**
     * Créer le jeu TreasureQuest avec une carte aléatoire
     */
    @Override
    public void createRandomGame() {
        this.curentGame = new TreasureQuestGame(new CaseMapFactory("resources/maps/big-map.txt", true).getCaseMap(), this.player);
    }

    /**
     * Retourne le dernier jeu TreasureQuestGame créé
     * @return le dernier jeu TreasureQuestGame créé
     */
    @Override
    public TreasureQuestGame getCurentGame() {
        return this.curentGame;
    }
}
