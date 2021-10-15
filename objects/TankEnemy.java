package ce1002.finalproject.s107502019.objects;

import ce1002.finalproject.s107502019.controllers.Level1Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TankEnemy extends Tank{
	private static ImageView TankEnemyImage;
	private static int Level;
	private static int[][] wall;
	public static int total;
	public static Level1Controller Lv1ctrl;
	public Bullet TankEnemyBullet;
	public Timeline enemymove;
	private int step;
	
	public Pane newTankEnemy;// = new Pane();	
	public int TankEnemyID;
	
	public TankEnemy(Level1Controller L1C) {//TankEnemy Constructor
		Lv1ctrl = L1C;//設定以方便控制Level1Controller
		Level = 1;
	}
	public TankEnemy(int[][] wall,ImageView tankenemy,int n) {
		TankEnemy.wall = wall;
		TankEnemyImage = tankenemy;
		total = n;
	}
	public TankEnemy() {
		TankEnemyBullet = new Bullet(true);//each TankEnemy object have its own control Bullet
		TankEnemyBullet.move();//開啟發射Bullet的Time line
		enemy = true;
	}	
	public void drawTankEnemy(int ex,int ey,int Dir,int ID) {
		//initialize
		TankEnemyID = ID;
		curx = ex;
		cury = ey;
		curd = Dir;
		alive = true;
		//create a new Tank Enemy image
		ImageView newTankEnemyImage = new ImageView(TankEnemyImage.getImage());
		newTankEnemyImage.setFitHeight(50);
		newTankEnemyImage.setFitWidth(50);
		//create a new Tank Enemy pane
		newTankEnemy = new Pane(newTankEnemyImage);
		newTankEnemy.prefWidth(50);
		newTankEnemy.prefHeight(50);
		//set Tank Enemy direction
		newTankEnemy.setRotate(Dir*90);
		//put the TankEnemy on the wanted position
		GridPane.setColumnIndex(newTankEnemy, ex);
		GridPane.setRowIndex(newTankEnemy, ey);
		switch (Level) {
		case 1:
			Lv1ctrl._maze.getChildren().add(newTankEnemy);
			break;
		}
		easymove(700,this.newTankEnemy);
	}
	public void easymove(int speed,Pane etp) {		
		step = 4;
		//Time line
		enemymove = new Timeline(new KeyFrame(Duration.millis(speed),(anything)-> {
			if(!alive) {
				enemydead();
			}
			int ox = curx, oy = cury;
			switch(curd) {
			case 0:
				//角度非向上時，先調整方向為上
				if(etp.getRotate()!=0) etp.setRotate(0);
    			else if(step==0) {
    					curd = 2;
    					step = 4;
    			}
    			else if(step%4==1) {
    				TankEnemyBullet.shoot(curd*90,curx,cury);
    				step--;
    			}
    			else {
    				cury -= 1;
    				step--;
    			}
				break;
			case 1:
				if(etp.getRotate()!=90) etp.setRotate(90);
				else if(step==0) {
    					curd = 3;
    					step = 4;
    			}
				else if(step%4==1) {
					TankEnemyBullet.shoot(curd*90,curx,cury);
					step--;
				}
				else {
					curx += 1;
    				step--;
    			}
				break;
			case 2:
				if(etp.getRotate()!=180) etp.setRotate(180);
				else if(step==0) {
    				curd = 0;
    				step = 4;
    			}
				else if(step%4==1) {
					TankEnemyBullet.shoot(curd*90,curx,cury);
					step--;
				}
				else {
					cury += 1;
    				step--;
    			}
				break;
			case 3:
				if(etp.getRotate()!=270) etp.setRotate(270);
				else if(step==0) {
    				curd = 1;
    				step = 4;
    			}
				else if(step%4==1) {
					TankEnemyBullet.shoot(curd*90,curx,cury);
					step--;
				}
				else {
					curx -= 1;
    				step--;
    			}
				break;
			}
			for(int i=0;i<wall.length;i++) {//判斷是否撞牆
	    		if(curx==wall[i][0]&&cury==wall[i][1]) {
	    			curx = ox;
	    			cury = oy;
	    		}
	    	}
			curx = Math.max(Math.min(curx, 10), 0);
			cury = Math.max(Math.min(cury, 10), 0);
			GridPane.setColumnIndex(etp, curx);
			GridPane.setRowIndex(etp, cury);
		      	
		    }));
		  	enemymove.setCycleCount(Timeline.INDEFINITE);
		  	enemymove.play();
		  	
	}
	public void enemydead() {
		enemymove.stop();
	}
	
}
