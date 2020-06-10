package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.character.Pickles;
import main.data.Item;

public class PicklesTest {
	
	double delta = 5e-10;
	
	Pickles pickles;
	String name;
	int level;

	@Before
	public void setUp() throws Exception {
		name = "Pickles Bot";
		level = 25;
		pickles = new Pickles(name, level);
	}

	@Test
	public void test_Pickles() {
		
		// attributes
		assertEquals(pickles.getName(), name);
		assertEquals(pickles.getLevel(), level);
		assertEquals(pickles.getAttack(), level * 100);
		assertEquals(pickles.getDefense(), level * 70);
		assertEquals(pickles.getHp().getMax(), level * 200);
		assertEquals(pickles.getHp().getActual_value(), level * 200);
		assertEquals(pickles.getMana().getMax(), level * 200);
		assertEquals(pickles.getMana().getActual_value(), level * 200);
		assertEquals(pickles.getXp().getMax(), level * 100);
		assertEquals(pickles.getXp().getActual_value(), 0);
		assertEquals(pickles.getStamina(), level * 60, delta);
		
		// random number item value
		int rand_item = pickles.getRandomNumItems();
		assertTrue(rand_item >= 1 && rand_item <= 10);
		
		// item list
		assertEquals(pickles.getItems().size(), 2);
		if (rand_item % 2 == 0) {
			assertEquals(pickles.getItems().get(0), Item.HP_POTION);
			assertEquals(pickles.getItems().get(1), Item.ARMOR);
		} else {
			assertEquals(pickles.getItems().get(0), Item.MANA_POTION);
			assertEquals(pickles.getItems().get(1), Item.ARMOR);
		}
	}

}