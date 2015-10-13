package MIDIBlock;
import java.awt.Color;

import java.awt.Container;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import jm.music.data.*;
import jm.util.*;
import jm.JMC;

public class MIDIBlockGUI extends JFrame {
	
	private JButton SHIFTNOTEUP, SHIFTNOTEDOWN, SHIFTOCTAVEUP, SHIFTOCTAVEDOWN, LOADMIDI, PLAYMIDI, STOPMIDI, SAVEMIDI;
	private ArrayList<JButton> Keys = new ArrayList<JButton>(110);
	private ArrayList<Note> Notes = new ArrayList<Note>(84);
	private ArrayList<Note> inputNotes = new ArrayList<Note>();
	private ArrayList<Note> outputNotes = new ArrayList<Note>();
	private Container contentpane;
	private JSlider VOLUME_CONTROL;
	private JPanel KEYPANEL = new JPanel();
	private JPanel CONTROLPANEL = new JPanel();
	private boolean[] NoteStatus = {false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									false, false, false, false, false, false, false, false, false, false, false, false,
									};
	private int volume = 63;
	private int firstNote = 36;
	private boolean mute = false;
	private JFileChooser fc = new JFileChooser();
	private JFileChooser sc = new JFileChooser();
	private String filePath;
	private Score score = new Score();
	private Phrase phrase = new Phrase();
	private SpinnerNumberModel tempo;
	private boolean scoreLoaded = false;
	private Integer[] keyStroke = {KeyEvent.VK_A, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_E, KeyEvent.VK_D, KeyEvent.VK_F, 
			KeyEvent.VK_T, KeyEvent.VK_G, KeyEvent.VK_Y, KeyEvent.VK_H, KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_O, KeyEvent.VK_L};  
	private Integer[] pitch = {
			JMC.C1, JMC.CS1, JMC.D1, JMC.DS1, JMC.E1, JMC.F1, JMC.FS1, JMC.G1, JMC.GS1,JMC.A1, JMC.AS1, JMC.B1, 
			JMC.C2, JMC.CS2, JMC.D2, JMC.DS2, JMC.E2, JMC.F2, JMC.FS2, JMC.G2, JMC.GS2,JMC.A2, JMC.AS2, JMC.B2,  
			JMC.C3, JMC.CS3, JMC.D3, JMC.DS3, JMC.E3, JMC.F3, JMC.FS3, JMC.G3, JMC.GS3,JMC.A3, JMC.AS3, JMC.B3,  
			JMC.C4, JMC.CS4, JMC.D4, JMC.DS4, JMC.E4, JMC.F4, JMC.FS4, JMC.G4, JMC.GS4,JMC.A4, JMC.AS4, JMC.B4,  
			JMC.C5, JMC.CS5, JMC.D5, JMC.DS5, JMC.E5, JMC.F5, JMC.FS5, JMC.G5, JMC.GS5,JMC.A5, JMC.AS5, JMC.B5,  
			JMC.C6, JMC.CS6, JMC.D6, JMC.DS6, JMC.E6, JMC.F6, JMC.FS6, JMC.G6, JMC.GS6,JMC.A6, JMC.AS6, JMC.B6,  
			JMC.C7, JMC.CS7, JMC.D7, JMC.DS7, JMC.E7, JMC.F7, JMC.FS7, JMC.G7, JMC.GS7,JMC.A7, JMC.AS7, JMC.B7,
	};
	private Color[] keyColor = {Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.WHITE, 
			Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE};
	private KeyAdapter keyAction;
	
	private String[] keyStrokePressCap = {"pressed A", "pressed W", "pressed S", "pressed E", "pressed D", "pressed F", 
			"pressed T", "pressed G", "pressed Y", "pressed H", "pressed U", "pressed J", "pressed K", "pressed O", "pressed L"
	};

