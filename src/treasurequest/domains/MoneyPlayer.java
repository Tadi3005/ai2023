package treasurequest.domains;

/**
 * Représente les finances du joueur
 */
public class MoneyPlayer {
    private int amount;
    private int expense;
    private int gains;

    /**
     * Retourne l'argent du joueur
     * @return l'argent du joueur
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Payer un montant
     * @param price le prix à payer
     */
    public void pay(int price) {
        this.expense += price;
        this.amount -= price;
    }

    /**
     * Recevoir un montant
     * @param price le prix à recevoir
     */
    public void receive(int price) {
        this.gains += price;
        this.amount += price;
    }

    /**
     * Retourne les gains du joueur
     * @return les gains du joueur
     */
    public int getGains() {
        return this.gains;
    }

    /**
     * Retourne les dépenses du joueur
     * @return les dépenses du joueur
     */
    public int getExpense() {
        return this.expense;
    }

    /**
     * Set le montant du joueur
     * @param amount le montant du joueur
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Ajoute des dépenses au joueur
     * @param amount le montant à ajouter
     */
    public void addGains(int amount) {
        this.gains += amount;
    }
}
