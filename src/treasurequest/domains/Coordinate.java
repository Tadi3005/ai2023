package treasurequest.domains;

import java.util.Objects;

/**
 * Représente les coordonnées d'une case de la carte du jeu TreasureQuest
 */
public class Coordinate {

	private final int row;
    private final int col;

    /**
     * Construit les coordonnées d'une case de la carte du jeu Treasure Quest.
     * @param row la ligne
     * @param col la colonne
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Retourne la ligne de la case
     * @return la ligne de la case
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retourne la colonne de la case
     * @return la colonne de la case
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Déplace la case de deltaRow et deltaCol
     * @param deltaRow deltaRow
     * @param deltaCol deltaCol
     */
    public Coordinate move(int deltaRow, int deltaCol) {
        return new Coordinate(this.row + deltaRow, this.col + deltaCol);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinate that = (Coordinate) object;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Retourne la distance entre les deux coordonnées
     * @param coordinate les coordonnées
     * @return la distance entre les deux coordonnées
     */
    public double distance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(this.row - coordinate.row, 2) + Math.pow(this.col - coordinate.col, 2));
    }

    /**
     * Retourne vrai si les deux coordonnées sont adjacentes et faux sinon
     * @param coord les coordonnées
     * @return vrai si les deux coordonnées sont adjacentes et faux sinon
     */
    public boolean isAdjacent(Coordinate coord) {
        return this.distance(coord) < 2;
    }
}
