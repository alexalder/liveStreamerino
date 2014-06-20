package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public OpeningFrame() {
		setSize(451, 720);
		ImageIcon img = new ImageIcon("32pix.png");
		setIconImage(img.getImage());
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel); 
		
		JLabel kappa = new JLabel(new ImageIcon("LiveStreamerinoSmall.png"));
		mainPanel.add(kappa);
	}
}