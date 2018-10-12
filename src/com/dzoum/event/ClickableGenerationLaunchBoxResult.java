package com.dzoum.event;

import java.awt.Color;

import com.dzoum.core.NameGenerationContext;
import com.dzoum.rendering.Screen;
import com.dzoum.utils.Config;

public class ClickableGenerationLaunchBoxResult extends ClickableBoxActionResult{

	private Config config;
	private NameGenerationContext context;
	private String content;
	private int x;
	private int y;
	
	public ClickableGenerationLaunchBoxResult(Config config, NameGenerationContext context, String content, int x, int y) {
		this.config = config;
		this.context = context;
		this.content = content;
		this.x = x;
		this.y = y;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Screen s) {
		int cw = config.getScreenWidth();
		int ch = config.getScreenHeight();
		
		int height = (int) (ch * 0.75f);
		
		s.renderRect(null, new Color(125, 20, 12), x, y, cw, height, 3.0f, true);
		int xoff = (s.g().getFontMetrics().stringWidth(content));
		
		int x = (cw >> 1) - xoff;
		int y = (int) (ch * 0.65f);
		int genSize = context.getSize();
		
		x -= (genSize * 0.1f);
		s.render(content, x, y);
	}
}
