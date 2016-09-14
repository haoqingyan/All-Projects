//Haoqing Yan    
//converter C to F, or F to C.
public class TemperatureConverter {
	public static double CtoF(double C){
		double F = 32+1.8*C;
		return roundToOne(F);
	}
	public static double FtoC(double F){
		double C = (F-32.0)*5.0/9.0;
		return roundToOne(C);
	}
	
	private static double roundToOne(double d){
		double temp = Math.round(d * 10);
		return temp/10.0;
	}
}
