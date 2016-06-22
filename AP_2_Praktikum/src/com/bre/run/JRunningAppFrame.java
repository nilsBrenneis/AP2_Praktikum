package com.bre.run;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.bre.list.RunMeters;
import com.bre.list.StreckenCounter;

@SuppressWarnings("serial")
public class JRunningAppFrame extends JFrame {

	private Lauf currentRun;

	private final int OFFSET_INDEX = 1;

	private Container content = getContentPane();
	private JPanel westPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();

	private JLabel trackNameLabel = new JLabel("Lauf");
	private JLabel lengthLabel = new JLabel("Länge");
	private JLabel timeLabel = new JLabel("Zeit");
	private JLabel start = new JLabel("Start");
	private JLabel endLabel = new JLabel("Ende");
	private JLabel timesRunLabel = new JLabel("Gelaufen");

	private JTextField trackNameText = new JTextField();
	private JTextField lengthText = new JTextField();
	private JTextField timeText = new JTextField();
	private JTextField startText = new JTextField();
	private JTextField endText = new JTextField();
	private JTextField timesRunText = new JTextField();

	private JRunDiagram diagram = new JRunDiagram();

	private JButton forwardBtn = new JButton(">");
	private JButton backwardBtn = new JButton("<");
	private JButton addRunBtn = new JButton("Neuer Lauf");
	private JButton undoBtn = new JButton("Undo");
	private JButton statsBtn = new JButton("Laufstatistik");

	public JRunningAppFrame() {
		super("Lauftracker");
		initRuns();
		buildGUI();
	}

	public JRunDiagram getDiagram() {
		return diagram;
	}

	private void initRuns() {
		diagram.getRuns().addFirst(new Lauf(160, LocalDate.of(2016, 2, 22), Runner.getAllTracks().get(0)));
		diagram.getRuns().addFirst(new Lauf(40, LocalDate.of(2015, 1, 12), Runner.getAllTracks().get(2)));
		diagram.getRuns().addFirst(new Lauf(50, LocalDate.of(2015, 3, 29), Runner.getAllTracks().get(1)));
		diagram.getRuns().addFirst(new Lauf(73, LocalDate.of(2016, 4, 25), Runner.getAllTracks().get(1)));
		diagram.getRuns().addFirst(new Lauf(45, LocalDate.of(2016, 4, 21), Runner.getAllTracks().get(2)));
		diagram.getRuns().addFirst(new Lauf(43, LocalDate.of(2014, 12, 22), Runner.getAllTracks().get(0)));
		diagram.getRuns().addFirst(new Lauf(69, LocalDate.of(2014, 5, 1), Runner.getAllTracks().get(1)));
		diagram.getRuns().addFirst(new Lauf(22, LocalDate.of(2016, 4, 14), Runner.getAllTracks().get(0)));
		diagram.getRuns().addFirst(new Lauf(51, LocalDate.of(2015, 3, 17), Runner.getAllTracks().get(2)));
		diagram.getRuns().addFirst(new Lauf(27, LocalDate.of(2016, 1, 19), Runner.getAllTracks().get(2)));

		StreckenCounter sc = new StreckenCounter(diagram.getRuns().get(1).getTrack());
		diagram.getRuns().analyzeRuns(sc);
		System.out.println(sc.getTrackCounter());

		RunMeters rm = new RunMeters();
		diagram.getRuns().analyzeRuns(rm);
		System.out.println(rm.getRunMeters());
	}

