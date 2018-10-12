package com.dzoum.event;

import java.awt.Color;

import com.dzoum.core.NameGenerationContext;
import com.dzoum.core.NameGenerator;
import com.dzoum.rendering.Screen;
import com.dzoum.utils.Utils;

public class ClickableGenerationLaunchBox extends ClickableBox{

	private final static Color COLOR = new Color(24, 152, 100);
	
	public ClickableGenerationLaunchBox(NameGenerator generator, String title, int x, int y, int width, int height) {
		super(generator, title, x, y, width, height);
	}

	@Override
	public ClickableBoxActionResult act() {
		int cw = config.getScreenWidth();
		int ch = config.getScreenHeight();
		int x = (int) (cw * 0.0f);
		int y = (int) (ch * 0.26f);

		NameGenerationContext context = generator.getContext();
		
		Utils.println("Size: "+  context.getSize());
		
		return new ClickableGenerationLaunchBoxResult(config, context,
				generator.generateName(context.getStrategy(), context.getSize()).getContent(), x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Screen s) {
		s.renderRect(title, COLOR, x, y, width, height, 3.0f, true);
	}

}
