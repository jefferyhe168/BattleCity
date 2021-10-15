package ce1002.finalproject.s107502019.controllers;

import java.io.IOException;

import ce1002.finalproject.s107502019.finalproject;
import ce1002.finalproject.s107502019.objects.Blood;
import ce1002.finalproject.s107502019.objects.Bullet;
import ce1002.finalproject.s107502019.objects.TankEnemy;
import ce1002.finalproject.s107502019.objects.TankUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LevelSelectController {
	
	public void onElementaryPressed(ActionEvent e) throws IOException {//Button Event
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/Level1.fxml"));
		Parent Level1 = loadder.load();
		Scene Level1Scene = new Scene(Level1);
		
		Level1Controller Level1Ctrl = loadder.getController();//Register KeyEvent
		Level1Scene.setOnKeyPressed(Level1Ctrl.onKeyPressed);
		TankUser tankuserctrl = new TankUser (Level1Ctrl);//初始化TankUser的參數
		Bullet bulletctrl = new Bullet (Level1Ctrl);//初始化Bullet的參數
		TankEnemy tankenemyctrl = new TankEnemy (Level1Ctrl);//初始化TankEnemy的參數
		Blood bloodctrl = new Blood (Level1Ctrl);//初始化Blood的參數
		//create a Tank User image
		Level1Ctrl.MyTank.drawTankUser(0, 0, 0);
		//Change Scene
		finalproject.mainStage.setScene(Level1Scene);
		finalproject.mainStage.setTitle("坦克大戰-初級");//frame name		
	}
	public void onIntermediatePressed(ActionEvent e) throws IOException {//Button Event
		//FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/Level2.fxml"));
		//Parent Level2 = loadder.load();
		//Scene Level2Scene = new Scene(Level2);
		//Change Scene
		//finalproject.mainStage.setScene(Level2Scene);
	}
	public void onAdvancedPressed(ActionEvent e) throws IOException {//Button Event
		//FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/Level3.fxml"));
		//Parent Level3 = loadder.load();
		//Scene Level3Scene = new Scene(Level3);
		//Change Scene
		//finalproject.mainStage.setScene(Level3Scene);
	}
}
