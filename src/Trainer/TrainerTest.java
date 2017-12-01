package Trainer;
import PokemonInit.*;
import Items.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TrainerTest {
	
	@Test
	public void testBasics(){
		Pokemon newPoke = new Mew();
		Trainer newtrainer = new Trainer();
		newtrainer.addPokemons(newPoke);
		Pokemon poke = newtrainer.getPokemons(0);
		Pokemon p = new Mew();
		int hp = poke.getMaxHP();
		assertEquals(200,hp);
	}
	
	@Test
	public void testGetPokemon(){
		Pokemon mew = new Mew();
		Pokemon bulb = new Bulbasaur();
		Trainer trainer = new Trainer();
		trainer.addPokemons(mew);
		trainer.addPokemons(bulb);
		assertEquals(200,trainer.getPokemons(0).getHP());
		assertEquals(45,trainer.getPokemons(1).getHP());
		assertEquals(2,trainer.getPokNum());
	}
	
	@Test
	public void testMoney(){
		Trainer trainer = new Trainer();
		trainer.addMoney(200);
		assertEquals(200,trainer.getMoney());
		trainer.setMoney(100);
		assertEquals(100,trainer.getMoney());
		trainer.addMoney(300);
		assertEquals(400,trainer.getMoney());
	}
	
	@Test
	public void testBalls(){
		Trainer trainer = new Trainer();
		assertEquals(10,trainer.getPokeball().getNum());
	}
	
	@Test
	public void testItesms(){
		Trainer trainer = new Trainer();
		Items item = new Recover(10);
		trainer.addItems(item);
		assertEquals(item,trainer.getItems(3));
		assertEquals(10,trainer.getItems(3).getNums());
	}
	
	@Test public void testSpecials(){
		Trainer trainer = new Trainer();
		assertEquals(0,trainer.getAliveNum());
		Pokemon mew = new Mew();
		Pokemon ch  = new Charizard();
		trainer.addPokemons(mew);
		trainer.addPokemons(ch);
		assertEquals(2,trainer.getAliveNum());
		trainer.removeAlivePokemon(ch);
		mew.changeHP(200);
		trainer.addDeadPokemon(mew);
		assertEquals(1,trainer.getAliveNum());
		assertEquals(0,trainer.deadPokemons.get(0).getHP());
		assertEquals(mew,trainer.alivePokemons.get(0));
		assertEquals(null,trainer.getNextPokemon());
		trainer.alivePokemons.add(ch);
		assertEquals(ch,trainer.getNextPokemon());
		assertEquals(mew,trainer.getPokemon(mew.getName()));
		assertEquals(null,trainer.getPokemon("Ninetales"));
	}

}
