package PokemonInit;
import java.util.ArrayList;


public class Ninetales extends Pokemon implements java.io.Serializable{
	
	private String Pokename = "Ninetales";//¾ÅÎ²
	private int attack = 25;
	private int HP = 73;
	private int maxHP = 73;
	private int maxMP = 70;
	private int MP = 70;
	private int level = 16;
	private int exp = 100;
	private int money = 100;
	private boolean wild = true;
	private String property = "fire";
	private String rarity = "rare";
	private int myexp = 0;
	
	public Ninetales(){
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
