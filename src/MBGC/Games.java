package MBGC;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of games in collection
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class Games {

	private List<Game> games = new ArrayList<Game>();
	
	/**
	 * Empty constructor for Games
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
     * </pre>
	 */
	public Games(){
		
	}
	
	/**
	 * Add game to games
	 * @param game game to be added
	 */
	public void addGame(Game game){
		
		games.add(game);
	}
	
	/**
	 * Remove game from games
	 * @param game game to be removed
	 */
	public void removeGame(Game game){
		
		games.remove(game);
	}
	
	/**
	 * Returns how many games in games
	 * @return how many games in games
	 */
	public int getCount(){
		
		return this.games.size();
	}
	
	/**
	 * Returns game i in games
	 * @param i i
	 * @return game
	 */
	public Game getGame(int i){
		
		return this.games.get(i);
	}
	
	/**
	 * Returns the data of all the games as string
	 */
	@Override
	public String toString(){
		StringBuffer retrurnStringBuffer = new StringBuffer();
		
		for(int i = 0; i < games.size(); i++){
			retrurnStringBuffer.append(games.get(i).toString() + "\n");
		}
		
		return retrurnStringBuffer.toString();
	}
}
