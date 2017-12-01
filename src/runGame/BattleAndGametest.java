package runGame;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import PokemonInit.*;
import Trainer.Trainer;

public class BattleAndGametest {

	@Test
	public void testBattle1() {
		Trainer t = new Trainer();
		Pokemon mew = new Mew();
		t.addPokemons(mew);
		Pokemon bulb = new Bulbasaur();
		Battle b = new Battle(t,bulb);
		assertEquals(mew,b.currentPokemon());
		b.command(1);
		assertEquals(1,b.command);	
	}
	
	@Test
	public void testBattle2() {
		Trainer t = new Trainer();
//		Pokemon mew = new Mew();
//		t.addPokemons(mew);
		Pokemon bulb = new Bulbasaur();
		Battle b = new Battle(t,bulb);
		assertEquals(null,b.playersPokemon);
		b.setEnd();
		assertEquals(true,b.end);	
		
	}
	@Test
	public void testBattle3() {
		Trainer t = new Trainer();
		Pokemon mew = new Mew();
		t.addPokemons(mew);
		Pokemon bulb = new Bulbasaur();
		Battle b = new Battle(t,bulb);
		b.currentPokemon().changeHP(300);
		b.setEnd();
		assertEquals(true,b.end);
		
	}
	@Test
	public void testBattle4() {
		Trainer t = new Trainer();
		Pokemon mew = new Mew();
		t.addPokemons(mew);
		Pokemon bulb = new Bulbasaur();
		Battle b = new Battle(t,bulb);
		b.catchindicate = true;
		b.setEnd();
		assertEquals(true,b.end);
		
	}
	
	@Test
	public void testBattle5() {
		Trainer t = new Trainer();
		Pokemon mew = new Mew();
		t.addPokemons(mew);
		Pokemon bulb = new Bulbasaur();
		Battle b = new Battle(t,bulb);
		b.enemyPokemon.changeHP(200);
		b.setEnd();
		assertEquals(true,b.end);
	}
	
	@Test
	public void testGame1() {
		Game newgame = new Game(4,4);
		int ir = newgame.currentRow;
		int ic = newgame.currentCol;
		Direction up = Direction.NORTH;
		Direction left = Direction.WEST;
		Direction right = Direction.EAST;
		Direction down = Direction.SOUTH;
		newgame.movePlayer(down);
		assertEquals(ir+1,newgame.currentRow);
		newgame.movePlayer(up);
		assertEquals(ir,newgame.currentRow);
		newgame.movePlayer(left);
		assertEquals(ir-1,newgame.currentCol);
		newgame.movePlayer(right);
		assertEquals(ir,newgame.currentCol);
	}
	
	@Test
	public void testGame2() {
		Trainer t =new Trainer();
		Game newgame = new Game(4,4,t);
		assertEquals(4,newgame.getCurrentRow());
		assertEquals(4,newgame.getCurrentColumn());
		assertEquals(new Point(4,4),newgame.getOldPoint());
		assertEquals(new Point(4,4),newgame.getPoint());
	}

}
