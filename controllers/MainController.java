package ce1002.finalproject.s107502019.controllers;

import java.io.IOException;

import ce1002.finalproject.s107502019.finalproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController {
	public static Scene LevelSelectScene;
	public static Scene GameOverScene;

	public void onStartPressed(ActionEvent e) throws IOException {//Button Event
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/LevelSelect.fxml"));
		Parent LevelSelect = loadder.load();
		LevelSelectScene = new Scene(LevelSelect);
		//Change Scene
		finalproject.mainStage.setScene(LevelSelectScene);
		finalproject.mainStage.setTitle("坦克大戰-關卡選擇");//frame name
		
		FXMLLoader loadder1 = new FXMLLoader(getClass().getResource("../views/GameOver.fxml"));
		Parent GameOver = loadder1.load();
		GameOverScene = new Scene(GameOver);
	}
}
