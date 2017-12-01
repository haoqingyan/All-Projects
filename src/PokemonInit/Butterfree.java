package PokemonInit;
import java.util.ArrayList;


public class Butterfree extends Pokemon implements java.io.Serializable{
	
	private String Pokename = "Butterfree";//°Í´óºû
	private int HP = 60;
	private int attack = 20;
	private int maxHP = 60;
	private int maxMP = 60;
	private int MP = 60;
	private int level = 16;
	private int exp = 100;
	private int money = 100;
	private boolean wild = true;
	private String property = "Bug";
	private String rarity = "rare";
	int myexp = 0;
	
	public Butterfree(){
		super();
	}
	
	public String getName(){
		return Pokename;
	}
	
	
	public int getHP(){
		return HP;
	}
	
	public int getMP(){
		return MP;
	}
	
	public boolean ifwild(){
		return wild;
	}
	
	public String getProperty(){
		return property;
	}
	
	public String getRarity(){
		return rarity;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getExp(){
		return exp;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeHP(int i){
		HP = HP - i;
	}
	
	public void changeMP(int i){
		MP = MP - i;
	}
	
	public void changeMaxHP(int i){
		maxHP = maxHP - i;
	}
	
	public void changeMaxMP(int i){
		maxMP = maxMP - i;
	}
	
	public void lvlup(int exp){
		myexp+=exp;
		int up = exp/100;
		level+=up;
		myexp = myexp - up*100;
		maxHP += 5*up;
		maxMP += 5*up;
		attack += 2*up;
		
		if (up>=1){
			HP = maxHP;
			MP = maxMP;
		}
	}
	
	public int getMaxHP(){
		return maxHP;
	}
	
	public int getMaxMP(){
		return maxMP;
	}
	
	@Override
	public int trueAttack(String s) {
		if (s != "Bug")
			return attack+5;
		else
			return attack;
	}
	
	public void changeAttack(int i){
		attack += i;
	}
}
