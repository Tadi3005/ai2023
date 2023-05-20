package treasurequest.domains;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une zone
 */
public class Zone implements Comparable<Zone> {
    private final CaseType caseType;
    private final List<Coordinate> coordinates;

    /**
     * Constructeur de la classe Zone
     * @param caseType le type de la zone
     * @param coordinates les coordonnées de la zone
     */
    public Zone(CaseType caseType, List<Coordinate> coordinates) {
        this.caseType = caseType == null ? CaseType.WATER : caseType;
        this.coordinates = coordinates == null ? new ArrayList<>() : List.copyOf(coordinates);
    }

    /**
     * Retourne le type de la zone
     * @return le type de la zone
     */
    public CaseType getCaseType() {
        return caseType;
    }

    /**
     * Fusionne deux zones
     * @param zoneRetourne la zone à fusionner
     * @return la zone fusionnée
     */
    public Zone fusionateAt(Zone zoneRetourne) {
        List<Coordinate> coodsZone = zoneRetourne.getCoordinates();
        List<Coordinate> allCoordinates = new ArrayList<>(this.coordinates);
        allCoordinates.addAll(coodsZone);
        return new Zone(this.caseType, allCoordinates);
    }

    /**
     * Compare deux zones
     * @param other une autre zone
     * @return le résultat de la comparaison
     */
    @Override
    public int compareTo(Zone other) {
        return this.coordinates.size() - other.coordinates.size();
    }

    /**
     * Retourne les coordonnées de la zone
     * @return les coordonnées de la zone
     */
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