	private String[] keyStrokeReleaseCap = {"released A", "released W", "released S", "released E", "released D", "released F", 
			"released T", "released G", "released Y", "released H", "released U", "released J", "released K", "released O", "released L"
	};

	
	public MIDIBlockGUI () {
		
		setTitle("MIDI Block");
		setSize(975, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentpane = getContentPane();
		contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.PAGE_AXIS));
		setUpKeyListener();
		setUpKeys();
		setUpNotes();
		updateKeyPanel();
		bindKeyToButton();
		contentpane.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				int j=e.getKeyCode();
				System.out.println(j+"");
			}
		});
		addControlPanel(contentpane);
		addKeyPanel(contentpane);
	}
	
	
	public void addControlPanel (Container contentpane) {
		SHIFTNOTEUP = new JButton(">");
		SHIFTNOTEDOWN = new JButton("<");
		SHIFTOCTAVEUP = new JButton(">>");
		SHIFTOCTAVEDOWN = new JButton("<<");
		LOADMIDI = new JButton("Load Midi");
		PLAYMIDI = new JButton("Play Midi");
		STOPMIDI = new JButton("Stop Midi");
		SAVEMIDI = new JButton("Save as Midi");
		JLabel tempoLabel = new JLabel("Enter Tempo");
		tempo = new SpinnerNumberModel(100, 0, 500, 10);
		JSpinner tempoSpinner = new JSpinner(tempo);
		fc.addChoosableFileFilter(new MIDIFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		sc.addChoosableFileFilter(new MIDIFileFilter());
		sc.setAcceptAllFileFilterUsed(false);
		VOLUME_CONTROL = new JSlider(JSlider.HORIZONTAL, 0, 127, 63);
		if (scoreLoaded == false) {
			PLAYMIDI.setEnabled(false);
			SAVEMIDI.setEnabled(false);
		}
		CONTROLPANEL.add(VOLUME_CONTROL);
		CONTROLPANEL.setLayout(new FlowLayout());
		CONTROLPANEL.add(SHIFTOCTAVEDOWN);
		CONTROLPANEL.add(SHIFTNOTEDOWN);
		CONTROLPANEL.add(SHIFTNOTEUP);
		CONTROLPANEL.add(SHIFTOCTAVEUP);
		CONTROLPANEL.add(LOADMIDI);
		CONTROLPANEL.add(PLAYMIDI);
		CONTROLPANEL.add(STOPMIDI);
		CONTROLPANEL.add(SAVEMIDI);
		CONTROLPANEL.add(tempoLabel);
		CONTROLPANEL.add(tempoSpinner);
		LOADMIDI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LOADMIDI) {
					fc.setCurrentDirectory(new File("C:"));
		            int returnVal = fc.showOpenDialog(MIDIBlockGUI.this);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                filePath = fc.getSelectedFile().getAbsolutePath();
		                Read.midi(score, filePath);
		                scoreLoaded = true;
		    			PLAYMIDI.setEnabled(true);
		    			SAVEMIDI.setEnabled(true);
		    			tempoSpinner.setValue(score.getTempo());
		                System.out.println("got to here");
		                repaint();
		                //score.setTitle(fc.getSelectedFile().getName());
		            }
				}
			}	 
		});
		PLAYMIDI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				playScore();
			}
		});
		STOPMIDI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Play.stopMidi();
			}
		});
		SAVEMIDI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				score.setTempo(tempo.getNumber().doubleValue());
				sc.setCurrentDirectory(new File("C:"));
				int retrival = sc.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
			    	Write.midi(score, sc.getSelectedFile()+".mid");
			    }
			}
		});
		
		SHIFTNOTEUP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				firstNote++;
				if (firstNote == 95) {
					SHIFTNOTEUP.setEnabled(false);
					SHIFTOCTAVEUP.setEnabled(false);
				}
				SHIFTNOTEDOWN.setEnabled(true);
				SHIFTOCTAVEDOWN.setEnabled(true);
				System.out.println("button was pressed");
				System.out.println(firstNote);
				KEYPANEL.removeAll();
				removeKeyBind();
				updateKeyPanel();
				bindKeyToButton();
				revalidate();
				repaint();
			}
		});
		SHIFTNOTEDOWN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				firstNote--;
				if (firstNote == 0) {
					SHIFTNOTEDOWN.setEnabled(false);
					SHIFTOCTAVEDOWN.setEnabled(false);
				}
				SHIFTNOTEUP.setEnabled(true);
				SHIFTOCTAVEUP.setEnabled(true);
				System.out.println("button was pressed");
				System.out.println(firstNote);
				KEYPANEL.removeAll();
				removeKeyBind();
				updateKeyPanel();
				bindKeyToButton();
				revalidate();
				repaint();
			}
		});
		SHIFTOCTAVEUP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if ((firstNote+12)>95) {
					firstNote = 95;
					System.out.println("button was pressed");
					System.out.println(firstNote);
				}
				else {
					firstNote += 12;
					System.out.println("button was pressed");
					System.out.println(firstNote);
				}
				if (firstNote == 95) {
					SHIFTNOTEUP.setEnabled(false);
					SHIFTOCTAVEUP.setEnabled(false);
				}
				SHIFTNOTEDOWN.setEnabled(true);
				SHIFTOCTAVEDOWN.setEnabled(true);
				System.out.println("button was pressed");
				System.out.println(firstNote);
				KEYPANEL.removeAll();
				removeKeyBind();
				updateKeyPanel();
				bindKeyToButton();
				revalidate();
				repaint();
			}
		});
		SHIFTOCTAVEDOWN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (firstNote<12) {
					firstNote = 0;
					System.out.println("button was pressed" + 33%12 + 1);
					System.out.println(firstNote);
				}
				else {
					firstNote -= 12;
					System.out.println("button was pressed");
					System.out.println(firstNote);
				}
				if (firstNote == 0) {
					SHIFTNOTEDOWN.setEnabled(false);
					SHIFTOCTAVEDOWN.setEnabled(false);
				}
				SHIFTNOTEUP.setEnabled(true);
				SHIFTOCTAVEUP.setEnabled(true);
				System.out.println("button was pressed");
				System.out.println(firstNote);
				KEYPANEL.removeAll();
				removeKeyBind();
				updateKeyPanel();
				bindKeyToButton();
				revalidate();
				repaint();
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
		contentpane.add(CONTROLPANEL);
	}
	
	public void addKeyPanel (Container contentpane) {
		KEYPANEL.setLayout(new GridLayout(1, 0, 0, 0));
		contentpane.add(KEYPANEL);
		updateKeyPanel();
	}
	public void playScore() {
		score.setTempo(tempo.getNumber().doubleValue());
		Play.midi(score, false);
		System.out.println(score.getPart(0).getPhrase(0).getNote(0).toString());
	}
	public void setUpKeys () {
		for (int i=0; i<110; i++) {
			Keys.add(new JButton());
			if ((i%12)==0) {
				Keys.get(i).setText("C" + Integer.toString(i/12));
			}
			if ((i < 12) || (i > 95)) {
				Keys.get(i).setEnabled(false);
				Keys.get(i).setBackground(Color.GRAY);
			}
			else {
				Keys.get(i).setEnabled(true);
				Keys.get(i).setBackground(keyColor[(i)%12]);
				//Note note = new Note();
				//note.setPitch(pitch[(i-12)]);
				/*Keys.get(i).addMouseListener(new MouseAdapter() {
			        public void mousePressed(MouseEvent e) {
			            mousePressed = true;
			            new Thread() {
			                public void run() {
			                    while (mousePressed && !mute) {
			                    	note.setDynamic(volume);
			                    	Play.midi(note);
			                    }
			                }
	
			            }.start();
			        }
	
			        public void mouseReleased(MouseEvent e) {
			            mousePressed = false;
			            Play.stopMidi();
			        }
	
			    });*/
			}
		}
	}
	public void toggleShiftUp(boolean bool) {
		SHIFTNOTEUP.setEnabled(bool);
		SHIFTOCTAVEUP.setEnabled(bool);
	}
	public void toggleShiftDown(boolean bool) {
		SHIFTNOTEDOWN.setEnabled(bool);
		SHIFTOCTAVEDOWN.setEnabled(bool);

	}
	public void toggleSavePlay(boolean bool) {
		PLAYMIDI.setEnabled(bool);
		SAVEMIDI.setEnabled(bool);

	}
	public void updateKeyPanel() {
		for (int i=firstNote; i<(firstNote+15); i++) {
			/*if (firstNote > 11 && firstNote < 96) {
				Keys.get(i).addKeyListener(new KeyBoardEvent((i-firstNote)) {
					public void keyPressed(KeyEvent e) {
						int j = e.getKeyCode();
						if (j==keyStroke[this.getCounter()]) {
							Play.midi(Notes.get((firstNote+this.getCounter()-12)), false);
						}
					}
					public void keyReleased(KeyEvent e) {
						int j = e.getKeyCode();
						if (j==keyStroke[this.getCounter()]) {
							Play.stopAudio();
						}
					}
				});
			}*/
			//need to add key listener here
			KEYPANEL.setFocusable(true);
			KEYPANEL.addKeyListener(keyAction);
			KEYPANEL.add(Keys.get(i));
		}
	}
	public void setUpNotes() {
		for (int i=0; i<84; i++) {
			Notes.add(new Note());
			Notes.get(i).setPitch(pitch[i]);
		}
	}
	public void bindKeyToButton() {
		for (int i=0; i<15; i++) {
			Keys.get(firstNote+i).getInputMap(KEYPANEL.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStrokePressCap[i]), keyStrokePressCap[i]);
			Keys.get(firstNote+i).getInputMap(KEYPANEL.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStrokeReleaseCap[i]), keyStrokeReleaseCap[i]);
			Keys.get(firstNote+i).getActionMap().put(keyStrokePressCap[i], new KeyBoardActionPress(firstNote+i-12));
			Keys.get(firstNote+i).getActionMap().put(keyStrokeReleaseCap[i], new KeyBoardActionRelease(firstNote+i-12));	
		}
	}
	public void removeKeyBind() {
		for (int i=0; i<Keys.size(); i++) {
			Keys.get(i).getInputMap(KEYPANEL.WHEN_IN_FOCUSED_WINDOW).clear();
			Keys.get(i).getInputMap(KEYPANEL.WHEN_IN_FOCUSED_WINDOW).clear();
			Keys.get(i).getActionMap().clear();
			Keys.get(i).getActionMap().clear();	
		}
	}
	public void setUpKeyListener() {
		keyAction = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int j=e.getKeyCode();
				if (j==keyStroke[0]) {
					System.out.println("A pressed");
					Play.midi(Notes.get(firstNote), false);
				}
				else if (j==keyStroke[1]) {
					System.out.println("W pressed");
					Play.midi(Notes.get(firstNote+1), false);
				}
				else if (j==keyStroke[2]) {
					System.out.println("S pressed");
					Play.midi(Notes.get(firstNote+2), false);
				}
				else if (j==keyStroke[3]) {
					System.out.println("E pressed");
					Play.midi(Notes.get(firstNote+3), false);
				}
				else if (j==keyStroke[4]) {
					System.out.println("D pressed");
					Play.midi(Notes.get(firstNote+4), false);
				}
				else if (j==keyStroke[5]) {
					System.out.println("F pressed");
					Play.midi(Notes.get(firstNote+5), false);
				}
				else if (j==keyStroke[6]) {
					System.out.println("T pressed");
					Play.midi(Notes.get(firstNote+6), false);
				}
				else if (j==keyStroke[7]) {
					System.out.println("G pressed");
					Play.midi(Notes.get(firstNote+7), false);
				}
				else if (j==keyStroke[8]) {
					System.out.println("Y pressed");
					Play.midi(Notes.get(firstNote+8), false);
				}
				else if (j==keyStroke[9]) {
					System.out.println("H pressed");
					Play.midi(Notes.get(firstNote+9), false);
				}
				else if (j==keyStroke[10]) {
					System.out.println("U pressed");
					Play.midi(Notes.get(firstNote+10), false);
				}
				else if (j==keyStroke[11]) {
					System.out.println("J pressed");
					Play.midi(Notes.get(firstNote+11), false);
				}
				else if (j==keyStroke[12]) {
					System.out.println("K pressed");
					Play.midi(Notes.get(firstNote+12), false);
				}
				else if (j==keyStroke[13]) {
					System.out.println("O pressed");
					Play.midi(Notes.get(firstNote+13), false);
				}
				else if (j==keyStroke[14]) {
					System.out.println("L pressed");
					Play.midi(Notes.get(firstNote+14), false);
				}
			}
		};
	}
	private class KeyBoardActionPress extends AbstractAction {
		
		private int Index;
		
		public KeyBoardActionPress (int Index) {
			this.Index = Index;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (Index < 0 || Index > 83) {
				System.out.println("Pitch outside given range");
			}
			else if (!NoteStatus[Index]) {
				System.out.println(Notes.get(Index).toString());
				NoteStatus[Index]=true;
				Keys.get(Index+12).getModel().setPressed(true);
				inputNotes.add(Notes.get(Index));
				outputNotes=inputNotes;
				//Cordify();
				Note[] noteList = new Note[outputNotes.size()];
				noteList = outputNotes.toArray(noteList);
				phrase = new Phrase(noteList);
				for (int i=0; i<outputNotes.size(); i++) {
					System.out.println(outputNotes.get(i).toString());
				}
				phrase.setTempo(tempo.getNumber().doubleValue());
				phrase.setDynamic(100);
				phrase.setStartTime(0);
				Play.midi(phrase, false);
			}
		}
		
	}
	private class KeyBoardActionRelease extends AbstractAction {
		
		private int Index;
		
		public KeyBoardActionRelease (int Index) {
			this.Index = Index;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (Index < 0 || Index > 83) {
				System.out.println("Pitch outside given range");
			}
			else {
				System.out.println(Notes.get(Index).toString()+" released");
				NoteStatus[Index]=false;
				Keys.get(Index+12).getModel().setPressed(false);
				inputNotes.remove(Notes.get(Index));
				outputNotes=inputNotes;
				//Cordify();
				Note[] noteList = new Note[outputNotes.size()];
				noteList = outputNotes.toArray(noteList);
				phrase = new Phrase(noteList);
				for (int i=0; i<outputNotes.size(); i++) {
					System.out.println(outputNotes.get(i).toString());
				}
				phrase.setTempo(tempo.getNumber().doubleValue());
				phrase.setDynamic(100);
				phrase.setStartTime(0);
				Play.midi(phrase, false);
			}
		}
		
	}
	private void Cordify () {
		ArrayList<Note> outNotes = new ArrayList<Note>();
		for (int i=0; i<outputNotes.size(); i++) {
			outNotes.add(outputNotes.get(i));
			if (outputNotes.get(i).getPitch()-Notes.get(firstNote-12).getPitch()<13) {
				Note note1 = new Note();
				note1.setPitch(outputNotes.get(i).getPitch()+2);
				outNotes.add(note1);
			}
			if (outputNotes.get(i).getPitch()-Notes.get(firstNote-12).getPitch()<11) {
				Note note2 = new Note();
				note2.setPitch(outputNotes.get(i).getPitch()+4);
				outNotes.add(note2);
			}
		}
		outputNotes = outNotes;
	}
}