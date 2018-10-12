package com.dzoum.event;

import com.dzoum.rendering.Screen;

public abstract class ClickableBoxActionResult {

	public ClickableBoxActionResult() {
		
	}
	
	public abstract void update();
	public abstract void render(Screen s);
	
}
