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

public class QRcodeStegoReader {

	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException {

		System.out.println("Please enter the absolute path to the QR code symbole that u want decoded (instead of a slash use 2 anti-slashes) ");
		Scanner terminalInput = new Scanner(System.in); //C://Users//sahll//Dropbox//workspace//QRcodeSteganography//StegoQR.png


		String filePath = terminalInput.nextLine();
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);


		System.out.println("Data read from QR Code is:  \n" + readQRCode(filePath, charset, hintMap) +  " \n and the hidden Data in the said QRcode is : \n" + embedOut(filePath, charset, hintMap));

	}

//normal QR code reader will read the QR code without noticing that there is  hidden information.

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
		return qrCodeResult.getText();
	}

// how to extract the hidden information in a QR code that contains it.
	public static String embedOut(String filePath, String charset, Map hintMap)
			throws WriterException, IOException {
		int i = 0;
		int j = 9;
		int l = 13;
		int m = 0;
		int w=0;
		int z=0;
		String binary ="";
		String result = "";

		BufferedImage img =null;
		img =ImageIO.read(new FileInputStream(filePath));
		byte[][] pixels = new byte[img.getWidth()][img.getWidth()];
		for (int x = 0; x < img.getWidth(); x++) {
		    pixels[x] = new byte[img.getHeight()];

		    for (int y = 0; y < img.getHeight(); y++) {
		        pixels[x][y] = (byte) (img.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
		    }
		}
		int s=0;
		//System.out.println(img.getWidth());

// Future me (I know that when you read this code you will laugh)I am using the switch command instead of loop as a limitation and test passience and
//the attention to the detail while repeating the switch
		switch(img.getWidth()){

		case 29:
			{
				s=4;
					while ( i < (7*s) ){
						if (pixels[j][l]== (byte) 0 )
						binary += "0";
						else if (pixels[j][l]== (byte) 1 )
							binary += "1";

						j++;i++;
						if (j==23){
							j=7;l++;
						}
					}
					char[] Hidden = binary.toCharArray();
					char[] hidden = new char[s];
					for (int  k=0; k<(7*s); k += 7)
					{
						for (int  q=0; q<7; q++){
						if (Hidden[k+q]== '1')
							m+= Math.pow((double)2,(double)(6-q));

						}
						hidden[w]=(char)m;


						if (hidden[w]=='^')
							z=w;
						w++;
						m=0;
					}
					char[] chec = new char[z];
					for (int r=0; r<z;r++){
						chec[r]= hidden[r];}
					result= new String(chec);
					break;
			}

		case 33 :
			{
				s=8;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 4){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}


		case 37:
			{

				s=13;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 8){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 41 :
			{

				s=18;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 12){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 45 :
			{

				s=24;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 16){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 49 :
			{

				s=30;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 20){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 53:
			{
				s=33;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 24){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 57:
			{

				s=43;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 28){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 61:
			{

				s=50;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 32){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 65:
			{
				s=60;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 36){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 69:
			{

				s=70;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 40){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 73:
			{

				s=79;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 44){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 77:
			{

				s=90;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 48){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 81:
			{
				s=98;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 52){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);

				break;
			}

		case 85:
			{

				s=111;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 56){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 89:

			{
				s=126;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 60){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}


		case 93:
			{


				s=141;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 64){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;

			}

		case 97:
			{

				s=156;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 68){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 101:
			{

				s=170;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 72){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 105:
			{

				s=192;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 76){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 109:
			{


				s=203;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 80){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);

				break;
			}

		case 113:
			{

				s=221;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 84){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 117:
			{

				s=232;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 88){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 121:
			{

				s=257;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 92){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 125:
			{

				s=268;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 96){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 129:
			{

				s=298;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 100){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 133:
			{

				s=314;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 104){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 137:
			{

				s=330;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 108){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 141:
			{

				s=350;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 112){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 145:
			{

				s=372;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 116){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 149:
			{

				s=396;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 120){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 153:
			{

				s=422;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 124){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 157:
			{

				s=450;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 128){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 161:
			{

				s=480;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 132 ){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 165:
			{

				s=493;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 136){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}


		case 169:
			{

				s=527;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 140){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 173:
			{

				s=558;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 144){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 177:
			{

				s=571;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 148){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 181:
			{

				s=611;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 152){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
				break;
			}

		case 185:
			{

				s=638;
				while ( i < (7*s) ){
					if (pixels[j][l]== (byte) 0 )
					binary += "0";
					else if (pixels[j][l]== (byte) 1 )
						binary += "1";

					j++;i++;
					if (j==23+ 156){
						j=7;l++;
					}
				}
				char[] Hidden = binary.toCharArray();
				char[] hidden = new char[s];
				for (int  k=0; k<(7*s); k += 7)
				{
					for (int  q=0; q<7; q++){
					if (Hidden[k+q]== '1')
						m+= Math.pow((double)2,(double)(6-q));

					}
					hidden[w]=(char)m;


					if (hidden[w]=='^')
						z=w;
					w++;
					m=0;
				}
				char[] chec = new char[z];
				for (int r=0; r<z;r++){
					chec[r]= hidden[r];}
				result= new String(chec);
			break;
			}

			}

		return result ;
	}
}
