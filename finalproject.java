package ce1002.finalproject.s107502019;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class finalproject extends Application{

	public static Stage mainStage;
	public static Scene mainScene;
	public static void main(String[] args) {
		//Launch the application
        launch(args);
    }
	@Override
    public void start(Stage mainStage) throws IOException{
		//Start Deploy Your Stage
		finalproject.mainStage = mainStage;		
	    FXMLLoader loadder = new FXMLLoader(getClass().getResource("views/MainWindows.fxml"));
	    Parent main = loadder.load();
	    mainScene = new Scene(main);
        mainStage.setTitle("坦克大戰");//frame name
        mainStage.setScene(mainScene);
        mainStage.show();
    }

}
