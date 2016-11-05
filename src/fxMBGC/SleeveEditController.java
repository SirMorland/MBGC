package fxMBGC;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for window that edits a sleeve from the collection.
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class SleeveEditController {
	
	@FXML private VBox vBox;
	
    @FXML private Button errorClose;
    
    @FXML private TextField name;
    @FXML private TextField pcPerSet;
    @FXML private TextField ePerSet;
    
    @FXML private MBGCController mbgcController;
    
    private int editNo;

    /**
	 * Set clicked textfield's style to "".
	 * Calls onOk() if enter was pressed
     * @param event event
     * @throws IOException
     */
    @FXML
	void onClick(KeyEvent event) throws IOException {
		((Node) event.getSource()).setStyle("");
		if(event.getCode() == KeyCode.ENTER) onOk();
	}

	/**
     * Pushes data from fields to sleeve in collection
     * If some field is in wrong format that field transforms to red and an error message is shown
	 */
    @FXML
    void onOk() {
    	if(
        		!name.getText().equals("") &&
        		pcPerSet.getText().matches("[0-9]+") &&
        		ePerSet.getText().matches("[0-9.,]+")
        ){
	    	mbgcController.getCollection().getSleeve(editNo).setName(name.getText());
	    	mbgcController.getCollection().getSleeve(editNo).setPcPerSet(Integer.parseInt(pcPerSet.getText()));
	    	mbgcController.getCollection().getSleeve(editNo).setEPerSet(Double.parseDouble(ePerSet.getText()));
	
			Stage stage = (Stage) errorClose.getScene().getWindow();
	    	mbgcController.addSleevesToSleeves();
			mbgcController.updateFooter();
			Main.setController(mbgcController);
			stage.close();
		}else{
			String errorText = "";
			if(name.getText().equals("")){errorText += "Name is missing\n"; name.setStyle("-fx-background-color:#FF0000");}
			if(pcPerSet.getText().equals("")){errorText += "Pc/set is missing\n"; pcPerSet.setStyle("-fx-background-color:#FF0000");}
			else if(!pcPerSet.getText().matches("[0-9]+")){errorText += "Pc/set-field can only have numbers\n"; pcPerSet.setStyle("-fx-background-color:#FF0000");}
			if(ePerSet.getText().equals("")){errorText += "Price/set is missing\n"; ePerSet.setStyle("-fx-background-color:#FF0000");}
			else if(!ePerSet.getText().matches("[0-9.,]+")){errorText += "Price/set-field can only have numbers\n"; ePerSet.setStyle("-fx-background-color:#FF0000");}
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText(errorText);

			alert.showAndWait();
		}
    }

    /**
     * Deletes the sleeve being edited from the collection
     * If some game uses the sleeve being edited it is not deleted and an warning is given to the user
     * @throws IOException
     */
    @FXML
    void onDelete() throws IOException {
    	boolean canBeDeleted = true;
    	for(int i = 0; i < mbgcController.getCollection().getSleeveAmounts().getCount(); i++){
    		if(mbgcController.getCollection().getSleeveAmount(i).getSleeveId() == editNo) canBeDeleted = false;
    	}
    	if(canBeDeleted){
	    	mbgcController.getCollection().removeSleeve(mbgcController.getCollection().getSleeve(editNo));
	    	
	    	Stage stage = (Stage) errorClose.getScene().getWindow();
	    	mbgcController.addSleevesToSleeves();
			mbgcController.updateFooter();
			Main.setController(mbgcController);
			stage.close();
		}else{
			String errorText = "Thease sleeves are in use! Remove thease sleeves from the game that uses thease before deleting!";
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Can't delete sleeves!");
			alert.setContentText(errorText);
	
			alert.showAndWait();
		}
    }
    
    /**
     * Closes the window
     */
    @FXML
    void onClose() {
		Stage stage = (Stage) errorClose.getScene().getWindow();
		
		stage.close();
    }
    
    /**
     * @param mbgcController MBGCCollection
     */
    public void setMBGCController(MBGCController mbgcController) {
		this.mbgcController = mbgcController;
	}
    
    /**
     * Sets the data of the sleeve which id is "i" to window's fields
     * @param i id
     */
    public void setEditValues(int i){
    	editNo = i;
    	name.setText(mbgcController.getCollection().getSleeve(i).getName());
    	pcPerSet.setText("" + mbgcController.getCollection().getSleeve(i).getPcPerSet());
    	ePerSet.setText("" + mbgcController.getCollection().getSleeve(i).getEPerSet());
    }
}
