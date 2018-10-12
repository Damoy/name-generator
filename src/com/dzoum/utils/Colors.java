package com.dzoum.utils;

import java.awt.Color;

public class Colors {

	private Colors() {}
	
	public static int red(int rgb) {
		return (rgb & 0xFF0000) >> 16;
	}
	
	public static int green(int rgb) {
		return (rgb & 0xFF00) >> 8;
	}
	
	public static int blue(int rgb) {
		return (rgb & 0xFF);
	}
	
	public static ColorBundle extract(int rgb) {
		int r = red(rgb);
		int g = green(rgb);
		int b = blue(rgb);
		
		return new ColorBundle(r, g, b);
	}
	
	public static Color of(ColorBundle colorBundle) {
		return new Color(colorBundle.r(), colorBundle.g(), colorBundle.b());
	}
	
	public static int increase(int rgb, Color color, float brightness) {
		return extract(rgb).increase(color, brightness).rgb();
	}
	
	public static class ColorBundle{
		
		private int r;
		private int g;
		private int b;
		
		public ColorBundle(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		public ColorBundle increase(Color color, float brightness) {
			r += (int) (color.getRed() * brightness);
			g += (int) (color.getGreen() * brightness);
			b += (int) (color.getBlue() * brightness);
			computeBounds();
			return this;
		}
		
		public ColorBundle increase(int dr, int dg, int db, float brightness) {
			r += (int) (dr * brightness);
			g += (int) (dg * brightness);
			b += (int) (db * brightness);
			computeBounds();
			return this;
		}
		
		public ColorBundle decrease(int dr, int dg, int db, float brightness) {
			r -= (int) (dr * brightness);
			g -= (int) (dg * brightness);
			b -= (int) (db * brightness);
			computeBounds();
			return this;
		}
		
		public ColorBundle computeBounds() {
			if(r < 0) r = 0;
			if(g < 0) g = 0;
			if(b < 0) b = 0;
			if(r > 255) r = 255;
			if(g > 255) g = 255;
			if(b > 255) b = 255;
			
			return this;
		}
		
		public int rgb() {
			computeBounds();
			return new Color(r, g, b).getRGB();
		}

		public int r() {
			return r;
		}

		public int g() {
			return g;
		}

		public int b() {
			return b;
		}
	}
}
