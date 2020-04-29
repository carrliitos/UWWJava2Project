import java.util.Scanner;
import java.util.Random;

public class Character{
	private static Scanner input = new Scanner(System.in);
	private Armor armor = new Armor();
	private Weapons weapon = new Weapons();
	private Bag bag = new Bag();
	private String name;
	private int maxHP;
	private int maxMP;
	private int maxPower;
	private int maxDefense;
	private int actualHP;
	private int actualMP;
	private int actualPower;
	private int actualDefense;
	private Skills fireball;
	private Skills knockout;
	private Skills healing;
	private Skills sword_attack;
	private boolean alive;
	private boolean isKnockedOut;
	Random rnd = new Random();

	public Character(String name){
		this.name = name;
		this.maxHP = rnd.nextInt(1000);
		this.maxMP = rnd.nextInt(1000);
		this.maxPower = rnd.nextInt(1000);
		this.maxDefense = rnd.nextInt(1000);
		this.name = name;
		this.actualHP = maxHP;
		this.actualMP = maxMP;
		this.actualPower = maxPower;
		this.actualDefense = maxDefense;
		
		fireball = new Skills(this, 1, 1.0, 50);
		knockout = new Skills(this, 1, 0.5, 100);
		healing = new Skills(this, 1, 2.0, 150);
		sword_attack = new Skills(this, 1, 2.0, 0);

		alive = true;
		isKnockedOut = false;
	}

	public Character(String name, int maxHP, int maxMP, int maxPower){
		this.name = name;
		this.maxHP = maxHP;
		this.maxMP = maxMP;
		this.maxPower = maxPower;
		this.maxDefense = rnd.nextInt(1000);
		this.name = name;
		this.actualHP = maxHP;
		this.actualMP = maxMP;
		this.actualPower = maxPower;
		this.actualDefense = maxDefense;
		
		fireball = new Skills(this, 1, 1.0, 50);
		knockout = new Skills(this, 1, 0.5, 100);
		healing = new Skills(this, 1, 2.0, 150);
		sword_attack = new Skills(this, 1, 2.0, 0);
		
		alive = true;
		isKnockedOut = false;
	}

	public String toString() {
		String text =" ";
		text += name + "\n";
		text += "Life: " + actualHP + " / " + maxHP + "\n";
		text += "Mana: " + actualMP + " / " + maxMP + "\n";
		text += "Power: " + actualPower + " / " + maxPower + "\n";
		return text;
	}

	public void setName(String name)
	{ this.name = name; }
	public void setMaxHP(int maxHP)
	{ this.maxHP = maxHP; }
	public void setMaxMP(int maxMP)
	{ this.maxMP = maxMP; }
	public void setMaxPower(int maxPower)
	{ this.maxPower = maxPower; }
	public void setMaxDefense(int maxDefense)
	{ this.maxDefense = maxDefense; }
	public void setActualHP(int actualHP)
	{ this.actualHP = actualHP; }
	public void setActualMP(int actualMP)
	{ this.actualMP = actualMP; }
	public void setActualPower(int actualPower)
	{ this.actualPower = actualPower; }
	public void setActualDefense(int actualDefense)
	{ this.actualDefense = actualDefense; }
	public void setAlive(boolean alive)
	{ this.alive = alive; }
	public void setIsKnockedOut(boolean isKnockedOut)
	{ this.isKnockedOut = isKnockedOut; }
	public void setKnockout(Skills knockout)
	{ this.knockout = knockout; }
	public void setFireball(Skills fireball)
	{ this.fireball = fireball; }
	public void setSwordAttack(Skills sword_attack)
	{ this.sword_attack = sword_attack; }
	public void setHealing(Skills healing)
	{ this.healing = healing; }
	public void setArmor(Armor armor)
	{ this.armor = armor; }
	public void setWeapon(Weapons weapon)
	{ this.weapon = weapon; }
	public void setBag(Bag bag)
	{ this.bag = bag; }

	public String getName() { return name; }
	public int getMaxHP() { return maxHP; }
	public int getMaxMP() { return maxMP; }
	public int getMaxPower() { return maxPower; }
	public int getMaxDefense() { return maxDefense; }
	public int getActualHP() { return actualHP; }
	public int getActualMP() { return actualMP; }
	public int getActualPower() { return actualPower; }
	public int getActualDefense() { return actualDefense; }
	public boolean getAlive() { return alive; }
	public boolean getIsKnockedOut() { return isKnockedOut; }
	public Skills knockout() { return knockout; }
	public Skills fireball() { return fireball; }
	public Skills healing() { return healing; }
	public Skills sword_attack() { return sword_attack; }
	public Armor armor() { return armor; }
	public Weapons weapon() { return weapon; }
	public Bag bag() { return bag; }

	public Character(){}

