import java.io.File;

public class PPMSub extends PPMImage {

	public PPMSub(File arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public void hideMessage(String message){
		for(int i = 1; i < message.length(); i++){
			int colorCount = 0;
			char letter = message.charAt(i);
			
			for(int bit = 0; bit < 9; bit++){
				char mask = (char) (1 << (bit - 1));

				if((letter & mask) == 0){
					mask = (char) ~1;
					
					getPixelData()[colorCount] = (char) (getPixelData()[colorCount] & mask);
					
				}
				else{
					mask = 1;
					
					getPixelData()[colorCount] = (char) (getPixelData()[colorCount] & mask);
				}
				colorCount++;
			}
		}
		
		writeImage("PPM_with_message.ppm");
	}
	public String recoverMesssage(){
		String message = "";
		
//		for(int ){
//			
//		}
		
		return message;
	}
	public void sepia(){

	}
	public void grayscale(){

	}
	public void negative(){
		for(int i = 0; i < getPixelData().length; i++){
			getPixelData()[i] = (char) (255 - getPixelData()[i]);
		}
		writeImage("Negative_PPM.ppm");
	}

}
