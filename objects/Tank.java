package ce1002.finalproject.s107502019.objects;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

abstract class Tank {
	public boolean alive = false;
	public int curx, cury, curd;
	public boolean enemy;	
	Tank(){
		
	}
}