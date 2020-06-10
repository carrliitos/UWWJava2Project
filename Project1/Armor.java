import java.util.Random;

public class Armor{
	private String name;
	private int defense;
	Random rnd = new Random();

	public Armor(){
		this.name = "Armor " + rnd.nextInt(10);
		this.defense = rnd.nextInt(100);
	}

	public Armor(String name, int defense){
		this.name = name;
		this.defense = defense;
	}

	public String getName(){ return name; }
	public int getDefense() { return defense; }
	public void setDefense(int power) { this.defense = defense; }
}
