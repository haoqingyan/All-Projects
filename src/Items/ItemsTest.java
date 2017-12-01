package Items;
import PokemonInit.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ItemsTest {

	@Test
	public void testRecover() {
		
		Pokemon mew = new Mew();
		mew.changeHP(50);
		assertEquals(150,mew.getHP());
		Items item = new Recover(10);
		item.effect(mew);
		assertEquals(180,mew.getHP());
		assertEquals("Recover",item.getName());
		assertEquals(9,item.getNums());
		item.addNums(5);
		assertEquals(14,item.getNums());
		item.effect(mew);
		assertEquals(200,mew.getHP());
		
	}
	
	
	@Test
	public void testMPrecover(){
		Pokemon mew = new Mew();
		mew.changeMP(50);
		assertEquals(150,mew.getMP());
		Items item = new MPrecover(10);
		item.effect(mew);
		assertEquals(170,mew.getMP());
		assertEquals("MPrecover",item.getName());
		assertEquals(9,item.getNums());
		item.addNums(5);
		assertEquals(14,item.getNums());
		item.effect(mew);
		assertEquals(190,mew.getMP());
		item.effect(mew);
		assertEquals(200,mew.getMP());
	}
	
	@Test
	public void testPokeball(){
		Pokemon mew = new Mew();
		Pokemon bul = new Bulbasaur();
		Pokeball balls = new Pokeball(10);
		assertEquals(10,balls.getNums());
		assertEquals(10,balls.getNums());
		assertEquals(200,balls.Price());
		assertEquals(200,balls.Price());
		assertEquals("Pokeball",balls.getName());
		balls.addNum(1);
		
		assertEquals(11,balls.getNums());
		balls.addNums(1);
		assertEquals(12,balls.getNum());
//		asserE
//		
	}

}
