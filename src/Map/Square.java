package Map;

public class Square implements java.io.Serializable{
	public boolean canStepIn;
	public String squareType;
	
	public Square(String squareType, boolean canStepIn){
		this.squareType = squareType;
		this.canStepIn = canStepIn;
	}
	public String toString(){
		return squareType;
	}
	public boolean StepIn(){
		return canStepIn;
	}
}
