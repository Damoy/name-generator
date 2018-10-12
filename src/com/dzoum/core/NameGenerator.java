package com.dzoum.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dzoum.event.ClickableBox;
import com.dzoum.event.ClickableBoxActionResultHandler;
import com.dzoum.event.ClickableContextModifierLauncherBox;
import com.dzoum.event.ClickableGenerationLaunchBox;
import com.dzoum.event.User;
import com.dzoum.rendering.Screen;
import com.dzoum.utils.Config;
import com.dzoum.utils.Utils;
import com.dzoum.utils.Validator;

public class NameGenerator {

	private final static String VOWELS = "aeiouy";
	private final static String CONSONANTS = "bcdfghjklmpqrstvwxz";
	private final static StringBuilder sb = new StringBuilder();
	private static Random seed = new Random();
	
	private Config config;
	private NameGenerationCore core;
	private ClickableBoxActionResultHandler resultHandler;
	private NameGenerationContext context;
	private NameGeneratorSetupMode setupMode;
	private List<ClickableBox> clickableBoxes;
	
	public NameGenerator(Config config, NameGenerationCore core) {
		this.config = config;
		this.core = core;
		build();
	}
	
	private void build() {
		this.context = new NameGenerationContext(generateInitialStrategy(), 5);
		this.clickableBoxes = new ArrayList<>();
		this.resultHandler = new ClickableBoxActionResultHandler(config);
		buildGenerationBox();
		buildContextModifierLauncherBox();
	}
	
	private NameGenerationStrategy generateInitialStrategy() {
		return randomize(5);
	}
	
	public void toSetupMode() {
		setupMode = new NameGeneratorSetupMode(this, context, config, core.getUser()).build();
		core.toSetupMode(setupMode);
	}
	
	private void buildGenerationBox() {
		int cw = config.getScreenWidth();
		int ch = config.getScreenHeight();
		
		int width = (int) (cw * 0.60);
		int height = (int) (ch * 0.26f);
		
		int x = (int) (cw * 0.40f);
		int y = (int) (ch * 0.0f);
		
		clickableBoxes.add(new ClickableGenerationLaunchBox(this, "Launch", x, y, width, height));
	}
	
	private void buildContextModifierLauncherBox() {
		int cw = config.getScreenWidth();
		int ch = config.getScreenHeight();
		
		int width = (int) (cw * 0.40);
		int height = (int) (ch * 0.26f);
		
		int x = (int) (cw * 0.0f);
		int y = (int) (ch * 0.0f);
		
		clickableBoxes.add(new ClickableContextModifierLauncherBox(this, "Setup", x, y, width, height));
	}
	
	// No box overlay
	public ClickableBox getBoxAt(int x, int y) {
		for(ClickableBox box : clickableBoxes) {
			if(box.collides(x, y, 2, 2)) {
				return box;
			}
		}
		
		return null;
	}
	
	public void update() {
		if(setupMode == null) {
			clickableBoxes.forEach(cb -> cb.update());
			resultHandler.update();
		} else {
			setupMode.update();
		}
	}
	
	public void exitFromSetupMode() {
		core.tolaunchMode(setupMode);
		setupMode = null;
	}
	
	public void render(Screen s) {
		if(setupMode == null) {
			clickableBoxes.forEach(cb -> cb.render(s));
			resultHandler.render(s);
		}
	}
	
	public Name generateName(NameGenerationStrategy strategy, int size) {
		Validator.validate(size >= 1 && size <= 64, "Name generation: name size inquired inadequate.");

		return customGeneration(context.getStrategy());
	}
	
	private Name customGeneration(NameGenerationStrategy strategy) {
		int[] strategyHints = strategy.getStrategyHints();
		
		sb.append(getCharSource(strategyHints[0]).toUpperCase().charAt(Utils.randomInt(seed, 0, getCharSource(strategyHints[0]).length() - 1)));
		
		for(int i = 1; i < strategyHints.length; ++i) {
			sb.append(getCharSource(strategyHints[i]).charAt(Utils.randomInt(seed, 0, getCharSource(strategyHints[i]).length() - 1)));
		}
		
		String name = sb.toString();
		sb.setLength(0);
		return new Name(name);
	}
	
	private String getCharSource(int nameGenerationStrategyHint) {
		switch (nameGenerationStrategyHint) {
		case NameGenerationStrategy.VOWEL:
			return VOWELS;
		case NameGenerationStrategy.CONSONANT:
			return CONSONANTS;
		case NameGenerationStrategy.RANDOM_CHAR:
			return seed.nextBoolean() ? VOWELS : CONSONANTS;
		default:
			throw new IllegalStateException("Could not identify name generation strategy composition.");
		}
	}
	
	public NameGenerationStrategy randomize(int size) {
		Validator.validate(size >= 2, "Invalid generation size !");
		
		int[] strategyHints = new int[size];
		
		for(int i = 0; i < strategyHints.length; ++i) {
			strategyHints[i] = seed.nextBoolean() ? NameGenerationStrategy.VOWEL : NameGenerationStrategy.CONSONANT;
		}
		
		NameGenerationStrategy strategy = new NameGenerationStrategy(strategyHints);
		
		Validator.validate(strategy != null, "Null generation strategy !");
		return strategy;
	}
	
	public ClickableBoxActionResultHandler getResultHandler() {
		return resultHandler;
	}
	
	public Config getConfig() {
		return config;
	}
	
	public NameGenerationContext getContext() {
		return context;
	}
	
	public NameGenerationCore getCore(){
		return core;
	}
	
	public User getUser() {
		return core.getUser();
	}
}
