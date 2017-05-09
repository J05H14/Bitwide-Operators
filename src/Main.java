

	
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
		
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			PPMSub ppm = new PPMSub(PPMImage);
			
			Image image = SwingFXUtils.toFXImage(ImageIO.read(PPMImage), null);
			ImageView iv = new ImageView(image);
			
			HBox iBox = new HBox();
			HBox buttons = new HBox();
			
			iBox.getChildren().add(iv);
			
			Button hide = new Button("Hide Message");
			hide.setOnAction(e -> {
				ppm.hideMessage("hi");
			});
			Button sepia = new Button("Sepia");
			sepia.setOnAction(e -> {
				ppm.sepia();
			});
			Button gray = new Button("Grayscale");
			gray.setOnAction(e -> {
				ppm.grayscale();
			});
			Button negative = new Button("negative");
			negative.setOnAction(e -> {
				ppm.negative();
			});
			buttons.getChildren().add(sepia);
			buttons.getChildren().add(gray);
			buttons.getChildren().add(negative);
			buttons.getChildren().add(hide);
			
			
			root.setCenter(iBox);
			root.setBottom(buttons);
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
