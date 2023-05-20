package treasurequest.domains;

import java.time.Duration;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Représente une case du jeu TreasureQuest
 *
 * ITERATION 2 :
 * CALCUL DE CTT DU CALCUL D'INDICES
 * - la méthode setIndices va boucler sur l'ensemble des cases de la carte. Cette méthode est en O(n) avec n le nombre de cases de la carte
 * - la méthode setIndices va appeler une fois la méthode getCoordsTreasuresNextTo qui est en O(n - 1) car elle boucle sur l'ensemble des cases pour trouver les trésors à proximité sauf la case courante
 * - la méthode setIndices va appeler une fois la méthode closestTreasure qui va boucler sur l'ensemble des trésors à proximité de la case. Cette méthode est en O(m) avec m le nombre de trésors à proximité de la case
 * - la méthode setIndices va appeler une fois la méthode getOrientation qui est en O(1)
 * - N représente le nombre de cases de la carte et M le nombre de trésors à proximité de la case
 * donc setIndices est en O(n) + O(n - 1) * (O(m) + O(1) + O(1)) = O(n) + O(n * m) = O(n * m)
 *
 * CHOIX DE TYPE ET D'IMPLEMENTATION
 * CHOIX DU TYPE : LIST
 * - Nous avons besoin de la méthode get pour obtenir la coordonnées de la case à la position donnée
 * - De plus nous devons itérer sur chacune des cases à proximité de la case courante pour trouver le trésor le plus proche
 * - Nous avons besoin de la méthode add pour ajouter les coordonnées des trésors à proximité de la case courante
 * CHOIX DE L'IMPLEMENTATION : ARRAYLIST
 * - Même si on peut avoir des coordonnées dupliqués, nous ne bouclons qu'une fois sur l'ensemble des cases à proximité de la case courante
 * - Le get est en O(1) 
 * - Le add est généralement en O(1) sauf si on dépasse la capacité de l'arraylist, dans ce cas il est en O(n)
 * sauf que nous ne dépasserons jamais la capacité de l'arraylist vu que le nombre de trésors à proximité de la case courante est au maximum à 24 cases
 */
public class TreasureQuestGame implements Iterable<Coordinate> {
    private final CaseMap caseMap;
    private final Player player;

    /**
     * Construit le jeu Treasure Quest.
     * @param caseMap la carte du jeu
     */
    public TreasureQuestGame (CaseMap caseMap, Player player) {
        this.caseMap = caseMap == null ? new CaseMap(new HashMap<>()) : caseMap;
        this.player = player == null ? new Player() : setPlayerAtStart(player);
        this.player.startTime();
    }

    private Player setPlayerAtStart(Player player) {
        player.setAmount(this.caseMap.getTreasureCount()*2);
        player.setCoordinate(this.caseMap.getCoordinateMiddle());
        player.addGains(caseMap.getTreasureCount()*2);
        return player;
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return caseMap.iterator();
    }

    /**
     * Retourne le nombre de trésors à trouver sur la carte
     * @return le nombre de trésors à trouver sur la carte
     */
    public int getTreasureCount() {
        return this.caseMap.getTreasureCount();
    }

    /**
     * Retourne le montant du joueur
     */
    public int getPlayerAmount() {
        return this.player.getAmount();
    }

    /**
     * Retourne la case à la position donnée
     * @param coordinate les coordonnées de la case
     * @return la case à la position donnée
     */
    public Case getCaseAt(Coordinate coordinate) {
        return coordinate == null ? new Case(CaseType.UNKNOWN) : this.caseMap.getCaseAt(coordinate);
    }

    /**
     * Retourne le prix de la case à la position donnée
     * @param coordCase les coordonnées de la case
     * @return le prix de la case à la position donnée
     */
    public int getCaseCostAt(Coordinate coordCase) {
        return coordCase == null ? 0 : this.caseMap.getCaseCostAt(coordCase);
    }

    /**
     * Retourne le type de la case à la position donnée
     * @param coordActiveCase les coordonnées de la case
     * @return le type de la case à la position donnée
     */
    public String getCaseTypeAt(Coordinate coordActiveCase) {
        return coordActiveCase == null ? " " : this.caseMap.getCaseTypeFor(coordActiveCase);
    }

    /**
     * Retourne les coordonnées du joueur
     * @return les coordonnées du joueur
     */
    public Coordinate getCoordinateActive() {
        return this.player.getCoordinate();
    }

    /**
     * Déplace le joueur
     * @param deltaRow delta de ligne
     * @param deltaCol delta de colonne
     */
    public void movePlayer(int deltaRow, int deltaCol) {
        player.move(deltaRow, deltaCol);
    }

    /**
     * Creuse la case à la position active du joueur
     */
    public void dig() {
        Case caseDig = getCaseAt(getCoordinateActive());

        // Voir si la case est creusable, que le joueur a assez d'argent et qu'elle n'est pas encore creusée
        if (caseDig.isDigable() && player.getAmount() >= getCaseCostAt(getCoordinateActive()) && !caseDig.isDug()) {
            // Creuser la case
            caseDig.dig();
            // Payer le prix de la case
            player.pay(caseDig.getCost());

            if (caseDig.isTreasure()) {
                // Si la case est un trésor, ajouter le trésor au joueur
                player.receive(caseDig.getAmountTreasure());

                // Supprimer le trésor de la carte
                caseMap.removeTreasure(this.getCoordinateActive());
            }
            this.player.addCaseDig(getCoordinateActive(), caseDig);
        }
    }

    /**
     * Vérifie si une partie est terminée ou non
     * @return true si une partie est terminée, false sinon
     */
    public boolean isGameOver() {
        return allTreasuresFound() || banqueroute();
    }

    /**
     * Vérifie si un joueur a fait banqueroute ou non (plus d'argent ou plus de cases creusables)
     * @return true si un joueur a fait banqueroute, false sinon
     */
    private boolean banqueroute() {
        return this.player.getAmount() <= 0 || !caseMap.canDigWith(player.getAmount());
    }

    /**
     * Vérifie si tous les trésors ont été trouvés ou non
     * @return true si tous les trésors ont été trouvés, false sinon
     */
    private boolean allTreasuresFound() {
        return this.caseMap.getTreasureCount() == 0;
    }

    /**
     * Retourne les gains du joueur
     * @return les gains du joueur
     */
    public int getGainsPlayer() {
        return this.player.getGains();
    }

    /**
     * Retourne les dépenses du joueur
     * @return les dépenses du joueur
     */
    public int getExpensesPlayer() {
        return this.player.getExpense();
    }

    /**
     * Arrète le timer du joueur
     */
    public void stopTimer() {
        this.player.stopTime();
    }

    /**
     * Retourne le temps de jeu du joueur
     * @return le temps de jeu du joueur
     */
    public Duration getTimeGame() {
        return this.player.getTimeGame();
    }

    /**
     * Retourne le profil du joueur
     * @return le profil du joueur
     */
    public ProfilPlayer getProfilPlayer() {
        return this.player.getProfilPlayer();
    }

    /**
     * Définit le profil du joueur
     */
    public void setProfilPlayer() {
        this.player.setProfilPlayer();
    }
}
