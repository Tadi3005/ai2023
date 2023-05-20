package treasurequest.domains;

/**
 * Représente le type de la case du jeu TreasureQuest
 */
public enum CaseType {

    WATER("Water"),
    SAND("Sand", 1),
    GRASSLAND("Grassland", 2),
    FOREST("Forest", 3),
    ROCK("Rock", 5),
    UNKNOWN("Unknown");

    private final String typeCase;
    private final int cost;

    /**
     * Construit un type de case du jeu Treasure Quest sans coût
     * @param typeCase le type de la case
     */
    CaseType(String typeCase) {
        this.typeCase = typeCase == null ? "Unknown" : typeCase;
        this.cost = 0;
    }

    /**
     * Construit un type de case du jeu Treasure Quest avec un coût
     * @param typeCase le type de la case
     * @param costCase le coût de la case
     */
    CaseType (String typeCase, int costCase) {
        this.cost = costCase;
        this.typeCase = typeCase;
    }

    /**
     * Défini le type de la case
     * @param typeCase le type de la case
     * @return le type de la case
     */
    public static CaseType defineCaseType(char typeCase) {
        switch (typeCase) {
            case 'S':
                return CaseType.SAND;
            case 'P':
                return CaseType.GRASSLAND;
            case 'F':
                return CaseType.FOREST;
            case 'R':
                return CaseType.ROCK;
            case 'X':
                return CaseType.WATER;
            default:
                return CaseType.UNKNOWN;
        }
    }

    /**
     * Retourne le coût de la case
     * @return le coût de la case
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Retourne le type de la case
     * @return le type de la case
     */
    public String getType() {
        return this.typeCase;
    }
    
    /**
     * Retourne le typé de la case
     * @return le type de la case
     */
    public CaseType getCaseType() {
        return this;
    }
}
