package com.dzoum.rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.dzoum.utils.Colors;
import com.dzoum.utils.Config;

public class Texture {

	private int width;
	private int height;
	private BufferedImage data;
	
	private String filePath;
	private Config config;
	
	public Texture(Config config, int width, int height, BufferedImage data, String filePath) {
		this.config = config;
		this.width = width;
		this.height = height;
		this.data = data;
		this.filePath = filePath;
	}

	public static Texture getSubTexture(Config config, String filePath, int xp, int yp, int width, int height) {
		BufferedImage tex = new BufferedImage(width, height, config.getImageRenderingType());
		try {
			tex = ImageIO.read(new FileInputStream(filePath));
			tex = tex.getSubimage(xp, yp, width, height);

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int argb = tex.getRGB(x, y);
					// removes the MAGENTA color from the texture
					if (argb == Color.MAGENTA.getRGB()) {
						tex.setRGB(x, y, 0);
					}
				}
			}
			
			return new Texture(config, width, height, tex, filePath);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}
	
	public Texture scale(float wscale, float hscale) {
		if(wscale == 1.0f && hscale == wscale) return this;
		
		int nw = (int) (width * wscale);
		int nh = (int) (height * hscale);
		
		Image tmp = data.getScaledInstance(nw, nh, Image.SCALE_SMOOTH);
		BufferedImage scaled = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaled.createGraphics();
		
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		
		data = scaled;
		return this;
	}
	
	public Texture colorize(Color color, float brightness) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				data.setRGB(x, y, Colors.increase(data.getRGB(x, y), color, brightness));
			}
		}
		
		return this;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getFilePath() {
		return filePath;
	}

	public BufferedImage get() {
		return data;
	}
	
	public Config getConfig() {
		return config;
	}
	
}
