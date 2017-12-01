package Items;
import java.util.Random;

import PokemonInit.Pokemon;

public class Pokeball extends Items implements java.io.Serializable{
	private Pokemon pokemon;
	public int number = 0;
	private boolean ifcatched;
	
	public Pokeball(int i) {
		super();
		number = i;
	}
	
//	public void CatchPokemon(Pokemon x){
//		number--;
//		Random generator = new Random();
//		float percent = generator.nextInt(100);
//		System.out.println(percent);
//		if(percent >= 50){
//			setPokemon(x);
//			ifcatched = true;
//		}
//		else{
//			ifcatched  = false;
//		}
//	}
	
//	public boolean ifcatched(){
//		return ifcatched;
//	}
//	
//	public Pokemon getPokemon(){
//		return pokemon;
//	}
//	
//	public void setPokemon(Pokemon p){
//		pokemon = p;
//	}
	
	public int Price(){
		return 200;
	}
	
	
	public void addNum(int i){
		number+=i;
	}
	
	public int getNum(){
		return number;
	}
	@Override
	public void effect(Pokemon p) {
		// TODO Auto-generated method stub
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Pokeball";
	}
	@Override
	public int getNums() {
		// TODO Auto-generated method stub
		return number;
	}
	@Override
	public void addNums(int i) {
		// TODO Auto-generated method stub
		number+=i;
	}
}
