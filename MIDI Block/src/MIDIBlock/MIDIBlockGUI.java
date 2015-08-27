package MIDIBlock;
import java.awt.Color;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import jm.music.data.*;
import jm.util.*;
import jm.JMC;

public class MIDIBlockGUI extends JFrame {
	
	private JButton KEY0, KEY1, KEY2, KEY3, KEY4, KEY5, KEY6, KEY7, KEY8, KEY9, KEY10, KEY11, KEY12, KEY13, KEY14;
	private Note NOTE0, NOTE1, NOTE2, NOTE3, NOTE4, NOTE5, NOTE6, NOTE7, NOTE8, NOTE9, NOTE10, NOTE11, NOTE12, NOTE13, NOTE14;
	private JPanel KEYPANEL;
	private JSlider VOLUME_CONTROL;
	private boolean mousePressed;
	private int volume = 63;
	private boolean mute = false;
	
	public MIDIBlockGUI () {
		setTitle("MIDI Block");
		setSize(800, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentpane = getContentPane();
		KEYPANEL = new JPanel();
		KEY0 = new JButton();
		KEY1 = new JButton();
		KEY2 = new JButton();
		KEY3 = new JButton();
		KEY4 = new JButton();
		KEY5 = new JButton();
		KEY6 = new JButton();
		KEY7 = new JButton();
		KEY8 = new JButton();
		KEY9 = new JButton();
		KEY10 = new JButton();
		KEY11 = new JButton();
		KEY12 = new JButton();
		KEY13 = new JButton();
		KEY14 = new JButton();
		KEY0.setBackground(Color.WHITE);
		KEY1.setBackground(Color.BLACK);
		KEY2.setBackground(Color.WHITE);
		KEY3.setBackground(Color.BLACK);
		KEY4.setBackground(Color.WHITE);
		KEY5.setBackground(Color.WHITE);
		KEY6.setBackground(Color.BLACK);
		KEY7.setBackground(Color.WHITE);
		KEY8.setBackground(Color.BLACK);
		KEY9.setBackground(Color.WHITE);
		KEY10.setBackground(Color.BLACK);
		KEY11.setBackground(Color.WHITE);
		KEY12.setBackground(Color.WHITE);
		KEY13.setBackground(Color.BLACK);
		KEY14.setBackground(Color.WHITE);
		NOTE0 = new Note();
		NOTE1 = new Note();
		NOTE2 = new Note();
		NOTE3 = new Note();
		NOTE4 = new Note();
		NOTE5 = new Note();
		NOTE6 = new Note();
		NOTE7 = new Note();
		NOTE8 = new Note();
		NOTE9 = new Note();
		NOTE10 = new Note();
		NOTE11 = new Note();
		NOTE12 = new Note();
		NOTE13 = new Note();
		NOTE14 = new Note();
		NOTE0.setPitch(JMC.C3);
		NOTE1.setPitch(JMC.CS3);
		NOTE2.setPitch(JMC.D3);
		NOTE3.setPitch(JMC.DS3);
		NOTE4.setPitch(JMC.E3);
		NOTE5.setPitch(JMC.F3);
		NOTE6.setPitch(JMC.FS3);
		NOTE7.setPitch(JMC.G3);
		NOTE8.setPitch(JMC.GS3);
		NOTE9.setPitch(JMC.A3);
		NOTE10.setPitch(JMC.AS3);
		NOTE11.setPitch(JMC.B3);
		NOTE12.setPitch(JMC.C4);
		NOTE13.setPitch(JMC.CS4);
		NOTE14.setPitch(JMC.D4);
		VOLUME_CONTROL = new JSlider(JSlider.VERTICAL, 0, 127, 63);
		KEYPANEL.setLayout(new GridLayout(1, 0, 0, 0));
		KEYPANEL.add(VOLUME_CONTROL);
		KEYPANEL.add(KEY0);
		KEYPANEL.add(KEY1);
		KEYPANEL.add(KEY2);
		KEYPANEL.add(KEY3);
		KEYPANEL.add(KEY4);
		KEYPANEL.add(KEY5);
		KEYPANEL.add(KEY6);
		KEYPANEL.add(KEY7);
		KEYPANEL.add(KEY8);
		KEYPANEL.add(KEY9);
		KEYPANEL.add(KEY10);
		KEYPANEL.add(KEY11);
		KEYPANEL.add(KEY12);
		KEYPANEL.add(KEY13);
		KEYPANEL.add(KEY14);
		contentpane.add(KEYPANEL);
		KEY0.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				NOTE0.setDynamic(volume);
            	Play.midi(NOTE0);
			}
		});
		KEY1.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE1.setDynamic(volume);
	                    	Play.midi(NOTE1);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY2.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE2.setDynamic(volume);
	                    	Play.midi(NOTE2);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY3.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE3.setDynamic(volume);
	                    	Play.midi(NOTE3);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY4.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE4.setDynamic(volume);
	                    	Play.midi(NOTE4);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY5.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE5.setDynamic(volume);
	                    	Play.midi(NOTE5);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY6.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE6.setDynamic(volume);
	                    	Play.midi(NOTE6);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		KEY7.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE7.setDynamic(volume);
	                    	Play.midi(NOTE7);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		KEY8.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE8.setDynamic(volume);
	                    	Play.midi(NOTE8);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY9.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE9.setDynamic(volume);
	                    	Play.midi(NOTE9);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY10.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE10.setDynamic(volume);
	                    	Play.midi(NOTE10);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY11.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE11.setDynamic(volume);
	                    	Play.midi(NOTE11);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY12.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE12.setDynamic(volume);
	                    	Play.midi(NOTE12);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY13.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE13.setDynamic(volume);
	                    	Play.midi(NOTE13);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		KEY14.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            mousePressed = true;
	            new Thread() {
	                public void run() {
	                    while (mousePressed && !mute) {
	                    	NOTE14.setDynamic(volume);
	                    	Play.midi(NOTE14);
	                    }
	                }

	            }.start();
	        }

	        public void mouseReleased(MouseEvent e) {
	            mousePressed = false;
	            Play.stopMidi();
	        }

	    });
		
		VOLUME_CONTROL.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					int value = source.getValue();
					volume = value;
					if (volume == 0) {
						mute = true;
					} else {
						mute = false;
					}
				}
				
			}
		});
		
	}
	/*public void addKey1Listener(MouseListener listener) {
		KEY1.addMouseListener(listener);
	}*/
}