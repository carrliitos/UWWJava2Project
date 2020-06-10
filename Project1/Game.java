import java.util.Scanner;

public class Game {
	private static String server_name = "CS220";
	private Character player;
	private Character enemy;
	private int score;
	static Scanner scanner;
	

	public static void setServer_Name(String server_name)
	{ Game.server_name = server_name; }

	public static String getServer_name() { return server_name; }
	
	public Game(Character player, Character enemy) {
		this.player = player;
		this.enemy = enemy;
	}
	
	public boolean fight() {
		Scanner scanner = new Scanner(System.in);
		
		Utility.println("Your current server is: " + Game.getServer_name(), 1000);
		System.out.println("Would you like to change your current server? (y/n)");
		String serverAnswer = scanner.nextLine();
		switch(serverAnswer) {
			case "y":
				System.out.println("What is your new server?");
				String newServer = scanner.nextLine();
				Game.setServer_Name(newServer);
				Utility.println("Your new current server is: " + Game.getServer_name(), 1000);
				System.out.println("");
			case "n":
				break;
		}
		
		Utility.println(player.getName() + " is fighting against " + enemy.getName(), 1000);
		int rounds = 1;
		
		while(player.getActualHP() > 0 && enemy.getActualHP() > 0) {
			System.out.println("");
			System.out.println("Do you wanna see what is in your bag? (y/n): ");
			String answer = scanner.nextLine();
			switch(answer) {
				case "y":
					show_bag(player);
					break;
				case "n":
					break;
			}
			
			System.out.println("Show current status? (y/n)");
				String statusAnswer = scanner.nextLine();
				switch(statusAnswer) {
				case "y":
					show_PlayerStatus(player);
					break;
				case "n":
					break;
			}
			
			System.out.println("Show enemy's status? (y/n)");
			String enemyStatusAnswer = scanner.nextLine();
			switch(enemyStatusAnswer) {
				case "y":
					show_EnemyStatus(enemy);
					break;
				case "n":
					break;
			}
			
			Utility.println(" --- Round " + rounds + " --- \n", 1000);
			Utility.println(" --- FIGHT! ---", 1000);
			Utility.println(player.getName() + " Stats", 0);
			Utility.println("Life: " + Utility.displayGraphical(player.getActualHP(), player.getMaxHP(), "#"), 0);
			Utility.println("Mana: " + Utility.displayGraphical(player.getActualMP(), player.getMaxMP(), "@") + "\n", 1000);
			System.out.println(" ");
			Utility.println(enemy.getName() + " Stats", 0);
			Utility.println("Life: " + Utility.displayGraphical(enemy.getActualHP(), enemy.getMaxHP(), "#"), 0);
			Utility.println("Mana: " + Utility.displayGraphical(enemy.getActualMP(), enemy.getMaxMP(), "@") + "\n", 1000);
			
			if(player.getActualHP() <= 0) break;
			player.playerChooseSkill(enemy);
			
			if(enemy.getActualHP() <= 0 || player.getActualHP() <= 0) break;
			if(enemy.getActualHP() <= 0) break;
			enemy.enemyChooseSkill(player);
			rounds++;
		}
		
		scanner.close();

		if(player.getActualHP() > 0 && enemy.getActualHP() <= 0) {
			Utility.println("You have won! Congratulations! Level up!", 2000);
			Utility.println("PREPARE FOR THE NEXT ROUND!", 2000);
			score++;
			enemy.enemyLevelUp(1.2);
			player.playerLevelUp();
			return true;
		}else {
			Utility.println("Game Over!", 0);
			Utility.println("You've reached " + score + " Points. Congratulations!", 2000);
			return false;
		}
	}
	
	public void mainLoop() {
		while(fight());
	}
	
	public static void main(String[] args) throws NullPointerException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Input the main character's desired Name: ");
		String playerName = scanner.nextLine();
		System.out.println("Input the main character's desired HP: ");
		int playerMaxHP = scanner.nextInt();
		System.out.println("Input the main character's desired MP: ");
		int playerMaxMP = scanner.nextInt();
		System.out.println("Input the main character's desired Power: ");
		int playerMaxPower = scanner.nextInt();
		
		Character enemy = new Character("Enemy");
		Character player = new Character(playerName, playerMaxHP, playerMaxMP, playerMaxPower);
		
		Game game = new Game(player, enemy);
		game.mainLoop();

		scanner.close();
	}

	public static void show_bag(Character player) {
		System.out.println("");
		Utility.println("==========Player Bag:", 1000);
		Utility.println("Money: " + player.bag().getMoney(), 1000);
		for(int i = 0; i < 4; i++) {
		Utility.println("Weapon Name: " + player.bag().getWeaponArray()[i].getName(), 1000);
		Utility.println("Weapon Power: " + player.bag().getWeaponArray()[i].getPower(), 1000);
		Utility.println("Armor Name: " + player.bag().getArmorArray()[i].getName(), 1000);
		Utility.println("Armor Defense: " + player.bag().getArmorArray()[i].getDefense(), 1000);
		System.out.println("");
		}
	}
	
	public static void show_PlayerStatus(Character player) {
		System.out.println("");
		Utility.println("==========Player Status: ", 1000);
		Utility.println("==========Player Name: " + player.getName(), 1000);
		Utility.println("==========Player HP: " + player.getActualHP(), 1000);
		Utility.println("==========Player MP: " + player.getActualMP(), 1000);
		Utility.println("==========Player Power: " + player.getActualPower(), 1000);
		Utility.println("==========Player Defense: " + player.getActualDefense(), 1000);
		System.out.println("");
	}
	
	public static void show_EnemyStatus(Character enemy) {
		System.out.println("");
		Utility.println("==========Enemy Status: ", 1000);
		Utility.println("==========Enemy Name: " + enemy.getName(), 1000);
		Utility.println("==========Enemy HP: " + enemy.getActualHP(), 1000);
		Utility.println("==========Enemy MP: " + enemy.getActualMP(), 1000);
		Utility.println("==========Enemy Power: " + enemy.getActualPower(), 1000);
		Utility.println("==========Enemy Defense: " + enemy.getActualDefense(), 1000);
		System.out.println("");
	}
}