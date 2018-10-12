package com.dzoum.utils;

public final class Validator {

	private Validator() {}
	
	public static void validate(boolean cond, String errorMsg) {
		if(!cond) throw new IllegalStateException(errorMsg);
	}
}
