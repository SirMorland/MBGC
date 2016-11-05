package fxMBGC;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for error window
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class ErrorController {
    @FXML private Button errorClose;
	
    /**
     * Closes the window
     */
    @FXML
    void onClose() {
		Stage stage = (Stage) errorClose.getScene().getWindow();
		
		stage.close();
    }
}