	private void buildGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}

		setPreferredSize(new Dimension(600, 450));
		startText.setEditable(false);
		endText.setEditable(false);
		lengthText.setEditable(false);
		timesRunText.setEditable(false);

		content.setLayout(new BorderLayout());
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		content.add(southPanel, BorderLayout.SOUTH);
		content.add(westPanel, BorderLayout.WEST);
		content.add(centerPanel, BorderLayout.CENTER);

		southPanel.add(backwardBtn);
		southPanel.add(addRunBtn);
		southPanel.add(undoBtn);
		southPanel.add(statsBtn);
		southPanel.add(forwardBtn);

		centerPanel.add(diagram);

		westPanel.add(trackNameLabel);
		westPanel.add(trackNameText);
		westPanel.add(lengthLabel);
		westPanel.add(lengthText);
		westPanel.add(timeLabel);
		westPanel.add(timeText);
		westPanel.add(start);
		westPanel.add(startText);
		westPanel.add(endLabel);
		westPanel.add(endText);
		westPanel.add(timesRunLabel);
		westPanel.add(timesRunText);

		currentRun = diagram.getRuns().get(0);
		showRunData();

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		forwardBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		backwardBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});

		addRunBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRun();
			}
		});

		undoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteLastAddedRun();

			}
		});

		statsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showStats();
			}
		});

		centerPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.println(x + "," + y);
				addRun(y);
			}
		});
	}

	private void showRunData() {
		if (currentRun != null) {
			String trackName = currentRun.getTrack().getTrackName();
			String trackLength = Integer.toString(currentRun.getTrack().getLength());
			String trackTime = Integer.toString(currentRun.getLaufzeit());
			String trackStart = currentRun.getTrack().getStart();
			String trackEnd = currentRun.getTrack().getEnd();
			String timesRun = Integer.toString(currentRun.getTrack().getTimesRun());

			trackNameText.setText(trackName);
			lengthText.setText(trackLength);
			timeText.setText(trackTime);
			startText.setText(trackStart);
			endText.setText(trackEnd);
			timesRunText.setText(timesRun);
		}
	}

	private void next() {
		int index = diagram.getRuns().indexOf(currentRun) + 1;
		if (index < diagram.getRuns().size()) {
			currentRun = diagram.getRuns().get(index);
			showRunData();
		}
	}

	private void prev() {
		int index = diagram.getRuns().indexOf(currentRun) - 1;
		if (index >= 0) {
			currentRun = diagram.getRuns().get(index);
			showRunData();
		}
	}

	private void addRun() {
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		JTextField timeText = new JTextField();
		JButton okBtn = new JButton("Speichern");

		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Laufstrecke track = Runner.getTrackByName(trackNameText.getText());
				int time = Integer.parseInt(timeText.getText());
				if (time > 0) {
					diagram.getRuns().addLast(new Lauf(time, LocalDate.now(), track));
				} else {
					JOptionPane.showMessageDialog(null, "Bitte einen Wert größer 0 angeben");
				}
				jd.dispose();
				showRunData();
				repaint();
			}
		});

		jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
		jp.add(new JLabel("Rundenzeit"));
		jp.add(timeText);
		jp.add(okBtn);

		jd.setTitle("Neuer Lauf");
		jd.setSize(350, 60);
		jd.setModal(true);
		jd.add(jp);
		jd.setVisible(true);
	}

	private void addRun(int yCoordinate) {
		int length = currentRun.getTrack().getLength();
		int xCoordinate = length / (diagram.getxMaxValue() / diagram.getLENGTH_CO_SYS());

		double yMaxValueDoub = diagram.getyMaxValue();
		double heightCoSysDoub = diagram.getHEIGHT_CO_SYS();
		double yMinutesPerPixel = yMaxValueDoub / heightCoSysDoub;
		int minutes = (int) (yCoordinate * yMinutesPerPixel);
		minutes = minutes - diagram.getyMaxValue();
		minutes = (minutes + 2 * -minutes) + (diagram.getyMaxValue() / diagram.getCountDotsCoSys());

		System.out.println(minutes);
		diagram.getRuns().addLast(new Lauf(minutes, LocalDate.now(), currentRun.getTrack()));
		diagram.paintOval(xCoordinate, yCoordinate);
	}

	private void deleteLastAddedRun() {
		int lastIndex = diagram.getRuns().size() - OFFSET_INDEX;
		diagram.getRuns().remove(lastIndex);
		repaint();
	}

	private void showStats() {
		JDialog jd = new JDialog();
		ArrayList<Lauf> runs = new ArrayList<Lauf>();

		for (int i = 0; i < diagram.getRuns().size(); i++) {
			if (diagram.getRuns().get(i).getTrack().equals(currentRun.getTrack())) {
				runs.add(diagram.getRuns().get(i));
			}
		}

		Lauf fastest = runs.get(0);
		for (int i = 1; i < runs.size(); i++) {
			if (fastest.getLaufzeit() > runs.get(i).getLaufzeit()) {
				fastest = runs.get(i);
			}
		}

		Lauf slowest = runs.get(0);
		for (int i = 1; i < runs.size(); i++) {
			if (slowest.getLaufzeit() < runs.get(i).getLaufzeit()) {
				slowest = runs.get(i);
			}
		}

		JRunStats jp = new JRunStats(runs, fastest, slowest);
		System.out.println("method");
		jd.setTitle(currentRun.getTrack().getTrackName());
		jd.setSize(400, 500);
		jd.setModal(true);
		jd.add(jp);
		jd.setVisible(true);

	}
}
