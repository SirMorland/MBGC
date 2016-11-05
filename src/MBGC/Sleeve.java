package MBGC;

/**
 * Sleeve
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class Sleeve {

	private int id = -1;
	private String name = "";
	private int pcPerSet = 0;
	private double ePerSet = 0.0;

	/**
	 * Empty constructor for sleeve
	 * @example
	 * <pre name="test">
     * Sleeve sleeve = new Sleeve("0|Ultra Pro|50|3.50");
     * sleeve.toString() === "0|Ultra Pro|50|3.5"
     * Sleeve sleeve1 = new Sleeve("|Ultra Pro|50|3.50");
     * sleeve1.toString() === "-1||0|0.0";
     * Sleeve sleeve2 = new Sleeve("1||50|3.50");
     * sleeve2.toString() === "1||50|3.5";
     * Sleeve sleeve3 = new Sleeve("|");
     * sleeve3.toString() === "-1||0|0.0";
     * Sleeve sleeve4 = new Sleeve("");
     * sleeve4.toString() === "-1||0|0.0";
     * </pre>
	 */
	public Sleeve(){
		this.id = -1;
		this.name = "";
		this.pcPerSet = -1;
		this.ePerSet = -1;
	}
	
	/**
	 * Constructor
	 * @param id id
	 * @param name name
	 * @param pcPerSet piece per set
	 * @param ePerSet euro per set
	 */
	public Sleeve(
			int id,
			String name,
			int pcPerSet,
			double ePerSet){
		this.id = id;
		this.name = name;
		this.pcPerSet = pcPerSet;
		this.ePerSet = ePerSet;
	}

	/**
	 * Constructor from string
	 * @param sleeve string that has sleeve data
	 */
	public Sleeve(String sleeve){
		
		try{
			String[] sleeveData = sleeve.split("\\|");
			
			this.id = Integer.parseInt(sleeveData[0]);
			this.name = sleeveData[1];
			this.pcPerSet = Integer.parseInt(sleeveData[2]);
			this.ePerSet = Double.parseDouble(sleeveData[3]);
		}
		catch(Exception e){
			this.id = -1;
		}
	}
	
	/**
	 * @return id
	 */
	public int getId(){
		
		return this.id;
	}
	
	/**
	 * @return name
	 */
	public String getName(){
		
		return this.name;
	}
	
	/**
	 * @param name name
	 */
	public void setName(String name){
		
		this.name = name;
	}
	
	/**
	 * @return pcPerSet
	 */
	public int getPcPerSet(){
		
		return this.pcPerSet;
	}
	
	/**
	 * @param pcPerSet pcPerSet
	 */
	public void setPcPerSet(int pcPerSet){
		
		this.pcPerSet = pcPerSet;
	}
	
	/**
	 * @return ePerSet
	 */
	public double getEPerSet(){
		
		return this.ePerSet;
	}
	
	/**
	 * @param ePerSet ePerSet
	 */
	public void setEPerSet(double ePerSet){
		
		this.ePerSet = ePerSet;
	}
	
	/**
	 * Returns copy of this sleeve
	 * @return copy of this sleeve
	 */
	public Sleeve getCopy(){
		Sleeve newSleeve = new Sleeve();
		newSleeve.id = this.id;
		newSleeve.name = this.name;
		newSleeve.pcPerSet = this.pcPerSet;
		newSleeve.ePerSet = this.ePerSet;
		return newSleeve;
	}

	/**
	 * Checks if this sleeve is equal to other sleeve
	 * @param sleeve the other sleeve
	 * @return true if sleeves are the same, otherwise false
	 */
	public boolean equals(Sleeve sleeve){
		
		if(this.id == sleeve.id &&
			this.name == sleeve.name &&
			this.pcPerSet == sleeve.pcPerSet &&
			this.ePerSet == sleeve.ePerSet) return true;
		return false;
	}
	
	/**
	 * Returns data of the sleeve as string
	 */
	@Override
	public String toString(){
		StringBuffer returnStringBuffer = new StringBuffer();
		
		returnStringBuffer.append(
				this.id + "|" +
				this.name + "|" +
				this.pcPerSet + "|" +
				this.ePerSet);
		
		return returnStringBuffer.toString();
	}
}
