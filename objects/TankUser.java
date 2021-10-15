package ce1002.finalproject.s107502019.objects;

import ce1002.finalproject.s107502019.controllers.Level1Controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TankUser extends Tank{
	private static ImageView TankUserImage;
	private static int Level;
	private static int[][] wall;
	
	public static Level1Controller Lv1ctrl;
	
	private Bullet TankUserBullet;
	private Blood TankUserBlood;

	public Pane newTankUser;
	private int blood;
	
	public TankUser(Level1Controller L1C) {//Tank Constructor
		Lv1ctrl = L1C;//設定以方便控制Level1Controller
		Level = 1;
	}
	public TankUser(int[][] wall,ImageView tankuser) {
		TankUser.wall = wall;
		TankUserImage = tankuser;
	}
	public TankUser(int blood) {
		TankUserBullet = new Bullet(false);//each TankUser object have its own control Bullet
		TankUserBullet.move();//開啟發射Bullet的Time line
		enemy = false;
		this.blood = blood;
		TankUserBlood = new Blood(3);
	}
	
	public void drawTankUser(int ex,int ey,int Dir) {
		//initialize
		curx = ex;
		cury = ey;
		curd = Dir;
		alive = true;
		//create a new Tank User image
		ImageView newTankUserImage = new ImageView(TankUserImage.getImage());
		newTankUserImage.setFitHeight(50);
		newTankUserImage.setFitWidth(50);
		//create a new Tank User pane
		//Pane newUserEnemy = new Pane(newTankUserImage);
		newTankUser = new Pane(newTankUserImage);
		newTankUser.prefWidth(50);
		newTankUser.prefHeight(50);
		//set Tank User direction
		newTankUser.setRotate(Dir*90);
		//put the TankUser on the wanted position
		GridPane.setColumnIndex(newTankUser, ex);
		GridPane.setRowIndex(newTankUser, ey);
		switch (Level) {
		case 1:
			Lv1ctrl._maze.getChildren().add(newTankUser);
			break;
		}
	}
	public void move(int dir) {
		//Get Tank Position
		int ox = curx, oy = cury;
		//Compute Next Position
		switch(dir) {
		case 0:
			if(newTankUser.getRotate()!=0) {//角度非向上時，按上鍵則是調整方向為上
				newTankUser.setRotate(0);
			}
			else cury -= 1;
			break;
		case 1:
			if(newTankUser.getRotate()!=90) {
				newTankUser.setRotate(90);
			}
			else curx += 1;
			break;
		case 2:
			if(newTankUser.getRotate()!=180) {
				newTankUser.setRotate(180);
			}
			else cury += 1;
			break;
		case 3:
			if(newTankUser.getRotate()!=270) {
				newTankUser.setRotate(270);
			}
			else curx -= 1;
			break;
		}
		curx = Math.max(Math.min(curx, 10), 0);
    	cury = Math.max(Math.min(cury, 10), 0);
    	curd = (int) (newTankUser.getRotate() / 90);
    	for(int i=0;i<wall.length;i++) {//判斷是否撞牆
    		if(curx==wall[i][0]&&cury==wall[i][1]) {
    			curx = ox;
    			cury = oy;
    		}
    	}
    	GridPane.setColumnIndex(newTankUser, curx);
		GridPane.setRowIndex(newTankUser, cury);
		
	}
	public void shootbullet() {
		TankUserBullet.shoot(curd*90, curx, cury);
	}
	public void minusblood() {
		if(blood!=1) {
			blood--;
			
		}
		else{
			Lv1ctrl.died();
		}
	}
}