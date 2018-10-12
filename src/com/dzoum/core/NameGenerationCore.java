package com.dzoum.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.dzoum.event.User;
import com.dzoum.rendering.Screen;
import com.dzoum.rendering.Window;
import com.dzoum.utils.Config;

public class NameGenerationCore extends JPanel implements Runnable {

	private static final long serialVersionUID = -4245832821424993732L;

	private Config config;
	private Window window;
	private Screen screen;
	private NameGenerator generator;
	private User user;

	private Thread thread;
	private boolean running;
	private boolean setupMode;

	public NameGenerationCore(Config config) {
		super();
		this.config = config;
		this.running = false;
		this.setupMode = false;
		
		this.generator = new NameGenerator(this.config, this);
		this.user = new User(this.config, this.generator);
		this.window = new Window(this, this.config);
		this.screen = new Screen(this, this.config);
		
		this.addMouseListener(user);
		this.setPreferredSize(new Dimension(config.getScreenWidth() * config.getRenderingScale(),
				config.getScreenHeight() * config.getRenderingScale()));
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		@SuppressWarnings("unused")
		int frames = 0;

		@SuppressWarnings("unused")
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (unprocessed >= 1) {
				ticks++;
				update();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void toSetupMode(Component component) {
		this.setupMode = true;
		this.setVisible(false);
		this.window.add(component);
	}
	
	public void tolaunchMode(Component component) {
		this.setupMode = false;
		this.setVisible(true);
		this.window.remove(component);
	}
	
	private void update() {
		generator.update();
	}

	private void render() {
		if(!setupMode) {
			screen.colorize(Color.WHITE);
			generator.render(screen);
			screen.render();
		}
	}

	public void start() {
		if (running) {
			return;
		}

		running = true;
		window.start();
	}

	public void stop() {
		if (!running) {
			return;
		}

		window = null;
		running = false;
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public User getUser() {
		return user;
	}
	
}
