package com.dzoum.utils;

import java.awt.Color;
import java.awt.Font;

public class Config {
	private int screenWidth;
	private int screenHeight;
	private int renderingScale;
	private int imageRenderingType;
	
	private String appTitle;
	private Font renderingFont;
	private Color renderingColor;

	public Config(int screenWidth, int screenHeight, int renderingScale, int imageRenderingType,
			Font renderingFont, Color renderingColor, String appTitle) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.renderingScale = renderingScale;
		this.imageRenderingType = imageRenderingType;
		this.renderingFont = renderingFont;
		this.renderingColor = renderingColor;
		this.appTitle = appTitle;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getRenderingScale() {
		return renderingScale;
	}

	public void setRenderingScale(int renderingScale) {
		this.renderingScale = renderingScale;
	}

	public int getImageRenderingType() {
		return imageRenderingType;
	}

	public void setImageRenderingType(int imageRenderingType) {
		this.imageRenderingType = imageRenderingType;
	}
	
	public Font getRenderingFont() {
		return renderingFont;
	}

	public void setRenderingFont(Font renderingFont) {
		this.renderingFont = renderingFont;
	}
	
	public Color getRenderingColor() {
		return renderingColor;
	}

	public void setRenderingColor(Color renderingColor) {
		this.renderingColor = renderingColor;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

}