	public boolean isKnockedOut() {
		if(isKnockedOut) {
			Utility.println(name + " is knocked out and can't cast skills this round\n", 1000);
			isKnockedOut = false;
			return true;
		} else {
		return false;
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void sustainDamage(int value) {
		if(value > 0) {
			actualHP -= value;
			Utility.println(name + " has gotten " + value + " damage\n", 1000);
		}
		if (actualHP <= 0) {
			alive = false;
		}
	}
	
	public boolean useMana(int value) {
		if(value <= actualMP) {
			actualMP -= value;
			Utility.println(name + " has used " + value + " mana.", 1000);
			return true;
		}else {
			Utility.println(name + " does not have enough mana.", 1000);
			return false;
		}
	}
	
	public void swordAttack(Character enemy) {
		Utility.println(name + " has attacked with a sword on " + enemy.getName(), 1000);
		
		if(useMana(0)) {
			enemy.sustainDamage(sword_attack.getValue());
		}
	}
	
	public void cureLife(int value) {
		int difference = maxHP - actualHP;
		
		if(value >= difference) {
			actualHP = maxHP;
			Utility.println(name + " is fully healed.", 1000);
		}else {
			actualHP += value;
			Utility.println(name + " has healed " + value + " life points.", 1000);
		}
	}
	
	public void castFireball(Character enemy) {
		System.out.println(name + " casts a Fireball on " + enemy.getName() + ".");
		
		if(useMana(50)) {
			enemy.sustainDamage(fireball.getValue());
		}
	}
	
	public void castHealing() {
		System.out.println(name + " casts a Heal spell.");
		
		if(useMana(150)) {
			cureLife(healing.getValue());
		}
	}
	
	public void castKnockOut(Character enemy) {
		System.out.println(name + " used Knock Out on " + enemy.getName() + ".");
		
		if(useMana(100)) {
			enemy.sustainDamage(knockout.getValue());
			enemy.isKnockedOut = true;
			Utility.println(enemy.getName() + " is knocked out.", 1000);
		}
	}
	
	public void playerChooseSkill(Character enemy) {
		if(!isKnockedOut()) {
			System.out.println("[1] Fireball\n"
							+ "[2] Heal\n"
							+ "[3] Knock Out\n"
							+ "[4] Sword Attack\n"
							+ "[5] Give up\n"
							+ "[6] Do nothing.\n"
							+ "Your choice: "
			);
			
			if(input.hasNext()) {
				String choice = input.next();
				
				if(choice.equals("1")) {
					castFireball(enemy);
				}else if(choice.contentEquals("2")) {
					castHealing();
				}else if(choice.equals("3")) {
					castKnockOut(enemy);
				}else if(choice.equals("4")) {
					swordAttack(enemy);
				}else if(choice.equals("5")) {
					setActualHP(0);
				}
			}
		}
	}
	
	public void playerLevelUp() {
		setMaxHP(getMaxHP() + 5);
		setMaxMP(getMaxMP() + 2);
		setMaxPower(getMaxPower() + 1);
		setActualHP(getMaxHP());
		setActualMP(getMaxMP());
		setActualPower(getMaxPower());
		setIsKnockedOut(false);
	}

	public void enemyChooseSkill(Character enemy) {
		if(isKnockedOut()) { return; }
		if(getActualMP() == 0) {
			setActualHP(0);
			Utility.println(getName() + " gives up because he has no mana.", 1000);
			return;
		}
		int skillDecision;
		while(true) {
			skillDecision = rnd.nextInt(4);
			
			if((skillDecision == 1 && enemy.healing().getManaRequirement() > enemy.getActualMP())
				|| (skillDecision == 1 && enemy.getMaxHP() == getActualHP())
				|| (skillDecision == 2 && enemy.knockout().getManaRequirement() > enemy.getActualMP())
				|| (skillDecision == 3))
			{ continue; }
			else { break; }
		}
		
		if(skillDecision == 0) {
			castFireball(enemy);
		}else if(skillDecision == 1) {
			castHealing();
		}else if(skillDecision == 2) {
			castKnockOut(enemy);
		}else if(skillDecision == 3) {
			swordAttack(enemy);
		}
	}

	public void enemyLevelUp(double factor) {
		setMaxHP((int) (getMaxHP() * factor));
		setMaxMP((int) (getMaxMP() * factor));
		setMaxPower((int) (getMaxPower() * factor));
		setActualHP(getMaxHP());
		setActualMP(getMaxMP());
		setActualPower(getMaxPower());
		setIsKnockedOut(false);
	}

	public class Bag {
		Weapons weaponArray[] = new Weapons[4];
		Armor armorArray[] = new Armor[4];
		int money = 120;
		
		Bag() {
			for(int i = 0; i < 4; i++) {
				weaponArray[i] = new Weapons();
				armorArray[i] = new Armor();
			}
		}
		
		public void setWeaponArray(Weapons[] weaponArray)
		{ this.weaponArray = weaponArray; }
		public void setArmorArray(Armor[] armorArray)
		{ this.armorArray = armorArray; }
		public void setMoney(int money)
		{ this.money = money; }
		
		public Weapons[] getWeaponArray() { return weaponArray; }
		public Armor[] getArmorArray() { return armorArray; }
		public int getMoney() { return money; }
	}

}