package treasurequest.domains;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class ZoneTest {
	@Test
	void zoneTest() {
		Zone zone = new Zone(null ,null);
		assertEquals(CaseType.WATER, zone.getCaseType());
	}
}
