package MIDIBlock;
import javax.swing.*;

public class MIDIBlockApp {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				MIDIBlockGUI MIDIBlock = new MIDIBlockGUI();
				MIDIBlock.setVisible(true);
				MIDIBlock.repaint();
				//MIDIPlaySound soundtest = new MIDIPlaySound();
				//soundtest.playSound("500Hz.wav");
				
			}
		});
	}

}
