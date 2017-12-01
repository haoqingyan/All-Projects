package Items;
import PokemonInit.*;
public abstract class Items {
	private String name;
	
	public Items(){
		
	}
	
	public abstract void effect(Pokemon p);
	
	public abstract String getName();
	
	public abstract int getNums();
	public abstract void addNums(int i);
	
}
