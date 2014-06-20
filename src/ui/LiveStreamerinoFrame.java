package ui;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.LiveStreamerino;
import persistence.SettingsController;

public class LiveStreamerinoFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private JTextField urlField;
	private JComboBox<String> streamsList;
	private JComboBox<String> qualityList;
	private JButton avvia;
	private SettingsController controller;
	private String path;
	private List<String> streams;
	private JButton resetPath;
	private JButton resetStreams;
	private JButton donate;
	private JLabel label2;
	@SuppressWarnings("unused")
	private LiveStreamerino liveStream;
	


	public LiveStreamerinoFrame() {
		controller = new SettingsController();
		try {
			path = controller.getPath();
		} catch (IOException e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Locate livestreamer.exe");
			@SuppressWarnings("unused")
			int n = fileChooser.showOpenDialog(LiveStreamerinoFrame.this);
			path = fileChooser.getSelectedFile().getAbsolutePath();
			controller.scriviPath(path);
		}
		try {
			streams = controller.getStreams();
		} catch (IOException e) {
			streams = null;
		}
		
		initGUI();
		ImageIcon img = new ImageIcon("32pix.png");
		setIconImage(img.getImage());
	}

	private void initGUI() {

		mainPanel = new JPanel();
		GridLayout mainPanelLayout = new GridLayout(9, 1);
		mainPanelLayout.setHgap(5);
		mainPanelLayout.setVgap(5);
		mainPanel.setLayout(mainPanelLayout);
		
		getContentPane().add(mainPanel); 

		setTitle("LiveStreamerino");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 

		JLabel label = new JLabel("Stream link:");
		mainPanel.add(label); 
		
		urlField = new JTextField();
		mainPanel.add(urlField);
		
		if (streams != null){
			label2 = new JLabel("Recent streams:");
			mainPanel.add(label2);
		
		
			streamsList = new JComboBox<String>();
			for (String s : streams)
				streamsList.addItem(s);
			streamsList.addActionListener(this);
			mainPanel.add(streamsList);
		}
		
		JLabel label3 = new JLabel("Stream quality:");
		mainPanel.add(label3);
		
		qualityList = new JComboBox<String>();
		qualityList.addItem("Best");
		qualityList.addItem("Source");
		qualityList.addItem("High");
		qualityList.addItem("Medium");
		qualityList.addItem("Low");
		mainPanel.add(qualityList);
		
		avvia = new JButton("Play stream");
		avvia.addActionListener(this);
		mainPanel.add(avvia);
		
		mainPanel.add(new JLabel());
		
		GridLayout subPanelLayout = new GridLayout(1, 3);
		JPanel subPanel = new JPanel();
		subPanel.setLayout(subPanelLayout);
		resetStreams = new JButton("Reset stream history");
		resetStreams.addActionListener(this);
		subPanel.add(resetStreams);
		resetPath = new JButton("Reset Livestreamer path");
		resetPath.addActionListener(this);
		subPanel.add(resetPath);
		donate = new JButton("Donate");
		donate.addActionListener(this);
		subPanel.add(donate);
		
		mainPanel.add(subPanel);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == avvia)
			if (qualityList.getSelectedItem() != null){
				String res = urlField.getText();
				String resCut = null;
				if (res.contains("http://www."))
					resCut = res.replaceFirst("http://www.", "");
				else if (res.contains("www."))
					resCut = res.replaceFirst("www.", "");
				else 
					resCut = res;
				if (resCut.contains("twitch.tv/") && (resCut.replaceFirst("twitch.tv/", "").length() > 0)){
					controller.SaveStreams(resCut);
					liveStream = new LiveStreamerino(path, resCut, (String) qualityList.getSelectedItem());
				}
				else throw new IllegalArgumentException();
			}
		
		if (arg0.getSource() == streamsList)
			urlField.setText((String) streamsList.getSelectedItem());
		
		if (arg0.getSource() == resetPath){
			File file = new File("settings.bin");
			file.delete();
		}
		
		if (arg0.getSource() == resetStreams){
			label2.setVisible(false);
			streamsList.setVisible(false);
			File file = new File("streams.txt");
			file.delete();
		}
		
		if (arg0.getSource() == donate){
			try {
		        Desktop.getDesktop().browse(new URL("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=W9RDHQSR3PTHA").toURI());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
		}
	}
}