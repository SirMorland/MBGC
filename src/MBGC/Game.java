package MBGC;

import java.util.Random;

/**
 * Game
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 */
public class Game {

	private int id = -1;
	private int motherGameId = -1;
	private String name;
	private int age;
	private String length;
	private String playerCount;
	private String publisher;
	private String designer;
	private double price;
	private String purchasedFrom;
	private String theme;
	private String mechanics;
	private String language;
	private String complexity;
	private String waitingTime;
	private String packageSize;
	private String picturePath;
	
	private String[] defaultBoxes = new String[]{
			"pics/defaultBox.png",
			"pics/defaultBox1.png",
			"pics/defaultBox2.png",
			"pics/defaultBox3.png",
			"pics/defaultBox4.png",
			"pics/defaultBox5.png",
			"pics/defaultBox6.png",
			"pics/defaultBox7.png",
			"pics/defaultBox8.png",
			"pics/defaultBox9.png",
			"pics/defaultBox10.png",
			"pics/defaultBox11.png",
			"pics/defaultBox12.png",
			"pics/defaultBox13.png",
			"pics/defaultBox14.png",
			"pics/defaultBox15.png",
			"pics/defaultBox16.png",
			"pics/defaultBox17.png",
			"pics/defaultBox18.png",
			"pics/defaultBox19.png",
			"pics/defaultBox20.png",
			"pics/defaultBox21.png",
			"pics/defaultBox22.png",
			"pics/defaultBox23.png",
			"pics/defaultBox24.png",
			"pics/defaultBox25.png",
			"pics/defaultBox26.png"
	};
	
	/**
	 * Empty constructor for game
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
     * </pre>
	 */
	public Game(){
		this.id = -1;
		this.motherGameId = -1;
		this.name = "";
		this.age = -1;
		this.length = "";
		this.playerCount = "";
		this.publisher = "";
		this.designer = "";
		this.price = -1;
		this.purchasedFrom = "";
		this.theme = "";
		this.mechanics = "";
		this.language = "";
		this.complexity = "";
		this.waitingTime = "";
		this.packageSize = "";
		this.picturePath = defaultBoxes[new Random().nextInt(defaultBoxes.length)];
	}
	
	/**
	 * Constructor
	 * @param id id
	 * @param motherGameId mother game's id
	 * @param name name
	 * @param age age
	 * @param length length
	 * @param playerCount player count
	 * @param publisher publisher
	 * @param designer designer
	 * @param price price
	 * @param purchasedFrom purchased from
	 * @param theme theme
	 * @param mechanics mechanics
	 * @param language language
	 * @param complexity complexity
	 * @param waitingTime waiting time
	 * @param packageSize package size
	 * @param picturePath picture path
	 */
	public Game(
			int id,
			int motherGameId,
			String name,
			int age,
			String length,
			String playerCount,
			String publisher,
			String designer,
			double price,
			String purchasedFrom,
			String theme,
			String mechanics,
			String language,
			String complexity,
			String waitingTime,
			String packageSize,
			String picturePath){
		this.id = id;
		this.motherGameId = motherGameId;
		this.name = name;
		this.age = age;
		this.length = length;
		this.playerCount = playerCount;
		this.publisher = publisher;
		this.designer = designer;
		this.price = price;
		this.purchasedFrom = purchasedFrom;
		this.theme = theme;
		this.mechanics = mechanics;
		this.language = language;
		this.complexity = complexity;
		this.waitingTime = waitingTime;
		this.packageSize = packageSize;
		this.picturePath = picturePath;
	}

