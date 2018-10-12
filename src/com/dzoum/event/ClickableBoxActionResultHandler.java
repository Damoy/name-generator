package com.dzoum.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.dzoum.rendering.Screen;
import com.dzoum.utils.Config;

public class ClickableBoxActionResultHandler {

	@SuppressWarnings("unused")
	private Config config;
	private Map<Class<? extends ClickableBoxActionResult>, ClickableBoxActionResult> results;
	
	public ClickableBoxActionResultHandler(Config config) {
		this.config = config;
		results = new HashMap<>();
	}
	
	public boolean handle(Class<? extends ClickableBoxActionResult> clazz, ClickableBoxActionResult result) {
		if(results.containsKey(clazz)) {
			results.remove(clazz);
		}
		
		results.put(clazz, result);
		return true;
	}
	
	public void update() {
		Iterator<Entry<Class<? extends ClickableBoxActionResult>, ClickableBoxActionResult>> it = results.entrySet().iterator();
		
		while(it.hasNext()) {
			it.next().getValue().update();
		}
	}
	
	public void render(Screen s) {
		Iterator<Entry<Class<? extends ClickableBoxActionResult>, ClickableBoxActionResult>> it = results.entrySet().iterator();
		
		while(it.hasNext()) {
			it.next().getValue().render(s);
		}
	}
	
	public Class<? extends ClickableBoxActionResult> getClassOf(ClickableBoxActionResult result){
		if(result instanceof ClickableGenerationLaunchBoxResult) {
			return ClickableGenerationLaunchBoxResult.class;
		}
		
		return null;
	}
}
