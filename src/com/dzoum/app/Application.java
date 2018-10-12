package com.dzoum.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import com.dzoum.core.NameGenerationCore;
import com.dzoum.utils.Config;

public class Application {

	public static void main(String[] args){
		new NameGenerationCore(new Config(360, 120, 1,
				BufferedImage.TYPE_INT_RGB, new Font("Times New Roman", Font.BOLD, 26), Color.WHITE, "NameGenerator")).start();
	}
	
}
