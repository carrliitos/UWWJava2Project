public class Skills {
	private Character caster;
	private String name;
	private int level;
	private double value;
	private int manaRequirement;
	
	public Skills(Character caster, int level, double value, int manaRequirement) {
		this.caster = caster;
		this.level = level;
		this.value = value;
		this.manaRequirement = manaRequirement;
	}
	
	public Skills() { }
	
	public String getName() { return name; }
	public int getValue() { return (int)(caster.getActualPower() * value * level); }
	public int getLevel() { return level; }
	public int getManaRequirement() { return manaRequirement * level; }

}