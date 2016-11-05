package MBGC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Collection
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class Collection {

	private Games games;
	private Sleeves sleeves;
	private SleeveAmounts sleeveAmounts;

	/**
	 * Empty constructor for collection
	 * @example
	 * <pre name="test">
     *  Game peli = new Game("1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg");
     *	peli.getLeftColumnText() === "Descent: Journeys in the Dark (Second Edition)\nAge 14+ 120 Min 2-5 Players\nFantasy Flight Games\nDaniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson\n75,00 @ Poromagia";
     *	peli.getMiddleColumnText() === "Theme: Fantasy\nMechanics: Figure\nLanguage: English\nComplexity: Very complex\nWaiting time: No waiting time\nPackage size: Very large";
     *  Game peli1 = new Game("|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg");
     *	peli1.getLeftColumnText() === "\nAge 0+ 0 Min 0 Players\n0,00 ";
     *	peli1.getMiddleColumnText() === "";
     *  Game peli2 = new Game("1||Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg");
     *	peli2.getLeftColumnText() === "\nAge 0+ 0 Min 0 Players\n0,00 ";
     *	peli2.getMiddleColumnText() === "";
     *  Game peli3 = new Game("1||Descent: Journeys in the Dark (Second Edition)||120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg");
     *	peli3.getLeftColumnText() === "\nAge 0+ 0 Min 0 Players\n0,00 ";
     *	peli3.getMiddleColumnText() === "";
     *  Game peli4 = new Game("|");
     *	peli4.getLeftColumnText() === "\nAge 0+ 0 Min 0 Players\n0,00 ";
     *	peli4.getMiddleColumnText() === "";
     *  Game peli5 = new Game("");
     *	peli5.getLeftColumnText() === "\nAge 0+ 0 Min 0 Players\n0,00 ";
     *	peli5.getMiddleColumnText() === "";
     *
     *  Games pelit = new Games();
     *  pelit.addGame(peli);
     *  pelit.addGame(peli1);
     *  pelit.toString() === "1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg\n-1|-1||0|0|0|||0.0||||||||" + peli1.getPicturePath() +"\n";
     *  pelit.removeGame(peli);
     *  pelit.toString() === "-1|-1||0|0|0|||0.0||||||||" + peli1.getPicturePath() + "\n";
     *  
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
     * 
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
     * 
     * Collection collection = new Collection(pelit, sleeves, sleeveAmounts);
     * collection.toString() === "Games:\n-1|-1||0|0|0|||0.0||||||||" + peli1.getPicturePath() + "\n\nSleeves:\n-1||0|0.0\n\nSleeveAmounts:\n-1|0|0\n";
     * collection.addGame(peli);
     * collection.addSleeve(sleeve);
     * collection.addSleeveAmount(sleeveAmount);
     * collection.toString() === "Games:\n-1|-1||0|0|0|||0.0||||||||" + peli1.getPicturePath() + "\n1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg\n\nSleeves:\n-1||0|0.0\n0|Ultra Pro|50|3.5\n\nSleeveAmounts:\n-1|0|0\n0|0|50\n";

     * collection.getGame(0).toString() === "-1|-1||0|0|0|||0.0||||||||" + peli1.getPicturePath();
     * collection.getGameWithId(1).toString() === "1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg";
     * collection.removeGame(peli1);
     * 
     * collection.getSleeve(0).toString() === "-1||0|0.0";
     * collection.getSleeveWithId(0).toString() === "0|Ultra Pro|50|3.5";
     * collection.removeSleeve(sleeve1);
     * 
     * collection.getSleeveAmount(0).toString() === "-1|0|0";
     * collection.getSleeveAmountsWithGameId(0).getSleeveAmount(0).toString() === "0|0|50";
     * collection.removeSleeveAmount(sleeveAmount1);
     * 
     * collection.toString() === "Games:\n1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|Very complex|No waiting time|Very large|C:\\kuvat\\descent.jpg\n\nSleeves:\n0|Ultra Pro|50|3.5\n\nSleeveAmounts:\n0|0|50\n";
     * </pre>
	 */
	public Collection(){
		
		this.games = new Games();
		this.sleeves = new Sleeves();
		this.sleeveAmounts = new SleeveAmounts();
	}
	
	/**
	 * Collection constructor from games, sleeves and sleeveAmounts
	 * @param games games
	 * @param sleeves sleeves
	 * @param sleeveAmounts sleeveAmounts
	 */
	public Collection(Games games, Sleeves sleeves, SleeveAmounts sleeveAmounts){
		
		this.games = games;
		this.sleeves = sleeves;
		this.sleeveAmounts = sleeveAmounts;
	}
	
	/**
	 * Collection constructor from file path
	 * @param path file path
	 */
	@SuppressWarnings("resource")
	public Collection (String path) {

		this.games = new Games();
		this.sleeves = new Sleeves();
		this.sleeveAmounts = new SleeveAmounts();
		
		try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            
            boolean addingGames = false;
            boolean addingSleeves = false;
            boolean addingSleeveAmounts = false;
            
            while(true){
                String line;
                if((line = bufferedReader.readLine()) != null){

                    if(line.equals("")){
                        addingGames = false;
                        addingSleeves = false;
                        addingSleeveAmounts = false;
                    }
                    else if(addingGames && !line.substring(0, 1).equals(";"))this.games.addGame(new Game(line));
                    else if(addingSleeves && !line.substring(0, 1).equals(";")) this.sleeves.addSleeve(new Sleeve(line));
                    else if(addingSleeveAmounts && !line.substring(0, 1).equals(";")) this.sleeveAmounts.addSleeveAmount(new SleeveAmount(line));
                	
                    if(line.equals("Games:")) addingGames = true;
                    if(line.equals("Sleeves:")) addingSleeves = true;
                    if(line.equals("SleeveAmounts:")) addingSleeveAmounts = true;
                }else{
                	break;
                }
            }
            bufferedReader.close();
        }catch (IOException e){
        	System.out.println(e);
        }
	}
	
	/**
	 * @param game game
	 */
	public void addGame(Game game){
		
		this.games.addGame(game);
	}
	
	/**
	 * @param game game
	 */
	public void removeGame(Game game){
		
		this.games.removeGame(game);
	}
	
	/**
	 * @return games
	 */
	public Games getGames(){
		
		return this.games;
	}
	
	/**
	 * @param i i
	 * @return game
	 */
	public Game getGame(int i){
		
		return this.games.getGame(i);
	}
	
	/**
	 * @param i id
	 * @return game
	 */
	public Game getGameWithId(int i){
		
		for(int j = 0; j < games.getCount(); j++){
			if(games.getGame(j).getId() == i) return this.games.getGame(j);
		}
		return null;
	}
	
	/**
	 * @param sleeve sleeve
	 */
	public void addSleeve(Sleeve sleeve){
		
		this.sleeves.addSleeve(sleeve);
	}
	
	/**
	 * @param sleeve sleeve
	 */
	public void removeSleeve(Sleeve sleeve){
		
		this.sleeves.removeSleeve(sleeve);
	}
	
	/**
	 * @return sleeves
	 */
	public Sleeves getSleeves(){
		
		return this.sleeves;
	}
	
	/**
	 * @param i i
	 * @return sleeve
	 */
	public Sleeve getSleeve(int i){
		
		return this.sleeves.getSleeve(i);
	}
	
	/**
	 * @param id id
	 * @return sleeve
	 */
	public Sleeve getSleeveWithId(int id){

		for(int j = 0; j < sleeves.getCount(); j++){
			if(sleeves.getSleeve(j).getId() == id) return this.sleeves.getSleeve(j);
		}
		return null;
	}
	
	/**
	 * @param sleeveAmount sleeveAmount
	 */
	public void addSleeveAmount(SleeveAmount sleeveAmount){
		
		this.sleeveAmounts.addSleeveAmount(sleeveAmount);
	}
	
	/**
	 * @param sleeveAmount sleeveAmount
	 */
	public void removeSleeveAmount(SleeveAmount sleeveAmount){
		
		this.sleeveAmounts.removeSleeveAmount(sleeveAmount);
	}
	
	/**
	 * @return sleeveAmounts
	 */
	public SleeveAmounts getSleeveAmounts(){
		
		return this.sleeveAmounts;
	}
	
	/**
	 * @param i i
	 * @return sleeveAmount
	 */
	public SleeveAmount getSleeveAmount(int i){
		
		return this.sleeveAmounts.getSleeveAmount(i);
	}
	
	/**
	 * @param id id
	 * @return sleeveAmounts
	 */
	public SleeveAmounts getSleeveAmountsWithGameId(int id){
		SleeveAmounts newSleeveAmounts = new SleeveAmounts();
		for(int i = 0; i < this.sleeveAmounts.getCount(); i++){
			if(this.getSleeveAmount(i).getGameId() == id){
				newSleeveAmounts.addSleeveAmount(this.getSleeveAmount(i));
			}
		}
		return newSleeveAmounts;
	}
	
	/**
	 * Writes the collection to file in path
	 * @param path path
	 * @throws IOException IOException
	 */
	@SuppressWarnings("resource")
	public void saveToFile(String path) throws IOException {
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
    	bufferedWriter.write(this.toString());
    	bufferedWriter.close();
	}
	
	/**
	 * Gets the data of the collection as a string
	 */
	@Override
	public String toString(){
		StringBuffer returnStringBuffer = new StringBuffer();
		
		returnStringBuffer.append("Games:\n" + games.toString());
		returnStringBuffer.append("\nSleeves:\n" + sleeves.toString());
		returnStringBuffer.append("\nSleeveAmounts:\n" + sleeveAmounts.toString());
		
		return returnStringBuffer.toString();
	}
}
