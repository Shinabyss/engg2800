package MIDIBlock;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputAdapter;

public class MIDIBlockController {
	
	private MIDIBlockGUI view;
	private boolean mousePressed;
	
	public MIDIBlockController (MIDIBlockGUI view) {
		this.view = view;
		/*view.addKey1Listener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed) {
	                       System.out.println("mouse press working");
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	        }

	    });
	}
	
	private class KeyActionListener extends MouseAdapter {
		private boolean mousePressed = true;
		private String soundFile;
		private MIDIPlaySound sound = new MIDIPlaySound();
		
		private KeyActionListener (String soundFile) {
			super();
			this.soundFile = soundFile;
			this.sound = new MIDIPlaySound();
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//mousePressed = true;
			System.out.println("pressed");
			sound.playSound("500Hz.wav");
            while (mousePressed) {
                sound.playSound("500Hz"); 
            }

            
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//mousePressed = false;
		}*/
		
	}
}
