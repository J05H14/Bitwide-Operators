import java.io.File;

public class PPMSub extends PPMImage {

	public PPMSub(File arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public void hideMessage(String message){
		message += "\0";

		int colorCount = 0;
		for(int i = 0; i < message.length(); i++){

			char letter = message.charAt(i);

			for(int bit = 8; bit > 0; bit--){
				char mask = (char) (1 << (bit - 1));

				if((letter & mask) == 0){
					mask = (char) ~1;

					getPixelData()[colorCount] = (char) (getPixelData()[colorCount] & mask);

				}
				else{
					mask = 1;

					getPixelData()[colorCount] = (char) (getPixelData()[colorCount]| mask);
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

		while(!done){
			for(int bit = 1; bit < 9; bit++){
				char lsb = (char) (getPixelData()[count] & 1);

				//currChar = lsb; //probably is whats messing me up
				if(lsb == 0){
					currChar = (char) (currChar & ~1);
				}
				else{
					currChar = (char) (currChar | lsb);
				}

				if(bit != 8){
					currChar = (char) (currChar << 1);
				}
				count++;
			}
			if(currChar == '\0'){
				done = true;
			}
			message += currChar;
			currChar = '\0';
		}
		return message;
	}
	public void sepia(){
		char rResult;
		char gResult;
		char bResult;
		char[] pixelData = getPixelData();
		
		for(int i = 0; i < getPixelData().length - 2; i+=3){
			int red = pixelData[i];
			int green = pixelData[i + 1];
			int blue = pixelData[i + 2];

			rResult = (char) ((red * 0.393) + (green * 0.769) + (blue * 0.189));
			gResult = (char) ((red * 0.349) + (green * 0.686) + (blue * 0.168));
			bResult = (char) ((red * 0.272) + (green * 0.534) + (blue * 0.131));


			if(rResult > 255){
				rResult = 255;
			}
			
			if(gResult > 255){
				gResult =  255;
			}
			
			if(bResult > 255){
				bResult = 255;
			}
			getPixelData()[i] = rResult;
			getPixelData()[i + 1] = gResult;
			getPixelData()[i + 2] = bResult;
			

		}

		writeImage("Sepia_PPM.ppm");
	}
	public void grayscale(){
		for(int i = 0; i < getPixelData().length - 2; i += 3){
			char red = getPixelData()[i];
			char green = getPixelData()[i + 1];
			char blue = getPixelData()[i + 2];
			
			getPixelData()[i] = (char) ((red * 0.299) + (green * 0.587) + (blue * 0.114));
			getPixelData()[i + 1] = (char) ((red * 0.299) + (green * 0.587) + (blue * 0.114));
			getPixelData()[i + 2] = (char) ((red * 0.299) + (green * 0.587) + (blue * 0.114));
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
