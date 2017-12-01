package Map;

import static org.junit.Assert.*;

import org.junit.Test;

public class mapTest {

	@Test
	public void testSquare() {
		Square s = new Square("Test",true);
		assertEquals("Test",s.toString());
		boolean b = s.StepIn();
		assertEquals(true,b);
		
	}
	
	@Test
	public void testBlue(){
		blueHouseMap b = new blueHouseMap();
	}
	
	@Test
	public void testMap(){
		Map testmap = new Map(20.0);
	}

}
