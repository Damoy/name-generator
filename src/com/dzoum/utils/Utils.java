package com.dzoum.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Utils {

	private Utils() {}
	
	public static String readFile(String filePath) {
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			String line = br.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			
			br.close();
			return sb.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		throw new IllegalStateException("Error while reading " + filePath + " file.");
	}
	
	public static <T> void println(T object) {
		System.out.println(object);
	}
	
	public static int randomInt(Random seed, int min, int max) {
		return seed.nextInt((max - min) + 1) + min;
	}
	
	public static String resourcePath(String fileName) {
		return "./resources/generation/" + fileName;
	}
}
