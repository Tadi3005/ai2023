package treasurequest.domains;
import treasurequest.io.CharArrayFileReader;

import java.util.*;

/**
 * Représente la création de la carte du jeu TreasureQuest
 * @author alext
 */
public class CaseMapFactory {
    private static final Map<Coordinate, Case> MAP_EMPTY = new HashMap<>();
    private final CaseMap caseMap;
    private Map<Coordinate, Case> mapOfCaseMap;
    private static final int MAP_SIZE = 16;

    /**
     * Constructeur de la classe MapCaseFactory
     * @param fileReader le fichier contenant la carte du jeu
     */
    public CaseMapFactory(String fileReader, boolean isRandom) {
        // Créer la Map de la case Map
        this.mapOfCaseMap = fileReader == null ? MAP_EMPTY : isRandom ? createRandomMap(fileReader) : createMapOfCaseMap(fileReader);

        // Placer les trésors
        setUpTreasuresPlacement();

        // Placer les indices
        setUpIndices();

        // Créer la map
        this.caseMap = new CaseMap(mapOfCaseMap);
    }

    private Map<Coordinate, Case> createRandomMap(String fileReader) {
        if (fileReader == null) {
            return MAP_EMPTY;
        }
        char[][] fileReaderInArray = CharArrayFileReader.parseFile(fileReader);

        int startRow = calculedStartRow(fileReaderInArray.length, MAP_SIZE);
        int startCol = calculedStartCol(fileReaderInArray[0].length, MAP_SIZE);

        Map<Coordinate, Case> mapOfCaseMap = new HashMap<>();

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                CaseType caseType = CaseType.defineCaseType(fileReaderInArray[startRow + i][startCol + j]);
                mapOfCaseMap.put(new Coordinate(i, j), new Case(caseType));
            }
        }

        return mapOfCaseMap;
    }

    /**
     * Calcule la ligne de départ de la carte aléatoirement
     * @param rowsCount le nombre de lignes de la carte
     * @param mapSize la taille de la carte
     * @return la ligne de départ de la carte aléatoirement
     */
    public int calculedStartRow(int rowsCount, int mapSize) {
        return rowsCount > mapSize ? (int) (Math.random() * (rowsCount - mapSize)) : 0;
    }

    /**
     * Calcule la colonne de départ de la carte aléatoirement
     * @param colsCount le nombre de colonnes de la carte
     * @param mapSize la taille de la carte
     * @return la colonne de départ de la carte aléatoirement
     */
    public int calculedStartCol(int colsCount, int mapSize) {
        return colsCount > mapSize ? (int) (Math.random() * (colsCount - mapSize)) : 0;
    }


    /**
     * Place les trésors sur la carte
     */
    private void setUpTreasuresPlacement() {
        TreasuresPlacement placement = new RandomTreasuresPlacement(mapOfCaseMap);
        placement.placeTreasures();
    }

    /**
     * Place les indices sur la carte
     */
    private void setUpIndices() {
        OrientationFactory orientFactory = new OrientationFactory(mapOfCaseMap);
        this.mapOfCaseMap = orientFactory.getMapWithOrientation();
    }

    /**
     * Créer la map de la carte du jeu
     * @param fileReader le fichier contenant la carte du jeu
     * @return la map de la carte du jeu
     */
    private Map<Coordinate, Case> createMapOfCaseMap(String fileReader) {
        if (fileReader == null) {
            return MAP_EMPTY;
        }
        char[][] fileReaderInArray = CharArrayFileReader.parseFile(fileReader);

        Map<Coordinate, Case> mapOfCaseMap = new HashMap<>();

        for (int i = 0; i < fileReaderInArray.length; i++) {
            for (int j = 0; j < fileReaderInArray[i].length; j++) {
                CaseType caseType = CaseType.defineCaseType(fileReaderInArray[i][j]);
                mapOfCaseMap.put(new Coordinate(i, j), new Case(caseType));
            }
        }
        return mapOfCaseMap;
    }

    /**
     * Retourne la carte du jeu
     * @return la carte du jeu
     */
    public CaseMap getCaseMap() {
        return this.caseMap;
    }

}
