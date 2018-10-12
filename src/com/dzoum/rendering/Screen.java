package com.dzoum.rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import com.dzoum.core.NameGenerationCore;
import com.dzoum.utils.Config;

public class Screen {

	private BufferedImage content;
	private Graphics2D g;
	
	private NameGenerationCore core;
	private Config config;
	
	private Color savedColor;

	public Screen(NameGenerationCore core, Config config) {
		this.core = core;
		this.config = config;
		this.content = new BufferedImage(config.getScreenWidth(), config.getScreenHeight(), config.getImageRenderingType());
		this.g = (Graphics2D) this.content.getGraphics();
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void saveColor() {
		savedColor = g.getColor();
	}
	
	public void setColor(Color color) {
		g.setColor(color);
	}
	
	public void restoreColor() {
		g.setColor(savedColor);
	}

	public void render() {
		Graphics g2 = core.getGraphics();
		g2.drawImage(content, 0, 0, config.getScreenWidth() * config.getRenderingScale(),
				config.getScreenHeight() * config.getRenderingScale(), null);
		g2.dispose();
	}

	public void render(BufferedImage image, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public void render(String text, int x, int y) {
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		
		g.setFont(config.getRenderingFont());
		g.setColor(config.getRenderingColor());
		
		g.drawString(text, x, y);
		
		g.setFont(oldFont);
		g.setColor(oldColor);;
	}
	
	public void render2(String text, int x, int y) {
		Font oldFont = g.getFont();
		g.setFont(config.getRenderingFont());
		g.drawString(text, x, y);
		g.setFont(oldFont);
	}
	
	public void renderRect(String title, Color rectColor, int x, int y, int width, int height, float thickness, boolean filled) {
		Color oldColor = g.getColor();
		g.setColor(rectColor);

		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(thickness));
		
		if(filled) {
			g.fillRect(x, y, width, height);
		} else {
			g.drawRect(x, y, width, height);
		}
		
		g.setStroke(oldStroke);
		
		if(title != null && !title.isEmpty()) {
			Font oldFont = g.getFont();
			g.setFont(config.getRenderingFont());
			g.setColor(config.getRenderingColor());
			
			int strWidth = g.getFontMetrics().stringWidth(title);
			g.drawString(title, x + (width >> 1) - (strWidth >> 1), y + (int) (height * 0.75f));
			g.setFont(oldFont);
		}
		
		g.setColor(oldColor);
	}

	public void colorize(Color color) {
		g.setColor(color);
		g.fillRect(0, 0, config.getScreenWidth(), config.getScreenHeight());
	}
	
	public Graphics2D g() {
		return g;
	}

}