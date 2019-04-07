package com.javapapers.java;
import java.io.IOException;
import java.lang.*;

import javax.imageio.ImageIO;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRcodeStegoGenerator {

	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException {

		System.out.println("Enter the public information  :   ");
		Scanner terminalInput = new Scanner(System.in);
		String qrCodeData = terminalInput.nextLine();

		String filePath = "C://Users//sahll//Dropbox//workspace//QRcodeSteganography//NormalQR.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		createQRCode(qrCodeData, filePath, charset, hintMap, 25 , 25);
		System.out.println("QR Code image created successfully! chec your desktop the name of the public QR symbole is  NormaleQR");
		System.out.println("enter the secret information");
		String HiddenData =terminalInput.nextLine();
		embedIn(filePath, charset, hintMap, HiddenData);
		System.out.println("QR Code modifiation done! chec your desktop the name of the modified QR symbole is:  StegoQR ");

	}
// creat a QR Code
	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

//	public static String readQRCode(String filePath, String charset, Map hintMap)
//			throws FileNotFoundException, IOException, NotFoundException {
//		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
//				new BufferedImageLuminanceSource(
//						ImageIO.read(new FileInputStream(filePath)))));
//		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
//		return qrCodeResult.getText();
//	}

	//insert hidden information withing the creayed QR code without impacting it's usual fonctions
	public static Void embedIn(String filePath, String charset, Map hintMap, String hiddentext)
			throws FileNotFoundException, IOException, NotFoundException, WriterException, IOException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource( ImageIO.read(new FileInputStream(filePath)))));

		BufferedImage img =null;
		img =ImageIO.read(new FileInputStream(filePath));

		byte[][] pixels = new byte[img.getWidth()][img.getWidth()];
		for (int x = 0; x < img.getWidth(); x++) {

		    for (int y = 0; y < img.getHeight(); y++) {
		        pixels[x][y] = (byte) (img.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
		//	        System.out.print(pixels[x][y]+ "  ");
		    }
		//    System.out.println();
		}
//		System.out.print(img.getWidth());

		for (int v =0;v<40;v++){
			if (img.getWidth()== 29+ v*4){
		char[] HiddenChar = hiddentext.toCharArray();
		int i = 0;
		int j = 9;
		int l = 13;
		String kk="";
		String BinaryString="";
		for (int k = 0; k < HiddenChar.length; k++) {

	        kk = Integer.toBinaryString(HiddenChar[k]);

	        if ( kk.toCharArray().length <7)
	        	kk="0"+ kk;
	        BinaryString += kk;
	    }
		BinaryString+="1011110";
		char[] HiddenBinary = BinaryString.toCharArray();
//		System.out.println(BinaryString);
		while ( i < HiddenBinary.length ){
			if (HiddenBinary[i]== '0')
			img.setRGB(j, l,0xFFFFFFFF  );
			else if (HiddenBinary[i]== '1')
				img.setRGB(j, l, 25555  );
			j++;i++;
			if (j==23+ v*4){
				j=7;l++;
			}
		}
		}
		}
//		System.out.println(img.getWidth());
			File outputfile = new File("C://Users//sahll//Dropbox//workspace//QRcodeSteganography//StegoQR.png");
		 ImageIO.write(img, "png", outputfile);


		return null;
	}
}
