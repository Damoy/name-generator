package com.dzoum.rendering;

import java.awt.Component;

import javax.swing.JFrame;

import com.dzoum.core.NameGenerationCore;
import com.dzoum.utils.Config;

public class Window {

	private JFrame win;
	private NameGenerationCore core;
	private Config config;

	public Window(NameGenerationCore core, Config config) {
		this.win = new JFrame(config.getAppTitle());
		this.core = core;
		this.config = config;
	}
	
	public void add(Component component) {
		win.add(component);
		win.pack();
	}
	
	public void remove(Component component) {
		win.remove(component);
	}
	
	private void setDefaultState() {
		win.add(core);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setResizable(false);
		win.pack();
		win.setLocationRelativeTo(null);
	}

	public void start() {
		setDefaultState();
		win.setVisible(true);
	}

	public void setTitle(String newTitle) {
		win.setTitle(config.getAppTitle() + " | " + newTitle);
	}

}