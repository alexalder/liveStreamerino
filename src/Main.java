import ui.LiveStreamerinoFrame;
import ui.OpeningFrame;


public class Main {

	public static void main(String[] args) {
		OpeningFrame opening = new OpeningFrame();
		opening.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		opening.setVisible(false);
		LiveStreamerinoFrame mainFrame = new LiveStreamerinoFrame();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}

//twitch.tv/riotgames
//http://www.twitch.tv/quanticcenter