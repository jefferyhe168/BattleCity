package ce1002.finalproject.s107502019.objects;

import java.util.ArrayList;
import java.util.LinkedList;

import ce1002.finalproject.s107502019.controllers.Level1Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Bullet {
	private LinkedList<Pane> _bullets = new LinkedList<Pane>();
	private static int[][] wall;
	private static ImageView BulletImage;
	private static int Level;
	public static Level1Controller Lv1ctrl;
	private Timeline shootbullet;
	
	private boolean enemybullet = false;
	
	public Bullet(Level1Controller L1C) {//Bullet Constructor
		Lv1ctrl = L1C;//設定方便控制Level1Controller的物件
		Level = 1;
	}
	public Bullet(int[][] wall,ImageView bullet) {//Bullet Constructor
		Bullet.wall = wall;
		Bullet.BulletImage = bullet;		
	}
	public Bullet(boolean enemy) {
		enemybullet = enemy;
	}
	
	public void shoot(double dir,int tx,int ty) {		
		//draw a new bullet image
		ImageView newBulletImage = new ImageView(BulletImage.getImage());
		//ImageView newBulletImage = new ImageView("../resources/bullet.jpg");
		newBulletImage.setFitHeight(50);
		newBulletImage.setFitWidth(50);
		//create a new bullet pane
		Pane newBullet = new Pane(newBulletImage);
		newBullet.prefWidth(50);
		newBullet.prefHeight(50);
		//set bullet direction
		newBullet.setRotate(dir);
		//Get Tank Position and put the bullet
		GridPane.setColumnIndex(newBullet, tx);
		GridPane.setRowIndex(newBullet, ty);
		_bullets.push(newBullet);
		Lv1ctrl._maze.getChildren().add(newBullet);		
	}	
	public void move(){
		shootbullet = new Timeline(new KeyFrame(Duration.millis(300),(anything)-> {
			ArrayList<Pane> tBullets = new ArrayList<Pane>(_bullets);
		    for(var b : tBullets) {
		    	int b_x = GridPane.getColumnIndex(b);
		    	int b_y = GridPane.getRowIndex(b);
		    	double b_r = b.getRotate();
		    	//case1 :判斷是否撞牆
		    	for(int i=0;i<wall.length;i++) {
			    	if((b_r==180 && b_x==wall[i][0] && b_y+1==wall[i][1])
			    		|| (b_r==0 && b_x==wall[i][0] && b_y-1==wall[i][1])	
			    		|| (b_r==90 && b_x+1==wall[i][0] && b_y==wall[i][1])
			    		|| (b_r==270 && b_x-1==wall[i][0] && b_y==wall[i][1])) {
			    		_bullets.remove(b);
			    		Lv1ctrl._maze.getChildren().remove(b);
			    	}		    	
			    }		    	
		    	//case2：判斷是否超出邊界
		    	if(b_r==0 && b_y!=0) GridPane.setRowIndex(b, b_y -1);
				else if(b_r==180 && b_y!=10) GridPane.setRowIndex(b, b_y +1);
				else if(b_r==90 && b_x!=10) GridPane.setColumnIndex(b, b_x +1);
				else if(b_r==270 && b_x!=0) GridPane.setColumnIndex(b, b_x -1);
				else {
					//(b_r==0&&b_y==0)||b_x==0||b_y==10||b_y==0) 
					_bullets.remove(b);
					Lv1ctrl._maze.getChildren().remove(b);
			    }
		    	//case3：判斷我方子彈是否擊中敵方坦克
		    	for(int i=0;i<TankEnemy.total;i++) {
		    		if( hitTank(Lv1ctrl.enemy[i],b_x,b_y) ) {
		    			_bullets.remove(b);
						Lv1ctrl._maze.getChildren().remove(b);
						Lv1ctrl._maze.getChildren().remove(Lv1ctrl.enemy[i].newTankEnemy);						
		    		}
		    	}
		    	//case4：判斷敵方子彈是否擊中我方坦克
		    	if( hitTank(Lv1ctrl.MyTank,b_x,b_y) ) {
		    		_bullets.remove(b);
		    		Lv1ctrl._maze.getChildren().remove(b);
		    		Lv1ctrl.MyTank.alive = true;
		    		Lv1ctrl.MyTank.minusblood();
		    	}		      	
		    }}));
		  	shootbullet.setCycleCount(Timeline.INDEFINITE);
		  	shootbullet.play();
	}
	public boolean hitTank(Tank tank,int bx,int by) {
		if(!tank.alive) {//if TankEnemy is dead, ignore the hitTank event
			return false;
		}
		else if(tank.curx==bx && tank.cury==by && enemybullet != tank.enemy) {
			tank.alive = false;
			return true;
		}
		else {
			return false;
		}
	}
	public void gamestop() {
		shootbullet.stop();
	}
}
