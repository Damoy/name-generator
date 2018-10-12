package com.dzoum.event;

import com.dzoum.core.NameGenerator;
import com.dzoum.rendering.Screen;
import com.dzoum.utils.Config;

public abstract class ClickableBox {

	protected String title;
	protected NameGenerator generator;
	protected Config config;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public ClickableBox(NameGenerator generator, String title, int x, int y, int width, int height) {
		this.generator = generator;
		this.config = generator.getConfig();
		this.title = title;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract ClickableBoxActionResult act();
	public abstract void update();
	public abstract void render(Screen s);
	
	public boolean collides(ClickableBox box) {
		return collides(box.x, box.y, box.width, box.height);
	}
	
	public boolean collides(int x2, int y2, int w2, int h2) {
		return !((x > (x2 + w2)) ||
				((x + width) < x2) ||
				(y > (y2 + h2))
				|| ((y + height) < y2));
	}
}
