package runGame;
import PokemonInit.*;

import java.util.Observable;
import Items.*;
import Trainer.*;
public class Battle extends Observable {
	Pokemon playersPokemon;
	Pokemon enemyPokemon;
	Trainer player;
	Trainer NPC;
	boolean end = false;
	boolean catchindicate = false;
	int command = 0;
	
	public Battle (Trainer player,Pokemon wildPokemon){
		this.player = player;
		playersPokemon = player.getNextPokemon();
		enemyPokemon = wildPokemon;
	}
//	public Battle (Trainer player, Trainer NPC){
//		this.player = player;
//		this.NPC = NPC;
//		playersPokemon = player.getPokemons(0);
//		enemyPokemon = NPC.getPokemons(0);
//		System.out.println(playersPokemon.getName());
//	}
	public Pokemon currentPokemon(){
		
		return playersPokemon;
	}
	public void command(int command){
		this.command = command;
//		if(command == 1 ){
//			enemyPokemon.changeHP( playersPokemon.trueAttack(enemyPokemon.getProperty()));
//			playersPokemon.changeHP( enemyPokemon.trueAttack(playersPokemon.getProperty()));
//			enemyPokemon.changeMP(10);
//			playersPokemon.changeMP(10);
//		}
		
	    setChanged();
	    notifyObservers(command);
	}
	public void setEnd(){
		if(playersPokemon == null){
			end = true;
		}
		else if (playersPokemon.getHP()<=0){
			end = true;
		}
		else if (enemyPokemon.getHP()<=0){
			end = true;
		}
		else if (catchindicate == true){
			end = true;
		}
		setChanged();
	    notifyObservers(end);
	}
	
	

}
