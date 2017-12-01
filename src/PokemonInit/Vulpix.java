package PokemonInit;
import java.util.ArrayList;


public class Vulpix extends Pokemon implements java.io.Serializable{
	
	private String Pokename = "Vulpix";//ÁùÎ²
	private int attack = 14;
	private int HP = 38;
	private int maxHP = 38;
	private int maxMP = 48;
	private int MP = 48;
	private int level = 1;
	private int exp = 10;
	private int money = 10;
	private boolean wild = true;
	private String property = "fire";
	private String rarity = "normal";
	int myexp = 0;
	
	public Vulpix(){
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
		if (s == "grass")
			return attack*2;
		else if (s == "water")
			return (int)(attack * 0.5);
		else
			return attack;
	}
	
	public void changeAttack(int i){
		attack += i;
	}

}
