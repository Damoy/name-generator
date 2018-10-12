package com.dzoum.core;

import com.dzoum.utils.Validator;

public class NameGenerationStrategy {
	
	public final static int VOWEL = 0;
	public final static int CONSONANT = 1;
	public final static int RANDOM_CHAR = 2;
	
	private int[] strategyHints;
	
	public NameGenerationStrategy(int... strategyHints) {
		Validator.validate(strategyHints != null && strategyHints.length >= 2 , "Invalid generation strategy hints");
		this.strategyHints = strategyHints;
	}
	
	public int[] getStrategyHints() {
		if(strategyHints == null || strategyHints.length == 0)
			throw new IllegalStateException("Can't get strategy hints of predefined one.");
		return strategyHints;
	}
	
}