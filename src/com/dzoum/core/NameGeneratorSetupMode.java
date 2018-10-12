package com.dzoum.core;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.dzoum.event.User;
import com.dzoum.utils.Config;

public class NameGeneratorSetupMode extends JPanel{

	private static final long serialVersionUID = 4651674729507881756L;
	
	private NameGenerator generator;
	private NameGenerationContext context;
	private Config config;
	private User user;
	
	private JLabel generationSizeLabel;
	private JList<Integer> generationSizeList;
	private JButton exitButton;
	private JButton randomizeButton;
	
	public NameGeneratorSetupMode(NameGenerator generator, NameGenerationContext context, Config config, User user) {
		super();
		this.generator = generator;
		this.context = context;
		this.config = config;
		this.user = user;
	}
	
	public NameGeneratorSetupMode build() {
		this.generationSizeLabel = new JLabel("Text length: ");
		this.exitButton = new JButton("Ok");
		this.randomizeButton = new JButton("Randomize");
		
		Integer[] selection = new Integer[] {
				2, 3, 4, 5, 6, 7, 8, 9,
		};
		
		this.generationSizeList = new JList<>(selection);
		
		this.generationSizeLabel.setFont(generationSizeLabel.getFont().deriveFont(20.0f));
		this.generationSizeList.setFont(generationSizeList.getFont().deriveFont(20.0f));
		this.generationSizeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.generationSizeList.setVisibleRowCount(1);
		
		this.exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					int size = generationSizeList.getSelectedValue();
					if(size < 2) size = 2;
					if(size > 9) size = 9;
					context.setStrategy(generator.randomize(size));
				} catch(Exception ex) {
					
				}
				
				generator.exitFromSetupMode();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		this.randomizeButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(context.getStrategy() != null || context.getStrategy().getStrategyHints() != null
						&& context.getStrategy().getStrategyHints().length >= 2)
				context.setStrategy(generator.randomize(context.getSize()));
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		this.add(generationSizeLabel);
		this.add(generationSizeList);
		this.add(exitButton);
		this.add(randomizeButton);
		
		this.setVisible(true);
		this.addMouseListener(user);
		this.setPreferredSize(new Dimension((config.getScreenWidth()) * config.getRenderingScale(),
				(config.getScreenHeight()) * config.getRenderingScale()));
		this.setFocusable(true);
		this.requestFocus();
		return this;
	}
	
	public void update() {
	}
	
}
