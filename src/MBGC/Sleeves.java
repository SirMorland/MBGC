package MBGC;

/**
 * Keeps track of sleeves in collection
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class Sleeves {

	private Sleeve[] sleeves = new Sleeve[0];
	
	/**
	 * Constructor
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
     * 
     * Sleeves sleeves = new Sleeves();
     * sleeves.addSleeve(sleeve);
     * sleeves.addSleeve(sleeve1);
     * sleeves.toString() === "0|Ultra Pro|50|3.5\n-1||0|0.0\n";
     * sleeves.removeSleeve(sleeve);
     * sleeves.toString() === "-1||0|0.0\n";
     * </pre>
	 */
	public Sleeves(){
		
	}
	
	/**
	 * Add sleeve to sleeves
	 * @param sleeve sleeve to be added
	 */
	public void addSleeve(Sleeve sleeve){
		
		for(int i = 0; i < this.sleeves.length; i++){
			if(this.sleeves[i].equals(sleeve)) return;
		}
		
		Sleeve[] newSleeves = new Sleeve[this.sleeves.length + 1];
		for(int i = 0; i < this.sleeves.length; i++){
			newSleeves[i] = this.sleeves[i].getCopy();
		}
		newSleeves[newSleeves.length - 1] = sleeve;
		this.sleeves = newSleeves;
	}
	
	/**
	 * Remove sleeve from sleeves
	 * @param sleeve sleeve to be removed
	 */
	public void removeSleeve(Sleeve sleeve){
		Sleeve[] newSleeves = new Sleeve[this.sleeves.length - 1];
		int j = 0;
		for(int i = 0; i < this.sleeves.length; i++){
			if(!this.sleeves[i].equals(sleeve)){
				newSleeves[j] = this.sleeves[i].getCopy();
				j++;
			}
		}
		sleeves = newSleeves;
	}
	
	/**
	 * Returns how many sleeves in sleeves
	 * @return how many sleeves in sleeves
	 */
	public int getCount(){
		
		return this.sleeves.length;
	}
	
	/**
	 * Returns sleeve i in sleeves
	 * @param i i
	 * @return sleeve
	 */
	public Sleeve getSleeve(int i){
		
		return this.sleeves[i];
	}
	
	/**
	 * Returns the data of all sleeves as string
	 */
	@Override
	public String toString(){
		StringBuffer retrurnStringBuffer = new StringBuffer();
		
		for(int i = 0; i < sleeves.length; i++){
			retrurnStringBuffer.append(sleeves[i].toString() + "\n");
		}
		
		return retrurnStringBuffer.toString();
	}
	
	/**
	 * Tests the working of the class
	 * @param args not in use
	 */
    public static void main(String args[]) {
    	
    	Sleeve muovi1 = new Sleeve("0|FFG Yellow|50|3.50");
    	Sleeve muovi2 = new Sleeve("1|FFG Green|50|3.50");
    	Sleeves muovit = new Sleeves();
    	muovit.addSleeve(muovi1);
    	muovit.addSleeve(muovi2);
    	System.out.println(muovit);
    	muovit.removeSleeve(muovi2);
    	System.out.println(muovit);
    	muovit.addSleeve(muovi2);
    	muovit.addSleeve(muovi2);
    	System.out.println(muovit);
    	muovit.removeSleeve(muovi1);
    	System.out.println(muovit);
    }
}
