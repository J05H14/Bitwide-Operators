

	
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		File PPMImage = null;		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PPM Files", "ppm");
		fc.setFileFilter(filter);
		int retVal = fc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION){
			PPMImage = fc.getSelectedFile();
		}	
		
		PPMSub ppm = new PPMSub(PPMImage);
		ppm.sepia();
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Image image = SwingFXUtils.toFXImage(ImageIO.read(PPMImage), null);
			ImageView iv = new ImageView(image);
			
			
			root.getChildren().add(iv);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
