package main.processing;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import main.character.Enemy;
import main.character.Hero;
import main.character.Pickles;
import main.character.Shrek;
import main.character.Hades;
import main.data.Item;


public class GameProgress {
	private Hero hero;
	private List<Enemy> enemies;
	private final int randomNumPickles;
	private final int randomNumShrek;
	private final int randomNumHades;
	
	public GameProgress(String playerName) throws Exception {
		
		hero = new Hero(playerName, 25, 20, 100, 75, 100, 5);
		enemies = new LinkedList<>();

		randomNumPickles = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		randomNumShrek = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		randomNumHades = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		
		for (int i = 0; i < randomNumPickles; i++) {
			enemies.add(new Pickles("Pickles Bot", 1));
		}
		for (int i = 0; i < randomNumShrek; i++) {
			enemies.add(new Shrek("Shrek Bot", 1));
		}
		for (int i = 0; i < randomNumHades; i++) {
			enemies.add(new Hades("Hades Bot", 1));
		}
		
	}
	
	public void gameLoop() throws Exception {
		
		while (hero.getLevel() <= 100) {
			fightEnemy();
			System.out.println(hero.statusToString());
			Thread.sleep(1500);
			System.out.print("\n");
		}
	}
	
	// fight, remove 1 enemy, add new 1
	private void fightEnemy() throws Exception {
		int enemyNum = ThreadLocalRandom.current().nextInt(0, enemies.size());
		Enemy toFight = enemies.get(enemyNum);
		Enemy newEnemy;
		
		hero.addXp(toFight.getXp().getMax());
		int level = hero.getLevel();

		if (toFight instanceof Pickles) {
			newEnemy = new Pickles("Pickles Bot", level);
			System.out.println("Killed Pickles LVL: " + toFight.getLevel());
		} else if (toFight instanceof Shrek) {
			newEnemy = new Shrek("Shrek Bot", level);
			System.out.println("Killed Shrek LVL: " + toFight.getLevel());
		} else {
			newEnemy = new Hades("Hades Bot", level);
			System.out.println("Killed Hades LVL: " + toFight.getLevel());
		}
		
		// get all items from the enemy bot
		System.out.print("Picked up: ");
		for (int i = 0; i < toFight.getItems().size(); i++) {
			Item item = toFight.getItems().get(i);
			hero.addItem(item);
			System.out.print(item.name() + " ");	
		}
		System.out.print("\n");
		
		enemies.remove(enemyNum);
		enemies.add(newEnemy);
	}
	
	public boolean isEnemyExisting(Enemy enemy) {
		return enemies.contains(enemy);
	}
}
