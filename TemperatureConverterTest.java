//Haoqing Yan

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TemperatureConverterTest {
	 @Test
	  public void testFtoC() {
		 assertEquals( 40.6, TemperatureConverter.FtoC(105.0), 0.0001);
		 assertEquals( 37.0, TemperatureConverter.FtoC(98.6), 0.0001);
	  }
	 
	 @Test
	  public void testCtoF() {
		 assertEquals( 212.0, TemperatureConverter.CtoF(100), 0.0001);
		 assertEquals( 32.0, TemperatureConverter.CtoF(0), 0.0001);
	  }
}

