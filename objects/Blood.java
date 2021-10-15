package ce1002.finalproject.s107502019.objects;

import ce1002.finalproject.s107502019.controllers.Level1Controller;
import javafx.scene.control.Label;

public class Blood {
	private static int Level;
	private int blood;
	public static Level1Controller Lv1ctrl;
	
	public Blood(Level1Controller L1C) {
		Lv1ctrl = L1C;//設定以方便控制Level1Controller
		Level = 1;
	}
	public Blood(Label blood) {
		blood.setText("X 3");
	}
	public Blood(int blood) {
		this.blood = blood;
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
