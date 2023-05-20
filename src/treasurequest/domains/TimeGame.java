package treasurequest.domains;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Représente la durée de jeu
 */
public class TimeGame {
    private LocalDateTime startTime;
    private Duration totalTime;

    /**
     * Construit une durée de jeu en initialisant la durée à 0
     */
    public TimeGame() {
        totalTime = Duration.ZERO;
    }

    /**
     * Démarre la durée de jeu
     */
    public void startTime() {
        this.startTime = LocalDateTime.now();
    }

    /**
     * Arrête la durée de jeu et ajoute la durée de jeu à la durée totale
     */
    public void stopTime() {
        this.totalTime = totalTime.plus(Duration.between(this.startTime, LocalDateTime.now()));
    }

    /**
     * Retourne la durée totale de jeu
     * @return la durée totale de jeu
     */
    public Duration getTotalTime() {
        return totalTime;
    }

}
