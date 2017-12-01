package Items;


import PokemonInit.Pokemon;

public class MPrecover extends Items implements java.io.Serializable{
	private String name = "MPrecover";
	private int nums  = 0;
	
	public MPrecover(int i){
		super();
		nums = i;
	}

	@Override
	public void effect(Pokemon p) {
		// TODO Auto-generated method stub
		if (p.getMP()+20 < p.getMaxMP()){
			p.changeMP(-20);
		}
		else {
			p.changeMP(p.getMP()-p.getMaxMP());
		}
		nums--;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getNums() {
		// TODO Auto-generated method stub
		return nums;
	}

	@Override
	public void addNums(int i) {
		// TODO Auto-generated method stub
		nums+=i;
	}
	
}
