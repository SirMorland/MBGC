package MBGC;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of sleeveAmounts in collection
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class SleeveAmounts {

	private List<SleeveAmount> sleeveAmounts = new ArrayList<SleeveAmount>();
	
	/**
	 * Empty constructor for sleeveAmounts
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
     * 
     * SleeveAmounts sleeveAmounts = new SleeveAmounts();
     * sleeveAmounts.addSleeveAmount(sleeveAmount);
     * sleeveAmounts.addSleeveAmount(sleeveAmount1);
     * sleeveAmounts.toString() === "0|0|50\n-1|0|0\n";
     * sleeveAmounts.removeSleeveAmount(sleeveAmount);
     * sleeveAmounts.toString() === "-1|0|0\n";
     * </pre>
	 */
	public SleeveAmounts(){
		
	}
	
	/**
	 * Adds sleeveAmount to sleeveAmounts
	 * @param sleeveAmount sleeveAmount to be added
	 */
	public void addSleeveAmount(SleeveAmount sleeveAmount){
		
		sleeveAmounts.add(sleeveAmount);
	}
	
	/**
	 * Removes sleeveAmount from sleeveAmounts
	 * @param sleeveAmount sleeveAmount to be removed
	 */
	public void removeSleeveAmount(SleeveAmount sleeveAmount){
		
		sleeveAmounts.remove(sleeveAmount);
	}
	
	/**
	 * Returns how many sleeveAmounts in sleeveAmounts
	 * @return how many sleeveAmounts in sleeveAmounts
	 */
	public int getCount(){
		
		return this.sleeveAmounts.size();
	}
	
	/**
	 * Returns sleeveAmount i in sleeveAmounts
	 * @param i i
	 * @return sleeveAmount
	 */
	public SleeveAmount getSleeveAmount(int i){
		
		return this.sleeveAmounts.get(i);
	}
	
	/**
	 * Returns the data of all sleeveAmounts as string
	 */
	@Override
	public String toString(){
		StringBuffer retrurnStringBuffer = new StringBuffer();
		
		for(int i = 0; i < sleeveAmounts.size(); i++){
			retrurnStringBuffer.append(sleeveAmounts.get(i).toString() + "\n");
		}
		
		return retrurnStringBuffer.toString();
	}
}
