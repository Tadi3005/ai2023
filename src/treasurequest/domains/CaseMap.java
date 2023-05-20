package treasurequest.domains;
import java.util.*;


/**
 * Représente la carte du jeu TreasureQuest
 * ITERATION 1 :
 * CHOIX DE LA COLLECTION :
 * - La collection utilisée est une Map car elle permet de stocker des données sous forme de clé-valeur.
 * - L'ordre des cases n'est pas important, donc une Map non ordonnée est suffisante.
 * - On a besoin d'accéder à une case en particulier, donc une Map est plus adaptée qu'une List.
 */
public class CaseMap implements Iterable<Coordinate> {
    private final Map <Coordinate, Case> caseMap;
    private final List<Coordinate> coordOfTreasure;

    /**
     * Construit une carte du jeu Treasure Quest.
     * @param caseMap la carte du jeu
     */
    public CaseMap(Map<Coordinate, Case> caseMap) {
        this.caseMap = caseMap == null ? new HashMap<>() : Map.copyOf(caseMap);
        this.coordOfTreasure = createCoordsOfTreasures();
    }

    /**
     * Crée une liste des coordonnées des cases trésors
     * @return une liste des coordonnées des cases trésors
     */
    private List<Coordinate> createCoordsOfTreasures() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate coordinate : caseMap.keySet()) {
            if (getCaseAt(coordinate).isTreasure()) {
                coordinates.add(coordinate);
            }
        }
        return coordinates;
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return caseMap.keySet().iterator();
    }

    /**
     * Retourne les coordonnées de la case du milieu de la carte
     * @return les coordonnées de la case du milieu de la carte
     */
    public Coordinate getCoordinateMiddle() {
        return new Coordinate(this.getRowMiddle(), this.getColMiddle());
    }

    /**
     * Retourne le numéro de la ligne du milieu de la carte
     * @return le numéro de la ligne du milieu de la carte
     */
    private int getRowMiddle() {
        int maxRow = 0;
        for (Coordinate coordinate : this) {
             maxRow = Math.max(maxRow, coordinate.getRow());
        }

        return (int) Math.ceil(maxRow / 2.0);
    }

    /**
     * Retourne le numéro de la colonne du milieu de la carte
     * @return le numéro de la colonne du milieu de la carte
     */
    private int getColMiddle() {
        int maxCol = 0;
        for (Coordinate coordinate : this) {
             maxCol = Math.max(maxCol, coordinate.getCol());
        }

        return (int) Math.ceil(maxCol / 2.0);
    }

    /**
     * Retourne la case à la position donnée
     * @param coordinate la position de la case
     * @return la case à la position donnée
     */
    public Case getCaseAt(Coordinate coordinate) {
        return coordinate == null || !this.caseMap.containsKey(coordinate) ? new Case(CaseType.UNKNOWN) : this.caseMap.get(coordinate);
    }

    /**
     * Retourne le coût de la case à la position donnée
     * @param coordinate la position de la case
     * @return le coût de la case à la position donnée
     */
    public int getCaseCostAt(Coordinate coordinate) {
        return coordinate == null ? 0 : getCaseAt(coordinate).getCost();
    }

    /**
     * Retourne le type de la case à la position donnée
     * @param coordinate la position de la case
     * @return le type de la case à la position donnée
     */
    public String getCaseTypeFor(Coordinate coordinate) {
        return coordinate == null ? " " : getCaseAt(coordinate).getType();
    }

    /**
     * Retourne le nombre de trésors à trouver sur la carte
     * @return le nombre de trésors à trouver sur la carte
     */
    public int getTreasureCount() {
        return this.coordOfTreasure.size();
    }

    /**
     * Supprime les coordonnées du trésor à la position donnée
     * @param coordinateActive la position du trésor à supprimer
     */
    public void removeTreasure(Coordinate coordinateActive) {
    	if (coordinateActive != null) {
            this.coordOfTreasure.remove(coordinateActive);
    	}
    }

    /**
     * Voir si on peut encore creusé avec un certain montant
     * @param amount le montant
     * @return true si on peut encore creusé avec un certain montant
     */
    public boolean canDigWith(int amount) {
        for (Coordinate coordinate : this) {
            if (getCaseAt(coordinate).isDigable() && !getCaseAt(coordinate).isDug() && amount >= getCaseCostAt(coordinate)) {
                return true;
            }
        }
        return false;
    }

}
