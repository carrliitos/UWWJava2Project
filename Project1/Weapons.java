import java.util.Random;

public class Weapons{
	private String name;
	private int power;
	Random rnd = new Random();

	public Weapons(){
		this.name = "Weapon " + rnd.nextInt(10);
		this.power = rnd.nextInt(100);
	}

	public Weapons(String name, int power){
		this.name = name;
		this.power = power;
	}

	public String getName(){ return name; }
	public int getPower() { return power; }
	public void setPower(int power) { this.power = power; }
}