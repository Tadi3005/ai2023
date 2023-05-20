package treasurequest.domains;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class CaseMapFactoryTest {
	
	CaseMapFactory caseMap = new CaseMapFactory("tests/map-sample-4.txt", false);
	CaseMapFactory caseMapRandom = new CaseMapFactory("tests/big-map.txt", true);
	
	@Test
	void caseMapFactoryTest() {
		assertNotNull(caseMap.getCaseMap());
		assertNotNull(caseMapRandom.getCaseMap());
	}
}
