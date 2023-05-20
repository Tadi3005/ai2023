package treasurequest.domains;

/**
 * Enumère les types de profil d'un joueur du jeu Treasure Quest Game.
 *
 */
public enum ProfilPlayer {
    LUMBERJACK("Bucheron"), MINER("Mineur"), FARMER("Fermier"), TOURIST("Touriste"), NONE("Inconnu");

    private final String title;

    /**
     * Construit un profil de joueur.
     * @param title le titre du profil
     */
    ProfilPlayer(String title) {
        this.title = title == null ? "Inconnu" : title;
    }

    public String getTitle() {
        return this.title;
    }
}
