package Trainer;
import java.util.ArrayList;
import java.util.Collections;

import Items.*;
//
//import Item;
import PokemonInit.*;
import java.awt.Point;
public class Trainer implements java.io.Serializable{
	
	private ArrayList<Items> items;//use string to instead of type item first as there is no item class in my folder now
	private ArrayList<Pokemon> Pokemons;
	private int Money;
	private Pokeball balls;
	public ArrayList<Pokemon> alivePokemons = new ArrayList<Pokemon>();
	public ArrayList<Pokemon> deadPokemons = new ArrayList<Pokemon>();
	
	public Trainer(){
		Money = 0;
		balls  = new Pokeball(10);
		items = new ArrayList<Items>();
		items.add(balls);
		items.add(new Recover(1));
		items.add(new MPrecover(1));
		
		Pokemons = new ArrayList<Pokemon>();
//		alivePokemons.add(new ManKey());
//		alivePokemons.add(new Butterfree());
		
		Pokemons = alivePokemons;
	}
	public int getAliveNum(){
		return alivePokemons.size();
	}
	public void addDeadPokemon(Pokemon deadPokemon){
		deadPokemons.add(deadPokemon);
	}
	public void removeAlivePokemon(Pokemon deadPokemon){
		alivePokemons.remove(deadPokemon);
	}
	public void addItems(Items i){
		items.add(i);
	}
	
	public Items getItems(int i){
		return items.get(i);
	}
	
	public void addPokemons(Pokemon p){
		alivePokemons.add(p);
	}
	
	public Pokemon getPokemons(int i){
		return alivePokemons.get(i);
	}
	public Pokemon getPokemon(String s){
		for(int i  = 0; i< alivePokemons.size(); i++){
			if(alivePokemons.get(i).getName().equals(s)){
				return alivePokemons.get(i);
			}
		}
		return null;
	}
	public Pokemon getNextPokemon(){
		for(int i = 0; i< alivePokemons.size(); i++){
			if(alivePokemons.get(i).getHP() > 0){
				return alivePokemons.get(i);
			}
		}
		return null;
	}

	public int getMoney(){
		return Money;
	}
	
	public void addMoney(int m){
		Money+=m;
	}
	
	public void setMoney(int i) {
		// TODO Auto-generated method stub
		Money = i;
	}
	
	public int getPokNum(){
		return Pokemons.size();
	}
	
	public Pokeball getPokeball(){
		return balls;
	}


	
	
}

