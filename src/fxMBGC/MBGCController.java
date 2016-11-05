package fxMBGC;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import MBGC.Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for window that shows all the games in the collection
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class MBGCController {
	
	private Collection collection;
	private String filePath;
	
	@FXML private BorderPane borderPane;

    @FXML private MenuItem fileNewButton;
    @FXML private MenuItem fileOpenButton;
    @FXML private MenuItem fileSaveButton;
    @FXML private MenuItem fileSaveAsButton;
    
    @FXML private MenuItem editAddButton;
    
    @FXML private RadioButton viewGamesButton;
    @FXML private RadioButton viewSleevesButton;
    
    @FXML private MenuItem aboutHelpButton;
    
    @FXML private Button AddGameButton;

    @FXML private TextField search;
    @FXML private Label footer;

    @FXML private VBox games;
    @FXML private VBox sleeves;
    
    /**
     * Creates a new collection
     * If there is a collection open with unsaved changes the user is asked if they want to save those changes
     * @throws IOException
     */
    @FXML
    void onNew() throws IOException {
    	boolean canMakeNew = true;
    	if(collection != null) if(filePath == null ||(filePath != null && !getCollection().toString().equals(new Collection(filePath).toString()))){
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("");
	    	alert.setHeaderText("Do you want to save changes to your collection?");
	
	    	ButtonType saveButton = new ButtonType("Save");
	    	ButtonType dontSaveButton = new ButtonType("Don't save");
	    	ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	
	   		alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);
	
	   		Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == saveButton){
	    		onSave();
	    	} else if (result.get() == dontSaveButton) {
	    		/**/
	    	} else {
	    		canMakeNew = false;
	    	    
	    	}
	    }
    	
    	if(canMakeNew){
	    	collection = new Collection();
	    	filePath = null;
	
	    	Stage stage = (Stage) borderPane.getScene().getWindow();
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MBGCView.fxml"));
	    	
	    	Scene scene = new Scene((BorderPane) loader.load());
	    	stage.setScene(scene);
	    	stage.setTitle("New Collection");
	    	stage.show();
	    	
	    	MBGCController controller = loader.<MBGCController>getController();
	    	controller.viewGamesButton.setSelected(true);
	    	controller.viewSleevesButton.setSelected(false);
	    	controller.setCollection(collection);
	    	controller.setFilePath(filePath);
	    	controller.addGamesToGames();
			controller.updateFooter();
			Main.setController(this);
		}
    }

    /**
     * Creates a new collection from a .bgc file
     * If there is a collection open with unsaved changes the user is asked if they want to save those changes
     * @throws IOException
     */
    @FXML
    void onOpen() throws IOException {
    	boolean canOpen = true;
    	if(collection != null) if(filePath == null ||(filePath != null && !getCollection().toString().equals(new Collection(filePath).toString()))){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("");
    		alert.setHeaderText("Do you want to save changes to your collection?");

    		ButtonType saveButton = new ButtonType("Save");
    		ButtonType dontSaveButton = new ButtonType("Don't save");
    		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    		alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == saveButton){
    			onSave();
    		} else if (result.get() == dontSaveButton) {
    			/**/
    		} else {
    			canOpen = false;
    		    
    		}
    	}
    	
    	if(canOpen){
			Stage stage = (Stage) borderPane.getScene().getWindow();
	    	FileChooser fileChooser = new FileChooser();
	    	if(filePath != null && new File(filePath.replace("/", "\\").substring(0, filePath.replace("/", "\\").lastIndexOf("\\"))).exists()) fileChooser.setInitialDirectory(new File(filePath.replace("/", "\\").substring(0, filePath.replace("/", "\\").lastIndexOf("\\"))));
	    	if(filePath == null) fileChooser.setInitialDirectory(new File("."));
	    	fileChooser.setTitle("Open Collection");              
	        fileChooser.getExtensionFilters().add(
	        	new FileChooser.ExtensionFilter("Board Game Collection (*.bgc)", "*.bgc*"));
	    	File file = fileChooser.showOpenDialog(stage);
	    	String path = "";
	    	String fileName = "";
	    	try{
	    		path = file.getAbsolutePath();
	    		fileName = file.getName();
	    	}catch (Exception e){/**/}
	    	
	    	if(path != ""){
				collection = new Collection(path);
				filePath = path;
		
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MBGCView.fxml"));
		    	
		    	Scene scene = new Scene((BorderPane) loader.load());
		    	stage.setScene(scene);
		    	stage.setTitle(fileName);
		    	stage.show();
		    	
		    	MBGCController controller = loader.<MBGCController>getController();
		    	controller.viewGamesButton.setSelected(true);
		    	controller.viewSleevesButton.setSelected(false);
		    	controller.setCollection(collection);
		    	controller.setFilePath(filePath);
		    	controller.addGamesToGames();
				controller.updateFooter();
				Main.setController(this);
	    	}
    	}
    }

    /**
     * Calls collection saveToFile method if filePath is known
     * Calls onSaveAs if filePath is unknown
     * @throws IOException
     */
    @FXML
    void onSave() throws IOException {
    	if(filePath != null){
    		collection.saveToFile(filePath);
    	}else onSaveAs();
    }

    /**
     * Asks user where they want to save their file
     * Calls collections saveToFile method
     * @throws IOException
     */
    @FXML
    void onSaveAs() throws IOException {
		Stage stage = (Stage) borderPane.getScene().getWindow();
    	FileChooser fileChooser = new FileChooser();
    	if(filePath != null && new File(filePath.replace("/", "\\").substring(0, filePath.replace("/", "\\").lastIndexOf("\\"))).exists()) fileChooser.setInitialDirectory(new File(filePath.replace("/", "\\").substring(0, filePath.replace("/", "\\").lastIndexOf("\\"))));
    	if(filePath == null) fileChooser.setInitialDirectory(new File("."));
    	fileChooser.setTitle("Save Collection");              
        fileChooser.getExtensionFilters().add(
        	new FileChooser.ExtensionFilter("Board Game Collection", "*.bgc*"));
    	File file = fileChooser.showSaveDialog(stage);
    	String path = "";
    	try{
    		path = file.getAbsolutePath();
    		if(!path.substring(path.length()-4).equals(".bgc")) path += ".bgc";
    	}catch (Exception e){/**/}
    	
    	if(path != ""){
    		collection.saveToFile(path);
	    	
			collection = new Collection(path);
			filePath = path;
		}
    }
    
    /**
     * Opens a window to add new game to the collection
     * @throws IOException
     */
    @FXML
    void onAdd() throws IOException {
    	Stage stage=new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdderView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	AdderController controller = loader.<AdderController>getController();
    	controller.setMBGCController(this);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Add Game");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }

	/**
	 * Opens a window to edit a game from the collection
	 * @throws IOException
	 */
    @FXML
    void onEdit(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditorView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	EditorController controller = loader.<EditorController>getController();
    	controller.setMBGCController(this);
    	controller.setEditValues(Integer.parseInt(((Button) event.getSource()).getId()));
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Edit Game");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }

    
    /**
     * Opens a window to add new expansion to the game
     * @throws IOException
     */
    @FXML
    void onAddExpansion(ActionEvent event) throws IOException {
    	Stage stage=new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdderView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	AdderController controller = loader.<AdderController>getController();
    	controller.setMBGCController(this);
    	controller.setMotherGameId(Integer.parseInt(((Button) event.getSource()).getId()));
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Add Game");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }

    /**
     * Opens a window to edit a expansion from the game
     * @throws IOException
     */
    @FXML
    void onEditExpansion(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditorView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	EditorController controller = loader.<EditorController>getController();
    	controller.setMBGCController(this);
    	controller.setEditValues(Integer.parseInt(((Button) event.getSource()).getId()));
    	controller.setMotherGameId(Integer.parseInt(((Button) event.getSource()).getId()));
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Edit Game");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }
    
    /**
     * Opens a window to add sleeves to the collection
     * @throws IOException
     */
    @FXML
    void onAddSleeves() throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SleeveAddView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	SleeveAddController controller = loader.<SleeveAddController>getController();
    	controller.setMBGCController(this);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Add Sleeve");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }
    
    /**
     * Opens a window to edit sleeves from the collection
     * @throws IOException
     */
    @FXML
    void onEditSleeves(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SleeveEditView.fxml"));
    	stage.setScene(new Scene((VBox) loader.load()));
    	SleeveEditController controller = loader.<SleeveEditController>getController();
    	controller.setMBGCController(this);
    	controller.setEditValues(Integer.parseInt(((Button) event.getSource()).getId()));
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
    	stage.setTitle("Edit Sleeve");
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }
    
    /**
     * Changes view to MBGCView
     * @throws IOException
     */
    @FXML
    void onGames() throws IOException {

    	Stage stage = (Stage) borderPane.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MBGCView.fxml"));
    	
    	Scene scene = new Scene((BorderPane) loader.load());
    	stage.setScene(scene);
    	stage.show();
    	
    	MBGCController controller = loader.<MBGCController>getController();
    	controller.viewGamesButton.setSelected(true);
    	controller.viewSleevesButton.setSelected(false);
    	controller.setCollection(collection);
    	controller.setFilePath(filePath);
    	controller.addGamesToGames();
		controller.updateFooter();
		Main.setController(this);
    }

    
    /**
     * Changes view to SleeveView
     * @throws IOException
     */
    @FXML
    void onSleeves() throws IOException {
    	
    	Stage stage = (Stage) borderPane.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SleevesView.fxml"));
    	
    	Scene scene = new Scene((BorderPane) loader.load());
    	stage.setScene(scene);
    	stage.show();
    	
    	MBGCController controller = loader.<MBGCController>getController();
    	controller.viewGamesButton.setSelected(false);
    	controller.viewSleevesButton.setSelected(true);
    	controller.setCollection(collection);
    	controller.setFilePath(filePath);
    	controller.addSleevesToSleeves();
		controller.updateFooter();
		Main.setController(this);
    }
    
    /**
     * Shows the expansions of the game or hides them
     * @throws IOException
     */
    @FXML
    void onViewExpansions(ActionEvent event) throws IOException {
    	if(((ToggleButton) event.getSource()).isSelected()){
    		addExpansionsToGame((ToggleButton) event.getSource());
    	}
    	else hideExpansions((ToggleButton) event.getSource());
    }
    
    /**
     * Opens the AboutView window
     * @throws IOException
     */
    @FXML
    void onHelp() throws IOException {
    	Stage stage=new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("AboutView.fxml"));
    	stage.setScene(new Scene(root));
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(borderPane.getScene().getWindow());
		stage.getIcons().add(new Image("file:icon.png"));
    	stage.showAndWait();
    }

    /**
     * Calls addGamesToGames or addSleevesToSleeves depending on in which view the user is
     * @throws IOException
     */
    @FXML
    void onSearch() throws IOException {
    	if(viewGamesButton.isSelected()) addGamesToGames();
    	if(viewSleevesButton.isSelected()) addSleevesToSleeves();
    }
    
    /**
     * Asks user if they want to save their changed to the collection if there are changes
     * @throws IOException IOException
     */
    public void endProgram() throws IOException {
    	if(collection != null) if(filePath == null ||(filePath != null && !getCollection().toString().equals(new Collection(filePath).toString()))){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("");
    		alert.setHeaderText("Do you want to save changes to your collection?");

    		ButtonType saveButton = new ButtonType("Save");
    		ButtonType dontSaveButton = new ButtonType("Don't save");

    		alert.getButtonTypes().setAll(saveButton, dontSaveButton);

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == saveButton){
    			onSave();
    		} else if (result.get() == dontSaveButton) {
    			/**/
    		} else {
    		    /**/
    		}
    	}
	}
    
    /**
     * Removes are games from the view
     * If search field is empty adds all games with motherGameId -1 to the view
     * Else adds all games which match the search criterion to the view
     */
    public void addGamesToGames(){
    	
    	while(true){
    		if(!games.getChildren().isEmpty())
    			games.getChildren().remove(0);
    		else break;
    	}

    	int a = 0;
    	for(int i = 0; i < collection.getGames().getCount(); i++){
    		if(collection.getGame(i).getId() > -1) if((collection.getGame(i).getMotherGameId() == -1 && isAMatch(i, search.getText())) ||
    			(!search.getText().equals("") && isAMatch(i, search.getText()))){
    			a++;
	    		VBox newVBox = new VBox();
	    			newVBox.setFillWidth(true);
	    			newVBox.setId("" + collection.getGame(i).getId());
	    			if(a%2 == 0) newVBox.setStyle("-fx-background-color:#E0E0E0");
	    			else newVBox.setStyle("-fx-background-color:#FFFFFF");
	    			HBox newHBox = new HBox();
	    				VBox.setVgrow(newHBox, Priority.ALWAYS);
	    				Pane newPane = new Pane();
			    			newPane.setPrefWidth(150);
			    			newPane.setPrefHeight(150);
				    		if(a%2 == 0) newPane.setStyle("-fx-background-color:#E0E0E0");
				    		else newPane.setStyle("-fx-background-color:#FFFFFF");
			    			ImageView newImageView = new ImageView();
		    					newImageView.setFitWidth(150);
		    					newImageView.setFitHeight(150);
		    					newImageView.setImage(new Image("file:" + collection.getGame(i).getPicturePath()));
		    					newImageView.setPreserveRatio(true);
		    				newPane.getChildren().add(newImageView);
	    				VBox newVBox1 = new VBox();
	    					HBox.setHgrow(newVBox1, Priority.ALWAYS);
	        				HBox newHBox1 = new HBox();
	        					VBox.setVgrow(newHBox1, Priority.ALWAYS);
	        					TextArea newText = new TextArea();
						    		if(a%2 == 0) newText.getStylesheets().add("fxMBGC/application2.css");
						    		else newText.getStylesheets().add("fxMBGC/application1.css");
	        						newText.setStyle("-fx-background-color: #00000000");
	        						//newText.setPrefHeight(0);
	        						newText.setPrefWidth(0);
	        						newText.setPrefRowCount(6);
	        						newText.setWrapText(true);
	    							HBox.setHgrow(newText, Priority.ALWAYS);
	    							newText.setEditable(false);
	        						newText.setText(collection.getGame(i).getLeftColumnText());
		        				TextArea newText1 = new TextArea();
							    	if(a%2 == 0) newText1.getStylesheets().add("fxMBGC/application2.css");
							    	else newText1.getStylesheets().add("fxMBGC/application1.css");
		        					newText1.setStyle("-fx-background-color: #00000000");
		        					//newText1.setPrefHeight(0);
		        					newText1.setPrefWidth(0);
	        						newText1.setPrefRowCount(6);
		        					newText1.setWrapText(true);
		    						HBox.setHgrow(newText1, Priority.ALWAYS);
		    						newText1.setEditable(false);
	        						newText1.setText(collection.getGame(i).getMiddleColumnText());
		        				TextArea newText2 = new TextArea();
							    	if(a%2 == 0) newText2.getStylesheets().add("fxMBGC/application2.css");
							    	else newText2.getStylesheets().add("fxMBGC/application1.css");
		        					newText2.setStyle("-fx-background-color: #00000000");
		        					//newText2.setPrefHeight(0);
		        					newText2.setPrefWidth(0);
	        						newText2.setPrefRowCount(6);
		        					newText2.setWrapText(true);
		    						HBox.setHgrow(newText2, Priority.ALWAYS);
		    						newText2.setEditable(false);
	        						newText2.setText(getRightColumnText(i));
	        					newHBox1.getChildren().addAll(newText, newText1, newText2);
	        				HBox newHBox2 = new HBox();
	        					newHBox2.setSpacing(10);
	        					VBox.setVgrow(newHBox2, Priority.NEVER);
	        					if(collection.getGame(i).getMotherGameId() == -1){
		        					ToggleButton newToggleButton = new ToggleButton("Show Expansions");
		            					newToggleButton.setId("" + collection.getGame(i).getId());
		            					newToggleButton.setOnAction((event) ->{try {onViewExpansions(event);} catch (Exception e) {/**/}});
		        					Button newButton = new Button("Add Expansion");
		            					newButton.setId("" + collection.getGame(i).getId());
		            					newButton.setOnAction((event) ->{try {onAddExpansion(event);} catch (Exception e) {/**/}});
		        					Region newRegion3 = new Region();
										HBox.setHgrow(newRegion3, Priority.ALWAYS);
		        					Button newButton1 = new Button("Edit");
		            					newButton1.setId("" + collection.getGame(i).getId());
		            					newButton1.setOnAction((event) ->{try {onEdit(event);} catch (Exception e) {/**/}});
		        					newHBox2.getChildren().addAll(newToggleButton, newButton, newRegion3, newButton1);
	        					}
	        					if(collection.getGame(i).getMotherGameId() > -1){
		        					Region newRegion3 = new Region();
										HBox.setHgrow(newRegion3, Priority.ALWAYS);
		        					Button newButton1 = new Button("Edit");
		            					newButton1.setId("" + collection.getGame(i).getId());
		            					newButton1.setOnAction((event) ->{try {onEdit(event);} catch (Exception e) {/**/}});
		        					newHBox2.getChildren().addAll(newRegion3, newButton1);
	        					}
	        				newVBox1.getChildren().addAll(newHBox1, newHBox2);
	        			newHBox.getChildren().addAll(newPane, newVBox1);
	        		newVBox.getChildren().add(newHBox);
	    		games.getChildren().add(newVBox);
    		}
    	}
    }
    
    /**
     * Add all games with motherGameId equal to toggleButton's id to game with that id
     * @param toggleButton Toggle button
     */
    public void addExpansionsToGame(ToggleButton toggleButton){
    	
    	VBox parent = (VBox)toggleButton.getParent().getParent().getParent().getParent();
    	for(int i = 1; i < parent.getChildren().size();){
    		parent.getChildren().remove(i);
    	}
    	
    	int a = 0;
    	for(int i = 0; i < collection.getGames().getCount(); i++){
    		if(collection.getGame(i).getId() > -1) if(collection.getGame(i).getMotherGameId() == Integer.parseInt(toggleButton.getId())){
    			a++;
	    		VBox newVBox = new VBox();
	    			newVBox.setFillWidth(true);
	    			newVBox.setId("" + collection.getGame(i).getId());
	    			HBox newHBox = new HBox();
	    				VBox.setVgrow(newHBox, Priority.ALWAYS);
			    		newHBox.setPadding(new Insets(0, 0, 0, 150));
			    		Pane newPane = new Pane();
			    			newPane.setPrefWidth(125);
			    			newPane.setPrefHeight(125);
				    		if(a%2 == 0) newPane.setStyle("-fx-background-color:#A0A0A0");
				    		else newPane.setStyle("-fx-background-color:#C0C0C0");
			    			ImageView newImageView = new ImageView();
		    					newImageView.setFitWidth(125);
		    					newImageView.setFitHeight(125);
		    					newImageView.setImage(new Image("file:" + collection.getGame(i).getPicturePath()));
		    					newImageView.setPreserveRatio(true);
		    				newPane.getChildren().add(newImageView);
	    				VBox newVBox1 = new VBox();
				    		if(a%2 == 0) newVBox1.setStyle("-fx-background-color:#A0A0A0");
				    		else newVBox1.setStyle("-fx-background-color:#C0C0C0");
	    					HBox.setHgrow(newVBox1, Priority.ALWAYS);
	        				HBox newHBox1 = new HBox();
	        					VBox.setVgrow(newHBox1, Priority.ALWAYS);
	        					TextArea newText = new TextArea();
						    		if(a%2 == 0) newText.getStylesheets().add("fxMBGC/application4.css");
						    		else newText.getStylesheets().add("fxMBGC/application3.css");
	        						newText.setStyle("-fx-background-color: #00000000");
	        						//newText.setPrefHeight(0);
	        						newText.setPrefWidth(0);
	        						newText.setPrefRowCount(6);
	        						newText.setWrapText(true);
	    							HBox.setHgrow(newText, Priority.ALWAYS);
	    							newText.setEditable(false);
	        						newText.setText(collection.getGame(i).getLeftColumnText());
		        				TextArea newText1 = new TextArea();
							    	if(a%2 == 0) newText1.getStylesheets().add("fxMBGC/application4.css");
							    	else newText1.getStylesheets().add("fxMBGC/application3.css");
		        					newText1.setStyle("-fx-background-color: #00000000");
		        					//newText1.setPrefHeight(0);
		        					newText1.setPrefWidth(0);
	        						newText1.setPrefRowCount(6);
		        					newText1.setWrapText(true);
		    						HBox.setHgrow(newText1, Priority.ALWAYS);
		    						newText1.setEditable(false);
	        						newText1.setText(collection.getGame(i).getMiddleColumnText());
		        				TextArea newText2 = new TextArea();
							    	if(a%2 == 0) newText2.getStylesheets().add("fxMBGC/application4.css");
							    	else newText2.getStylesheets().add("fxMBGC/application3.css");
		        					newText2.setStyle("-fx-background-color: #00000000");
		        					//newText2.setPrefHeight(0);
		        					newText2.setPrefWidth(0);
	        						newText2.setPrefRowCount(6);
		        					newText2.setWrapText(true);
		    						HBox.setHgrow(newText2, Priority.ALWAYS);
		    						newText2.setEditable(false);
	        						newText2.setText(getRightColumnText(i));
	        					newHBox1.getChildren().addAll(newText, newText1, newText2);
	        				HBox newHBox2 = new HBox();
	        					newHBox2.setSpacing(10);
	        					VBox.setVgrow(newHBox2, Priority.NEVER);
	        					Region newRegion3 = new Region();
									HBox.setHgrow(newRegion3, Priority.ALWAYS);
	        					Button newButton1 = new Button("Edit");
	            					newButton1.setId("" + collection.getGame(i).getId());
	            					newButton1.setOnAction((event) ->{try {onEditExpansion(event);} catch (Exception e) {/**/}});
	        					newHBox2.getChildren().addAll(newRegion3, newButton1);
	        				newVBox1.getChildren().addAll(newHBox1, newHBox2);
	        			newHBox.getChildren().addAll(newPane, newVBox1);
	        		newVBox.getChildren().add(newHBox);
	    		parent.getChildren().add(newVBox);
    		}
    	}
    }

    
    /**
     * Add all games with motherGameId "id" to game with that id
     * @param id id
     */
    public void addExpansionsToGame(int id){
    	
    	VBox parent = new VBox();
    	for(int i = 0; i < games.getChildren().size(); i++){
    		if(Integer.parseInt(games.getChildren().get(i).getId()) == id){
    			parent = (VBox)games.getChildren().get(i);
    		}
    	}
    	
    	for(int i = 1; i < parent.getChildren().size();){
    		parent.getChildren().remove(i);
    	}
    	
    	int a = 0;
    	for(int i = 0; i < collection.getGames().getCount(); i++){
    		if(collection.getGame(i).getId() > -1) if(collection.getGame(i).getMotherGameId() == id){
    			a++;
	    		VBox newVBox = new VBox();
	    			newVBox.setFillWidth(true);
	    			newVBox.setId("" + collection.getGame(i).getId());
	    			HBox newHBox = new HBox();
	    				VBox.setVgrow(newHBox, Priority.ALWAYS);
			    		newHBox.setPadding(new Insets(0, 0, 0, 150));
			    		Pane newPane = new Pane();
			    			newPane.setPrefWidth(125);
			    			newPane.setPrefHeight(125);
				    		if(a%2 == 0) newPane.setStyle("-fx-background-color:#A0A0A0");
				    		else newPane.setStyle("-fx-background-color:#C0C0C0");
			    			ImageView newImageView = new ImageView();
		    					newImageView.setFitWidth(125);
		    					newImageView.setFitHeight(125);
		    					newImageView.setImage(new Image("file:" + collection.getGame(i).getPicturePath()));
		    					newImageView.setPreserveRatio(true);
		    				newPane.getChildren().add(newImageView);
	    				VBox newVBox1 = new VBox();
				    		if(a%2 == 0) newVBox1.setStyle("-fx-background-color:#A0A0A0");
				    		else newVBox1.setStyle("-fx-background-color:#C0C0C0");
	    					HBox.setHgrow(newVBox1, Priority.ALWAYS);
	        				HBox newHBox1 = new HBox();
	        					VBox.setVgrow(newHBox1, Priority.ALWAYS);
	        					TextArea newText = new TextArea();
						    		if(a%2 == 0) newText.getStylesheets().add("fxMBGC/application4.css");
						    		else newText.getStylesheets().add("fxMBGC/application3.css");
	        						newText.setStyle("-fx-background-color: #00000000");
	        						//newText.setPrefHeight(0);
	        						newText.setPrefWidth(0);
	        						newText.setPrefRowCount(6);
	        						newText.setWrapText(true);
	    							HBox.setHgrow(newText, Priority.ALWAYS);
	    							newText.setEditable(false);
	        						newText.setText(collection.getGame(i).getLeftColumnText());
		        				TextArea newText1 = new TextArea();
							    	if(a%2 == 0) newText1.getStylesheets().add("fxMBGC/application4.css");
							    	else newText1.getStylesheets().add("fxMBGC/application3.css");
		        					newText1.setStyle("-fx-background-color: #00000000");
		        					//newText1.setPrefHeight(0);
		        					newText1.setPrefWidth(0);
	        						newText1.setPrefRowCount(6);
		        					newText1.setWrapText(true);
		    						HBox.setHgrow(newText1, Priority.ALWAYS);
		    						newText1.setEditable(false);
	        						newText1.setText(collection.getGame(i).getMiddleColumnText());
		        				TextArea newText2 = new TextArea();
							    	if(a%2 == 0) newText2.getStylesheets().add("fxMBGC/application4.css");
							    	else newText2.getStylesheets().add("fxMBGC/application3.css");
		        					newText2.setStyle("-fx-background-color: #00000000");
		        					//newText2.setPrefHeight(0);
		        					newText2.setPrefWidth(0);
	        						newText2.setPrefRowCount(6);
		        					newText2.setWrapText(true);
		    						HBox.setHgrow(newText2, Priority.ALWAYS);
		    						newText2.setEditable(false);
	        						newText2.setText(getRightColumnText(i));
	        					newHBox1.getChildren().addAll(newText, newText1, newText2);
	        				HBox newHBox2 = new HBox();
	        					newHBox2.setSpacing(10);
	        					VBox.setVgrow(newHBox2, Priority.NEVER);
	        					Region newRegion3 = new Region();
									HBox.setHgrow(newRegion3, Priority.ALWAYS);
	        					Button newButton1 = new Button("Edit");
	            					newButton1.setId("" + collection.getGame(i).getId());
	            					newButton1.setOnAction((event) ->{try {onEditExpansion(event);} catch (Exception e) {/**/}});
	        					newHBox2.getChildren().addAll(newRegion3, newButton1);
	        				newVBox1.getChildren().addAll(newHBox1, newHBox2);
	        			newHBox.getChildren().addAll(newPane, newVBox1);
	        		newVBox.getChildren().add(newHBox);
	    		parent.getChildren().add(newVBox);
    		}
    	}
    }
    
    /**
     * Removes all games with motherGameId equal to toggleButton's id from the game with that id
     * @param toggleButton Toggle button
     */
    public void hideExpansions(ToggleButton toggleButton){
    	
    	VBox parent = (VBox)toggleButton.getParent().getParent().getParent().getParent();
    	for(int i = 1; i < parent.getChildren().size();){
    		parent.getChildren().remove(i);
    	}
    }
    
    /**
     * Removes all sleeves from the view
     * Adds all sleeves which contains the text form the search field to the view
     */
    public void addSleevesToSleeves(){
    	
    	while(true){
    		if(!sleeves.getChildren().isEmpty())
    			sleeves.getChildren().remove(0);
    		else break;
    	}
    	
    	for(int i = 0; i < collection.getSleeves().getCount(); i++){
    		if(collection.getSleeve(i).getId() > -1) if(getSleeveText(i).toUpperCase().contains(search.getText().toUpperCase())){
	    		VBox newVBox = new VBox();
	    			newVBox.setAlignment(Pos.TOP_RIGHT);
	    			newVBox.setStyle("-fx-border-color: #000000");
	    			HBox newHBox = new HBox();
	    				Region newRegion = new Region();
	    					newRegion.setPrefWidth(10);
	    				Text newText = new Text();
	    					newText.setText(getSleeveText(i));
	    				newHBox.getChildren().addAll(newRegion, newText);
	    			Button newButton = new Button("Edit");
	    				newButton.setId("" + i);
	    				newButton.setOnAction((event) ->{try {onEditSleeves(event);} catch (Exception e) {/**/}});
	    			newVBox.getChildren().addAll(newHBox, newButton);
	    		sleeves.getChildren().add(newVBox);
    		}
    	}
    }
    
    /**
     * @return collection
     */
    public Collection getCollection(){
    	
    	return this.collection;
    }
    /**
     * @param collection collection
     */
    public void setCollection(Collection collection) {
		
    	this.collection = collection;
	}
    /**
     * @return filePath
     */
    public String getFilePath(){
    	
    	return this.filePath;
    }
    /**
     * @param filePath filePath
     */
    public void setFilePath(String filePath) {
		
    	this.filePath = filePath;
	}
    
    /**
     * Gets the text for the right column of the game's view which id is "id"
     * @param id id
     * @return the text
     */
    public String getRightColumnText(int id){
    	StringBuilder returnString = new StringBuilder();
    	
    	if(collection.getSleeveAmountsWithGameId(id).getCount() > 0) returnString.append("Sleeves:\t" + collection.getSleeveAmountsWithGameId(id).getSleeveAmount(0).getAmount() + " x " + collection.getSleeveWithId(collection.getSleeveAmountsWithGameId(id).getSleeveAmount(0).getSleeveId()).getName() + "\n");
    	for(int i = 1; i < collection.getSleeveAmountsWithGameId(id).getCount(); i++){
    		returnString.append("\t\t" + collection.getSleeveAmountsWithGameId(id).getSleeveAmount(i).getAmount() + " x " + collection.getSleeve(collection.getSleeveAmountsWithGameId(id).getSleeveAmount(i).getSleeveId()).getName() + "\n");
    	}
    	
    	return returnString.toString();
    }
    
    /**
     * Gets the text for the sleeve's view which id is "id"
     * @param id id
     * @return the text
     */
    public String getSleeveText(int id) {
    	StringBuilder returnText = new StringBuilder();
    	returnText.append(collection.getSleeve(id).getName() + "\n");
    	returnText.append(collection.getSleeve(id).getPcPerSet() + "pc/set\t");
    	returnText.append(String.format("%.2f", collection.getSleeve(id).getEPerSet()) + "/set\nNumber of cards: ");
    	int numberOfCards = 0;
    	for(int i = 0; i < collection.getSleeveAmounts().getCount(); i++){
    		if(collection.getSleeveAmount(i).getSleeveId() == id) numberOfCards += collection.getSleeveAmount(i).getAmount();
    	}
    	returnText.append(numberOfCards + "\nNeeded sets: ");
    	double neededSetsDouble = (double)numberOfCards/(double)collection.getSleeve(id).getPcPerSet();
    	int neededSets = (int) Math.ceil(neededSetsDouble);
    	returnText.append(neededSets + "\nNeeded sets' price: ");
    	double neededSetsPrice = neededSets * collection.getSleeve(id).getEPerSet();
    	returnText.append(String.format("%.2f", neededSetsPrice) + "\nUnused: ");
    	int unused = neededSets * collection.getSleeve(id).getPcPerSet() - numberOfCards;
    	returnText.append(unused);
    	
		return returnText.toString();
	}
    
    /**
     * Updates the footer's text
     */
    public void updateFooter() {
		StringBuilder footerText = new StringBuilder("Total value: ");
		double totalValue = 0;
		for(int i = 0; i < collection.getGames().getCount(); i++){
			totalValue += collection.getGame(i).getPrice();
		}
		footerText.append(String.format("%.2f", totalValue) + "\tWith Sleeves: ");
		double totalValueWithSleeves = totalValue;
		for(int id = 0; id < collection.getSleeves().getCount(); id++){
			int numberOfCards = 0;
	    	for(int i = 0; i < collection.getSleeveAmounts().getCount(); i++){
	    		if(collection.getSleeveAmount(i).getSleeveId() == id) numberOfCards += collection.getSleeveAmount(i).getAmount();
	    	}
	    	double neededSetsDouble = (double)numberOfCards/(double)collection.getSleeve(id).getPcPerSet();
	    	int neededSets = (int) Math.ceil(neededSetsDouble);
	    	double neededSetsPrice = neededSets * collection.getSleeve(id).getEPerSet();
	    	totalValueWithSleeves += neededSetsPrice;
		}
		footerText.append(String.format("%.2f", totalValueWithSleeves) + "\tNumber of sleeved cards: ");
		int numberOfCards = 0;
		for(int i = 0; i < collection.getSleeveAmounts().getCount(); i++){
			numberOfCards += collection.getSleeveAmount(i).getAmount();
		}
		footerText.append(numberOfCards + "\tMax players: ");
		int maxPlayers = 0;
		for(int i = 0; i < collection.getGames().getCount(); i++){
			if(collection.getGame(i).getMotherGameId() == -1){
				String players[] = collection.getGame(i).getPlayerCount().split("-");
				if(players.length == 1) maxPlayers += Integer.parseInt(players[0]);
				if(players.length == 2) maxPlayers += Integer.parseInt(players[1]);
			}
		}
		footerText.append(maxPlayers);
		footer.setText(footerText.toString());
	}
    
    /**
     * Checks if the game which id is id matches to the criterion
     * "+" is or
     * "," is and
     * "--" is not
     * Those marks are checked in the opposite order, first "--" and lastly "+"
     * @param id id
     * @param criterion criterion
     * @return true if matches, false if doesn't
     */
    public boolean isAMatch(int id, String criterion) {
    	boolean returnBool = false;
    	String[] criterionsOr = criterion.split("\\+");
    	for(int j = 0; j < criterionsOr.length; j++){
    		boolean smallReturnBool = true;
	    	String[] criterions = criterionsOr[j].split(",");
	    	for(int i = 0; i < criterions.length; i++){
	    		if(
		    		!collection.getGame(id).getLeftColumnText().toUpperCase().contains(criterions[i].toUpperCase().trim()) &&
		 			!collection.getGame(id).getMiddleColumnText().toUpperCase().contains(criterions[i].toUpperCase().trim()) &&
		 			!getRightColumnText(id).toUpperCase().contains(criterions[i].toUpperCase().trim())
	 			){
	    			if(criterions[i].trim().length() > 1) if(!criterions[i].trim().substring(0,2).equals("--")) smallReturnBool = false;
	    		}
	    		if(criterions[i].trim().length() > 1) if(criterions[i].trim().substring(0,2).equals("--")){
		    		if(
			    		collection.getGame(id).getLeftColumnText().toUpperCase().contains(criterions[i].toUpperCase().trim().substring(2)) ||
			 			collection.getGame(id).getMiddleColumnText().toUpperCase().contains(criterions[i].toUpperCase().trim().substring(2)) ||
			 			getRightColumnText(id).toUpperCase().contains(criterions[i].toUpperCase().trim().substring(2))
		 			) smallReturnBool = false;
	    		}
	    	}
	    	if(smallReturnBool) returnBool = true;
    	}
		return returnBool;
	}
}
