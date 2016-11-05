package fxMBGC;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MBGC.SleeveAmount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller for window that edits a game from the collection.
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class EditorController implements Initializable {
	
	@FXML private VBox vBox;
	@FXML private VBox sleeveAmounts;
	
    @FXML private Button errorClose;
    
    @FXML private TextField name;
    @FXML private TextField age;
    @FXML private TextField length;
    @FXML private TextField players;
    @FXML private TextField publisher;
    @FXML private TextField designer;
    @FXML private TextField price;
    @FXML private TextField boughtFrom;
    @FXML private TextField theme;
    @FXML private TextField mechanics;
    @FXML private TextField language;
    @FXML private ComboBox<String> complexity;
    ObservableList<String> complexities = FXCollections.observableArrayList(
    		"Choose",
    		"Very simple",
    		"Simple",
    		"Middle",
    		"Complex",
    		"Very complex");
    @FXML private ComboBox<String> waitingTime;
    ObservableList<String> waitingTimes = FXCollections.observableArrayList(
    		"Choose",
    		"No waiting time",
    		"Little waiting time",
    		"Some waiting time",
    		"Much waiting time",
    		"Very much waiting time");
    @FXML private ComboBox<String> packageSize;
    ObservableList<String> packageSizes = FXCollections.observableArrayList(
    		"Choose",
    		"Very small",
    		"Small",
    		"Middle",
    		"Large",
    		"Very large");
    
    private String picturePath;

    @FXML private Button addImage;
    @FXML private ImageView image;
    
    @FXML private MBGCController mbgcController;
    private int motherGameId = -1;
    
    private int editNo;

    /**
     * Initializes the window
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		complexity.getItems().addAll(complexities);
		waitingTime.getItems().addAll(waitingTimes);
		packageSize.getItems().addAll(packageSizes);
	}

	/**
	 * Set clicked textfield's style to "".
	 * Calls onOk() if enter was pressed
	 * @param event event
	 * @throws IOException IOException
	 */
	@FXML
	void onClick(KeyEvent event) throws IOException {
		((Node) event.getSource()).setStyle("");
		if(event.getCode() == KeyCode.ENTER) onOk();
	}

	/**
	 * Opens a window where user can select an image for the game
	 * @throws IOException IOException
	 */
    @FXML
    void onAddImage() throws IOException {
		Stage stage = (Stage) errorClose.getScene().getWindow();
    	FileChooser fileChooser = new FileChooser();
    	if(new File(picturePath.replace("/", "\\").substring(0, picturePath.replace("/", "\\").lastIndexOf("\\"))).exists()) fileChooser.setInitialDirectory(new File(picturePath.replace("/", "\\").substring(0, picturePath.replace("/", "\\").lastIndexOf("\\"))));
    	fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
        	new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png"));
    	File file = fileChooser.showOpenDialog(stage);
    	try{picturePath = file.getAbsolutePath();}catch (Exception e){/**/}
		image.setImage(new Image("file:" + picturePath));
		image.setPreserveRatio(true);
    }
    
    /**
     * Adds new field for user to add sleeves to game
     * @throws IOException
     */
    @FXML
    void onAddSleeveAmount() throws IOException {
		HBox newHBox = new HBox();
			ComboBox<String> newComboBox = new ComboBox<String>();
				newComboBox.setPromptText("Choose");
				newComboBox.getItems().add("Choose");
				for(int j = 0; j < mbgcController.getCollection().getSleeves().getCount(); j++){
					newComboBox.getItems().add(mbgcController.getCollection().getSleeve(j).getName());
				}
			Region newRegion = new Region();
				HBox.setHgrow(newRegion, Priority.ALWAYS);
			Text newText = new Text("x");
			TextField newTextField = new TextField();
				newTextField.setPrefWidth(50);
			newHBox.getChildren().addAll(newComboBox, newRegion, newText, newTextField);
		sleeveAmounts.getChildren().add(newHBox);
    }

    /**
     * Pushes data from fields to game in collection
     * If some field is in wrong format that field transforms to red and an error message is shown
     */
    @SuppressWarnings("unchecked")
    @FXML
	void onOk() {
    	if(
        		!name.getText().equals("") &&
        		age.getText().matches("[0-9]+") &&
        		length.getText().matches("\\d|\\d\\d|\\d\\d\\d|\\d-\\d|\\d-\\d\\d|\\d\\d-\\d\\d|\\d\\d-\\d\\d\\d|\\d\\d\\d-\\d\\d\\d") &&
        		players.getText().matches("\\d|\\d\\d|\\d\\d\\d|\\d-\\d|\\d-\\d\\d|\\d\\d-\\d\\d|\\d\\d-\\d\\d\\d|\\d\\d\\d-\\d\\d\\d") &&
        		price.getText().matches("[0-9.,]+")
        ){
    		boolean canContinue = true;
    		for(int i = 1; i < sleeveAmounts.getChildren().size(); i++){
    			if(((ComboBox<String>)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(0)).getValue() != null &&
    					!((ComboBox<String>)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(0)).getValue().equals("Choose") &&
    			    	!((TextField)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(3)).getText().matches("[0-9]+")) canContinue = false;
    		}
    		if(canContinue){
	    		if(complexity.getValue() == null) complexity.getSelectionModel().select("Choose");
	    		if(waitingTime.getValue() == null) waitingTime.getSelectionModel().select("Choose");
	    		if(packageSize.getValue() == null) packageSize.getSelectionModel().select("Choose");
		    	mbgcController.getCollection().getGameWithId(editNo).setName(name.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setAge(Integer.parseInt(age.getText()));
		    	mbgcController.getCollection().getGameWithId(editNo).setLength(length.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setPlayerCount(players.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setPublisher(publisher.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setDesigner(designer.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setPrice(Double.parseDouble(price.getText()));
		    	mbgcController.getCollection().getGameWithId(editNo).setPurchasedFrom(boughtFrom.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setTheme(theme.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setMechanics(mechanics.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setLanguage(language.getText());
		    	mbgcController.getCollection().getGameWithId(editNo).setComplecity(complexity.getValue());
		    	mbgcController.getCollection().getGameWithId(editNo).setWaitingTime(waitingTime.getValue());
		    	mbgcController.getCollection().getGameWithId(editNo).setPackageSize(packageSize.getValue());
		    	mbgcController.getCollection().getGameWithId(editNo).setPicturePath(picturePath);
		    	
		    	for(int i = 0; i < mbgcController.getCollection().getSleeveAmounts().getCount(); i++){
		    		if(mbgcController.getCollection().getSleeveAmount(i).getGameId() == editNo){
		    			mbgcController.getCollection().removeSleeveAmount(mbgcController.getCollection().getSleeveAmount(i));
		    			i--;
		    		}
		    	}
		    	
		    	for(int i = 1; i < sleeveAmounts.getChildren().size(); i++){
		    		
		    		if(((ComboBox<String>)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(0)).getValue() != null &&
		    			!((ComboBox<String>)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(0)).getValue().equals("Choose")){
		    			
		    			SleeveAmount newSleeveAmount = new SleeveAmount(
		    					editNo,
		    					getSleeveId(((ComboBox<String>)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(0)).getValue()),
		    					Integer.parseInt(((TextField)((HBox)sleeveAmounts.getChildren().get(i)).getChildren().get(3)).getText())
		    			);
		    			mbgcController.getCollection().addSleeveAmount(newSleeveAmount);
		    		}
		    	}
		
				Stage stage = (Stage) errorClose.getScene().getWindow();
				if(motherGameId == -1) mbgcController.addGamesToGames();
				if(motherGameId != -1) mbgcController.addExpansionsToGame(motherGameId);
				mbgcController.updateFooter();
				Main.setController(mbgcController);
				stage.close();
			}else{
    			String errorText = "Add proper amount of sleeves";
    			
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Error");
    			alert.setContentText(errorText);

    			alert.showAndWait();
    		}
		}else{
			String errorText = "";
			if(name.getText().equals("")){errorText += "Name is missing\n"; name.setStyle("-fx-background-color:#FF0000");}
			if(age.getText().equals("")){errorText += "Age is missing\n"; age.setStyle("-fx-background-color:#FF0000");}
			else if(!age.getText().matches("[0-9]+")){errorText += "Age-field can only have numbers\n"; age.setStyle("-fx-background-color:#FF0000");}
			if(length.getText().equals("")){errorText += "Length is missing\n"; length.setStyle("-fx-background-color:#FF0000");}
			else if(!length.getText().matches("\\d|\\d\\d|\\d\\d\\d|\\d-\\d|\\d-\\d\\d|\\d\\d-\\d\\d|\\d\\d-\\d\\d\\d|\\d\\d\\d-\\d\\d\\d")){errorText += "Length-field have to be in format X or X-X\n"; length.setStyle("-fx-background-color:#FF0000");}
			if(players.getText().equals("")){errorText += "Players is missing\n"; players.setStyle("-fx-background-color:#FF0000");}
			else if(!players.getText().matches("\\d|\\d\\d|\\d\\d\\d|\\d-\\d|\\d-\\d\\d|\\d\\d-\\d\\d|\\d\\d-\\d\\d\\d|\\d\\d\\d-\\d\\d\\d")){errorText += "Player-field have to be in format X or X-X\n"; length.setStyle("-fx-background-color:#FF0000");}
			if(price.getText().equals("")){errorText += "Price is missing\n"; price.setStyle("-fx-background-color:#FF0000");}
			else if(!price.getText().matches("[0-9.,]+")){errorText += "Price-field can only have numbers\n"; price.setStyle("-fx-background-color:#FF0000");}
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText(errorText);

			alert.showAndWait();
		}
    }
    
    /**
     * Deletes the game being edited from the collection
     * @throws IOException
     */
    @FXML
    void onDelete() throws IOException {
    	for(int i = 0; i < mbgcController.getCollection().getGames().getCount(); i++){
    		if(mbgcController.getCollection().getGame(i).getMotherGameId() == editNo) {
    	    	mbgcController.getCollection().removeGame(mbgcController.getCollection().getGame(i));
    	    	
		    	for(int j = 0; j < mbgcController.getCollection().getSleeveAmounts().getCount(); j++){
		    		if(mbgcController.getCollection().getSleeveAmount(j).getGameId() == i){
		    			mbgcController.getCollection().removeSleeveAmount(mbgcController.getCollection().getSleeveAmount(j));
		    			j--;
		    		}
		    	}
    	    }
    	}
    	mbgcController.getCollection().removeGame(mbgcController.getCollection().getGameWithId(editNo));
    	for(int i = 0; i < mbgcController.getCollection().getSleeveAmounts().getCount(); i++){
    		if(mbgcController.getCollection().getSleeveAmount(i).getGameId() == editNo){
    			mbgcController.getCollection().removeSleeveAmount(mbgcController.getCollection().getSleeveAmount(i));
    			i--;
    		}
    	}

		if(motherGameId == -1) mbgcController.addGamesToGames();
		if(motherGameId != -1) mbgcController.addExpansionsToGame(motherGameId);
		mbgcController.updateFooter();
		Main.setController(mbgcController);
		
    	Stage stage = (Stage) errorClose.getScene().getWindow();
		stage.close();
    }
    /**
     * Closes the window
     * @param event
     */
    @FXML
    void onClose() {
		Stage stage = (Stage) errorClose.getScene().getWindow();
		
		stage.close();
    }
    /**
     * @param mbgcController MBGCController
     */
    public void setMBGCController(MBGCController mbgcController) {
		this.mbgcController = mbgcController;
	}
    
    /**
     * Sets motherGamesId to motherGameId of the game which id is "id"
     * @param id id
     */
    public void setMotherGameId(int id){
    	this.motherGameId = mbgcController.getCollection().getGameWithId(id).getMotherGameId();
    }
    
    /**
     * Sets the data of the game which id is "id" to window's fields
     * @param id id
     */
    public void setEditValues(int id){

    	editNo = id;
    	name.setText(mbgcController.getCollection().getGameWithId(id).getName());
    	age.setText("" + mbgcController.getCollection().getGameWithId(id).getAge());
    	length.setText(mbgcController.getCollection().getGameWithId(id).getLength());
    	players.setText(mbgcController.getCollection().getGameWithId(id).getPlayerCount());
    	publisher.setText(mbgcController.getCollection().getGameWithId(id).getPublisher());
    	designer.setText(mbgcController.getCollection().getGameWithId(id).getDesigner());
    	price.setText("" + mbgcController.getCollection().getGameWithId(id).getPrice());
    	boughtFrom.setText(mbgcController.getCollection().getGameWithId(id).getPurchasedFrom());
    	theme.setText(mbgcController.getCollection().getGameWithId(id).getTheme());
    	mechanics.setText(mbgcController.getCollection().getGameWithId(id).getMechanics());
    	language.setText(mbgcController.getCollection().getGameWithId(id).getLanguage());
    	complexity.getSelectionModel().select(mbgcController.getCollection().getGameWithId(id).getComplexity());
    	waitingTime.getSelectionModel().select(mbgcController.getCollection().getGameWithId(id).getWaitingTime());
    	packageSize.getSelectionModel().select(mbgcController.getCollection().getGameWithId(id).getPackageSize());
    	picturePath = mbgcController.getCollection().getGameWithId(id).getPicturePath();
		
		image.setImage(new Image("file:" + picturePath));
		image.setPreserveRatio(true);

		Button newButton = new Button("Add sleeves");
			newButton.setOnAction((event) ->{try {onAddSleeveAmount();} catch (Exception e) {/**/}});
		sleeveAmounts.getChildren().add(newButton);
		for(int i = 0; i < mbgcController.getCollection().getSleeveAmountsWithGameId(id).getCount(); i++){
			HBox newHBox = new HBox();
				ComboBox<String> newComboBox = new ComboBox<String>();
					newComboBox.setPromptText("Choose");
					newComboBox.getItems().add("Choose");
					for(int j = 0; j < mbgcController.getCollection().getSleeves().getCount(); j++){
						newComboBox.getItems().add(mbgcController.getCollection().getSleeve(j).getName());
					}
					newComboBox.getSelectionModel().select(mbgcController.getCollection().getSleeve(mbgcController.getCollection().getSleeveAmountsWithGameId(id).getSleeveAmount(i).getSleeveId()).getName());
				Region newRegion = new Region();
					HBox.setHgrow(newRegion, Priority.ALWAYS);
				Text newText = new Text("x");
				TextField newTextField = new TextField();
					newTextField.setPrefWidth(50);
					newTextField.setText("" + mbgcController.getCollection().getSleeveAmountsWithGameId(id).getSleeveAmount(i).getAmount());
				newHBox.getChildren().addAll(newComboBox, newRegion, newText, newTextField);
			sleeveAmounts.getChildren().add(newHBox);
		}
    }
    
    /**
     * Returns what is the id of the named sleeve
     * @param sleeveName name
     * @return id
     */
    public int getSleeveId(String sleeveName){
    	for(int i = 0; i < mbgcController.getCollection().getSleeves().getCount(); i++){
    		if(mbgcController.getCollection().getSleeve(i).getName().equals(sleeveName)) return mbgcController.getCollection().getSleeve(i).getId();
    	}
    	return -1;
    }
}
