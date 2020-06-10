package main.character;

import main.data.Amount;
import main.data.Item;

public class Pickles extends Enemy {
	public Pickles(String name, int level) throws Exception {
		
		super(name, level);
		setAttack(level * 100);
		setDefense(level * 70);
		setHp(level * 200);
		setMana(level * 200);
		setXp(level * 100);
		setStamina(level * 60);
		
		if (randomNumItems % 2 == 0) {
			addItem(Item.HP_POTION);
			addItem(Item.ARMOR);
		} else {
			addItem(Item.MANA_POTION);
			addItem(Item.ARMOR);
		}
	}
}