	/**
	 * Constructor from string
	 * @param game string that has game data
	 */
	public Game(String game){

		this.id = -1;
		this.motherGameId = -1;
		this.name = "";
		this.age = 0;
		this.length = "0";
		this.playerCount = "0";
		this.publisher = "";
		this.designer = "";
		this.price = 0;
		this.purchasedFrom = "";
		this.theme = "";
		this.mechanics = "";
		this.language = "";
		this.complexity = "";
		this.waitingTime = "";
		this.packageSize = "";
		this.picturePath = defaultBoxes[new Random().nextInt(defaultBoxes.length)];
		
		try{
			
			String[] gameData = game.split("\\|");
			
			this.id = Integer.parseInt(gameData[0]);
			this.motherGameId = Integer.parseInt(gameData[1]);
			this.name = gameData[2];
			this.age = Integer.parseInt(gameData[3]);
			this.length = gameData[4];
			this.playerCount = gameData[5];
			this.publisher = gameData[6];
			this.designer = gameData[7];
			this.price = Double.parseDouble(gameData[8]);
			this.purchasedFrom = gameData[9];
			this.theme = gameData[10];
			this.mechanics = gameData[11];
			this.language = gameData[12];
			this.complexity = gameData[13];
			this.waitingTime = gameData[14];
			this.packageSize = gameData[15];
			this.picturePath = gameData[16];
		}
		catch(Exception e){
			this.id = -1;
		}
	}
	
	/**
	 * @return id
	 */
	public int getId() {
		
		return this.id;
	}
	
	/**
	 * @param id id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * @return motherGamerId
	 */
	public int getMotherGameId() {
		
		return this.motherGameId;
	}
	
	/**
	 * @param id id
	 */
	public void setMotherGameId(int id) {
		
		this.motherGameId = id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		
		return this.name;
	}
	
	/**
	 * @param name name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * @return age
	 */
	public int getAge() {
		
		return this.age;
	}
	
	/**
	 * @param age age
	 */
	public void setAge(int age) {
		
		this.age = age;
	}
	
	/**
	 * @return length
	 */
	public String getLength() {
		
		return this.length;
	}
	
	/**
	 * @param length length
	 */
	public void setLength(String length) {
		
		this.length = length;
	}
	
	/**
	 * @return playerCount
	 */
	public String getPlayerCount() {
		
		return this.playerCount;
	}
	
	/**
	 * @param playerCount playerCount
	 */
	public void setPlayerCount(String playerCount) {
		
		this.playerCount = playerCount;
	}
	
	/**
	 * @return publisher
	 */
	public String getPublisher() {
		
		return this.publisher;
	}
	
	/**
	 * @param publisher publisher
	 */
	public void setPublisher(String publisher) {
		
		this.publisher = publisher;
	}
	
	/**
	 * @return designer
	 */
	public String getDesigner() {
		
		return this.designer;
	}
	
	/**
	 * @param designer designer
	 */
	public void setDesigner(String designer) {
		
		this.designer = designer;
	}
	
	/**
	 * @return price
	 */
	public Double getPrice() {
		
		return this.price;
	}
	
	/**
	 * @param price price
	 */
	public void setPrice(Double price) {
		
		this.price = price;
	}
	
	/**
	 * @return purchasedFrom
	 */
	public String getPurchasedFrom() {
		
		return this.purchasedFrom;
	}
	
	/**
	 * 
	 * @param purchasedFrom purchasedFrom
	 */
	public void setPurchasedFrom(String purchasedFrom) {
		
		this.purchasedFrom = purchasedFrom;
	}
	
	/**
	 * @return theme
	 */
	public String getTheme() {
		
		return this.theme;
	}
	
	/**
	 * @param theme theme
	 */
	public void setTheme(String theme) {
		
		this.theme = theme;
	}
	
	/**
	 * @return mechanics
	 */
	public String getMechanics() {
		
		return this.mechanics;
	}
	
	/**
	 * @param mechanics mechanics
	 */
	public void setMechanics(String mechanics) {
		
		this.mechanics = mechanics;
	}
	
	/**
	 * @return language
	 */
	public String getLanguage() {
		
		return this.language;
	}
	
	/**
	 * @param language language
	 */
	public void setLanguage(String language) {
		
		this.language = language;
	}
	
	/**
	 * @return complexity
	 */
	public String getComplexity() {
		
		return this.complexity;
	}
	
	/**
	 * @param complexity complexity
	 */
	public void setComplecity(String complexity) {
		
		this.complexity = complexity;
	}
	
