package main;

import main.character.Enemy;
import main.processing.GameProgress;
import test.HeroTest;

public class Main {
	public static void main(String[] args) {
		try {
			GameProgress gameProgress = new GameProgress("UWW-CS220");
			gameProgress.gameLoop();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}