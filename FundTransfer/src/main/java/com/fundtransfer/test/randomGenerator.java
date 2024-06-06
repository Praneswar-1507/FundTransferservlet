package com.fundtransfer.test;

import java.util.Random;

public class randomGenerator {
	
	public static String generateRandomAccountNumber() {
	    Random random = new Random();
	    String firstEightDigits = "12345678";
	    StringBuilder accountNumber = new StringBuilder(firstEightDigits);
	    
	    for (int i = 0; i < 4; i++) {
	        int randomDigit = random.nextInt(10);
	        accountNumber.append(randomDigit);
	    }
	    
	    return accountNumber.toString();
	}

	    public static String generateRandomIFSC() {
	        StringBuilder ifsc = new StringBuilder();
	        Random random = new Random();
	        String pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        
	        for (int i = 0; i < 4; i++) {
	            int randomIndex = random.nextInt(pattern.length());
	            char randomChar = pattern.charAt(randomIndex);
	            ifsc.append(randomChar);
	        }
	        ifsc.append('0'); // Append '0' as a character
	           
	        for (int i = 0; i < 6; i++) {
	            int randomDigit = random.nextInt(10);
	            ifsc.append(randomDigit);
	        }
	        
	        return ifsc.toString();
	    }


	   
	}