	/**
	 * @return waitingTime
	 */
	public String getWaitingTime() {
		
		return this.waitingTime;
	}
	
	/**
	 * @param waitingTime waitingTime
	 */
	public void setWaitingTime(String waitingTime) {
		
		this.waitingTime = waitingTime;
	}
	
	/**
	 * @return packageSize
	 */
	public String getPackageSize() {
		
		return this.packageSize;
	}
	
	/**
	 * @param packageSize packageSize
	 */
	public void setPackageSize(String packageSize) {
		
		this.packageSize = packageSize;
	}
	
	/**
	 * @return picturePath
	 */
	public String getPicturePath() {
		
		return this.picturePath;
	}
	
	/**
	 * @param picturePath picturePath
	 */
	public void setPicturePath(String picturePath) {
		
		this.picturePath = picturePath;
	}
	
	/**
	 * Returns text for the left column (name, age, length, player count, publisher, designer, price and purchase place)
	 * @return text for the left column
	 */
	public String getLeftColumnText(){

		StringBuilder returnText = new StringBuilder();
		returnText.append(this.name + "\nAge " + this.age + "+ " + this.length + " Min " + this.playerCount + " Players\n");
		if(!this.publisher.matches("")) returnText.append(this.publisher + "\n");
		if(!this.designer.matches("")) returnText.append(this.designer + "\n");
		if(this.price >= 0) returnText.append(String.format("%.2f", this.price) + " ");
		if(!this.purchasedFrom.equals("")) returnText.append("@ " + this.purchasedFrom);
		
		return returnText.toString();
	}
	
	/**
	 * Returns text for the middle column (theme, mechanics, language, complexity, waiting time, package size)
	 * @return text for the middle column
	 */
	public String getMiddleColumnText(){
		
		StringBuilder returnText = new StringBuilder();
		
		if(!this.theme.equals("")) returnText.append("Theme: " + this.theme + "\n");
		if(!this.mechanics.equals("")) returnText.append("Mechanics: " + this.mechanics + "\n");
		if(!this.language.equals("")) returnText.append("Language: " + this.language + "\n");

		if(!this.complexity.equals("") && !this.complexity.equals("Choose")) returnText.append("Complexity: " + this.complexity + "\n");
		if(!this.waitingTime.equals("") && !this.waitingTime.equals("Choose")) returnText.append("Waiting time: " + this.waitingTime + "\n");
		if(!this.packageSize.equals("") && !this.packageSize.equals("Choose")) returnText.append("Package size: " + this.packageSize);
		
		return returnText.toString();
	}
	
	/**
	 * Returns game's data as a string
	 */
	@Override
	public String toString(){

		StringBuffer returnStringBuffer = new StringBuffer();
		if(this.picturePath == null || this.picturePath.equals("null")) this.picturePath = defaultBoxes[new Random().nextInt(defaultBoxes.length)];
		
		returnStringBuffer.append(
				this.id + "|" +
				this.motherGameId + "|" +
				this.name + "|" +
				this.age + "|" +
				this.length + "|" +
				this.playerCount + "|" +
				this.publisher + "|" +
				this.designer + "|" +
				this.price + "|" +
				this.purchasedFrom + "|" +
				this.theme + "|" +
				this.mechanics + "|" +
				this.language + "|" +
				this.complexity + "|" +
				this.waitingTime + "|" +
				this.packageSize + "|" +
				this.picturePath);
		
		return returnStringBuffer.toString();
	}

	/**
	 * Tests the working of the class
	 * @param args not in use
	 */
    public static void main(String args[]) {
    	Game peli = new Game("1|-1|Descent: Journeys in the Dark (Second Edition)|14|120|2-5|Fantasy Flight Games|Daniel Clark, Corey Konieczka, Adam Sadler, Kevin Wilson|75.0|Poromagia|Fantasy|Figure|English|5|1|5|C:\\kuvat\\descent.jpg");
    	System.out.println(peli.getLeftColumnText());
    	System.out.println(peli.getMiddleColumnText());
    }
}
