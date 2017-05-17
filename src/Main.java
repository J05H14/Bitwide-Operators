

	
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {	
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("application/application.css");
			
			
			Button importImage = new Button("Import Image");
			importImage.setOnAction(e -> {
				try {
					File image = imagePicker(false);
					showImage(SwingFXUtils.toFXImage(ImageIO.read(image), null), image);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Label title = new Label("Bitwise Image Manipulation");
			
			title.getStyleClass().add("title");
			
			root.setTop(title);
			root.setBottom(importImage);
			
			primaryStage.setTitle("Bitwise Operators");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public File imagePicker(boolean recover){
		File PPMImage = null;
		JFileChooser fc = null;
		if(recover){
			fc = new JFileChooser(".");
		}
		else{
			fc = new JFileChooser();
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PPM Files", "ppm");
		fc.setFileFilter(filter);
		int retVal = fc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION){
			PPMImage = fc.getSelectedFile();
		}
		return PPMImage;
	}
	
	public HBox buttons(File ppmImage){
		HBox buttons = new HBox();
		
		Button hide = new Button("Hide Message");
		hide.setOnAction(e -> {
			PPMSub ppm = new PPMSub(ppmImage);
			String message = JOptionPane.showInputDialog("What is Your Message?");
			ppm.hideMessage(message);
		});
		Button read = new Button("Recover Message");
		read.setOnAction(e -> {
			PPMSub ppm = new PPMSub(imagePicker(true));
			JOptionPane.showMessageDialog(null, ppm.recoverMesssage());
			
		});
		Button sepia = new Button("Sepia");
		sepia.setOnAction(e -> {
			PPMSub ppm = new PPMSub(ppmImage);
			ppm.sepia();
		});
		Button gray = new Button("Grayscale");
		gray.setOnAction(e -> {
			PPMSub ppm = new PPMSub(ppmImage);
			ppm.grayscale();
		});
		Button negative = new Button("Negative");
		negative.setOnAction(e -> {
			PPMSub ppm = new PPMSub(ppmImage);
			ppm.negative();
		});
		buttons.getChildren().add(sepia);
		buttons.getChildren().add(gray);
		buttons.getChildren().add(negative);
		buttons.getChildren().add(hide);
		buttons.getChildren().add(read);
		
		return buttons;
	}
	
	public void showImage(Image image, File imageFile){
		BorderPane bp = new BorderPane();
		HBox iBox = new HBox();
		Stage stage = new Stage();
		ImageView iv = new ImageView(image);
		iv.setPreserveRatio(true);
		iv.setFitHeight(500);
		Scene scene = new Scene(bp);
		
		iBox.getChildren().add(iv);
		
		HBox buttons = buttons(imageFile);
		buttons.getStyleClass().add("center");
		
		bp.setCenter(iBox);
		stage.setScene(scene);
		bp.setTop(buttons);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
