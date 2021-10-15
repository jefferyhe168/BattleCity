package ce1002.finalproject.s107502019.controllers;

import ce1002.finalproject.s107502019.finalproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {
	@FXML
	Button _return;
	public void onBackPressed(ActionEvent e) {
		finalproject.mainStage.setScene(MainController.LevelSelectScene);
	}
}
