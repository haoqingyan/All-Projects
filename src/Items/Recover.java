package Items;
import PokemonInit.*;

public class Recover extends Items implements java.io.Serializable{
	
	private String name = "Recover";
	int nums;
	
	public Recover(int i){
		super();
		nums = i;
	}

	@Override
	public void effect(Pokemon p) {
		// TODO Auto-generated method stub
		if (p.getHP()+30>p.getMaxHP()){
			p.changeHP(p.getHP()-p.getMaxHP());
		}
		else {
			p.changeHP(-30);
		}
		nums--;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public int getNums(){
		return nums;
	}
	
	public void addNums(int i){
		nums = nums + i;
	}
	
	
	
}
