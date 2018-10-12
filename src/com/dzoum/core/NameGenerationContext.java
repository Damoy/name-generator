package com.dzoum.core;

public class NameGenerationContext {

	private NameGenerationStrategy strategy;
	
	public NameGenerationContext(NameGenerationStrategy strategy, int size) {
		this.strategy = strategy;
	}
	
	public NameGenerationContext() {
		
	}

	public NameGenerationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(NameGenerationStrategy strategy) {
		this.strategy = strategy;
	}

	public int getSize() {
		return strategy.getStrategyHints().length;
	}

}
