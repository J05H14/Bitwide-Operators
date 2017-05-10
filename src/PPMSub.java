import java.io.File;

public class PPMSub extends PPMImage {

	public PPMSub(File arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public void hideMessage(String message){
		message += "\0";
		System.out.println(message);
		for(int i = 0; i < message.length(); i++){
			int colorCount = 0;
			char letter = message.charAt(i);
			
			for(int bit = 8; bit > 0; bit--){
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
		char currChar = '\0';
		int count = 0;
		boolean done = false;
		
		System.out.println((int) currChar);
		while(!done){
			for(int bit = 0; bit < 9; bit++){
				char lsb = (char) (getPixelData()[count] & 1);
				
				currChar += lsb;
				//System.out.println((int) currChar); 
				System.out.println((int) lsb);
				currChar = (char) (currChar << 1);
				count++;
			}
			if(currChar == '\0'){
				done = true;
			}
			System.out.println(currChar);
			message += currChar;
			currChar = '\0';
		}
		
		return message;
	}
	public void sepia(){
		for(int i = 0; i < getPixelData().length; i++){
			if(i % 3 == 0){
				getPixelData()[i] = (char) ((getPixelData()[i] * 0.393) + (getPixelData()[i + 1] * 0.769) + (getPixelData()[i + 2] * 0.189));
			}
			else if(i % 3 == 1){
				getPixelData()[i] = (char) ((getPixelData()[i - 1] * 0.349) + (getPixelData()[i] * 0.686) + (getPixelData()[i + 1] * 0.168));
			}
			else{
				getPixelData()[i] = (char) ((getPixelData()[i - 2] * 0.272) + (getPixelData()[i - 1] * 0.534) + (getPixelData()[i] * 0.131));
			}
			
			if(getPixelData()[i] > 255){
				getPixelData()[i] = 255;
			}
		}
		
		writeImage("Sepia_PPM.ppm");
	}
	public void grayscale(){
		for(int i = 0; i < getPixelData().length; i++){
			if(i % 3 == 0){
				getPixelData()[i] = (char) ((getPixelData()[i] * 0.299) + (getPixelData()[i + 1] * 0.587) + (getPixelData()[i + 2] * 0.114));
			}
			else if(i % 3 == 1){
				getPixelData()[i] = (char) ((getPixelData()[i - 1] * 0.299) + (getPixelData()[i] * 0.587) + (getPixelData()[i + 1] * 0.114));
			}
			else{
				getPixelData()[i] = (char) ((getPixelData()[i - 2] * 0.299) + (getPixelData()[i - 1] * 0.587) + (getPixelData()[i] * 0.114));
			}
		}
		
		writeImage("Grayscale_PPM.ppm");

	}
	public void negative(){
		for(int i = 0; i < getPixelData().length; i++){
			getPixelData()[i] = (char) (255 - getPixelData()[i]);
		}
		writeImage("Negative_PPM.ppm");
	}

}
