package treasurequest.domains;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente le joueur du jeu TreasureQuest
 */
public class Player {
    private final MoneyPlayer money;
    private Coordinate coordinate;
    private final TimeGame timeGame;
    private ProfilPlayer profil;
    private final Map<Coordinate, Case> caseDug;

    /**
     * Construit un joueur en initialisant sa durée de jeu à 0
     */
    public Player() {
        this.money = new MoneyPlayer();
        this.timeGame = new TimeGame();
        this.profil = ProfilPlayer.NONE;
        this.caseDug = new HashMap<>();
        this.coordinate = new Coordinate(0, 0);
    }

    /**
     * Ajoute une case d'une case au case creusé par le joueur
     */
    public void addCaseDig(Coordinate caseDigCoord, Case caseDig) {
        if (caseDigCoord != null && caseDig != null) {
            this.caseDug.put(caseDigCoord, caseDig);
        }
    }

    /**
     * Retourne l'argent du joueur
     * @return l'argent du joueur
     */
    public int getAmount() {
        return this.money.getAmount();
    }

    /**
     * Déplace le joueur
     * @param deltaRow delta de ligne
     * @param deltaCol delta de colonne
     */
    public void move(int deltaRow, int deltaCol) {
        this.coordinate = this.coordinate.move(deltaRow, deltaCol);
    }

    /**
     * Retourne les coordonnées du joueur
     * @return les coordonnées du joueur
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Le joueur paye de l'argent
     * @param price le prix à payer
     */
    public void pay(int price) {
        this.money.pay(price);
    }

    /**
     * Le joueur reçoit de l'argent
     * @param price le prix à recevoir
     */
    public void receive(int price) {
        this.money.receive(price);
    }

    /**
     * Retourne les gains du joueur
     * @return les gains du joueur
     */
    public int getGains() {
        return this.money.getGains();
    }

    /**
     * Retourne les dépenses du joueur
     * @return les dépenses du joueur
     */
    public int getExpense() {
        return this.money.getExpense();
    }

    /**
     * Set le montant du joueur
     * @param amount le montant du joueur
     */
    public void setAmount(int amount) {
        this.money.setAmount(amount);
    }

    public void setCoordinate(Coordinate coordinateMiddle) {
        if (coordinateMiddle != null) {
            this.coordinate = new Coordinate(coordinateMiddle.getRow(), coordinateMiddle.getCol());
        }
    }

    /**
     * Démarre le temps de jeu du joueur
     */
    public void startTime() {
        this.timeGame.startTime();
    }

    /**
     * Arrête le temps de jeu du joueur
     */
    public void stopTime() {
        this.timeGame.stopTime();
    }

    /**
     * Retourne le temps de jeu du joueur
     * @return le temps de jeu du joueur
     */
    public Duration getTimeGame() {
        return this.timeGame.getTotalTime();
    }

    /**
     * Ajoute des dépenses au joueur
     * @param amount les dépenses à ajouter
     */
    public void addGains(int amount) {
        this.money.addGains(amount);
    }

    /**
     * Setter du profil du joueur
     */
    public void setProfilPlayer() {
        this.profil = new ProfilPlayerFactory(this.caseDug).getProfilPlayer();
    }

    /**
     * Retourne le profil du joueur
     * @return le profil du joueur
     */
    public ProfilPlayer getProfilPlayer() {
        return this.profil;
    }
}
