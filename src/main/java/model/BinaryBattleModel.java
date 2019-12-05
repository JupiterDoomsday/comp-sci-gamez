package model;

import java.util.Random;

public class BinaryBattleModel {
	
	public int currentByteNum = 0;
	public int score = 0;
	public int currentNibbleNum = 0;
	
	public String generateRandomByte() {
		
		Random rand = new Random();
		
		int val = rand.nextInt(128);
		currentByteNum = val;
		
		String bString = Integer.toBinaryString(val);
		while(bString.length() < 8) {
			bString = "0" + bString;
		}
		
		
		
		return "\n" + bString.substring(0, 4) + " " + bString.substring(4);
	}
	
	public String generateRandomNibble() {
		
		Random rand = new Random();
		
		int val = rand.nextInt(16);
		currentNibbleNum = val;
		
		String nString = Integer.toBinaryString(val);
		while(nString.length() < 4) {
			nString = "0" + nString;
		}
		
		return "\n" + nString;
	}

}
