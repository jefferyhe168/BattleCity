package ce1002.finalproject.s107502019.controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import ce1002.finalproject.s107502019.finalproject;
import ce1002.finalproject.s107502019.objects.Bullet;
import ce1002.finalproject.s107502019.objects.TankEnemy;
import ce1002.finalproject.s107502019.objects.TankUser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Level1Controller implements Initializable{
	@FXML
	ImageView _mytank;
	@FXML
	Pane _mytankpane;
	@FXML
	Label _blood;
	@FXML
	ImageView _tankenemy;
	@FXML
	Pane _tankenemypane;
	@FXML
	ImageView _bullet;
	@FXML
	Pane _bulletpane;
	@FXML
	Button _return;
	@FXML
	Label _timer;
	@FXML
	public GridPane _maze;
	
	int[][] wall = {{1,1},{1,2},{1,3},{1,5},{1,7},{1,8},{1,9},
			{2,5},{3,2},{3,3},{3,5},{3,7},{3,8},
			{5,1},{5,2},{5,3},{5,4},{5,6},{5,7},{5,8},{5,9},
			{8,5},{7,2},{7,3},{7,5},{7,7},{7,8},
			{9,1},{9,2},{9,3},{9,5},{9,7},{9,8},{9,9}
		};

	public TankUser MyTank;
	private Bullet MyTankBullet;
	public LinkedList<Pane> _bullets = new LinkedList<Pane>();

	private static int EnemyCounter = 10;
	public TankEnemy [] enemy = new TankEnemy[10];
	int[][] TankEnemyPos = { {9,10,3},{10,2,2},{0,8,0},{6,3,2},
			{4,0,3},{10,9,0},{4,8,0},{2,10,1},{2,4,3},{8,0,3}		
		};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Bullet bulletinitial = new Bullet(wall, _bullet);//初始化Bullet的參數
		MyTankBullet = new Bullet(false);
		MyTankBullet.move();//開啟發射Bullet的Time line
		TankEnemy tankenemyinitial = new TankEnemy(wall, _tankenemy, EnemyCounter);//初始化TankEnemy的參數
		TankUser tankuser = new TankUser(wall,_mytank);
		MyTank = new TankUser(3);		
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
		for(int i=0;i<10;i++) {
			enemy[i] = new TankEnemy();
		}
		//don't let key code to control the button
		_return.setFocusTraversable(false);
	}
	
	public EventHandler<KeyEvent> onKeyPressed = (e)->{//KeyEvent
    	if (e.getCode() == KeyCode.UP) { 
    		MyTank.move(0);
    	}
    	if (e.getCode() == KeyCode.DOWN) {
    		MyTank.move(2);
    	}
    	if (e.getCode() == KeyCode.LEFT) {
    		MyTank.move(3);
    	}
    	if (e.getCode() == KeyCode.RIGHT) {
    		MyTank.move(1);
    	}
    	//press "SAPCE" to shoot bullet
    	if(e.getCode() == KeyCode.SPACE) {  		
    		MyTank.shootbullet();		 
    	}
    	/*
    	if(e.getCode() == KeyCode.A) {  		
    		died();
    	}*/
	};
	public void onBackPressed(ActionEvent e) {
		finalproject.mainStage.setScene(MainController.LevelSelectScene);
		//stop
		MyTank = null;
		MyTankBullet.gamestop();
		MyTankBullet = null;
		for(int i=0;i<10;i++) {
			enemy[i].TankEnemyBullet.gamestop();
			if(enemy[i].enemymove!=null) {
				enemy[i].enemydead();
			}
		}
		enemy = null;
		timer.stop();
	}
	public void died() {
		finalproject.mainStage.setScene(MainController.GameOverScene);
		//stop
		MyTank = null;
		MyTankBullet.gamestop();
		MyTankBullet = null;
		for(int i=0;i<10;i++) {
			enemy[i].TankEnemyBullet.gamestop();
			if(enemy[i].enemymove!=null) {
				enemy[i].enemydead();
			}
		}
		enemy = null;
		timer.stop();
	}
	public void timesup() {
		timer.stop();
	}	
	//Timer part：
	String s = "30", m = "01";
	int sec = 30, min = 1;
	
	Timeline timer = new Timeline(
			new KeyFrame(Duration.seconds(1),(anything)-> {		
			    //part 1:timer
				//deal with carry of second
				if(sec == 0) {
			    	min -= 1;
			    	//set minute display
			    	if(min<10) {
			    		m = "0" + Integer.toString(min); 
				    }
			    	else {
			    		m = Integer.toString(min); 
				    }
			    	sec=60;
			    }
			    sec -= 1;
			    //set second display
		    	if(sec<10) {
		    		s = "0" + Integer.toString(sec); 
			    }
		    	else {
		    		s = Integer.toString(sec); 
			    }
			    //display the timer text
			    _timer.setText(m + ":" + s);
			    if(sec==0&&min==0) timesup();
			    
			    //part 2:createTankEnemy in regular time interval
			    if(/*min>2 &&*/ sec<30 && sec%6==0 && EnemyCounter!=0) {
			    	//System.out.println(enemy[0].testnumber);			    	
			    	enemy[10-EnemyCounter].drawTankEnemy(TankEnemyPos[10-EnemyCounter][0],
			    			TankEnemyPos[10-EnemyCounter][1], TankEnemyPos[10-EnemyCounter][2],10-EnemyCounter);
			    	EnemyCounter--;
			    }
			    else if(sec%10==0 && EnemyCounter!=0) {
			    	enemy[10-EnemyCounter].drawTankEnemy(TankEnemyPos[10-EnemyCounter][0],
			    			TankEnemyPos[10-EnemyCounter][1], TankEnemyPos[10-EnemyCounter][2],10-EnemyCounter);
			    	EnemyCounter--;
			    }
				}));
	
}
