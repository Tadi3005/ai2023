package treasurequest.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * Représente la création du profil du joueur
 */
public class ProfilPlayerFactory {
    private final ProfilPlayer profilPlayer;
    private final List<Coordinate> coordVisited;

    /**
     * Constructeur de la classe ProfilPlayerFactory
     * @param caseDug la map des cases creusées par le joueur
     */
    public ProfilPlayerFactory(Map<Coordinate, Case> caseDug) {
        this.coordVisited = new ArrayList<>();
        this.profilPlayer = caseDug == null ? ProfilPlayer.NONE : createProfilPlayer(caseDug);
    }

    /**
     * Crée le profil du joueur
     * @param caseDigged la map des cases creusées par le joueur
     * @return le profil du joueur
     */
    private ProfilPlayer createProfilPlayer(Map<Coordinate, Case> caseDigged) {
        if (caseDigged == null) {
            return ProfilPlayer.NONE;
        }
        switch (typeBiggerZone(caseDigged)) {
            case SAND:
                return ProfilPlayer.TOURIST;
            case GRASSLAND:
                return ProfilPlayer.FARMER;
            case FOREST:
                return ProfilPlayer.LUMBERJACK;
            case ROCK:
                return ProfilPlayer.MINER;
            default:
                return ProfilPlayer.NONE;
        }
    }

    /**
     * Retourne le type de la plus grande zone
     * @param caseDigged la map des cases creusées par le joueur
     * @return le type de la plus grande zone
     */
    private CaseType typeBiggerZone(Map<Coordinate, Case> caseDigged) {
        if (caseDigged == null) {
            return CaseType.WATER;
        }
        return biggerZone(caseDigged).getCaseType();
    }

    /**
     * Retourne la plus grande zone
     * @param caseDigged la map des cases creusées par le joueur
     * @return la plus grande zone
     */
    private Zone biggerZone(Map<Coordinate, Case> caseDigged) {
        if (caseDigged == null) {
            return new Zone(CaseType.WATER, new ArrayList<>());
        }
        // 1. Initialiser la plus grande zone courante comme étant une zone vide de type Eau.
        Zone zoneCourante = new Zone(CaseType.WATER, new ArrayList<>());
        // 2. Pour chaque case creusée par le joueur pas encore visitée:
        for (Coordinate coord : caseDigged.keySet()) {
            if (!coordVisited.contains(coord)) {
                // 2.1. Explorer la zone de la case courante.
                Zone zoneExploree = explorerZone(coord, caseDigged);
                // 2.2. Si la zone explorée est plus grande que la zone courante, remplacer la plus grande zone par la zone explorée
                if (zoneExploree.compareTo(zoneCourante) > 0) {
                    zoneCourante = zoneExploree;
                }
            }
        }
        // 3. Retourner la plus grande zone
        return zoneCourante;
    }

    /**
     * Retourne la zone de la case à explorer
     * @param coord la coordonnée de la case à explorer
     * @param caseDigged la map des cases creusées par le joueur
     * @return la zone de la case à explorer
     */
    private Zone explorerZone(Coordinate coord, Map<Coordinate, Case> caseDigged) {
        if (coord == null || caseDigged == null) {
            return new Zone(CaseType.WATER, new ArrayList<>());
        }
        // 1. Ajouter la case à explorer aux cases visitées et initialiser une zone composée de cette case
        this.coordVisited.add(coord);
        Zone zoneCourante = new Zone(caseDigged.get(coord).getCaseType(), List.of(coord));

        // 2. Pour chaque case adjacente à la case explorer, de même type et pas encore visitée
        for (Coordinate coordinate : caseDigged.keySet()) {
            if (coordinate.isAdjacent(coord) && caseDigged.get(coordinate).getCaseType() == caseDigged.get(coord).getCaseType() && !coordVisited.contains(coordinate) && coordinate != coord) {
                // 2.1. Explorer la zone de la case adjacente
                Zone zoneExploree = explorerZone(coordinate, caseDigged);
                // 2.2. Ajouter la zone retournée à la zone de la case à retourner
                zoneCourante = zoneCourante.fusionateAt(zoneExploree);
            }
        }
        return zoneCourante;
    }

    /**
     * Retourne le profil du joueur
     * @return le profil du joueur
     */
    public ProfilPlayer getProfilPlayer() {
        return this.profilPlayer;
    }
}
