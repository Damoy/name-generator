package com.dzoum.event;

import java.awt.Color;

import com.dzoum.core.NameGenerator;
import com.dzoum.rendering.Screen;

public class ClickableContextModifierLauncherBox extends ClickableBox{

	public ClickableContextModifierLauncherBox(NameGenerator generator, String title, int x, int y, int width,
			int height) {
		super(generator, title, x, y, width, height);
	}

	@Override
	public ClickableBoxActionResult act() {
		generator.toSetupMode();
		return null;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Screen s) {
		s.renderRect(title, Color.LIGHT_GRAY, x, y, width, height, 3.0f, true);
	}

}
