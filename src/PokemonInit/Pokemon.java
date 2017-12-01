package PokemonInit;
public abstract class Pokemon implements java.io.Serializable{
	
	public Pokemon(){
		
	}
	
//	public abstract void setAttributes(int h,int m,boolean w,String p,String r,int l,int e,int mo);
	
	
	
	public abstract String getName();
	public abstract int getHP();
	
	public abstract int getMP();
	
	public abstract int getMaxHP();
	
	public abstract int getMaxMP();
	
	public abstract void changeMaxHP(int i);
	
	public abstract void changeMaxMP(int i);
	
	public abstract boolean ifwild();
	
	public abstract String getProperty();
	
	public abstract String getRarity();
	
	public abstract int getLevel();
	
	public abstract int getExp();
	
	public abstract int getMoney();
	
	public abstract void changeHP(int i);
	
	public abstract void changeMP(int i);
	
	public abstract void lvlup(int exp);
	
	public abstract int trueAttack(String s);
	public abstract void changeAttack(int i);
}

