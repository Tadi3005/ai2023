package treasurequest.domains;

import java.util.Objects;

/**
 * Représente une case du jeu TreasureQuest
 */
public class Case implements Comparable<Case> {
private final CaseType caseType;
private int pieceTreasure;
private boolean isDug;
private Orientation orientation;

    /**
     * Construit une case de la carte du jeu Treasure Quest.
     * @param caseType le type de la case
     */
    public Case(CaseType caseType)  {
        this.caseType = caseType == null ? CaseType.UNKNOWN : caseType;
        this.pieceTreasure = 0;
        this.isDug = false;
        this.orientation = Orientation.NONE;
    }

    /**
     * Retourne le prix de la case
     * @return le prix de la case
     */
    public int getCost() {
        return this.caseType.getCost();
    }

    /**
     * Dis si la case est creusable
     * @return true si la case est creusable, false sinon
     */
    public boolean isDigable() {
        return this.caseType != CaseType.WATER && this.caseType != CaseType.UNKNOWN;
    }

    /**
     * Place montant aléatoire pour le trésor de la case
     */
    public void setAmountTreasure() {
        this.pieceTreasure = (int) (Math.random() * (20 - 10)) + 10;
    }

    /**
     * Retourne le type de la case
     * @return le type de la case
     */
    public String getType() {
        return this.caseType.getType();
    }

    /**
     * Retourne le montant du trésor de la case
     * @return le montant du trésor de la case
     */
    public int getAmountTreasure() {
        return this.pieceTreasure;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Case aCase = (Case) object;
        return pieceTreasure == aCase.pieceTreasure && caseType == aCase.caseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseType, pieceTreasure);
    }

    /**
     * Dis si la case a été creusée
     * @return true si la case a été creusée, false sinon
     */
    public boolean isDug() {
        return this.isDug;
    }

    /**
     * Creuse la case
     */
    public void dig() {
        this.isDug = true;
    }

    /**
     * Dis si la case contient un trésor
     * @return true si la case contient un trésor, false sinon
     */
    public boolean isTreasure() {
        return this.pieceTreasure > 0;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Dis si la case a une orientation
     * @return true si la case a une orientation, false sinon
     */
    public boolean hasOrientation() {
        return this.orientation != Orientation.NONE;
    }

    /**
     * Compare la valeur du trésor de la case avec celle d'une autre case
     * @param otherCase the object to be compared.
     * @return une valeur négative si la valeur du trésor de la case est inférieure à celle de l'autre case,
     *        une valeur positive si la valeur du trésor de la case est supérieure à celle de l'autre case,
     *        0 si les deux valeurs sont égales.
     */
    @Override
    public int compareTo(Case otherCase) {
        return this.pieceTreasure - otherCase.pieceTreasure;
    }

    public CaseType getCaseType() {
        return this.caseType.getCaseType();
    }
}
