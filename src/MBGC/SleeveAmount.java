package MBGC;

/**
 * Keeps track of which game has how many of which sleeves
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class SleeveAmount {

	private int gameId = -1;
	private int sleeveId = 0;
	private int amount = 0;

	/**
	 * Empty constructor for sleeveAmount
	 * @example
	 * <pre name="test">
     * SleeveAmount sleeveAmount = new SleeveAmount("0|0|50");
     * sleeveAmount.toString() === "0|0|50";
     * SleeveAmount sleeveAmount1 = new SleeveAmount("|0|50");
     * sleeveAmount1.toString() === "-1|0|0";
     * SleeveAmount sleeveAmount2 = new SleeveAmount("|");
     * sleeveAmount2.toString() === "-1|0|0";
     * SleeveAmount sleeveAmount3 = new SleeveAmount("");
     * sleeveAmount3.toString() === "-1|0|0";
     * </pre>
	 */
	public SleeveAmount(){
		this.gameId = -1;
		this.sleeveId = -1;
		this.amount = -1;
	}
	
	/**
	 * Constructor
	 * @param gameId id of the game
	 * @param sleeveId id of sleeves
	 * @param amount amount of sleeves
	 */
	public SleeveAmount(
			int gameId,
			int sleeveId,
			int amount){
		this.gameId = gameId;
		this.sleeveId = sleeveId;
		this.amount = amount;
	}
	
	/**
	 * Constructor from string
	 * @param sleeveAmount string that has sleeveAmount data
	 */
	public SleeveAmount(String sleeveAmount){
		
		try{
			String[] sleeveAmountData = sleeveAmount.split("\\|");
			
			this.gameId = Integer.parseInt(sleeveAmountData[0]);
			this.sleeveId = Integer.parseInt(sleeveAmountData[1]);
			this.amount = Integer.parseInt(sleeveAmountData[2]);
		}
		catch(Exception e){
			/**/
		}
	}
	
	/**
	 * Returns gameId
	 * @return gameId
	 */
	public int getGameId(){
		
		return this.gameId;
	}
	
	/**
	 * Return sleeveId
	 * @return sleeveId
	 */
	public int getSleeveId(){
		
		return this.sleeveId;
	}
	
	/**
	 * Returns amount
	 * @return amount
	 */
	public int getAmount(){
		
		return this.amount;
	}
	
	/**
	 * Returns sleeveAmount's data as string
	 */
	@Override
	public String toString(){
		StringBuffer returnStringBuffer = new StringBuffer();
		
		returnStringBuffer.append(
				this.gameId + "|" +
				this.sleeveId + "|" +
				this.amount);
		
		return returnStringBuffer.toString();
	}
	
	/**
	 * Tests the working of the class
	 * @param args not in use
	 */
    public static void main(String args[]) {
    	
    	SleeveAmount muoviMaara = new SleeveAmount("0|0|152");
    	System.out.println(muoviMaara.getGameId() + " " + muoviMaara.getSleeveId() + " " + muoviMaara.getAmount());
    }
}
