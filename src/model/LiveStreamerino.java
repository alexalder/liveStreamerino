package model;

import java.io.IOException;

public class LiveStreamerino {
	public LiveStreamerino(String url, String quality){
		ProcessBuilder pb = new ProcessBuilder("D:\\Programmi\\Livestreamer\\livestreamer.exe", url, quality);
		try {
			@SuppressWarnings("unused")
			Process p = pb.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public LiveStreamerino(String path, String url, String quality){
		ProcessBuilder pb = new ProcessBuilder(path, url, quality);
		try {
			@SuppressWarnings("unused")
			Process p = pb.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
