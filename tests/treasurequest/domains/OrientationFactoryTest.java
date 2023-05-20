package treasurequest.domains;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrientationFactoryTest {

    @Test
    void getMapWithOrientation() {
        OrientationFactory orientationFactory = new OrientationFactory(
                Map.of(
                        new Coordinate(0, 0), new Case(CaseType.SAND),
                        new Coordinate(0, 1), new Case(CaseType.SAND),
                        new Coordinate(0, 2), new Case(CaseType.SAND),
                        new Coordinate(1, 0), new Case(CaseType.SAND),
                        new Coordinate(1, 1), new Case(CaseType.SAND),
                        new Coordinate(1, 2), new Case(CaseType.SAND),
                        new Coordinate(2, 0), new Case(CaseType.SAND),
                        new Coordinate(2, 1), new Case(CaseType.SAND),
                        new Coordinate(2, 2), new Case(CaseType.SAND)
                )
        );
        
        assertNotNull(orientationFactory.getMapWithOrientation());
    }
    
    @Test
    void closestTreasureTest0() {
        Case case1 = new Case(CaseType.SAND);
        Case case2 = new Case(CaseType.SAND);
        Case case3 = new Case(CaseType.SAND);
        Case case4 = new Case(CaseType.SAND);
        Case case5 = new Case(CaseType.SAND);
        Case case6 = new Case(CaseType.SAND);
        Case case7 = new Case(CaseType.SAND);
        Case case8 = new Case(CaseType.SAND);
        Case case9 = new Case(CaseType.SAND);
        case1.setAmountTreasure();
        case8.setAmountTreasure();
        case2.setAmountTreasure();
        OrientationFactory orientationFactory = new OrientationFactory(
                Map.of(
                        new Coordinate(0, 0), case1,
                        new Coordinate(0, 1), case2,
                        new Coordinate(0, 2), case3,
                        new Coordinate(1, 0), case4,
                        new Coordinate(1, 1), case5,
                        new Coordinate(1, 2), case6,
                        new Coordinate(2, 0), case7,
                        new Coordinate(2, 1), case8,
                        new Coordinate(2, 2), case9
                )
        );
        assertNotNull(orientationFactory.getMapWithOrientation());
    }
    
    @Test
    void closestTreasureTest1() {
        Case case1 = new Case(CaseType.SAND);
        Case case2 = new Case(CaseType.SAND);
        Case case3 = new Case(CaseType.SAND);
        Case case4 = new Case(CaseType.SAND);
        Case case5 = new Case(CaseType.SAND);
        Case case6 = new Case(CaseType.SAND);
        Case case7 = new Case(CaseType.SAND);
        Case case8 = new Case(CaseType.SAND);
        Case case9 = new Case(CaseType.SAND);
        case1.setAmountTreasure();
        case3.setAmountTreasure();
        case4.setAmountTreasure();
        case6.setAmountTreasure();
        case7.setAmountTreasure();
        case9.setAmountTreasure();
        OrientationFactory orientationFactory = new OrientationFactory(
                Map.of(
                        new Coordinate(0, 0), case1,
                        new Coordinate(0, 1), case2,
                        new Coordinate(0, 2), case3,
                        new Coordinate(1, 0), case4,
                        new Coordinate(1, 1), case5,
                        new Coordinate(1, 2), case6,
                        new Coordinate(2, 0), case7,
                        new Coordinate(2, 1), case8,
                        new Coordinate(2, 2), case9
                )
        );
        assertNotNull(orientationFactory.getMapWithOrientation());
    }
}