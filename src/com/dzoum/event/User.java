package com.dzoum.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.dzoum.core.NameGenerator;
import com.dzoum.rendering.Screen;
import com.dzoum.utils.Config;

public class User implements MouseListener{

	private int clickX;
	private int clickY;
	private Config config;
	private NameGenerator generator;
	
	public User(Config config, NameGenerator generator) {
		this.config = config;
		this.generator = generator;
		clickX = 0;
		clickY = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		clickX = e.getX() / config.getRenderingScale();
		clickY = e.getY() / config.getRenderingScale();
		
		ClickableBox box = generator.getBoxAt(clickX, clickY);
		if(box != null) {
			ClickableBoxActionResultHandler handler = generator.getResultHandler();
			ClickableBoxActionResult result = box.act();
			Class<? extends ClickableBoxActionResult> clazz = handler.getClassOf(result);
			if(result != null)
				handler.handle(clazz, result);
		}
	}
	
	public void update() {
		
	}
	
	public void render(Screen s) {
		
	}
	
	public int getClickX() {
		return clickX;
	}
	
	public int getClickY() {
		return clickY;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}
